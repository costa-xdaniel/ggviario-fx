package st.ggviario.house.controller;

import com.jfoenix.controls.JFXSnackbar;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SnackbarBuilder {

    JFXSnackbar snackbar;
    private int successTimeOut;
    private int informationTimeOut;
    private int warningTimeOut;
    private int errorTimeOut;

    public SnackbarBuilder( Pane pane ) {
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

    public void show( String message, MessageLevel leve ) {
        Map< MessageLevel, Runnable > map = new HashMap<>();
        map.put( MessageLevel.ERROR, () -> this.showError( message ) );
        map.put( MessageLevel.WARNING, () -> this.showWarning( message ) );
        map.put( MessageLevel.INFORMATION, () -> this.showInformation( message ) );
        map.put( MessageLevel.SUCCESS, () -> this.showSucess( message ) );
        map.get( leve ).run();
    }

    public void showSucess(String message ){
        this.addStylesheet("/styles/snackbar-success.css");
        snackbar.show( message, "Entendi", this.successTimeOut, event -> snackbar.close() );
    }


    public void showInformation(String message ){
        this.addStylesheet("/styles/snackbar-information.css");
        snackbar.show( message, "Entendi", this.informationTimeOut, event -> snackbar.close() );
    }

    public void showWarning(String message ){
        this.addStylesheet("/styles/snackbar-warning.css");
        snackbar.show( message, "Entendi", this.warningTimeOut, event -> snackbar.close() );
    }

    public void showError(String message ){
        this.addStylesheet("/styles/snackbar-error.css");
        snackbar.show( message, "Entendi", this.errorTimeOut, event -> snackbar.close() );
    }

    public enum MessageLevel {
        ERROR,
        WARNING,
        INFORMATION,
        SUCCESS;
    }
}
