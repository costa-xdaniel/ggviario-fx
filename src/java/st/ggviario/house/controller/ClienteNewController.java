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
import st.jigahd.support.sql.postgresql.PostgresSQL;

import java.net.URL;
import java.util.*;

public class ClienteNewController implements Initializable {

    private List<Sexo> listSexo;
    private List<Distrito> listDistrito;
    private List<TipoDocumento> listTipoDocumento;

    public void newClient() {

    }

    public interface OnResultSucess{
        void accept( Cliente cliente, Map<String, Object> data );
    }

    public interface OnResultFailed{
        void accept(Cliente cliente, Map< String, Object > data );
    }

    private OnResultFailed onResultFailed;
    private OnResultSucess onResultSucess;


    @FXML
    private JFXTextField textFieldClienteNome;

    @FXML
    private JFXTextField textFieldClienteApalido;

    @FXML
    private JFXComboBox<Sexo> comboClientSexo;

    @FXML
    private DatePicker dataPickerClientDateBirth;

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
                    .withVarchar( cliente.getClienteDocumentoNumero() )
                    .withVarchar( cliente.getClienteNome() )
                    .withVarchar( cliente.getClienteApelido() )
                    .withVarchar( cliente.getClienteeTelefone() )
                    .withVarchar( cliente.getClienteeTelefone() )
                    .withVarchar( cliente.getClienteMail() )
                    .withVarchar( cliente.getClienteMorada() )
                    .callFunctionTable()
                    .onResultQuery( row -> {
                        boolean result  = row.asBoolean("result" );
                        if( result ){
                            Map< String, Object > root = new Gson().fromJson( String.valueOf( row.valueOf("message") ), Map.class );
                            Map<String, Object> map = (Map<String, Object>) root.get("cliente");
                            Cliente.ClienteBuilder clienteBuilder = new Cliente.ClienteBuilder();
                            clienteBuilder.setId( cliente, UUID.fromString( ( String ) map.get( "cliente_id" ) ) );
                            if( this.onResultSucess != null ){
                                this.onResultSucess.accept( cliente, root );
                            }
                        } else {
                            String msg = String.valueOf( row.valueOf("message") );
                            System.out.println( msg );
                            Map< String, Object > root = new Gson().fromJson( msg, Map.class );
                            if( this.onResultFailed != null ) this.onResultFailed.accept( cliente, root );
                        }
                    });
        }
    }

    private Cliente clientMounter() {
        Cliente.ClienteBuilder builder = new Cliente.ClienteBuilder()
                .nome( this.textFieldClienteNome.getText() )
                .apelido( this.textFieldClienteApalido.getText() )
                .tipoDocumento( this.comboxClientTipoDocumento.getValue() )
                .documentoNumero( this.textFieldClientDocumentoNumero.getText() )
                .sexo( this.comboClientSexo.getValue() )
                .distrito( this.comboxClienteDistrito.getValue() )
                .morada( this.textFieldClienteMorada.getText() )
                ;
        return builder.build();
    }

    private boolean validadeForm() {
        return true;
    }

    public ClienteNewController setOnResultFailed(OnResultFailed onResultFailed) {
        this.onResultFailed = onResultFailed;
        return this;
    }

    public ClienteNewController setOnResultSucess(OnResultSucess onResultSucess) {
        this.onResultSucess = onResultSucess;
        return this;
    }
}