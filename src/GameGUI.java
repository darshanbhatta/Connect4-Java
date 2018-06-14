import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author Darshan B., David T., Benn O., Sudeep R.
 */

public class GameGUI extends Application {
    private GridPane gameboard;
    private GameTracker gameTracker;
    private Label currentPlayer;
    private String who;
    private String current;
    private int rows, columns, connectWin;

    public GameGUI(GameTracker presenter, String who) {
        this.gameTracker = presenter;
        this.who = who;
        rows = 6;
        columns = 7;
        connectWin = 4;
        presenter.attachView(this);
    }

    @Override
    public void start(final Stage stage) {
        stage.show();
        draw(stage);
    }

    public void draw(Stage stage) {
        //creates grid dimensions
        gameTracker.pushDimen(rows, columns);
        stage.setTitle("Connect " + connectWin);

        BorderPane borderPane = new BorderPane();
        Button home = new Button("Home");
        Button reset = new Button("Reset");

        gameTracker.addPlayer("Player 1");
        if (who.equals("pvc")) {
            gameTracker.addPlayer("Computer");
        } else
            gameTracker.addPlayer("Player 2");

        currentPlayer = new Label(gameTracker.getTurn() + "      ");

        ToolBar tb = new ToolBar();
        tb.setStyle("-fx-background-color: rgba(111, 169, 222, 1);");
        borderPane.setTop(tb);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(5, 1);
        currentPlayer.setTextFill(Color.web("#ffffff"));
        reset.getStyleClass().add("resetbtn");
        home.getStyleClass().add("resetbtn");

        tb.getItems().addAll(home, reset, spacer, currentPlayer);

        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("game-grid");
        gameboard = new GridPane();
        gameboard.getStyleClass().add("game-gridd");

        //creates the column spacing
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            gameboard.getColumnConstraints().add(column);
        }

        //creates the row spacing
        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(50);
            gameboard.getRowConstraints().add(row);
        }

        //creates circles and listens for the clicks
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                Pane pane = new Pane();
                pane.setMinSize(50, 50);
                //paints a circle on every click on the given grid
                pane.setOnMouseReleased(e -> {

                    gameTracker.updateModelGrid(GridPane.getColumnIndex(pane));

                    // updates currentPlayer
                    currentPlayer.setText(gameTracker.getTurn() + "      ");
                    // Check for a winner

                    int playerWin = gameTracker.checkWin();
                    if (playerWin != 0) {
                        // display a win message
                        Alert winAlert = new Alert(AlertType.INFORMATION);
                        winAlert.setTitle("Game Over!");
                        winAlert.setHeaderText(null);
                        if (who.equals("pvc")) {
                            if (playerWin == 1) {
                                winAlert.setContentText("Player 1" + " wins!");

                            } else {

                                winAlert.setContentText("Computer" + " wins!");

                            }

                        } else {

                            if (playerWin == 1) {
                                winAlert.setContentText("Player 1" + " wins!");

                            } else {

                                winAlert.setContentText("Player 2" + " wins!");

                            }
                        }

                        winAlert.showAndWait();
                        // reset grid
                        try {
                            draw(stage);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (gameTracker.getCounter() == 41) {
                        // display a tie message
                        Alert tieAlert = new Alert(AlertType.INFORMATION);
                        tieAlert.setTitle("Game Over!");
                        tieAlert.setHeaderText(null);
                        tieAlert.setContentText("It's a tie!");

                        tieAlert.showAndWait();
                        // reset grid
                        try {
                            draw(stage);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    }

                });

                pane.getStyleClass().add("game-grid-cell");
                if (c == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (r == 0) {
                    pane.getStyleClass().add("first-row");
                }

                gameboard.add(pane, c, r);

            }
        }

        //reset button listener
        reset.setOnAction((ActionEvent event) -> {
            try {
                draw(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //home button listener
        home.setOnAction((ActionEvent event) -> {
            StartScreenGUI gui = new StartScreenGUI(gameTracker);
            try {
                gui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        ImageView img = new ImageView("resources/connect4board.png");
        ImageView title = new ImageView("resources/title_text.png");

        stackPane.getChildren().add(img);
        stackPane.getChildren().add(title);
        stackPane.getChildren().add(gameboard);
        StackPane.setAlignment(gameboard, Pos.CENTER);
        StackPane.setAlignment(img, Pos.CENTER);
        StackPane.setAlignment(title, Pos.TOP_CENTER);

        borderPane.setCenter(stackPane);
        borderPane.setMinWidth(1280);
        borderPane.setMinHeight(800);

        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add(GameGUI.class.getResource("resources/game.css").toExternalForm());
        stage.setScene(scene);
        stage.setMinWidth(1280);
        stage.setMinHeight(800);
        stage.show();
    }

    //finds the circle clicked and returned the position for the 2d array
    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> circles = gridPane.getChildren();

        for (Node node : circles) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    //makes the circles inside the pane
    public void paintCircle(int row, int column) {

        //   System.out.println(turnCount);
        Circle circle = new Circle(25, 25, 27);

        if (gameTracker.getTurn().equals("Player 1"))
            circle.setFill(Color.web("#FFF056"));
        else {
            circle.setFill(Color.web("#FF565C"));
        }

        gameTracker.changeTurn();
        if (gameTracker.getTurn().equals("Computer") && gameTracker.checkWin() == 0) {
            gameTracker.updateModelGrid(gameTracker.computerMove());

        }

        Pane p = (Pane) getNodeByRowColumnIndex(row, column, gameboard);
        p.getChildren().add(circle);


    }

}
