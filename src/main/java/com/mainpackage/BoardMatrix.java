// matrix class used to track positions and check win condition
// credit https://stackoverflow.com/questions/32770321/connect-4-check-for-a-win-algorithm

package com.mainpackage;

public class BoardMatrix {

    public static int[][] getBoardPiecePositionMatrix() {
        return boardPiecePositionMatrix;
    }

    public static void setBoardPiecePositionMatrix(int i, int j, int player) {
        previousBoardPiecePositionMatrix = boardPiecePositionMatrix;
        boardPiecePositionMatrix[i][j] = player;
    }

    public static void setFullBoardPiecePositionMatrix(int [][] matrix) {
        boardPiecePositionMatrix = matrix;
    }

    public static void setZero(){
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                boardPiecePositionMatrix[i][j] = 0;
            }
        }
    }

    // A 14x14 matrix denoting positions of pieces on the board.
    // 0 corresponds to no piece
    // 1 corresponds to white piece
    // -1 corresponds to black piece
    // This matrix can be used to check for any 5 in a row
    private static int[][] boardPiecePositionMatrix = new int[15][15];
    private static int[][] previousBoardPiecePositionMatrix = new int[15][15];


    public static void printMatrix(){
        System.out.println("Board:");
        int size = boardPiecePositionMatrix.length;
        int[][] matrix = Transpose(boardPiecePositionMatrix);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    public static int[][] Transpose(int[][] matrix){
        int n = matrix.length;
        int[][] transpose = new int[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }


    public static boolean checkFiveInRow(int player){

        // Five in a row - 1
        final int N=4;

        // 1 for white, -1 for black. Temporary here for now. Later, pass player as an argument
        // into this method

        int boardLength = boardPiecePositionMatrix.length;

        // horizontalCheck
        for (int j = 0; j<boardLength-N; j++ ){
            for (int i = 0; i<boardLength; i++){
                if (boardPiecePositionMatrix[i][j] == player &&
                        boardPiecePositionMatrix[i][j+1] == player &&
                        boardPiecePositionMatrix[i][j+2] == player &&
                        boardPiecePositionMatrix[i][j+3] == player &&
                        boardPiecePositionMatrix[i][j+4] == player){
                    return true;
                }
            }
        }
        // verticalCheck
        for (int i = 0; i<boardLength-N ; i++ ){
            for (int j = 0; j<boardLength; j++){
                if (boardPiecePositionMatrix[i][j] == player &&
                        boardPiecePositionMatrix[i+1][j] == player &&
                        boardPiecePositionMatrix[i+2][j] == player &&
                        boardPiecePositionMatrix[i+3][j] == player &&
                        boardPiecePositionMatrix[i+4][j] == player){
                    return true;
                }
            }
        }
        // ascendingDiagonalCheck
        for (int i=N; i<boardLength; i++){
            for (int j=0; j<boardLength-N; j++){
                if (boardPiecePositionMatrix[i][j] == player &&
                        boardPiecePositionMatrix[i-1][j+1] == player &&
                        boardPiecePositionMatrix[i-2][j+2] == player &&
                        boardPiecePositionMatrix[i-3][j+3] == player &&
                        boardPiecePositionMatrix[i-4][j+4] == player) {
                    return true;
                }
            }
        }
        // descendingDiagonalCheck
        for (int i=N; i<boardLength; i++){
            for (int j=N; j<boardLength; j++){
                if (boardPiecePositionMatrix[i][j] == player &&
                        boardPiecePositionMatrix[i-1][j-1] == player &&
                        boardPiecePositionMatrix[i-2][j-2] == player &&
                        boardPiecePositionMatrix[i-3][j-3] == player &&
                        boardPiecePositionMatrix[i-4][j-4] == player){
                    return true;
                }
            }
        }
        return false;
    }

    public Memento createState(){
        return new Memento(BoardMatrix.getBoardPiecePositionMatrix());
    }

    public static void restore(Memento state){
        setFullBoardPiecePositionMatrix(state.getContent());
    }
}


