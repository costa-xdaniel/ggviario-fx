package st.ggviario.house.control.modals;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.LinkedList;
import java.util.Map;

public class ModalNovoLocalProducao extends AbstractModal< LocalProducao >{

    public static ModalNovoLocalProducao newInstance(StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalNovoLocalProducao>loader = new ControllerLoader<>("/fxml/modal/modal_novo_local_producao.fxml");
        loader.getController().createDialogModal( stackPane );
        loader.getController().structure();
        loader.getController().defineEvents( );
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconAreaCloseModal;
    @FXML private JFXComboBox< Setor > comboxSetor;
    @FXML private JFXTextField textFieldProduto;
    @FXML private JFXButton buttonRegistar;

    private Produto produto;

    @Override
    Region getContentRoot() {
        return this.root;
    }

    @Override
    Label getModalTitleView() {
        return this.modalTitle;
    }

    @Override
    AnchorPane getIconAreaCloseModal() {
        return this.iconAreaCloseModal;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    @Override
    @Deprecated
    public void openModal() { }

    @Override
    void structure() {
        super.structure();
        this.comboxSetor.setItems( FXCollections.observableList( new LinkedList<>()));
    }

    @Override
    void defineEvents() {
        super.defineEvents();
        this.buttonRegistar.setOnAction(event -> onRegisterLocalProducao() );
    }


    public void openModal(Produto produto ){
        this.produto = produto;
        this.textFieldProduto.setText( String.valueOf( produto ) );
        this.loadSetor( );
        super.openModal();
    }


    @Override
    public void clear() {
        this.comboxSetor.setDisable( false );
        this.textFieldProduto.setText( null );
        this.produto = null;
    }

    private void loadSetor(){
        final Thread thread =  new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Platform.runLater( ( )-> this.comboxSetor.getItems().clear() );
            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            JsonElement jsonb = new JsonObject();
            sql.query( "ggviario.funct_load_setor" )
                .withJsonb( jsonb )
                .callFunctionTable()
                .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> Platform.runLater(() ->{
                    setorBuilder.load( row );
                    this.comboxSetor.getItems().add( setorBuilder.build() );
                }));

            Platform.runLater(() -> {
                if( this.comboxSetor.getItems().size() > 1 )
                    this.comboxSetor.getItems().add( 0, new Setor() );

                if( this.comboxSetor.getItems().size() == 1 )
                    this.comboxSetor.getSelectionModel().select( 0 );
                else this.comboxSetor.setValue( null );
            });

        });
        thread.start();
    }

    private void onRegisterLocalProducao(){
        ModalNovoLocalProducaoResult res = this.checkForm();
        if( res.isSuccess() ){
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            sql.query( "ggviario.funct_reg_localproducao" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( res.value.getProduto().getProdutoId() )
                .withUUID( res.value.getSetor().getSetorId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            res.message = "Novo local de porducao cadastrado com sucesso!";
                            res.data = result.getData();
                            res.value = new LocalProducao.LocalProducaoBuilder()
                                    .load((Map<String, Object>) result.getData().get( "localproducao" ))
                                    .setProduto( this.produto )
                                    .setSetor( res.value.getSetor() )
                                    .build();
                        } else {
                            res.success = false;
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.message = result.getMessage();
                            res.data = result.getData();
                        }
                    })
            ;
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.message, res.level );
        if( res.isSuccess() ){
            this.closeModal();
            this.clear();
            this.executeOnOperationResult( res );
        }
    }

    private ModalNovoLocalProducaoResult checkForm( ){
        ModalNovoLocalProducaoResult result = new ModalNovoLocalProducaoResult();
        result.level = SnackbarBuilder.MessageLevel.WARNING;
        LocalProducao.LocalProducaoBuilder localProducaoBuilder = new LocalProducao.LocalProducaoBuilder();

        localProducaoBuilder.setSetor(this.comboxSetor.getValue());
        localProducaoBuilder.setProduto( this.produto );
        result.value = localProducaoBuilder.build();

        if( result.value.getSetor() == null ){
            result.message = "Indique um dos sectores!";
        } else {
            result.success= true;
        }
        return result;
    }

    private class ModalNovoLocalProducaoResult implements ModalResult < LocalProducao > {
        private boolean success;
        private String message;
        private boolean terminated;
        private LocalProducao value;
        private SnackbarBuilder.MessageLevel level;
        private  Map< String, Object > data;

        @Override
        public boolean isSuccess() {
            return success;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public boolean isTerminated() {
            return terminated;
        }

        @Override
        public LocalProducao getValue() {
            return value;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return level;
        }

        @Override
        public Map<String, Object> getData() {
            return this.data;
        }
    }

}
