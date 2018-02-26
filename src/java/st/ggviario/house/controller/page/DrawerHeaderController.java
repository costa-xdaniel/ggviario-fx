package st.ggviario.house.controller.page;

import javafx.fxml.Initializable;
import st.ggviario.house.model.DrawerItem;
import st.ggviario.house.model.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerHeaderController implements Initializable, DrawerItem {

    private Menu header;
    private HomeController homeController;

    public void setMenu( Menu header) {
        this.header = header;
    }

    @Override
    public void setRootController(HomeController rootController) {
        this.homeController = rootController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
