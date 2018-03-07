package st.ggviario.house.controller.modals;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.model.Colaborador;
import st.ggviario.house.model.Distrito;
import st.ggviario.house.model.Fornecedor;
import st.ggviario.house.model.SQLResult;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModalNovoFornecedor extends AbstractModal<Fornecedor >{


    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconAnchorCloseArea;
    @FXML private JFXTextField textFieldFornecedorNIF;
    @FXML private JFXTextField textFieldFornecedorTelemovel;
    @FXML private JFXTextField textFieldFornecedorTelefone;
    @FXML private JFXTextField textFieldFornecedorMail;
    @FXML private JFXComboBox< Distrito > comboxFornecedorDistrito;
    @FXML private JFXTextField textFieldFornecedorLocal;
    @FXML private JFXTextField textFieldFornecedorNome;
    @FXML private JFXButton buttomRegistarFornecedor;

    private JFXRippler rippler;

    private List< Distrito > distritos = new LinkedList<>();
    private Distrito voidDistrito;

    public static ModalNovoFornecedor load(StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalNovoFornecedor > loader = new ControllerLoader<>("/fxml/modal/modal_novo_fornecedor.fxml");
        ModalNovoFornecedor modal = loader.getController();
        modal.createDialogModal( stackPane );
        modal.structure();
        modal.defineEvents();
        modal.loadData();
        modal.pushAll();
        return modal;
    }

    void structure( ){
        this.rippler = new JFXRippler( this.iconAnchorCloseArea);
        this.anchorHeader.getChildren().add( this.rippler );
        AnchorPane.setTopAnchor( this.rippler, 0x0.0p0 );
        AnchorPane.setRightAnchor( this.rippler, 0x0.0p0 );
    }

    void defineEvents() {
        this.rippler.setOnMouseClicked(mouseEvent -> {
            this.clear();
            this.closeModal();
        });
        this.buttomRegistarFornecedor.setOnAction( actionEvent -> this.onRegisterFornecedor() );
        this.comboxFornecedorDistrito.getSelectionModel().selectedItemProperty().addListener((observableValue, oldDistrito, newDistrito) -> {
            if( newDistrito != null && newDistrito.equals( this.voidDistrito ) ){
                this.comboxFornecedorDistrito.setValue( null );
            }
        });
    }

    private void loadData( ) {
        //Load distritos
        this.distritos.clear();
        Distrito.DistritoBuilder builder = new Distrito.DistritoBuilder();
        this.distritos.add( this.voidDistrito != null ? voidDistrito : ( this.voidDistrito =  builder.nome( "" ).build() ) );
        PostgresSQL sql = PostgresSQLSingleton.getInstance();
        sql.query( "funct_load_distrito" )
            .withJsonb( (String)  null )
            .callFunctionTable()
                .onResultQuery(row -> this.distritos.add( builder.load( row ).build() ));
    }


    private void pushAll( ){
        this.comboxFornecedorDistrito.setItems(FXCollections.observableList( this.distritos ) );
    }

    private void onRegisterFornecedor( ){
        ModalNovoFornecedorResult result = this.mountFornecedor();
        Fornecedor.FornecedorBuilder builder = new Fornecedor.FornecedorBuilder();
        result.level = SnackbarBuilder.MessageLevel.WARNING;

        if( result.isSuccess() ){
            Colaborador colaborador = AuthSingleton.getInstance();
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Fornecedor fornecedor = result.fornecedor;
            sql.query( "funct_reg_fornecedor" )
                .withUUID( colaborador.getColaboradorId() )
                .withVarchar( fornecedor.getFornecedorNome() )
                .withVarchar( fornecedor.getFornecedorNif() )
                .withVarchar( fornecedor.getFornecedorTelefone() )
                .withVarchar( fornecedor.getFornecedorTelemovel() )
                .withVarchar( fornecedor.getFornecedorMail() )
                .withVarchar( fornecedor.getFornecedorLocal() )
                .withSmallint( fornecedor.getDistrito() == null? null : fornecedor.getDistrito().getDistritoId() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult res = new SQLResult( row );
                        result.message = res.getMessage();
                        result.success = res.isSuccess();
                        result.map = res.getData();

                        if( res.isSuccess() ){
                            result.fornecedor = builder.load( (Map<String, Object> ) res.getData().get("fornecedor") ).build();
                            result.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            result.message = "Novo fornecedor cadastrado com sucesso";
                        } else {
                            result.level = SnackbarBuilder.MessageLevel.ERROR;
                        }
                    });
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( result.message, result.level  );
        if( result.isSuccess() ){
            result.terminated = true;
            this.clear();
            this.closeModal();
        }

        this.executeOnOperationResult( result );
    }

    private ModalNovoFornecedorResult mountFornecedor( ){
        ModalNovoFornecedorResult res = new ModalNovoFornecedorResult();
        res.success = true;
        Fornecedor.FornecedorBuilder builder = new Fornecedor.FornecedorBuilder();

        res.fornecedor = builder
            .setNome( SQLText.normalize( this.textFieldFornecedorNome.getText() ) )
            .setNif( SQLText.normalize( this.textFieldFornecedorNIF.getText() ) )
            .setDistrito( this.comboxFornecedorDistrito.getValue() )
            .setLocal( SQLText.normalize( this.textFieldFornecedorLocal.getText() ) )
            .setTelefone( SQLText.normalize( this.textFieldFornecedorTelefone.getText() ) )
            .setTelemovel( SQLText.normalize( this.textFieldFornecedorTelemovel.getText() ) )
            .setMail( SQLText.normalize( this.textFieldFornecedorMail.getText() ) )
            .build();

        if( res.fornecedor.getFornecedorNome() == null ){
            res.success = false;
            res.message = "Nome do fornecedor em falta!";
        }
        return res;
    }

    @Override
    public void clear() {
        this.textFieldFornecedorNIF.setText( null );
        this.textFieldFornecedorTelemovel.setText( null );
        this.textFieldFornecedorTelefone.setText( null );
        this.textFieldFornecedorMail.setText( null );
        this.comboxFornecedorDistrito.setValue( null );
        this.textFieldFornecedorLocal.setText( null );
        this.textFieldFornecedorNome.setText( null );
    }

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
        return this.iconAnchorCloseArea;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    private class ModalNovoFornecedorResult implements ModalResult< Fornecedor > {

        private boolean success;
        private String message;
        private boolean terminated;
        private Fornecedor fornecedor;
        private Map< String, Object > map;
        private SnackbarBuilder.MessageLevel level;

        @Override
        public boolean isSuccess() {
            return this.success;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public boolean isTerminated() {
            return this.terminated;
        }

        @Override
        public Fornecedor getValue() {
            return this.fornecedor;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return this.level;
        }

        @Override
        public Map<String, Object> getData() {
            return this.map;
        }
    }
}
