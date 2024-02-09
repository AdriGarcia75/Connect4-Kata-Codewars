public class Connect4 {
    /*thx to my friend Omegui 4 helping me with the diagonals, chad*/
    //we define our grid
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    //this boolean will work as our controller everytime we have to check if the match finished or not
    private boolean hasFinished;
    //this integer will control the playerTurn (1 player1, 2 player2)
    private int playerTurn;
    //and this is our grid
    private int grid[][];

    //here we have the constructor that initiates what we need to start the game
    public Connect4() {
        hasFinished = false;
        playerTurn = 1;
        grid = new int[ROWS][COLUMNS];
    }

    //we check the rows to determine if someone won
    private boolean checkRow(int row) {
        int cont = 0;
        int j = 0;
        while(j < COLUMNS) {
            if (grid[row][j] == playerTurn) cont++;
            else cont = 0;
            if (cont == 4) return true;
            j++;
        }
        return false;
    }
  
    //we check the columns to determine if someone won
    private boolean checkColumn(int column) {
        int cont = 0;
        int i = 0;
        while (i < ROWS) {
            if (grid[i][column] == playerTurn) cont++;
            else cont = 0;
            if (cont == 4) return true;
            i++;
        }
        return false;
    }
  
    //we check both diagonals to determine if someone won
    private boolean checkDiagonals(int row, int column) {
        int i, j, cont;
        // we check left diagonal
        cont = 1;
        i = row; j = column;
        while(i >= 0 && j >= 0 && grid[i][j] == playerTurn) {
            if(i != row) cont++;
            i--; j--;
        }

        i = row; j = column;
        while (i < ROWS && j < COLUMNS && grid[i][j] == playerTurn) {
            if (i != row) cont++;
            i++; j++;
        }
        if (cont >= 4) return true;
      
        // we check right diagonal
        cont = 1;
        i = row; j = column;
        while (i >= 0 && j < COLUMNS && grid[i][j] == playerTurn) {
            if (i != row) cont++;
            i--; j++;
        }

        i = row; j = column;
        while (i < ROWS && j >= 0 && grid[i][j] == playerTurn) {
            if (i != row) cont++;
            i++; j--;
        }
        //if we encountered four pieces we return true, also has to be greater than because sometimes we can get a Connect 5, that should be true 
        if (cont >= 4) return true;
        //if not
        return false;
    }
    //method to return our final bool (for every placement, if we basicaly connected 4 or not)
    private boolean chequearFin(int row, int column) {
        return checkRow(row) || checkColumn(column) || checkDiagonals(row, column);
    }

    //"main" method, play method, we get the column we have to be adding the piece 
    public String play(int column) {
        //in case game has already finished 
        if (hasFinished) return "Game has finished!";
        //in case column is full
        if (grid[0][column]!=0) return "Column full!";
        int row = 0;
        int i = ROWS-1;
        boolean placedPiece = false;
        while( i >= 0 && !placedPiece) {
            if (grid[i][column]==0) {
                grid[i][column] = playerTurn;
                row = i;
                placedPiece = true;
            }
            i--;
        }
      
      //check if its going to win, if true we return player + the asked line
      if (chequearFin(row, column)) {
          hasFinished = true;
          return "Player " + playerTurn + " wins!";
      }

      //if not, we build our message, change turn and then we return the message containing the last player
      String returnString = "Player " + playerTurn + " has a turn";
      if (playerTurn==1) playerTurn = 2;
      else playerTurn = 1;
      return returnString;
    }
}
