import java.util.Scanner;

public class sudoku {

    private static final int GRID_SIZE = 9;
    private static final int EMPTY_CELL = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        System.out.println("Enter the Sudoku puzzle row by row (use 0 for empty cells):");
        for (int r = 0; r < GRID_SIZE; r++) {
            System.out.print("Row " + (r + 1) + ": ");
            for (int c = 0; c < GRID_SIZE; c++) {
                board[r][c] = scanner.nextInt();
            }
        }
        scanner.close();

        System.out.println("\nOriginal Sudoku Puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku Puzzle:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku puzzle.");
        }
    }

    // Solves the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] board) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (board[r][c] == EMPTY_CELL) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, r, c, num)) {
                            board[r][c] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[r][c] = EMPTY_CELL; // Backtrack
                            }
                        }
                    }
                    return false; // No valid number found for this cell
                }
            }
        }
        return true; // Puzzle solved
    }

    // Checks if placing 'num' at (row, col) is valid
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int c = 0; c < GRID_SIZE; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // Check column
        for (int r = 0; r < GRID_SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[startRow + r][startCol + c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Prints the Sudoku board
    public static void printBoard(int[][] board) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                System.out.print(board[r][c] + " ");
                if ((c + 1) % 3 == 0 && c != GRID_SIZE - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((r + 1) % 3 == 0 && r != GRID_SIZE - 1) {
                System.out.println("---------------------");
            }
        }
    }
}