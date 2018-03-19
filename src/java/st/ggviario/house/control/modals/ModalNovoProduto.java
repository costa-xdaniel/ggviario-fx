package st.ggviario.house.control.modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.Colaborador;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.SQLResult;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModalNovoProduto extends AbstractModal<Produto > {

    public static ModalNovoProduto newInstance(StackPane stackPane ){
        ControllerLoader<AnchorPane, ModalNovoProduto > loader = new ControllerLoader<>("/fxml/modal/modal_novo_produto.fxml");
        loader.getController().createDialogModal( stackPane );
        loader.getController().structure();
        loader.getController().defineEvents();
        return loader.getController();
    }

    private final Categoria VOID_CATEGORIA = new Categoria();

    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private Label modalTitle;
    @FXML private AnchorPane iconAnchorCloseArea;
    @FXML private JFXTextField textFieldNome;
    @FXML private JFXComboBox< Categoria > comboxCategoria;
    @FXML private JFXToggleButton toggleServicoCompra;
    @FXML private JFXToggleButton toggleServicoProducao;
    @FXML private JFXToggleButton toggleServicoStockDinamico;
    @FXML private JFXToggleButton toggleServicoVenda;
    @FXML private JFXButton buttonClientRegister;
    @FXML  private JFXTextField textFieldStockMinimo;

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
        return this.iconAnchorCloseArea;
    }

    @Override
    AnchorPane getAnchorHeader() {
        return this.anchorHeader;
    }

    @Override
    void structure() {
        this.comboxCategoria.setItems(FXCollections.observableList( new LinkedList<>() ) );
        this.requireStockDinamico( false );
    }

    @Override
    public void clear() {
        this.textFieldNome.setText( null );
        this.comboxCategoria.setValue( null );
        this.toggleServicoVenda.selectedProperty().setValue( false );
        this.toggleServicoCompra.selectedProperty().setValue( false );
        this.toggleServicoProducao.selectedProperty().setValue( false );
        this.toggleServicoStockDinamico.selectedProperty().setValue( false );
        this.textFieldStockMinimo.setText( null );
        this.produto = null;
        this.requireStockDinamico( false );
    }

    @Override
    void defineEvents() {
        super.defineEvents();
        this.comboxCategoria.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCategoria, newCategoria) -> this.onChangeCategoria( newCategoria ));
        this.toggleServicoProducao.selectedProperty().addListener( (observableValue, oldValue, newValue ) -> this.requireStockDinamico( newValue ) );
        this.toggleServicoVenda.selectedProperty().addListener((observableValue, oldValue, newValue) -> this.requireStockDinamico( newValue ) );
        this.toggleServicoCompra.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            this.requireStockDinamico( false );
        });
        this.buttonClientRegister.setOnAction(actionEvent -> {
            if( this.produto != null && this.produto.getProdutoId() != null ) this.onEditiProduto();
            else this.onRegisterNovoProduto();
        });
        this.toggleServicoStockDinamico.selectedProperty().addListener((observableValue, oldValue, newValue ) -> {
            if( newValue ) this.textFieldStockMinimo.setDisable( false );
            else{
                this.textFieldStockMinimo.setDisable( true );
                this.textFieldStockMinimo.setText( null );
            }
        });

    }



    @Override
    public void openModal() {
        super.openModal();
        this.clear();
    }

    public void openModal(Produto produto ) {
        this.produto = produto;
        if( this.produto != null && this.produto.getProdutoId() != null ){
            this.textFieldNome.setText( this.produto.getProdutoNome() );
            this.textFieldStockMinimo.setText( String.valueOf( this.produto.getProdutoStockMinimo() == null ? "" : this.produto.getProdutoStockMinimo() ) );
            this.toggleServicoVenda.setSelected( this.produto.getProdutoServicoVenda() );
            this.toggleServicoCompra.setSelected( this.produto.getProdutoServicoCompra() );
            this.toggleServicoProducao.setSelected( this.produto.getProdutoServicoProducao() );
            this.toggleServicoStockDinamico.setSelected( this.produto.getProdutoServicoDinamico() );
            this.comboxCategoria.setValue( produto.getProdutoCategoria() );
        }
        super.openModal();
    }

    private void onChangeCategoria(Categoria newCategoria){
        if (newCategoria == null || newCategoria.getCategoriaId() == null ) {
            this.comboxCategoria.setValue( null );
        }
    }

    private void requireStockDinamico( Boolean newValue ){
        if( newValue ){
            this.toggleServicoStockDinamico.setSelected( true );
            this.toggleServicoStockDinamico.setDisable( true );
        } else {
            if ( ! this.toggleServicoVenda.isSelected() && !this.toggleServicoProducao.isSelected() ){
                this.toggleServicoStockDinamico.setDisable( false );
                if( !this.toggleServicoCompra.isSelected()){
                    this.toggleServicoStockDinamico.setSelected( false );
                    this.toggleServicoStockDinamico.setDisable( true );
                }
            }
        }
    }


    private void onRegisterNovoProduto( ){
        ModalNovoProdutoResult res = this.chackForm();
        if (res.isSuccess()) {
            PostgresSQL sql = PostgresSQLSingleton.getInstance() ;
            Colaborador colaborador = AuthSingleton.getInstance();
            Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();
            sql.query( "ggviario.funct_reg_produto" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( res.value.getProdutoCategoria().getCategoriaId() )
                .withVarchar( res.value.getProdutoNome() )
                .withBoolean( res.value.getProdutoServicoVenda() )
                .withBoolean( res.value.getProdutoServicoCompra() )
                .withBoolean( res.value.getProdutoServicoProducao() )
                .withBoolean( res.value.getProdutoServicoDinamico() )
                .withNumeric( res.value.getProdutoStockMinimo() )
                .withJsonb( ( String ) null )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        res.map = result.getData();
                        if (result.isSuccess()) {
                            res.success  = true;
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            res.message = "Novo produto cadastrado com sucesso!";
                            res.terminated = true;
                            res.value = builder.load((Map<String, Object>) result.getData().get("produto")).build();
                        } else {
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.message = result.getMessage();
                        }
                    });

            ;
        }
        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.message, res.level );
        if( res.isSuccess() ){
            this.closeModal();
            this.clear();
        }
        executeOnOperationResult( res );
    }


     private void onEditiProduto( ){
        ModalNovoProdutoResult res = this.chackForm();
        if (res.isSuccess()) {
            PostgresSQL sql = PostgresSQLSingleton.getInstance() ;
            Colaborador colaborador = AuthSingleton.getInstance();
            Produto.ProdutoBuilder builder = new Produto.ProdutoBuilder();

            sql.query( "ggviario.funct_change_produto_data" )
                .withUUID( colaborador.getColaboradorId() )
                .withUUID( this.produto.getProdutoId() )
                .withUUID( res.value.getProdutoCategoria().getCategoriaId() )
                .withVarchar( res.value.getProdutoNome() )
                .withBoolean( res.value.getProdutoServicoVenda() )
                .withBoolean( res.value.getProdutoServicoCompra() )
                .withBoolean( res.value.getProdutoServicoProducao() )
                .withBoolean( res.value.getProdutoServicoDinamico() )
                .withNumeric( res.value.getProdutoStockMinimo() )
                .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        SQLResult result = new SQLResult( row );
                        res.map = result.getData();
                        if (result.isSuccess()) {
                            res.success  = true;
                            res.level = SnackbarBuilder.MessageLevel.SUCCESS;
                            res.message = "As informaçõe do produto foram atualizadas";
                            res.terminated = true;
                            res.value = builder.load((Map<String, Object>) result.getData().get("produto")).build();
                        } else {
                            res.level = SnackbarBuilder.MessageLevel.ERROR;
                            res.message = result.getMessage();
                        }
                    });

            ;
        }
        SnackbarBuilder snackbarBuilder = new SnackbarBuilder( this.getStakePane() );
        snackbarBuilder.show( res.message, res.level );
        if( res.isSuccess() ){
            this.closeModal();
            this.clear();
        }
        executeOnOperationResult( res );
    }

    private ModalNovoProdutoResult chackForm(){
        ModalNovoProdutoResult result = new ModalNovoProdutoResult();
        Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
        produtoBuilder.setNome( SQLText.normalize( this.textFieldNome.getText() ) );
        produtoBuilder.setCategoria( this.comboxCategoria.getValue() );

        boolean venda = this.toggleServicoVenda.isSelected();
        boolean compra = this.toggleServicoCompra.isSelected();
        boolean producao = this.toggleServicoProducao.isSelected();
        boolean stockDinamico = this.toggleServicoStockDinamico.isSelected();

        produtoBuilder.setServicoVenda( venda );
        produtoBuilder.setServicoCompra( compra );
        produtoBuilder.setServicoProducao( producao );
        produtoBuilder.setServicoStockDinamico( stockDinamico );
        produtoBuilder.setStockMinimo( SQLRow.doubleOf( SQLText.normalize( this.textFieldStockMinimo.getText() ) ) );
        result.value = produtoBuilder.build();
        result.level = SnackbarBuilder.MessageLevel.WARNING;
        if( result.value.getProdutoNome() == null ){
            result.message = "Informe o nome do produto!";
        } else if( result.getValue().getProdutoCategoria() == null || result.getValue().getProdutoCategoria().getCategoriaId() == null ) {
            result.message = "Informe a categoria do produto!";
        } else if ( !venda && !compra && !producao && this.produto == null ){
            result.message = "Ative pelo menus um dos serviço entre compra, venda ou produção!";
        } else {
            result.success = true;
        }
        return result;
    }

    public void setCategoriaList(List<Categoria > categoriaList ){
        this.comboxCategoria.getItems().addAll( categoriaList );
        if( categoriaList.size() > 1 ) {
            this.comboxCategoria.getItems().add(0, this.VOID_CATEGORIA);
        }
        if( this.produto != null ){
            this.comboxCategoria.setValue( this.produto.getProdutoCategoria() );
        }
    }

    class ModalNovoProdutoResult implements ModalResult < Produto >  {

        private boolean success;
        private String message;
        private boolean terminated;
        private Produto value;
        private SnackbarBuilder.MessageLevel level;
        private Map< String, Object > map;

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
        public Produto getValue() {
            return this.value;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return this.level;
        }

        @Override
        public Map<String, Object> getData() {
            return map;
        }
    }
}
