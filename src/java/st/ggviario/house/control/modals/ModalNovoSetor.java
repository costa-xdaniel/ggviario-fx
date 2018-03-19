package st.ggviario.house.control.modals;

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
import st.ggviario.house.model.Colaborador;
import st.ggviario.house.model.SQLResult;
import st.ggviario.house.model.Setor;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModalNovoSetor extends AbstractModal< Setor > {

    private static Setor SECTOR_VOID = new Setor();

    public static ModalNovoSetor newInstance(StackPane stackPane ) {
        ControllerLoader< AnchorPane, ModalNovoSetor> loader = new ControllerLoader<>("/fxml/modal/modal_novo_setor.fxml");
        ModalNovoSetor novoSetor = loader.getController();
        novoSetor.createDialogModal( stackPane );
        novoSetor.structure();
        novoSetor.defineEvent();
        return novoSetor;
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconArea;
    @FXML private JFXTextField textFieldSetorNome;
    @FXML private JFXComboBox< Setor > comboxCategoriaSuper;
    @FXML private Label labelCategoriaNivel;
    @FXML private Label labelCategoriaSuper;
    @FXML private JFXButton buttonRegistar;

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
        return this.iconArea;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    @Override
    public void clear() {
        this.textFieldSetorNome.setText(null);
        this.comboxCategoriaSuper.setValue( null );
        this.labelCategoriaNivel.setText( null );
        this.labelCategoriaSuper.setText( null );
    }

    @Override
    public void openModal() {
        this.loadSetorSuper();
        super.openModal();
    }

    protected void structure(){
        super.structure();
        this.comboxCategoriaSuper.getItems().clear();
    }

    void defineEvent(){
        this.buttonRegistar.setOnAction(event -> this.onRegistarNovoSetor() );
        this.comboxCategoriaSuper.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeSetor(newValue );
        });
    }


    private void onRegistarNovoSetor() {
        ModalNovoSetorResult res = this.validateForm();
        if( res.isSuccess() ){
            /*
            funct_reg_setor(arg_colaborador_id uuid, arg_setor_setor_id uuid, arg_setor_nome character varying)
             */
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            sql.query( "ggviario.funct_reg_setor" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( res.getValue().getSetorSuper().getSetorId() )
                .withVarchar( res.getValue().getSetorNome() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            res.succeed = true;
                            res.message = "Novo setor cadastrado com sucesso";
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            res.terminated = true;
                        } else {
                            res.message = result.getMessage();
                            res.map = result.getData();
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.succeed = false;
                        }
                    });

            SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
            snackbarBuilder.show( res.message, res.level );
            if( res.isSuccess() ){
                this.clear();
                this.closeModal();
            }
            executeOnOperationResult( res );
        }
    }

    void onChangeSetor(Setor newSetor){
        if(newSetor == null || newSetor.equals(SECTOR_VOID)){
            this.comboxCategoriaSuper.setValue( null );
            this.labelCategoriaNivel.setText("");
            this.labelCategoriaSuper.setText( "" );
        }  else {
            this.labelCategoriaNivel.setText( String.valueOf(  newSetor.getSetorNivel() ) );
            this.labelCategoriaSuper.setText( newSetor.getSetorSuper() == null ? null : newSetor.getSetorSuper().getSetorNome() );
        }
    }

    private void loadSetorSuper(){
        this.comboxCategoriaSuper.getItems().clear();
        Thread thread = new Thread(() -> {
            PostgresSQL sql = PostgresSQLSingleton.getInstance();

            Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
            Setor.SetorBuilder superSetorBuilder = new Setor.SetorBuilder();
            sql.query( "ggviario.funct_load_setor")
                .withJsonb( new JsonObject() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        setorBuilder.load( row );
                        if( row.get( "setor_super" ) != null ){
                            superSetorBuilder.load( row.asMapJsonn( "setor_super" ) );
                            setorBuilder.setSetorSuper( superSetorBuilder.build() );
                        }
                        this.comboxCategoriaSuper.getItems().add( setorBuilder.build() );
                    });

            Platform.runLater(() -> {
                if( this.comboxCategoriaSuper.getItems().size() == 1 ){
                    this.comboxCategoriaSuper.getSelectionModel().select( 0 );
                } else if( this.comboxCategoriaSuper.getItems().size() > 1 ){
                    this.comboxCategoriaSuper.getItems().add( 0, SECTOR_VOID );
                }
            });
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private ModalNovoSetorResult validateForm(){
        ModalNovoSetorResult res = new ModalNovoSetorResult();
        Setor.SetorBuilder setorBuilder = new Setor.SetorBuilder();
        setorBuilder.setNome(SQLText.normalize( this.textFieldSetorNome.getText() ) );
        setorBuilder.setSetorSuper( this.comboxCategoriaSuper.getValue() );
        res.resultVale = setorBuilder.build();
        res.level = SnackbarBuilder.MessageLevel.WARNING;

        if( res.resultVale.getSetorNome() == null )
            res.message = "Informe o nome sotor!";
        else if( res.resultVale.getSetorSuper() == null || res.resultVale.getSetorSuper().getSetorId() == null ){
            res.message = "Informe o setor mãe";
        } else {
            res.succeed = true;
        }
        return res;
    }

    class ModalNovoSetorResult implements ModalResult< Setor >{

        private boolean succeed;
        private String message;
        private boolean terminated;
        private Setor resultVale;
        private Map< String, Object > map;
        private SnackbarBuilder.MessageLevel level;

        @Override
        public boolean isSuccess() {
            return this.succeed;
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
        public Setor getValue() {
            return this.resultVale;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return level;
        }

        @Override
        public Map<String, Object> getData() {
            return this.map;
        }
    }
}
