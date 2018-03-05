package st.ggviario.house.controller.modal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.model.Colaborador;
import st.ggviario.house.model.SQLResult;
import st.ggviario.house.model.Unidade;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.util.Map;

public class ModalNovaUnidade extends AbstractModal< Unidade > {


    public static ModalNovaUnidade load(StackPane stackPane ){
        ControllerLoader < AnchorPane, ModalNovaUnidade > loader = new ControllerLoader<>("/fxml/modal/modal_nova_unidade.fxml");
        loader.getController().createDialogModal( stackPane );
        loader.getController().structure();
        loader.getController().defineEvents();
        return loader.getController();
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconAreaCloseModal;
    @FXML private JFXTextField textFieldUnidadeNome;
    @FXML private JFXTextField textFieldUnidadeCodigo;
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
        return this.iconAreaCloseModal;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    void structure(){
    }

    @Override
    public void clear() {
        this.textFieldUnidadeCodigo.setText( null );
        this.textFieldUnidadeNome.setText( null );
    }

    void defineEvents(){
        this.buttonRegistar.setOnAction(actionEvent -> onRegister() );
    }

    private void onRegister( ){
        ModalNovaUnidadeResult res = this.checkForm();
        if( res.isSuccess() ){
            PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
            Colaborador colaborador = AuthSingleton.getAuth();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            sql.query( "ggviario.funct_reg_unidade" )
                .withUUID( colaborador.getColaboradorId() )
                .withVarchar( res.resultValue.getUnidadeNome() )
                .withVarchar( res.resultValue.getUnidadeCodigo() )
                .callFunctionTable()
                    .onResultQuery(row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            res.message = "Nova unidade cadastrada com sucesso!";
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            unidadeBuilder.load( row );
                            res.resultValue = unidadeBuilder.build();
                            res.data = result.getData();
                        } else {
                            res.message = result.getMessage();
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.data = result.getData();
                        }
                    })
            ;
        }

        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.getMessage(), res.getLevel() );
        if( res.isSuccess() ){
            this.clear();
            this.closeModal();
            this.executeOnOperationResult( res );
        }
    }



    private ModalNovaUnidadeResult checkForm(){
        ModalNovaUnidadeResult result = new ModalNovaUnidadeResult();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
        unidadeBuilder.setNome( SQLText.normalize( this.textFieldUnidadeNome.getText() ) );
        unidadeBuilder.setCodigo( SQLText.normalize( this.textFieldUnidadeCodigo.getText() ) );
        result.resultValue = unidadeBuilder.build();
        result.level = SnackbarBuilder.MessageLevel.WARNING;
        if( result.resultValue.getUnidadeNome() == null ){
            result.message  ="Informe o nome da uniade!";
        } else if( result.resultValue.getUnidadeCodigo() == null ){
            result.message = "Informe o codigo da unidade";
        } else{
            result.success = true;
        }
        return result;
    }


    class ModalNovaUnidadeResult implements ModalResult< Unidade > {

        private boolean success;
        private String message;
        private Unidade resultValue;
        private SnackbarBuilder.MessageLevel level;
        private Map< String, Object > data;

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
            return false;
        }

        @Override
        public Unidade getResultValue() {
            return this.resultValue;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return this.level;
        }

        @Override
        public Map<String, Object> getData() {
            return this.data;
        }
    }
}
