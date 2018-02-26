package st.ggviario.house.controller;

import com.jfoenix.controls.JFXSnackbar;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SnackbarBuilder {

    public static final int SUCCESS_MESSAGE = 0x3;
    public static final int INFORMATION_MESSAGE = 0x2;
    public static final int WARNING_MESSAGE = 0x1;
    public static final int ERROR_MESSAGE = 0x0;


    JFXSnackbar snackbar;
    private int successTimeOut;
    private int informationTimeOut;
    private int warningTimeOut;
    private int errorTimeOut;

    public SnackbarBuilder() {
        this.snackbar =  new JFXSnackbar();
        init();
    }

    public SnackbarBuilder(Pane pane) {
        this.snackbar =  new JFXSnackbar( pane );
        init();
    }

    private void init() {
        addStylesheet( "/styles/material-design.css");
        addStylesheet( "/styles/styles.css");
        addStylesheet( "/styles/snackbar.css");
        this.successTimeOut = 10000;
        this.informationTimeOut = 12000;
        this.warningTimeOut = 15000;
        this.errorTimeOut = 20000;
    }

    private void addStylesheet(String location ) {
        String css = getClass().getResource( location ).toExternalForm();
        this.snackbar.getStylesheets().add(css);
    }

    public SnackbarBuilder setErrorTimeOut(int errorTimeOut) {
        this.errorTimeOut = errorTimeOut;
        return this;
    }

    public SnackbarBuilder setInformationTimeOut(int informationTimeOut) {
        this.informationTimeOut = informationTimeOut;
        return this;
    }

    public SnackbarBuilder setSnackbar(JFXSnackbar snackbar) {
        this.snackbar = snackbar;
        return this;
    }

    public SnackbarBuilder setSuccessTimeOut(int successTimeOut) {
        this.successTimeOut = successTimeOut;
        return this;
    }

    public SnackbarBuilder setWarningTimeOut(int warningTimeOut) {
        this.warningTimeOut = warningTimeOut;
        return this;
    }

    public void success(String message ){
        this.addStylesheet("/styles/snackbar-success.css");
        snackbar.show( message, "Entendi", this.successTimeOut, event -> snackbar.close() );
    }


    public void information( String message ){
        this.addStylesheet("/styles/snackbar-information.css");
        snackbar.show( message, "Entendi", this.informationTimeOut, event -> snackbar.close() );
    }

    public void warning( String message ){
        this.addStylesheet("/styles/snackbar-warning.css");
        snackbar.show( message, "Entendi", this.warningTimeOut, event -> snackbar.close() );
    }

    public void error( String message ){
        this.addStylesheet("/styles/snackbar-error.css");
        snackbar.show( message, "Entendi", this.errorTimeOut, event -> snackbar.close() );
    }

    public void show( String message, int leve ){

        Map< Integer, Runnable > map = new HashMap<>();
        map.put( ERROR_MESSAGE, () -> this.error( message ) );
        map.put( WARNING_MESSAGE, () -> this.error( message ) );
        map.put( INFORMATION_MESSAGE, () -> this.error( message ) );
        map.put( SUCCESS_MESSAGE, () -> this.error( message ) );
        map.get( leve ).run();
    }
}
