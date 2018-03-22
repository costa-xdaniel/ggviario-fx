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
import st.jigahd.support.sql.lib.SQLResource;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
        //From preview layout
        AuthSingleton.login( null, null );

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource("/fxml/page/page_home.fxml") );
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

        APP app = APP.getInstance();
        app.getServer().addOnNextClient(clientService -> {
            clientService.addOnNextLine(line -> {
                System.out.println("line = " + line);
                if(SQLResource.existIn(SimpleIntent.find( line ), SimpleIntent.REQUIRE_FOCUS ) ) {
                    Platform.runLater(() -> {
                        primaryStage.requestFocus();
                        clientService.writeUTF( SimpleIntent.REQUIRE_FOCUS );
                    });
                }
            });
        });
    }
}
