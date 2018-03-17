import java.io.IOException;
import java.io.PrintWriter;
public class NQueens {

	//main method
	public static void main(String[] args) throws IOException {
		// check number of command line arguments is at least 3
	    if(args.length < 3){
	    	System.out.println("Usage: FileCopy <n> <x-coordinate> <y-coordinate>");
	        System.exit(1);
	    }
		//allows the user to input a value for n
		//this program can handle n values such that n = [0,15]
		int n = Integer.parseInt(args[0]);
		int initialQueenColumn = Integer.parseInt(args[1]) - 1;
		int initialQueenRow = Integer.parseInt(args[2]) - 1;
		int[][] board = new int [n][n];
		int column = 0;
		if(initialQueenColumn == 0) column ++;
		//initializes and prints an open nxn board
		initializeBoard(board, n, initialQueenColumn, initialQueenRow);
		//calls a recursion loop
		boolean solved = placeQueen(board, n, column, initialQueenColumn);
		//prints to solution.txt
		PrintWriter out = new PrintWriter("solution.txt");
		//prints coordinates
		if(solved) printCoordinates(board, n, initialQueenRow, initialQueenColumn, out);
		//prints "No solution"
		else out.println("No solution");
		out.close();
	}

	//creates the board and places the initial queen
	public static void initializeBoard(int[][] board, int n, int initialQueenColumn, int initialQueenRow){
		//sets all spaces to 0
		for(int row = 0; row<n; row++){
			for(int column = 0; column <n; column ++){
				//places a queen on the initial coordinates entered by the user
				if(row == initialQueenRow && column == initialQueenColumn){
					board[row][column] = 1;
					int counter = 0;
					int queenRow = initialQueenRow;
					int queenColumn = initialQueenColumn;
					int storeQueenRow = queenRow;
					int storeQueenColumn = queenColumn;
					//blocks the horizontals
					while(counter < n){
						if(board[queenRow][counter] == 0){
							board[queenRow][counter] = -5;
						}
						counter ++;
					}
					//blocks the verticals
					counter = 0;
					while(counter < n){
						if(board[counter][queenColumn] == 0){
							board[counter][queenColumn] = -5;
						}
						counter ++;
					}
					//blocks the increasing diagonals above the queen
					while(queenRow < n && queenColumn < n){
						if(board[queenRow][queenColumn] == 0){
							board[queenRow][queenColumn] = -5;
						}
						queenRow ++;
						queenColumn ++;
					}
					//blocks the increasing diagonals below the queen
					queenRow = storeQueenRow;
					queenColumn = storeQueenColumn;
					while(queenRow >= 0 && queenColumn >= 0){
						if(board[queenRow][queenColumn] == 0){
							board[queenRow][queenColumn] = -5;
						}
						queenRow --;
						queenColumn --;
					}
					//blocks the decreasing diagonals above the queen
					queenRow = storeQueenRow;
					queenColumn = storeQueenColumn;
					while(queenRow < n && queenColumn >= 0){
						if(board[queenRow][queenColumn] == 0){
							board[queenRow][queenColumn] = -5;
						}
						queenRow ++;
						queenColumn --;
					}
					//blocks the decreasing diagonals below the queen
					queenRow = storeQueenRow;
					queenColumn = storeQueenColumn;
					while(queenRow >= 0 && queenColumn < n){
						if(board[queenRow][queenColumn] == 0){
							board[queenRow][queenColumn] = -5;
						}
						queenRow --;
						queenColumn ++;
					}
				}
			}
		}
	}

	//prints the coordinates of all the queens on the board to solution.txt
	public static void printCoordinates(int[][] board, int n, int initialQueenRow, int initialQueenColumn, PrintWriter out){
		//prints the initial queen's coordinates
		int initialQueenX = initialQueenColumn + 1;
		int initialQueenY = initialQueenRow + 1;
		out.println(initialQueenX + " " + initialQueenY);
		//prints the rest of the queen's coordinates
		for(int column = 0; column < n; column ++){
			for(int row = 0; row < n; row ++){
				if(board[row][column] == 1){
					if(row != initialQueenRow && column != initialQueenColumn){
						int xCoordinate = column + 1;
						int yCoordinate = row + 1;
						out.println(xCoordinate + " " + yCoordinate);
					}
				}
			}
		}
	}

	//removes an incorrectly placed queen and places a new queen on the board
	public static boolean placeQueen(int[][] board, int n, int column, int initialQueenColumn){
		//base case
		if(column >= n) return true;
		//base case
		else if(column < 0) return false;
		int row = 0, counter = 0;
		int queenColumn, queenRow;
		int storeQueenColumn, storeQueenRow;
		boolean queenPlaced = false;
		//removes an incorrectly placed queen and blocks the space it was in
		for(int i=0; i<n; i++){
			if(board[i][column] == 1){
				if(column == 0) board[i][column] = -2;
				else board[i][column] = column + 1;
				//re-opens the previously blocked spaces
				for(int j = 0; j<n; j++){
					for(int k=0; k<n; k++){
						if(board[k][j] == column + 2){
							board[k][j] = 0;
						}
					}
				}
			}
		}
		while(row < n){
			//places a queen on the first available spot in the column
			if(board[row][column] == 0){
				board[row][column] = 1;
				queenPlaced = true;
				queenRow = row;
				queenColumn = column;
				storeQueenRow = queenRow;
				storeQueenColumn = queenColumn;
				//blocks the horizontals
				while(counter < n){
					if(board[queenRow][counter] == 0){
						board[queenRow][counter] = column + 2;
					}
					counter ++;
				}
				//blocks the verticals
				counter = 0;
				while(counter < n){
					if(board[counter][queenColumn] == 0){
						board[counter][queenColumn] = column + 2;
					}
					counter ++;
				}
				//blocks the increasing diagonals above the queen
				while(queenRow < n && queenColumn < n){
					if(board[queenRow][queenColumn] == 0){
						board[queenRow][queenColumn] = column + 2;
					}
					queenRow ++;
					queenColumn ++;
				}
				//blocks the increasing diagonals below the queen
				queenRow = storeQueenRow;
				queenColumn = storeQueenColumn;
				while(queenRow >= 0 && queenColumn >= 0){
					if(board[queenRow][queenColumn] == 0){
						board[queenRow][queenColumn] = column + 2;
					}
					queenRow --;
					queenColumn --;
				}
				//blocks the decreasing diagonals above the queen
				queenRow = storeQueenRow;
				queenColumn = storeQueenColumn;
				while(queenRow < n && queenColumn >= 0){
					if(board[queenRow][queenColumn] == 0){
						board[queenRow][queenColumn] = column + 2;
					}
					queenRow ++;
					queenColumn --;
				}
				//blocks the decreasing diagonals below the queen
				queenRow = storeQueenRow;
				queenColumn = storeQueenColumn;
				while(queenRow >= 0 && queenColumn < n){
					if(board[queenRow][queenColumn] == 0){
						board[queenRow][queenColumn] = column + 2;
					}
					queenRow --;
					queenColumn ++;
				}
			}
			row ++;
		}
		//increases the column if a queen was placed
		if(queenPlaced) {
			column ++;
			//skips the initial queen column if necessary
			if(column == initialQueenColumn) column ++;
		}
		//decreases the column if a queen was removed
		else {
			column --;
			//skips the initial queen column if necessary
			if(column == initialQueenColumn) column --;
		}
		//recursive method calls itself
		return placeQueen(board, n,  column, initialQueenColumn);
	}
}