package st.ggviario.house.controller.tabs;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import st.ggviario.house.controller.SnackbarBuilder;
import st.ggviario.house.controller.drawers.DrawerVenda;
import st.ggviario.house.controller.modals.ModalDestroy;
import st.ggviario.house.controller.modals.ModalNovaVenda;
import st.ggviario.house.controller.pages.TableClontroller;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class VendaController extends TableClontroller< VendaController.VendaViewModel > implements TabPage,  Initializable {


    private ModalNovaVenda modalNovaVenda;
    DrawerVenda drawerVendaDetalhes;
    private ModalDestroy< Venda > modalDestroyVenda;


    private List< VendaViewModel > vendaOriginalList = new LinkedList<>();
    StackPane rootPage;
    private String oldTextFilter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.structure();
        defineEvents();
        push( new LinkedList<>(), this.getTableVenda()  );
        this.loadData( null, true );
    }

    @Override
    public void onSetRootPage( StackPane rootPage) {
        this.rootPage = rootPage;
    }


    private void defineEvents() {
        this.getTableVenda().getSelectionModel().selectedItemProperty().addListener((observableValue, oldVenda, newVenda) -> {
            this.openDrawer( newVenda == null? null : newVenda.getValue() );
        });

        this.getDrawerVendaDetails().setOnDrawerClosed(event -> {
            if( this.getLocalRootPage().getChildren().contains( this.getDrawerVendaDetails() ) )
                this.getLocalRootPage().getChildren().remove( this.getDrawerVendaDetails() );
        });

        this.getButonNew().setOnAction(actionEvent -> this.openModalNovaVenda());
    }

    void closeDetails() {
        if( this.getDrawerVendaDetails().isShown() )
            this.getDrawerVendaDetails().close();
    }

    void loadData( String text, boolean full ) {
        Thread thread = new Thread(() -> {
            Venda.VendaBuilder vendaBuilder = new Venda.VendaBuilder();
            Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
            Produto.ProdutoBuilder produtoBuilder = new Produto.ProdutoBuilder();
            Unidade.UnidadeBuilder unidadeBuilder = new Unidade.UnidadeBuilder();

            PostgresSQL sql = PostgresSQLSingleton.getInstance();
            this.vendaOriginalList.clear();
            this.getTableVenda().getRoot().getChildren().clear();

            sql.query( this.getFunctionLoadVendaName() )
                    .withJsonb( (String) null)
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        Platform.runLater(() -> {
                            vendaBuilder.load( row );
                            vendaBuilder.cliente( clienteBuilder.load( row ).build() );
                            vendaBuilder.produto( produtoBuilder.load( row ).build() );
                            vendaBuilder.unidade( unidadeBuilder.load( row ).build() );
                            VendaViewModel item = new VendaViewModel(vendaBuilder.build());
                            this.vendaOriginalList.add( item );
                            this.getTableVenda().getRoot().getChildren().add( new TreeItem<>( item ) );
                        });
                    });
        });
        thread.start();
    }

    void openDrawer( VendaViewModel newVenda ) {
        if( newVenda != null ){
            this.loadDrawerDetahesVenda();
            this.getDrawerVendaDetails().setSidePane( this.drawerVendaDetalhes.getRoot() );
            this.drawerVendaDetalhes.setVenda( newVenda.venda  );

            if( !this.getLocalRootPage().getChildren().contains( this.getDrawerVendaDetails() ) ){
                int index = this.getLocalRootPage().getChildren().indexOf( this.getTableVenda() );
                this.getLocalRootPage().getChildren().add(index+1, this.getDrawerVendaDetails() );
            }
            this.getDrawerVendaDetails().open();
        } else {
            this.closeDetails();
        }
    }

    private void openModalNovaVenda() {
        this.lodaModalNovaVenda();
        this.modalNovaVenda.openModal();
    }

    private void openModalNovaVendaForCliente(Cliente cliente ){
        if( cliente == null ) {
            this.closeDetails();
            return;
        }
        this.lodaModalNovaVenda();
        this.modalNovaVenda.openModalForCliente( cliente );
    }

    private void openModalDestroyVenda(Venda venda ){
        if (venda == null) {
            this.closeDetails();
            return;
        }
        this.loadModalDestroy();
        this.modalDestroyVenda.setTitle( "ANULAR "+ this.getTipoVenda().name()+" "+venda.getVendaFaturaNumero() );
        ModalDestroy.Destroy< Venda > destroy =new ModalDestroy.Destroy<>(
                venda,
                "Tens a certeza que pretente anuladr a "+getTipoVenda().name().toLowerCase()+" com codigo " + venda.getVendaFaturaNumero(),
                venda.getVendaFaturaNumero()
        );
        this.modalDestroyVenda.opemModal( destroy );
    }

    private void lodaModalNovaVenda() {
        if( this.modalNovaVenda == null){
            this.modalNovaVenda = ModalNovaVenda.load( this.getTipoVenda(), this.getFunctionLoadClienteNew(), (StackPane) this.rootPage);
            this.modalNovaVenda.setOnModalResult(operationResult -> {
                this.loadData( null, true );
            });
        }
    }

    void loadDrawerDetahesVenda() {
        if( this.drawerVendaDetalhes == null ){
            this.drawerVendaDetalhes = DrawerVenda.load( this.getDrawerVendaDetails(), this.getTipoVenda(), this.getAvalibleIcons() );
            if( drawerVendaDetalhes == null ) throw  new RuntimeException( "Para Qui" );
            this.drawerVendaDetalhes.setOnNewVendaForClinet( this::openModalNovaVendaForCliente);
            this.drawerVendaDetalhes.setOnDeleteVenda( this::openModalDestroyVenda);
        }
    }

    void loadModalDestroy(){
        if (this.modalDestroyVenda == null) {
            this.modalDestroyVenda = ModalDestroy.newInstance( this.rootPage );
            this.modalDestroyVenda.setMessageInvalidIdentifier( "O Codigo introduzido esta invalido!" );
            this.modalDestroyVenda.setMessageMissingIdentifier( "Para alunar a "+this.getTipoVenda().name().toLowerCase()+" é necessario que confirme o codigo!" );
            this.modalDestroyVenda.setMessageMissingText( "Expecifique o motivo de anulação da venda" );
            this.modalDestroyVenda.setOnModalResult(modalResult -> {

                String message = modalResult.getMessage();
                SnackbarBuilder.MessageLevel level = SnackbarBuilder.MessageLevel.WARNING;
                SnackbarBuilder snackbak = new SnackbarBuilder( this.rootPage );
                if( modalResult.isSuccess() ){
                    PostgresSQL sql = PostgresSQLSingleton.getInstance();
                    Colaborador colaborador = AuthSingleton.getInstance();
                    sql.query( "ggviario.funct_change_venda_anular" )
                        .withUUID( colaborador.getColaboradorId() )
                        .withUUID( modalResult.getValue().getObject().getVendaId() )
                        .withVarchar( modalResult.getValue().getText() )
                        .callFunctionTable()
                            .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                                SQLResult result = new SQLResult( row );
                                if (result.isSuccess()) {
                                    snackbak.show( getTipoVenda().name()+" anulado com sucesso!", SnackbarBuilder.MessageLevel.SUCCESS );
                                    this.modalDestroyVenda.clear();
                                    this.modalDestroyVenda.closeModal();
                                    String auxOld = oldTextFilter;
                                    this.oldTextFilter = null;
                                    this.loadData( auxOld, true );
                                } else {
                                    snackbak.show( result.getMessage(), SnackbarBuilder.MessageLevel.ERROR );
                                }
                            });
                    ;
                } else {
                    snackbak.show( message, level );
                }

            });
        }
    }

    @Override
    public void onSearch(KeyEvent event, String textFilter) {
        boolean full = event != null && event.getCode() == KeyCode.ENTER;
        if( full ) {
            this.loadData( textFilter, true );
        } else if( this.oldTextFilter != null && textFilter == null ) {
        } else if( textFilter != null & oldTextFilter != null && ! textFilter.equals( oldTextFilter ) ){
            List< VendaViewModel > search = new LinkedList<>( );
            this.getTableVenda().getRoot().getChildren().clear();
            boolean add;

            for( VendaViewModel next : this.vendaOriginalList  ){
                add = false;
                Venda venda  = next.venda;
                Cliente cliente  = next.venda.getCliente();
                if( cliente.getClienteCompletName().toLowerCase().contains( textFilter ) )  add = true;
                else if( venda.getProduto().getProdutoNome().toLowerCase().contains( textFilter ) )   add = true;


                if( add ) this.getTableVenda().getRoot().getChildren().add(new TreeItem<>(next));
            }
            this.push( search, this.getTableVenda() );
        }
        this.oldTextFilter = textFilter;
    }

    protected abstract JFXButton getButonNew() ;
    protected abstract TipoVenda getTipoVenda();
    protected abstract String getFunctionLoadClienteNew();
    protected abstract String getFunctionLoadVendaName();
    protected abstract JFXTreeTableView<VendaViewModel> getTableVenda();

    protected void structure(){
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
        JFXDepthManager.pop( this.getDrawerVendaDetails() );

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
            vendaProduto = new SimpleStringProperty( venda.getProduto().getProdutoNome() );
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
