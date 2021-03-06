package st.ggviario.house.control.tabs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.modals.ModalNovaDespesa;
import st.ggviario.house.control.TableClontroller;
import st.ggviario.house.model.Despesa;
import st.ggviario.house.model.Fornecedor;
import st.ggviario.house.model.Produto;
import st.ggviario.house.model.Unidade;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TabPageDespesa extends TableClontroller<TabPageDespesa.DespesaModelView> implements TabPage, Initializable {

    @FXML private JFXButton fabButton;
    @FXML private StackPane fabArea;
    @FXML private JFXTreeTableView <DespesaModelView> treeTableViewDespesa;

    private JFXTreeTableColumn <DespesaModelView, String > columnCodigo = new JFXTreeTableColumn<>("Codigo");
    private JFXTreeTableColumn <DespesaModelView, String > columnFornecedor = new JFXTreeTableColumn<>("Fornecedor");
    private JFXTreeTableColumn <DespesaModelView, String > columnProduto = new JFXTreeTableColumn<>("Produto");
    private JFXTreeTableColumn <DespesaModelView, String > columnQuantidade = new JFXTreeTableColumn<>("Qt.");
    private JFXTreeTableColumn <DespesaModelView, Number > columnTotal = new JFXTreeTableColumn<>("Total");
    private JFXTreeTableColumn <DespesaModelView, Number > columnAmortizadao = new JFXTreeTableColumn<>("Pago");
    private JFXTreeTableColumn <DespesaModelView, Number > columnPendente = new JFXTreeTableColumn<>("Pendente");
    private JFXTreeTableColumn <DespesaModelView, Date > columnData = new JFXTreeTableColumn<>("Data");
    private JFXTreeTableColumn <DespesaModelView, Date > columnRegisto = new JFXTreeTableColumn<>("Registo");
    private JFXTreeTableColumn <DespesaModelView, String > columnEstado = new JFXTreeTableColumn<>("Estado");


    private StackPane rootPage;
    private ModalNovaDespesa modalNovaDespesa;

    private List<DespesaModelView> originalListView = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.push( new LinkedList<>(), this.treeTableViewDespesa );
    }

    @Override
    public void onSetRootPage( StackPane rootPage) {
        this.rootPage = rootPage;
    }

    @Override
    public void onAfterOpen() {
        this.loadDataDespesa();
    }

    private void structure(){
        JFXDepthManager.setDepth( this.fabArea, 4 );
        this.columnCodigo.setCellValueFactory( paramn -> paramn.getValue().getValue().codigo );

        this.columnFornecedor.setCellValueFactory( param -> param.getValue().getValue().fornecedor );
        this.columnFornecedor.getStyleClass().add( CLASS_COLUMN_LEFT );

        this.columnProduto.setCellValueFactory( param -> param.getValue().getValue().produto );
        this.columnProduto.getStyleClass().add( CLASS_COLUMN_LEFT );

        this.columnQuantidade.setCellValueFactory( param -> param.getValue().getValue().quantiade );
        this.columnQuantidade.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.columnTotal.setCellValueFactory( param -> param.getValue().getValue().montanteTotal );
        this.columnTotal.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.columnAmortizadao.setCellValueFactory( param -> param.getValue().getValue().montanteAmortizado );
        this.columnAmortizadao.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.columnPendente.setCellValueFactory( param->param.getValue().getValue().montantePendente );
        this.columnPendente.getStyleClass().add( CLASS_COLUMN_MONEY );

        this.columnData.setCellValueFactory( param -> param.getValue().getValue().data );
        this.columnRegisto.setCellValueFactory( param -> param.getValue().getValue().dataRegisto );
        this.columnEstado.setCellValueFactory( param -> param.getValue().getValue().estada );
        this.columnEstado.getStyleClass().add( CLASS_COLUMN_LEFT );

        this.treeTableViewDespesa.getColumns().setAll(
                this.columnCodigo,
                this.columnFornecedor,
                this.columnProduto,
                this.columnQuantidade,
                this.columnTotal,
                this.columnAmortizadao,
                this.columnPendente,
                this.columnData,
                this.columnRegisto,
                this.columnEstado
        );

        this.columnCodigo.setMinWidth( 110 );
        this.columnCodigo.setMaxWidth( 110 );
        this.columnFornecedor.setMinWidth( 180 );
        this.columnProduto.setMinWidth( 130 );
        this.columnQuantidade.setMinWidth( 110 );
        this.columnQuantidade.setMaxWidth( 110 );
        this.columnTotal.setMinWidth( 130 );
        this.columnTotal.setMaxWidth( 130 );
        this.columnAmortizadao.setMinWidth( 130 );
        this.columnAmortizadao.setMaxWidth( 130 );
        this.columnPendente.setMinWidth( 130 );
        this.columnPendente.setMaxWidth( 130 );
        this.columnData.setMinWidth( 110 );
        this.columnData.setMaxWidth( 110 );
        this.columnRegisto.setMinWidth( 110 );
        this.columnRegisto.setMaxWidth( 110 );
        this.columnEstado.setMinWidth( 135 );
        this.columnEstado.setMaxWidth( 135 );

        this.columnData.setCellFactory( this.cellDateFormat(DD_MM_YYYY_FORMAT) );
        this.columnRegisto.setCellFactory( this.cellDateFormat(DD_MM_YYYY_FORMAT) );
    }

    private void defineEvents(){
        fabButton.setOnAction(actionEvent -> {
            onOpenModalNovaDespesa();
        });
    }

    private void loadDataDespesa( ){
       Thread thread = new Thread(() -> {
           Platform.runLater(() -> {
               this.originalListView.clear();
               this.treeTableViewDespesa.getRoot().getChildren().clear();
               this.treeTableViewDespesa.refresh();
           });

           Despesa.DespesaBuilder builder = new Despesa.DespesaBuilder();
           Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
           Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
           Fornecedor.FornecedorBuilder fornecedorBuilder = new Fornecedor.FornecedorBuilder();

           PostgresSQL sql = PostgresSQLSingleton.getInstance();
           sql.query( "ggviario.funct_load_despesa" )
                   .withJsonb( (String)  null )
                   .callFunctionTable()
                   .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                      Platform.runLater(() -> {
                          builder.load( row );
                          builder.setProduto( produtoBuilder.load( row ).build() );
                          builder.setFornecedor( fornecedorBuilder.load( row ).build() );
                          builder.setUnidade( unidadeBuilder.load( row ).build() );
                          DespesaModelView item = new DespesaModelView( builder.build() );
                          this.originalListView.add( item );
                          this.treeTableViewDespesa.getRoot().getChildren().add( new TreeItem<>( item ) );
                      });
                   });
       });
       thread.setPriority( Thread.MIN_PRIORITY );
       thread.start();
    }



    private void onOpenModalNovaDespesa() {
        this.loadModalDespesa();
        this.modalNovaDespesa.openModal();
    }

    private void loadModalDespesa( ){
        if( this.modalNovaDespesa == null ){
            this.modalNovaDespesa =  ModalNovaDespesa.load( (StackPane) this.rootPage );
            this.modalNovaDespesa.setOnModalResult(modalResult -> {
                if( modalResult.isSuccess() ) {
                    this.loadDataDespesa();
                }
            });
        }

    }



    class DespesaModelView extends RecursiveTreeObject<DespesaModelView> {

        private Despesa despesa;
        private StringProperty codigo;
        private StringProperty fornecedor;
        private StringProperty produto;
        private StringProperty quantiade;
        private ObjectProperty<Number> montanteTotal;
        private ObjectProperty<Number> montanteAmortizado;
        private ObjectProperty< Number > montantePendente;
        private ObjectProperty< Date > data;
        private ObjectProperty<Date> dataRegisto;
        private StringProperty estada;

        public DespesaModelView( Despesa despesa ){
            this.despesa = despesa;
            this.codigo = new SimpleStringProperty( despesa.getDespesaCodigo() );
            this.fornecedor = new SimpleStringProperty( despesa.getFornecedor().getFornecedorNome() );
            this.produto = new SimpleStringProperty( despesa.getProduto().getProdutoNome() );
            this.quantiade = new SimpleStringProperty( despesa.getDespesaQuantidade() + " "+despesa.getUnidade().getUnidadeCodigo() );
            this.montanteTotal = new SimpleObjectProperty<>( despesa.getDespesaMontanteTotal() );
            this.montanteAmortizado = new SimpleObjectProperty<>( despesa.getDespesaMontanteAmortizado() );
            this.montantePendente = new SimpleObjectProperty<>( despesa.getDespesaMontantePendente( ) );
            this.data = new SimpleObjectProperty<>( despesa.getDespesaData() );
            this.dataRegisto = new SimpleObjectProperty<>( despesa.getDespesaDataRegisto() );
            this.estada = new SimpleStringProperty( this.despesa.getEstado().getNome() );
        }
    }


}
