package st.ggviario.house.controller.page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.modal.ModalNovaVenda;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class VendaController extends TableController< VendaController.VendaViewModel > implements Page,  Initializable {


    //Modals
    private ModalNovaVenda modalNovaVenda;


    //Detalhes da venda
    protected DrawerVendaDetalhesController drawerVendaDetalhesController;
    //

    private List< VendaViewModel > vendaList = new LinkedList<>();
    Node rootPage;
    private String oldTextFilter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getButonNew().setOnAction(actionEvent -> this.openModalNovaVenda());
        this.structure();
        this.loadData( null );
        pushAll();
        defineEvents();
    }

    @Override
    public void onSetRootPage(Node rootPage) {
        this.rootPage = rootPage;
    }

    private void pushAll() {
        push( this.vendaList, this.getTableVenda()  );
    }

    private void defineEvents() {
        this.getTableVenda().getSelectionModel().selectedItemProperty().addListener((observableValue, oldVenda, newVenda) -> {
            if( newVenda != null ){
                this.loadVendaDetailLayout();
                this.getDrawerVendaDetails().setSidePane( this.drawerVendaDetalhesController.getRoot() );
                this.drawerVendaDetalhesController.setVenda( newVenda.getValue().venda  );

                if( !this.getLocalRootPage().getChildren().contains( this.getDrawerVendaDetails() ) ){
                    int index = this.getLocalRootPage().getChildren().indexOf( this.getTableVenda() );
                    this.getLocalRootPage().getChildren().add(index+1, this.getDrawerVendaDetails() );
                }
                this.getDrawerVendaDetails().open();
            } else {
                this.closeDetails();
            }
        });
        this.getDrawerVendaDetails().setOnDrawerClosed(event -> {
            if( this.getLocalRootPage().getChildren().contains( this.getDrawerVendaDetails() ) )
                this.getLocalRootPage().getChildren().remove( this.getDrawerVendaDetails() );
        });
    }



    private void openModalNovaVenda() {
        this.lodaModalNovaVenda();
        this.modalNovaVenda.openModal();
    }


    private void lodaModalNovaVenda() {
        if( this.modalNovaVenda == null){
            this.modalNovaVenda = ModalNovaVenda.load( this.getTipoVenda(), this.getFunctionLoadClienteNew(), (StackPane) this.rootPage);
            this.modalNovaVenda.setOnModalResult(operationResult -> {
                this.loadData( null );
                this.pushAll();
            });
        }
    }

    protected void loadVendaDetailLayout() {
        if( this.drawerVendaDetalhesController == null ){
            this.drawerVendaDetalhesController = DrawerVendaDetalhesController.load( this.getDrawerVendaDetails(), this.getTipoVenda(), this.getAvalibleIcons() );
            this.drawerVendaDetalhesController.setOnNewVendaForClinet( this::onNewVendaFormCliente );

            if( drawerVendaDetalhesController == null ) throw  new RuntimeException( "Para Qui" );

        }
    }

    private void onNewVendaFormCliente( Cliente cliente ){
        if( cliente == null ) {
            this.closeDetails();
            return;
        }
        this.lodaModalNovaVenda();
        this.modalNovaVenda.openModalForCliente( cliente );
    }

    void closeDetails() {
        if( this.getDrawerVendaDetails().isShown() )
            this.getDrawerVendaDetails().close();
    }


    void loadData( String text ) {
        Venda.VendaBuilder vendaBuilder = new Venda.VendaBuilder();
        Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
        Producto.ProdutoBuilder produtoBuilder = new Producto.ProdutoBuilder();
        Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

        PostgresSQL sql = PostgresSQLSingleton.loadPostgresSQL();
        this.vendaList.clear();

        sql.query( this.getFunctionLoadVendaName() ).withJsonb( (String) null).callFunctionTable().onResultQuery(row -> {
            vendaBuilder.load( row );
            vendaBuilder.cliente( clienteBuilder.load( row ).build() );
            vendaBuilder.produto( produtoBuilder.load( row ).build() );
            vendaBuilder.unidade( unidadeBuilder.load( row ).build() );
            this.vendaList.add( new VendaViewModel( vendaBuilder.build() ) );
        });

        System.out.println( vendaList );
    }

    @Override
    public void onSearch(KeyEvent event, String textFilter) {
        boolean full = event != null && event.getCode() == KeyCode.ENTER;
        if( full ) {
            this.loadData( textFilter );
            this.pushAll();
        } else if( this.oldTextFilter != null && textFilter == null ) {
            this.pushAll();
        } else if( textFilter != null & oldTextFilter != null && ! textFilter.equals( oldTextFilter ) ){
            List< VendaViewModel > search = new LinkedList<>( );
            for( VendaViewModel next : this.vendaList  ){
                Venda venda  = next.venda;
                Cliente cliente  = next.venda.getCliente();
                if( cliente.getClienteCompletName().toLowerCase().contains( textFilter ) ) search.add( next );
                else if( venda.getProducto().getProdutoNome().toLowerCase().contains( textFilter ) ) search.add( next );
            }
            this.push( search, this.getTableVenda() );
        }
        this.oldTextFilter = textFilter;
    }

    abstract JFXButton getButonNew() ;

    abstract TipoVenda getTipoVenda();

    abstract String getFunctionLoadClienteNew();

    abstract String getFunctionLoadVendaName();

    abstract JFXTreeTableView<VendaViewModel> getTableVenda();

    void structure(){
        this.getTableVenda().setRowFactory(param -> new JFXTreeTableRow< VendaViewModel>(){
            @Override
            protected void updateItem(VendaViewModel item, boolean empty) {
                super.updateItem(item, empty);
                if( item != null && !empty ){
                    String estado = item.venda.getVendaEstado().name().toLowerCase();
                    String tipo = item.venda.getTipoVendaCod();
                    for(Venda.VendaEstado vendaEstado : item.venda.getVendaEstado().others( ) ){
                        getStyleClass().remove( vendaEstado.name().toLowerCase() );
                    }
                    getStyleClass().remove( item.venda.getTipoVenda().other().name().toLowerCase() );
                    this.getStyleClass().addAll( tipo, estado );
                }
            }
        });
    }

    abstract String[] getAvalibleIcons();

    protected abstract Pane getLocalRootPage();

    protected abstract JFXDrawer getDrawerVendaDetails();

    protected class VendaViewModel extends  RecursiveTreeObject< VendaViewModel > {
        protected StringProperty vendaFaturaNumero;
        protected StringProperty vendaCliente;
        protected StringProperty vendaProduto;
        protected StringProperty vendaQuantidade;
        protected ObjectProperty< Date > vendaData;
        protected ObjectProperty< Number > vendaMontantePagar;
        protected ObjectProperty< Number > vendaMontanteAmortizado;
        protected ObjectProperty< Date > vendaDataFinalizar;
        protected ObjectProperty< Date > vendaDataRegisto;
        protected StringProperty vendaEstado;
        protected Venda venda;
        private VendaViewModel(Venda venda ) {
            vendaFaturaNumero = new SimpleStringProperty( venda.getVendaFaturaNumero() );
            vendaCliente = new SimpleStringProperty( venda.getCliente().getClienteCompletName() );
            vendaProduto = new SimpleStringProperty( venda.getProducto().getProdutoNome() );
            vendaQuantidade = new SimpleStringProperty( venda.getVandaQuantidade() + " "+ venda.getUnidade().getUnidadeCodigo() );
            vendaData = new SimpleObjectProperty<>( venda.getVendaData() );
            vendaMontantePagar = new SimpleObjectProperty<>( venda.getVendaMontantePagar() );
            vendaMontanteAmortizado = new SimpleObjectProperty<>( venda.getVendaMontanteAmortizado() );
            vendaDataFinalizar = new SimpleObjectProperty<>( venda.getVendaDataFinalizar() );
            vendaEstado = new SimpleStringProperty( venda.getVendaEstado().getShowName() );
            vendaDataRegisto = new SimpleObjectProperty<>( venda.getVendaDataRegisto() );
            this.venda = venda;
        }
    }



}
