package st.ggviario.house.controller.modals;

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
import st.ggviario.house.controller.ControllerLoader;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.model.Categoria;
import st.ggviario.house.model.Colaborador;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.SQLResult;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.util.List;
import java.util.Map;

public class ModalNovoProduto extends AbstractModal<Produto > {

    public static ModalNovoProduto load(StackPane stackPane ){
        ControllerLoader<AnchorPane, ModalNovoProduto > loader = new ControllerLoader<>("/fxml/modal/moda_novo_produto.fxml");
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
        this.buttonClientRegister.setOnAction(actionEvent -> this.onRegisterNovoProduto());

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

        System.out.println(
                "venda "+ venda
                +" & compra " + compra
                +" & producao " + producao
                +" & stockDInamico "+ stockDinamico
        );

        result.value = produtoBuilder.build();
        result.level = SnackbarBuilder.MessageLevel.WARNING;
        if( result.value.getProdutoNome() == null ){
            result.message = "Informe o nome do produto!";
        } else if( result.getValue().getProdutoCategoria() == null || result.getValue().getProdutoCategoria().getCategoriaId() == null ) {
            result.message = "Informe a categoria do produto!";
        } else if ( !venda && !compra && !producao ){
            result.message = "Ative pelo menus um dos serviço entre compra, venda ou produção!";
        } else {
            result.success = true;
        }
        return result;
    }

    public void setCategoriaList(List<Categoria > categoriaList ){
        this.comboxCategoria.setItems( FXCollections.observableList(categoriaList) );
        if( categoriaList.size() > 1 ) {
            this.comboxCategoria.getItems().add(0, this.VOID_CATEGORIA);
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
