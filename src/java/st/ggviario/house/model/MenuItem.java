package st.ggviario.house.model;

import com.google.gson.Gson;

import java.net.URL;

public class MenuItem implements Menu {

    private final URL contentPageLink;
    private String name;

    public MenuItem( String name, URL contentPageLink ) {
        this.name = name;
        this.contentPageLink = contentPageLink;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new Gson().toJson( this );
    }

    @Override
    public URL getFXMLUrl() {
        return getClass().getResource("/fxml/menu_item.fxml");
    }

    public URL getContentPageLink() {
        return contentPageLink;
    }

}
