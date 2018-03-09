package st.ggviario.house.controller.modals;

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
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.Map;

public class ModalNovaUnidade extends AbstractModal< Unidade > {


    public static ModalNovaUnidade newInstance(StackPane stackPane ){
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

    private Unidade unidade;

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
        this.unidade = null;
    }

    @Override
    public void openModal() {
        super.openModal();
    }


    public void openModal( Unidade unidade ) {
        this.unidade = unidade;
        super.openModal();
        this.textFieldUnidadeCodigo.setText( this.unidade.getUnidadeCodigo() );
        this.textFieldUnidadeNome.setText( this.unidade.getUnidadeNome() );
    }

    void defineEvents(){
        this.buttonRegistar.setOnAction(actionEvent -> {
            if( this.unidade != null ) this.onChangeUnidade();
            else this.onRegisterUnidade();
        });
    }

    private void onRegisterUnidade( ){
        ModalNovaUnidadeResult res = this.checkForm();
        if( res.isSuccess() ){
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            sql.query( "ggviario.funct_reg_unidade" )
                .withUUID( colaborador.getColaboradorId() )
                .withVarchar( res.resultValue.getUnidadeNome() )
                .withVarchar( res.resultValue.getUnidadeCodigo() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
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

    private void onChangeUnidade( ){
        ModalNovaUnidadeResult res = this.checkForm();
        if( res.isSuccess() ){
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
            sql.query( "ggviario.funct_change_unidade" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( unidade.getUnidadeId() )
                .withVarchar( res.resultValue.getUnidadeNome() )
                .withVarchar( res.resultValue.getUnidadeCodigo() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            res.message = "Unidade atualizado com sucesso!";
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
        public Unidade getValue() {
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
