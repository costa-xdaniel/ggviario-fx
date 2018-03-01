package st.ggviario.house.controller.page;

public class ClienteController {
//
//        extends TableController< Cliente > implements Page, Initializable {
//
//
//    @FXML
//    private Pane fabArea;
//
//    @FXML
//    private MaterialDesignIconView fabIcon;
//
//    @FXML
//    private Button fabButton;
//
//    @FXML
//    private TableView< Cliente > tableViewCliente;
//
//    @FXML
//    private TableColumn<Cliente, String> columnClienteNome;
//
//    @FXML
//    private TableColumn<Cliente, String> columnClienteContacto;
//
//    @FXML
//    private TableColumn<Cliente, String> columnClienteMorada;
//
//    @FXML
//    private TableColumn<Cliente, Number> columnClienteMontanteVenda;
//
//    @FXML
//    private TableColumn<Cliente, Number> columnClienteMontanteDivida;
//
//    @FXML
//    private TableColumn<Cliente, Number> columnClienteMontanteTotal;
//
//    @FXML
//    private TableColumn<Cliente, Number> columnClienteMontantePago;
//
//    @FXML
//    private TableColumn<Cliente, Number> columnClienteMontantePorPagar;
//
//    private ModalNovoCliente modalNovoCliente;
//
//
//    private ModalNovoCliente newClienteModalContentController;
//    private List<Cliente> listCliente;
//    private StackPane rootPagePane;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
////        JFXRippler rippler = new JFXRippler( this.fabIcon );
////        this.fabArea.getChildren().add( rippler );
////        AnchorPane.setLeftAnchor( rippler, 0.0 );
////        AnchorPane.setRightAnchor( rippler, 0.0 );
////        AnchorPane.setTopAnchor( rippler, 0.0 );
////        AnchorPane.setBottomAnchor( rippler, 0.0 );
////        rippler.setStyle( "-jfx-rippler-fill: #FFFFFF;");
//
//        this.fabIcon.setOnMouseClicked( event ->fabButton.fireEvent( event ) );
//        this.fabButton.setOnMouseClicked( event -> this.newClient() );
//        this.listCliente = new LinkedList<Cliente>();
//        this.structureColumns();
//        this.reloadData();
//    }
//
//    @Override
//    public void onSetRootPage(Node rootPage) {
//        this.rootPagePane = (StackPane) rootPage;
//    }
//
//    private void reloadData() {
//        PostgresSQL postgresSQL = PostgresSQLSingleton.loadPostgresSQL();
//        Cliente.ClienteBuilder builder = new Cliente.ClienteBuilder();
//        Distrito.DistritoBuilder distritoBuilder = new Distrito.DistritoBuilder();
//        this.listCliente.clear();
//        postgresSQL.query( "ggviario.funct_load_cliente" )
//                .withOther( null )
//                .callFunctionTable()
//                .onResultQuery(
//                        row -> this.listCliente.add(
//                                builder.load( row )
//                                        .distrito( distritoBuilder.load( row ).build() )
//                                        .build()
//                        )
//                );
//        this.tableViewCliente.setItems(FXCollections.observableList( this.listCliente ) );
//    }
//
//    private void structureColumns() {
//        this.tableViewCliente.setRowFactory( clienteTableView -> new TableRow<Cliente>(){
//            @Override
//            protected void updateItem(Cliente item, boolean empty) {
//                super.updateItem(item, empty);
//                if( item == null || empty ){
//                    setItem( item );
//                } else{
//                    this.getStyleClass().add("row-normal");
//                    setItem( item );
//                }
//            }
//        });
//
//        this.columnClienteNome.setCellValueFactory( data -> {
//            String nome = data.getValue().getClienteNome();
//            nome = nome +" "+ SQLResource.coalesce( data.getValue().getClienteApelido(), "" );
//            nome = SQLText.normalize( nome );
//            return  new SimpleStringProperty( nome );
//        } );
//
//
//        this.columnClienteNome.setMinWidth( 150 );
//        this.columnClienteMorada.setCellValueFactory( data -> new SimpleStringProperty( data.getValue().getClienteMorada() ) );
//        this.columnClienteContacto.setCellValueFactory( data-> new SimpleStringProperty( data.getValue().getClienteMorada() ) );
//        this.columnClienteMontanteVenda.setCellValueFactory(data -> new SimpleDoubleProperty( data.getValue().getClienteMonatenteCompra() ) );
//        this.columnClienteMontanteTotal.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontanteTotal() ) );
//        this.columnClienteMontantePago.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontantePago() ) );
//        this.columnClienteMontantePorPagar.setCellValueFactory( data -> new SimpleDoubleProperty( data.getValue().getClienteMontantePendente() ) );
//
//    }
//
//
//    private void newClient() {
//        this.loadModalContent();
//        this.modalNovoCliente.openModal();
//    }
//
//    private void loadModalContent() {
//        if( this.modalNovoCliente == null ) {
//            this.modalNovoCliente = ModalNovoCliente.load( this.rootPagePane );
//            this.modalNovoCliente.setOnModalResult(operationResult -> this.reloadData());
//        }
//    }
}
