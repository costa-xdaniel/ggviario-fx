package st.ggviario.house.controller;

import javafx.fxml.Initializable;
import st.ggviario.house.model.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuLeftHeader implements Initializable {

    private MenuItem header;

    public void setHeader(MenuItem header) {
        this.header = header;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
