package st.ggviario.house.model;

import java.net.URL;

public class DrawerHeader implements Menu {

    @Override
    public URL getFXMLUrl() {
        return getClass().getResource("/fxml/drawer/drawer_header.fxml");
    }

    @Override
    public void setClickMe(boolean b) {

    }

    @Override
    public boolean isClickMe() {
        return false;
    }
}
