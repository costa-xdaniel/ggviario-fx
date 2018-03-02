package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modal.ModalNovaDespesa;
import st.ggviario.house.model.Despesa;
import st.ggviario.house.model.Fornecedor;
import st.ggviario.house.model.Producto;
import st.ggviario.house.model.Unidade;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PageDespesa extends TableController<PageDespesa.DespesaModelView> implements  Page, Initializable {

    @FXML private JFXButton fabNovaDespesa;
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


    private Node rootPage;
    private ModalNovaDespesa modalNovaDespesa;

    private List<DespesaModelView> despesaModelViewList = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        this.defineEvents();
        this.loadData();
        this.push( this.despesaModelViewList, this.treeTableViewDespesa );
    }

    @Override
    public void onSetRootPage(Node rootPage) {
        this.rootPage = rootPage;
    }

    private void structure(){
        this.columnCodigo.setCellValueFactory( paramn -> paramn.getValue().getValue().codigo );
        this.columnFornecedor.setCellValueFactory( param -> param.getValue().getValue().fornecedor );
        this.columnProduto.setCellValueFactory( param -> param.getValue().getValue().produto );
        this.columnQuantidade.setCellValueFactory( param -> param.getValue().getValue().quantiade );
        this.columnTotal.setCellValueFactory( param -> param.getValue().getValue().montanteTotal );
        this.columnAmortizadao.setCellValueFactory( param -> param.getValue().getValue().montanteAmortizado );
        this.columnPendente.setCellValueFactory( param->param.getValue().getValue().montantePendente );
        this.columnData.setCellValueFactory( param -> param.getValue().getValue().data );
        this.columnRegisto.setCellValueFactory( param -> param.getValue().getValue().dataRegisto );
        this.columnEstado.setCellValueFactory( param -> param.getValue().getValue().estada );

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

        this.columnData.setCellFactory( this.createDateFormatTableCell( DD_MM_YYYY ) );
        this.columnRegisto.setCellFactory( this.createDateFormatTableCell( DD_MM_YYYY ) );
    }

    private void defineEvents(){
        fabNovaDespesa.setOnAction(actionEvent -> {
            onOpenModalNovaDespesa();
        });
    }

    private void loadData( ){
        this.loadDataDespesa();
    }

    private void loadDataDespesa( ){
        this.despesaModelViewList.clear();
        Despesa.DespesaBuilder builder = new Despesa.DespesaBuilder();
        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();
        Fornecedor.FornecedorBuilder fornecedorBuilder = new Fornecedor.FornecedorBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        sql.query( "ggviario.funct_load_despesa" )
            .withJsonb( (String)  null )
            .callFunctionTable()
                .onResultQuery(row -> {
                    builder.load( row );
                    builder.setProducto( produtoBuilder.load( row ).build() );
                    builder.setFornecedor( fornecedorBuilder.load( row ).build() );
                    builder.setUnidade( unidadeBuilder.load( row ).build() );
                    this.despesaModelViewList.add( new DespesaModelView(  builder.build() ) );
                });
        ;
    }



    private void onOpenModalNovaDespesa() {
        this.loadModalDespesa();
        this.modalNovaDespesa.openModal();
    }

    private void loadModalDespesa( ){
        if( this.modalNovaDespesa == null ){
            this.modalNovaDespesa =  ModalNovaDespesa.load( (StackPane) this.rootPage );
            this.modalNovaDespesa.setOnModalResult(modalResult -> {
                if( modalResult.isSucceed() ) {
                    this.loadDataDespesa();
                    this.push( this.despesaModelViewList, this.treeTableViewDespesa );
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
            this.produto = new SimpleStringProperty( despesa.getProducto().getProdutoNome() );
            this.quantiade = new SimpleStringProperty( despesa.getDespesaQuantidade() + " "+despesa.getUnidade().getUnidadeCodigo() );
            this.montanteTotal = new SimpleObjectProperty<>( despesa.getDespesaMontanteTotal() );
            this.montanteAmortizado = new SimpleObjectProperty<>( despesa.getDespesaMontanteAmortizado() );
            this.montantePendente = new SimpleObjectProperty<>( despesa.getDespesaMontantePendente( ) );
            this.data = new SimpleObjectProperty<>( despesa.getDespesaData() );
            this.dataRegisto = new SimpleObjectProperty<>( despesa.getDespesaDataRegisto() );
            this.estada = new SimpleStringProperty( this.despesa.getEstado().showName() );
        }
    }


}
