package st.ggviario.house.controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import st.ggviario.house.model.Cliente;
import st.ggviario.house.model.Producto;
import st.ggviario.house.model.Unidade;

import java.net.URL;
import java.util.ResourceBundle;

public class DividaNewController implements Initializable {

    @FXML
    private JFXTextField searchCliente;

    @FXML
    private JFXListView< Cliente > listViewCliente;

    @FXML
    private JFXButton fabNewCliente;

    @FXML
    private JFXButton btDividaCadastrar;

    @FXML
    private JFXComboBox< Producto > comboxProduto;

    @FXML
    private JFXComboBox< Unidade > comboxUnidades;

    @FXML
    private JFXTextField textFieldQunatidade;

    @FXML
    private JFXTextField textFieldCustoSemDesconto;

    @FXML
    private JFXTextField textFieldPrecoUnitirio;

    @FXML
    private JFXTextField textFieldDesconto;

    @FXML
    private JFXTextField textFieldMontantePagar;

    @FXML
    private JFXDatePicker dataPickerPrazo;

    @FXML
    private JFXDatePicker dataPckerData;

    @FXML
    private JFXTextField textFieldClienteNome;

    @FXML
    private JFXTextField textFieldClienteMorada;

    @FXML
    private JFXTextField textFieldClienteCOntacto;

    @FXML
    private JFXTextField textFieldMontante;

    @FXML
    private JFXTextField textFieldMontantePendente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
