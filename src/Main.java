import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Darshan B., David T., Benn O., Sudeep R.
 */

public class Main extends Application {
    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        StartScreenGUI app = new StartScreenGUI(new GameTracker());
        app.start(primaryStage);
    }
    
}