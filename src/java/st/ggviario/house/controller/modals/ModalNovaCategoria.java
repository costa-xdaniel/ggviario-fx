package st.ggviario.house.controller.modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.Colaborador;
import st.ggviario.house.model.SQLResult;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModalNovaCategoria extends AbstractModal<Categoria> {

    private static Categoria CATEGORIA_VOID = new Categoria();

    public static ModalNovaCategoria newInstance(StackPane stackPane ) {
        ControllerLoader< AnchorPane, ModalNovaCategoria> loader = new ControllerLoader<>("/fxml/modal/modal_nova_categoria.fxml");
        ModalNovaCategoria novoSetor = loader.getController();
        novoSetor.createDialogModal( stackPane );
        novoSetor.structure();
        novoSetor.defineEvents();
        return novoSetor;
    }

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconArea;
    @FXML private JFXTextField textFieldSetorNome;
    @FXML private JFXComboBox<Categoria> comboxCategoriaSuper;
    @FXML private Label labelCategoriaNivel;
    @FXML private Label labelCategoriaSuper;
    @FXML private JFXButton buttonRegistar;

    private List< Categoria > setorList = new LinkedList<>();
    private Categoria categoria;

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
        this.comboxCategoriaSuper.setDisable( false );
        this.categoria = null;
    }

    @Override
    protected void structure(){
    }

    @Override
    public void openModal() {
        this.clear();
        super.openModal();
    }

    public void openModal( Categoria categoria ) {
        this.categoria = categoria;
        this.comboxCategoriaSuper.setValue( categoria.getCategoriaSuper() );
        this.comboxCategoriaSuper.setDisable( true );
        this.textFieldSetorNome.setText( categoria.getCategoriaNome() );
        super.openModal();
    }

    @Override
    protected void defineEvents(){
        this.buttonRegistar.setOnAction(event ->{
            if( this.categoria == null ) this.onRegistarNovoSetor();
            else this.onChangeCategoria();
        } );
        this.comboxCategoriaSuper.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.onChangeCategoriaSuper(newValue );
        });
    }

    public void pushCategoriaSupers(List<Categoria> setorList){
        this.setorList.clear();
        this.setorList.add(CATEGORIA_VOID);
        this.setorList.addAll( setorList );
        this.comboxCategoriaSuper.setItems( FXCollections.observableList( this.setorList) );
    }


    private void onRegistarNovoSetor() {
        ModalNovaCategoriaResult res = this.validateForm();
        if( res.isSuccess() ){
            /*
            funct_reg_setor(arg_colaborador_id uuid, arg_setor_setor_id uuid, arg_setor_nome character varying)
             */
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            sql.query( "ggviario.funct_reg_categoria" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( res.getValue().getCategoriaSuper().getCategoriaId() )
                .withVarchar( res.getValue().getCategoriaNome() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            res.succeed = true;
                            res.message = "Nova categoria cadastrada com sucesso cadastrado com sucesso";
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

    private void onChangeCategoria() {
        ModalNovaCategoriaResult res = this.validateForm();
        if( res.isSuccess() ){
            /*
            funct_reg_setor(arg_colaborador_id uuid, arg_setor_setor_id uuid, arg_setor_nome character varying)
             */
            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            Colaborador colaborador = AuthSingleton.getInstance();
            sql.query( "ggviario.funct_change_categoria" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( this.categoria.getCategoriaId() )
                .withVarchar( res.getValue().getCategoriaNome() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        if( result.isSuccess() ){
                            res.succeed = true;
                            res.message = "Categoria atualizada com sucesso!";
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

    private void onChangeCategoriaSuper(Categoria newSetor){
        if(newSetor == null || newSetor.equals(CATEGORIA_VOID)){
            this.comboxCategoriaSuper.setValue( null );
            this.labelCategoriaNivel.setText("");
            this.labelCategoriaSuper.setText( "" );
        }  else {
            this.labelCategoriaNivel.setText( String.valueOf(  newSetor.getCategoriaNivel() ) );
            this.labelCategoriaSuper.setText( newSetor.getCategoriaSuper() == null ? null : newSetor.getCategoriaSuper().getCategoriaNome() );
        }
    }

    private ModalNovaCategoriaResult validateForm(){
        ModalNovaCategoriaResult res = new ModalNovaCategoriaResult();
        Categoria.CategoriaBuilder categoriaBuilder = new Categoria.CategoriaBuilder();
        categoriaBuilder.setNome(SQLText.normalize( this.textFieldSetorNome.getText() ) );
        categoriaBuilder.setCategoriaSuper( this.comboxCategoriaSuper.getValue() );
        res.resultVale = categoriaBuilder.build();
        res.level = SnackbarBuilder.MessageLevel.WARNING;

        if( res.resultVale.getCategoriaNome() == null )
            res.message = "Informe o nome da categoria!";
        else if( res.resultVale.getCategoriaSuper() == null || res.resultVale.getCategoriaSuper().getCategoriaId() == null ){
            res.message = "Informe a categoria super!";
        } else {
            res.succeed = true;
        }
        return res;
    }

    class ModalNovaCategoriaResult implements ModalResult< Categoria >{

        private boolean succeed;
        private String message;
        private boolean terminated;
        private Categoria resultVale;
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
        public Categoria getValue() {
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
