package st.ggviario.house.model;

import com.google.gson.Gson;

import java.net.URL;

public class MenuItem implements Menu {

    private final URL contentPageLink;
    private String name;
    private boolean clickMe;

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
        return getClass().getResource("/fxml/item/menu_item.fxml");
    }

    @Override
    public void setClickMe(boolean b) {
        this.clickMe = b;
    }

    @Override
    public boolean isClickMe() {
        return clickMe;
    }

    public URL getContentPageLink() {
        return contentPageLink;
    }

}
