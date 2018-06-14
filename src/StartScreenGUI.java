import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Darshan B., David T., Benn O., Sudeep R.
 */

public class StartScreenGUI extends Application {

    private GameTracker gameTracker;


    public StartScreenGUI(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
    }

    @Override
    public void start(final Stage stage) {
        stage.show();
        draw(stage);
    }

    void draw(Stage stage) {
        stage.setTitle("Connect " + 4);

        BorderPane borderPane = new BorderPane();
        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("game-grid");
        ImageView title = new ImageView("resources/title_text.png");
        Button pvp = new Button();
        Button pvc = new Button();
        Button howTo = new Button();
        pvp.getStyleClass().add("pvp");
        pvc.getStyleClass().add("pvc");
        howTo.getStyleClass().add("howto");
        pvp.setMinSize(433, 66);
        pvp.setMaxSize(433, 66);
        pvc.setMinSize(433, 66);
        pvp.setMaxSize(433, 66);
        howTo.setMinSize(76, 75);
        howTo.setMaxSize(76, 75);
        stackPane.getChildren().add(pvp);
        stackPane.getChildren().add(pvc);
        stackPane.getChildren().add(howTo);
        stackPane.getChildren().add(title);
        StackPane.setMargin(pvp, new Insets(0, 0, 166, 0));
        StackPane.setMargin(pvc, new Insets(0, 0, 0, 0));
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setAlignment(howTo, Pos.TOP_CENTER);
        StackPane.setMargin(howTo, new Insets(20, 0, 0, 1150));
        borderPane.setCenter(stackPane);
        borderPane.setMinWidth(1280);
        borderPane.setMinHeight(800);
        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add(StartScreenGUI.class.getResource("resources/game.css").toExternalForm());
        stage.setScene(scene);
        stage.setMinWidth(1280);
        stage.setMinHeight(800);
        stage.show();

        //listener for the how to play
        howTo.setOnAction((ActionEvent event) -> {

            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setTitle("How to Play");
            winAlert.setHeaderText(null);
            winAlert.setContentText("Connect 4 is a two-player game which takes place on a 6 x 7 rectangular board. Each player can drop a token at the top of the board in one of the seven columns. The token falls down and fills the lowest unoccupied circle. A player cannot drop a token in a column if it is already full. The object of the game is to connect four tokens vertically, horizontally, or diagonally. If the board is filled and no one has aligned four tokens then the game is a draw.");
            winAlert.showAndWait();


        });

        //listener for the player vs player button
        pvp.setOnAction((ActionEvent event) -> {
            GameGUI gui = new GameGUI(gameTracker, "pvp");
            try {
                gui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //listener for the player vs computer button
        pvc.setOnAction((ActionEvent event) -> {
            GameGUI gui = new GameGUI(gameTracker, "pvc");
            try {
                gui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


}
