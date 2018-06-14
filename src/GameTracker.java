/**
 * @author Darshan B., David T., Benn O., Sudeep R.
 */

public class GameTracker {
    GameGUI view;
    GameRules gameRules;


    private int counter = -1;

    //holds the gameGUI
    public void attachView(GameGUI view) {
        this.view = view;
        gameRules = new GameRules();
    }

    public void addPlayer(String name) {
        gameRules.addPlayer(name);
    }

    public void changeTurn() {
        gameRules.changeTurn();
    }

    String getTurn() {
        return gameRules.getTurn();
    }

    public void pushDimen(int row, int column) {
        gameRules.setgameBoard(row, column);
    }

    public void updateModelGrid(int column) {
        int row = gameRules.updategameBoard(column);
        if (row != -1) {
            view.paintCircle(row, column);
            changeCounter();
        }

        printOut();
    }

    public int checkWin() {
        return gameRules.checkWin();

    }


    public int computerMove() {
        return gameRules.getMove();
    }

    public void printOut() {
        for (int r = 0; r < gameRules.getgameBoard().length; r++) {
            for (int c = 0; c < gameRules.getgameBoard()[0].length; c++) {
                System.out.print(gameRules.getgameBoard()[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    public int getCounter() {
        return counter;
    }

    public void changeCounter() {
        counter++;
    }

}
