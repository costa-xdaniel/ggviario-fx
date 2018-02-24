package st.ggviario.house.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import st.ggviario.house.model.*;
import st.ggviario.house.singleton.AuthSingleton;
import st.ggviario.house.singleton.PostgresSQLSingleton;
import st.jigahd.support.sql.lib.SQLText;
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.*;

public class ModalNovoClienteController implements Initializable {

    private List<Sexo> listSexo;
    private List<Distrito> listDistrito;
    private List<TipoDocumento> listTipoDocumento;

    public void newClient() {

    }

    public interface OnNewClienteResult {
        void onNewClienteResult( boolean result,  Cliente cliente, Map<String, Object> data );
    }


    private OnNewClienteResult onNewClientSuccess;


    @FXML
    private JFXTextField textFieldClienteNome;

    @FXML
    private JFXTextField textFieldClienteApalido;

    @FXML
    private JFXComboBox<Sexo> comboClientSexo;

    @FXML
    private DatePicker dataPickerClienteDataNascimento;

    @FXML
    private JFXComboBox<Distrito> comboxClienteDistrito;

    @FXML
    private JFXTextField textFieldClienteMorada;

    @FXML
    private JFXTextField textFieldClientTelephone;

    @FXML
    private JFXTextField textFieldClientMail;

    @FXML
    private JFXComboBox<TipoDocumento> comboxClientTipoDocumento;

    @FXML
    private JFXTextField textFieldClientDocumentoNumero;

    @FXML
    private JFXButton buttonClientRegister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.buttonClientRegister.setOnAction( actionEvent -> this.register());
        this.loadForm();
    }

    private void loadForm() {
        this.listSexo = Sexo.LIST;
        this.listDistrito = new LinkedList<>();
        this.listTipoDocumento = new LinkedList<>();
        reloadForm();
    }

    private void reloadForm() {
        this.loadFromDatabase();
        this.comboxClienteDistrito.setItems(FXCollections.observableList( this.listDistrito ) );
        this.comboxClientTipoDocumento.setItems( FXCollections.observableList( this.listTipoDocumento ) );
        this.comboClientSexo.setItems( FXCollections.observableList( this.listSexo ) );
    }

    private void loadFromDatabase() {
        Distrito.DistritoBuilder distritoBuilder = new Distrito.DistritoBuilder();
        TipoDocumento.TipoDocumentoBuilder tipoDocumentoBuilder = new TipoDocumento.TipoDocumentoBuilder();

        this.listDistrito.clear();
        this.listTipoDocumento.clear();

        this.listDistrito.add( distritoBuilder.id(null).nome("Não definido").build() );
        this.listTipoDocumento.add( tipoDocumentoBuilder.id(null).desc("Não definido").build() );

        PostgresSQL postgresSQL = PostgresSQLSingleton.loadPostgresSQL();
        postgresSQL.query("ggviario.funct_load_distrito")
                .withOther( null )
                .callFunctionTable()
                .onResultQuery(row -> this.listDistrito.add( distritoBuilder.load( row ).build() ));

        postgresSQL.query( "ggviario.funct_load_tipodocumento" )
                .withOther( null )
                .callFunctionTable()
                .onResultQuery( row -> this.listTipoDocumento.add( tipoDocumentoBuilder.load( row ).build() ));

        postgresSQL.closeCurrentConnection();
    }

    private void register() {
        if( this.validadeForm() ){
            Cliente cliente = this.clientMounter();
            /*
            (
            arg_colaborador_id uuid,
            arg_sexo_id smallint,
             arg_distrito_id smallint,
             arg_tdocumenti_id smallint,
              arg_cliente_documentonumero character varying,
              arg_cliente_nome character varying,
              arg_cliente_apelido character varying,
              arg_cliente_telemovel character varying,
              arg_cliente_telefone character varying,
               arg_cliente_mail character varying, arg_cliente_morada character varying)
             */
            PostgresSQL postgresSQL = PostgresSQLSingleton.loadPostgresSQL();
            postgresSQL.query( "ggviario.funct_reg_cliente" )
                    .withOther( AuthSingleton.getAuth().getColaboradorId() )
                    .withSmallint( cliente.getSexo() == null? null : cliente.getSexo().getSexId() )
                    .withSmallint( cliente.getDistrito() == null? null : cliente.getDistrito().getDistritoId() )
                    .withSmallint( cliente.getTipoDocumento() == null? null : cliente.getTipoDocumento().getTipoDocumentoId() )
                    .withVarchar( SQLText.normalize( cliente.getClienteDocumentoNumero() ) )
                    .withVarchar( SQLText.normalize( cliente.getClienteNome() ) )
                    .withVarchar( SQLText.normalize( cliente.getClienteApelido()  ))
                    .withDate( cliente.getClienteDataNascimento() )
                    .withVarchar( SQLText.normalize( cliente.getClienteTelefone() ) )
                    .withVarchar( SQLText.normalize( cliente.getClienteTelefone() ) )
                    .withVarchar( SQLText.normalize( cliente.getClienteMail() ) )
                    .withVarchar( SQLText.normalize( cliente.getClienteMorada() ) )
                    .withVarchar( SQLText.normalize( cliente.getClienteLocalTrabalho() ) )
                    .callFunctionTable()
                    .onResultQuery( row -> {
                        boolean result  = row.asBoolean("result" );
                        if( result ){
                            Map< String, Object > root = new Gson().fromJson( String.valueOf( row.valueOf("message") ), Map.class );
                            Map<String, Object> map = (Map<String, Object>) root.get("cliente");
                            Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
                            clienteBuilder.id( cliente, UUID.fromString( ( String ) map.get( "cliente_id" ) ) );
                            if( this.onNewClientSuccess != null ){
                                this.onNewClientSuccess.onNewClienteResult( true, cliente, root );
                            }
                        } else {
                            String msg = String.valueOf( row.valueOf("message") );
                            Map< String, Object > root = new Gson().fromJson( msg, Map.class );
                            if( this.onNewClientSuccess != null ) this.onNewClientSuccess.onNewClienteResult( false, cliente, root );
                        }
                    });
        }
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
                .sexo( this.comboClientSexo.getValue() )
                .distrito( this.comboxClienteDistrito.getValue() )
                .morada( this.textFieldClienteMorada.getText() )
                .dataNascimento( dataNascimento )
                ;

        return builder.build();
    }

    private boolean validadeForm() {
        String nome = this.textFieldClienteNome.getText();
        nome = SQLText.normalize( nome );
        return nome != null;
    }

    public ModalNovoClienteController setOnNewClienteResult(OnNewClienteResult onNewClientSuccess) {
        this.onNewClientSuccess = onNewClientSuccess;
        return this;
    }
}
