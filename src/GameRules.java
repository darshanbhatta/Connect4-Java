import java.util.ArrayList;

/**
 * @author Darshan B., David T., Benn O., Sudeep R.
 */

public class GameRules {
    private ArrayList<Player> players;
    private Player turn;
    private int[][] gameBoard;

    GameRules() {
        players = new ArrayList();
    }

    public int[][] getgameBoard() {
        return gameBoard;
    }

    public void addPlayer(String name) {
        Player p = new Player(name);
        players.add(p);
        turn = players.get(0);
    }

    public void changeTurn() {
        if (turn.equals(players.get(0))) {
            turn = players.get(1);
        } else {
            turn = players.get(0);
        }
    }

    public String getTurn() {
        if (turn.equals(players.get(0))) {
            return players.get(0).getName();
        } else {
            return players.get(1).getName();
        }
    }

    public void setgameBoard(int row, int column) {
        gameBoard = new int[row][column];
    }

    //sudeep code works
    public int updategameBoard(int column) {
        for (int row = gameBoard.length - 1; row >= 0; row--) {

            if (turn.equals(players.get(0))) {
                if (gameBoard[row][column] == 0) {
                    gameBoard[row][column] = 1;
                    return row;

                }
            } else if (gameBoard[row][column] == 0) {
                gameBoard[row][column] = 2;
                return row;

            }
        }
        return -1;
    }

    //benn's code here return true if win
    public boolean checkWinPC(int[][] gameBoard) {

        String gameString = "";
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                gameString = gameString + gameBoard[r][c];
            }
            if (gameString.contains("1111")) {
                return true;
            } else if (gameString.contains("2222")) {
                return true;
            }
            gameString = "";
        }

        for (int r = 0; r < gameBoard[0].length; r++) {
            for (int c = 0; c < gameBoard.length; c++) {
                gameString = gameString + gameBoard[c][r];
            }
            if (gameString.contains("1111")) {
                return true;
            } else if (gameString.contains("2222")) {
                return true;
            }
            gameString = "";
        }

        // ascendingDiagonalCheck
        for (int i = 3; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length - 3; j++) {
                if (gameBoard[i][j] == 1 && gameBoard[i - 1][j + 1] == 1 && gameBoard[i - 2][j + 2] == 1 && gameBoard[i - 3][j + 3] == 1)
                    return true;
                if (gameBoard[i][j] == 2 && gameBoard[i - 1][j + 1] == 2 && gameBoard[i - 2][j + 2] == 2 && gameBoard[i - 3][j + 3] == 2)
                    return true;

            }
        }
        // descendingDiagonalCheck
        for (int i = 3; i < gameBoard.length; i++) {
            for (int j = 3; j < gameBoard[0].length; j++) {
                if (gameBoard[i][j] == 1 && gameBoard[i - 1][j - 1] == 1 && gameBoard[i - 2][j - 2] == 1 && gameBoard[i - 3][j - 3] == 1)
                    return true;
                if (gameBoard[i][j] == 2 && gameBoard[i - 1][j - 1] == 2 && gameBoard[i - 2][j - 2] == 2 && gameBoard[i - 3][j - 3] == 2)
                    return true;
            }
        }


        return false;
    }

    public int checkWin() {
        String gameString = "";

        //horizontal win check
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                gameString = gameString + gameBoard[r][c];
            }
            if (gameString.contains("1111")) {
                return 1;
            } else if (gameString.contains("2222")) {
                return 2;
            }
            gameString = "";
        }

        //vertical win check
        for (int r = 0; r < gameBoard[0].length; r++) {
            for (int c = 0; c < gameBoard.length; c++) {
                gameString = gameString + gameBoard[c][r];
            }
            if (gameString.contains("1111")) {
                return 1;
            } else if (gameString.contains("2222")) {
                return 2;
            }
            gameString = "";
        }

        //ascending diagonal check
        for (int i = 3; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length - 3; j++) {
                if (gameBoard[i][j] == 1 && gameBoard[i - 1][j + 1] == 1 && gameBoard[i - 2][j + 2] == 1 && gameBoard[i - 3][j + 3] == 1)
                    return 1;
                if (gameBoard[i][j] == 2 && gameBoard[i - 1][j + 1] == 2 && gameBoard[i - 2][j + 2] == 2 && gameBoard[i - 3][j + 3] == 2)
                    return 2;

            }
        }

        //descending diagonal check
        for (int i = 3; i < gameBoard.length; i++) {
            for (int j = 3; j < gameBoard[0].length; j++) {
                if (gameBoard[i][j] == 1 && gameBoard[i - 1][j - 1] == 1 && gameBoard[i - 2][j - 2] == 1 && gameBoard[i - 3][j - 3] == 1)
                    return 1;
                if (gameBoard[i][j] == 2 && gameBoard[i - 1][j - 1] == 2 && gameBoard[i - 2][j - 2] == 2 && gameBoard[i - 3][j - 3] == 2)
                    return 2;
            }
        }


        return 0;
    }


    public int getMove() {
        int[][] copyGameBoard = gameBoard;

        //checks to counter one specific move
        if (copyGameBoard[5][3] == 1 && copyGameBoard[5][4] == 0) {
            return 4;
        }

        //checks to see if any wins are possible
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                // System.out.println(r + " " + c);
                if (copyGameBoard[r][c] == 0) {
                    copyGameBoard[r][c] = 2;
                    if (checkWinPC(copyGameBoard)) {
                        copyGameBoard[r][c] = 0;
                        return c;
                    }
                    copyGameBoard[r][c] = 0;
                }
            }
        }

        //checks to see if any blocks are possible
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                // System.out.println(r + " " + c);
                if (copyGameBoard[r][c] == 0) {
                    copyGameBoard[r][c] = 1;
                    if (checkWinPC(copyGameBoard)) {
                        copyGameBoard[r][c] = 0;
                        return c;

                    }
                    copyGameBoard[r][c] = 0;
                }
            }
        }


        //middle move strategy
        if (checkColumn(3) && winnable(3)) {
            return 3;
        }
        if (checkColumn(4) && winnable(4)) {
            return 4;
        }
        if (checkColumn(2) && winnable(2)) {
            return 2;
        }
        if (checkColumn(5) && winnable(5)) {
            return 5;
        }
        if (checkColumn(1) && winnable(1)) {
            return 1;
        }
        if (checkColumn(6) && winnable(6)) {
            return 6;
        }
        if (checkColumn(0) && winnable(0)) {
            return 0;
        }
        if (checkColumn(3)) {
            return 3;
        }
        if (checkColumn(4)) {
            return 4;
        }
        if (checkColumn(2)) {
            return 2;
        }
        if (checkColumn(5)) {
            return 5;
        }
        if (checkColumn(1)) {
            return 1;
        }
        if (checkColumn(6)) {
            return 6;
        }
        if (checkColumn(0)) {
            return 0;
        }

        return -1;
    }


    //sees if column is full
    public boolean checkColumn(int col) {
        for (int row = 0; row < gameBoard.length; row++) {
            if (gameBoard[row][col] == 0) {
                return true;
            }
        }
        return false;
    }

    //sees if current placement is winnable
    public boolean winnable(int col) {
        for (int row = gameBoard.length - 1; row >= 0; row--) {
            System.out.println("Row " + row);
            if (row < 3 && (gameBoard[row][col] == 0 && gameBoard[row + 1][col] == 0 && gameBoard[row + 2][col] == 0 && gameBoard[row + 3][col] == 0 || gameBoard[row][col] == 2 && gameBoard[row + 1][col] == 0 && gameBoard[row + 2][col] == 0 && gameBoard[row + 3][col] == 0)) {
                return true;
            }
        }
        return false;
    }

}







