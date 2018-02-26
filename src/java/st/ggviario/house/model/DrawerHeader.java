package st.ggviario.house.model;

import java.net.URL;

public class DrawerHeader implements Menu {

    @Override
    public URL getFXMLUrl() {
        return getClass().getResource("/fxml/includs/drawer_header.fxml");
    }
}
