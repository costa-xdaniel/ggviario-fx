package st.ggviario.house;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import st.ggviario.house.singleton.AuthSingleton;

public class Main extends Application {

    public static void main(String[] args) {
        launch( args );
    }

    @Override
    public void start(Stage stage) throws Exception{

        //From preview layout
        AuthSingleton.login( null, null );

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "/fxml/home.fxml" ) );
        Parent parent  = loader.load();
        Scene scene = new Scene( parent );
        stage.setScene( scene );
        stage.show();
    }
}
