package st.ggviario.house;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import st.ggviario.house.control.HomeController;
import st.ggviario.house.service.net.SimpleIntent;
import st.ggviario.house.singleton.APP;
import st.ggviario.house.singleton.AuthSingleton;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
        APP app = APP.getInstance();
        app.getServer().addOnNextClient(clientService -> clientService.addOnNextLine(text -> {
            if(SimpleIntent.REQUIRE_FOCUS.equal( text ) ) {
                Platform.runLater(() -> {
                    primaryStage.requestFocus();
                    clientService.writeUTF( SimpleIntent.REQUIRE_FOCUS );
                });
            }
        }));

        //From preview layout
        AuthSingleton.login( null, null );
        primaryStage.setMaximized( true );

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource("/fxml/app.fxml") );
        Parent parent  = loader.load();
        HomeController homeController = loader.getController();
        Scene scene = new Scene( parent );
        primaryStage.setScene( scene );
        primaryStage.setTitle( "GGViario" );
        homeController.accept( primaryStage, scene );

        KeyCodeCombination keyCodeCombination = new KeyCodeCombination(
                KeyCode.E,
                KeyCombination.SHIFT_DOWN
        );

        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            KeyCombination.ModifierValue shift;
            KeyCombination.ModifierValue control;
            KeyCombination.ModifierValue alt;
            KeyCombination.ModifierValue meta;
            KeyCombination.ModifierValue shortcut;
            if( keyCodeCombination.match( keyEvent ) ){ }
        });
        primaryStage.show();

    }
}
