package st.ggviario.house.control.modals;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import st.ggviario.house.control.ControllerLoader;
import st.ggviario.house.control.SnackbarBuilder;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.Distrito;
import st.ggviario.house.model.Sexo;
import st.ggviario.house.model.TipoDocumento;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.SQLRow;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;
import st.jigahd.support.sql.postgresql.PostgresSQLResultSet;

import java.net.URL;
import java.util.*;

public class ModalNovoCliente extends AbstractModal< Cliente > implements Initializable {

    public static ModalNovoCliente load(StackPane stackPane ){
        ControllerLoader< AnchorPane, ModalNovoCliente > loader = new ControllerLoader<>("/fxml/modal/modal_novo_cliente.fxml");
        ModalNovoCliente cliente = loader.getController();
        cliente.createDialogModal( stackPane );
        return cliente;
    }


    @FXML private AnchorPane root;
    @FXML private AnchorPane anchorHeader;
    @FXML private AnchorPane iconAnchorCloseArea;
    @FXML private Label modalTitle;
    @FXML private JFXTextField textFieldClienteNome;
    @FXML private JFXTextField textFieldClienteApalido;
    @FXML private JFXComboBox<Sexo> comboClientSexo;
    @FXML private DatePicker dataPickerClienteDataNascimento;
    @FXML private JFXComboBox<Distrito> comboxClienteDistrito;
    @FXML private JFXTextField textFieldClienteMorada;
    @FXML private JFXTextField textFieldClientTelephone;
    @FXML private JFXTextField textFieldClientMail;
    @FXML private JFXComboBox<TipoDocumento> comboxClientTipoDocumento;
    @FXML private JFXTextField textFieldClientDocumentoNumero;
    @FXML private JFXButton buttonClientRegister;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize( location, resources );
        this.buttonClientRegister.setOnAction( actionEvent -> this.register());
        this.loadSexoData();
        this.loadTipoDocumentoData();
        this.loadDistritoData();
    }

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


    private void loadSexoData() {
        Thread thread = new Thread(() -> {
            Platform.runLater(() -> {
                this.comboClientSexo.getItems().clear();
                for( Sexo sexo: Sexo.LIST ){
                    this.comboClientSexo.getItems().add( sexo );
                }
            });
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private void loadDistritoData( ){
        Thread thread = new Thread(() -> {
            PostgresSQL postgresSQL = PostgresSQLSingleton.getInstance();
            Distrito.DistritoBuilder distritoBuilder = new Distrito.DistritoBuilder();
            Platform.runLater(() -> {
                this.comboxClienteDistrito.getItems().clear();
                this.comboxClienteDistrito.getItems().add( new Distrito() );
            });
            postgresSQL.query("ggviario.funct_load_distrito")
                    .withOther( null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> Platform.runLater(() -> {
                        this.comboxClienteDistrito.getItems().add( distritoBuilder.load( row ).build() );
                    }));
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }
    private void loadTipoDocumentoData( ){
        Thread thread = new Thread(() -> {
            PostgresSQL postgresSQL = PostgresSQLSingleton.getInstance();
            TipoDocumento.TipoDocumentoBuilder tipoDocumentoBuilder = new TipoDocumento.TipoDocumentoBuilder();
            Platform.runLater(() -> {
                this.comboxClientTipoDocumento.getItems().clear();
                this.comboxClientTipoDocumento.getItems().add( new TipoDocumento() );
            });
            postgresSQL.query("ggviario.funct_load_tipodocumento")
                    .withOther( null )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> Platform.runLater(() -> {
                        this.comboxClientTipoDocumento.getItems().add( tipoDocumentoBuilder.load( row ).build() );
                    }));
        });
        thread.setPriority( Thread.MIN_PRIORITY );
        thread.start();
    }

    private void register() {
        ReseultClient res = this.validadeForm();
        if( res.isSuccess() ){
            PostgresSQL postgresSQL = PostgresSQLSingleton.getInstance();
            postgresSQL.query( "ggviario.funct_reg_cliente" )
                    .withOther( AuthSingleton.getInstance().getColaboradorId() )
                    .withSmallint( res.cliente.getSexo() == null? null : res.cliente.getSexo().getSexId() )
                    .withSmallint( res.cliente.getDistrito() == null? null : res.cliente.getDistrito().getDistritoId() )
                    .withSmallint( res.cliente.getTipoDocumento() == null? null : res.cliente.getTipoDocumento().getTipoDocumentoId() )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteDocumentoNumero() ) )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteNome() ) )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteApelido()  ))
                    .withDate( res.cliente.getClienteDataNascimento() )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteTelefone() ) )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteTelefone() ) )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteMail() ) )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteMorada() ) )
                    .withVarchar( SQLText.normalize( res.cliente.getClienteLocalTrabalho() ) )
                    .callFunctionTable()
                    .onResultQuery((PostgresSQLResultSet.OnReadAllResultQuery) row -> {
                        boolean result  = row.asBoolean("result" );
                        String doc = row.asString("message" );
                        res.map  = new Gson().fromJson( doc, Map.class );
                        if( result ){
                            Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
                            res.cliente = clienteBuilder.load(( Map<String, Object>) res.map.get("cliente" ) ).build();
                            res.success = true;
                            res.message = "ClientServiceServer cadastrado com sucesso";
                        } else {
                            res.success = false;
                            res.message = SQLRow.stringOf( res.map.get("text") );
                        }
                    });
        }

        if( res.isSuccess() ){
            this.closeModal();
            this.clear();
        }
        this.executeOnOperationResult( res );
    }

    public void clear() {
        this.textFieldClienteNome.setText( null );
        this.textFieldClienteApalido.setText( null );
        this.textFieldClientDocumentoNumero.setText( null );
        this.textFieldClienteMorada.setText( null );
        this.textFieldClientTelephone.setText( null );
        this.textFieldClientMail.setText( null );
        this.comboClientSexo.setValue( null );
        this.comboxClienteDistrito.setValue( null );
        this.comboxClientTipoDocumento.setValue( null );
    }

    private Cliente clientMounter() {

        Date dataNascimento = null;
        if( this.dataPickerClienteDataNascimento.getValue() != null  )
            dataNascimento = java.sql.Date.valueOf( this.dataPickerClienteDataNascimento.getValue() );

        Cliente.ClienteBuilder builder = new Cliente.ClienteBuilder()
                .nome( this.textFieldClienteNome.getText() )
                .apelido( this.textFieldClienteApalido.getText() )
                .tipoDocumento( this.comboxClientTipoDocumento.getValue() )
                .documentoNumero( this.textFieldClientDocumentoNumero.getText() )
                .mail( this.textFieldClientMail.getText() )
                .sexo( this.comboClientSexo.getValue() )
                .distrito( this.comboxClienteDistrito.getValue() )
                .morada( this.textFieldClienteMorada.getText() )
                .dataNascimento( dataNascimento )
                ;

        return builder.build();
    }

    private ReseultClient validadeForm() {
        ReseultClient  res = new ReseultClient();
        res.cliente = this.clientMounter();
        if( res.cliente.getClienteNome() == null ){
            res.message = "O nome do cliente tem que ser informado";
        } else if ( res.cliente.getClienteNome().toUpperCase().equals("anonimo") ) {
            res.message = "Não pode usar esse nome para cadastrar nenhum cliente";
        } else {
            res.success = true;
        }
        return res;
    }


    private class ReseultClient implements ModalResult< Cliente > {
        private boolean success = false;
        private String message;
        private boolean terminated;
        private Cliente cliente;
        private Map<String, Object > map;

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
        public Cliente getValue() {
            return cliente;
        }

        @Override
        public SnackbarBuilder.MessageLevel getLevel() {
            return null;
        }

        @Override
        public Map<String, Object> getData() {
            return this.map;
        }
    }

}
