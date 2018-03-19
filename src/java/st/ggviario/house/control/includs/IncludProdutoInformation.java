package st.ggviario.house.control.includs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.model.Produto;
import st.jigahd.support.sql.lib.SQLResource;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class IncludProdutoInformation implements Initializable {

    public static IncludProdutoInformation newInstance( ){
        ControllerLoader< VBox, IncludProdutoInformation > loader = new ControllerLoader<VBox, IncludProdutoInformation>("/fxml/includs/includ_produto_information.fxml");
        return loader.getController();
    }


    @FXML private VBox root;
    @FXML private Label labelProdutoNome;
    @FXML private Label labelProdutoCategoria;
    @FXML private Label labelProdutoMontanteVendas;
    @FXML private Label labelProdutoMontanteVendaVendas;
    @FXML private Label labelProdutoMontanteVendaDividas;
    @FXML private Label labelProdutoMontanteVendaPagas;
    @FXML private Label labelProdutoMontanteVendaPendentes;
    @FXML private Label labelProdutoMontanteCompras;
    @FXML private Label labelProdutoMontanteCompraPagas;
    @FXML private Label labelProdutoMontanteCompraPendentes;
    @FXML private Label labelServicoVenda;
    @FXML private Label labelServicoCompra;
    @FXML private Label labelServicoProducao;
    @FXML private Label labelServioStockDinamico;
    @FXML private Label tableStockAtual;
    @FXML private Label labelStockMinimo;
    @FXML private Label labelProdutoMontanteSaido;
    @FXML private Label labelProdutoMontanteEntrado;

    private Produto produdo;
    private NumberFormat money = NumberFormat.getNumberInstance( Locale.FRANCE );
    private NumberFormat number = NumberFormat.getNumberInstance( Locale.FRANCE );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.money.setMinimumIntegerDigits( 1 );
        this.money.setMinimumFractionDigits( 2 );
        this.money.setMaximumFractionDigits( 2 );
        this.number.setMinimumIntegerDigits( 1 );
        this.clear();
    }

    public VBox getRoot() {
        return root;
    }

    public void setProdudo(Produto produdo) {
        this.produdo = produdo;
        if( this.produdo == null ) this.clear();
        else {
            this.labelProdutoNome.setText( this.produdo.getProdutoNome() );
            this.labelProdutoCategoria.setText( this.produdo.getProdutoCategoria().getCategoriaNome() );
            this.labelProdutoMontanteVendas.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteVendas(), 0 ) ) );
            this.labelProdutoMontanteVendaVendas.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteVendaVendas(), 0 ) ) );
            this.labelProdutoMontanteVendaDividas.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteVendaDivida(), 0 ) ) );
            this.labelProdutoMontanteVendaPagas.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteVendaPagas(), 0 ) ) );
            this.labelProdutoMontanteVendaPendentes.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteVendaPendentes(), 0 ) ) );
            this.labelProdutoMontanteCompras.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteCompras(), 0 ) ) );
            this.labelProdutoMontanteCompraPagas.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteCompraPagas(), 0 ) ) );
            this.labelProdutoMontanteCompraPendentes.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteCompraPendentes(), 0 ) ) );
            this.labelProdutoMontanteSaido.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteCompraPagas(), 0 ) ) );
            this.labelProdutoMontanteEntrado.setText( this.money.format(SQLResource.coalesce( this.produdo.getProdutoMontanteVendaPagas(), 0 ) ) );

            chose( "Serviço venda",  this.labelServicoVenda, this.produdo.getProdutoServicoVenda() );
            chose( "Serviço compra", this.labelServicoCompra, this.produdo.getProdutoServicoCompra() );
            chose( "Serviço produção", this.labelServicoProducao, this.produdo.getProdutoServicoProducao() );
            chose( "Stock dinamico", this.labelServioStockDinamico, this.produdo.getProdutoServicoDinamico() );

            String unit =  this.produdo.getPreco() == null ? "" : " "+this.produdo.getPreco().getUnidade().getUnidadeCodigo();
            this.tableStockAtual.setText( number.format( SQLResource.coalesce( this.produdo.getProdutoStock(), 0 ) )+unit );
            this.labelStockMinimo.setText( number.format( SQLResource.coalesce( this.produdo.getProdutoStockMinimo(), 0 ) ) + unit );
        }

    }

    private void chose( String text, Label label, Boolean ativo) {
        if( ativo ){
            label.getStyleClass().add( "ativo" );
        } else {
            label.getStyleClass().remove( "ativo" );
        }
    }
    private void clear( String text, Label label) {
        label.getStyleClass().remove( "ativo" );
    }

    private void clear( ){
        this.labelProdutoNome.setText( null );
        this.labelProdutoCategoria.setText( null );
        this.labelProdutoMontanteVendas.setText( null );
        this.labelProdutoMontanteVendaVendas.setText( null );
        this.labelProdutoMontanteVendaDividas.setText( null );
        this.labelProdutoMontanteVendaPagas.setText( null );
        this.labelProdutoMontanteVendaPendentes.setText( null );
        this.labelProdutoMontanteCompras.setText( null );
        this.labelProdutoMontanteCompraPagas.setText( null );
        this.labelProdutoMontanteCompraPendentes.setText( null );
        this.labelProdutoMontanteSaido.setText( null );
        this.labelProdutoMontanteEntrado.setText( null );
        clear( "Serviço venda",  this.labelServicoVenda );
        clear( "Serviço compra", this.labelServicoCompra  );
        clear( "Serviço produção", this.labelServicoProducao );
        clear( "Stock dinamico", this.labelServioStockDinamico );
    }
}
