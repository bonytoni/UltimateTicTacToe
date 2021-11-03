public class TicTacToe {
    private char[] board;

    public TicTacToe() {
        board= new char[9];
    }

    public char[] getBoard() {
        return board;
    }

    public void setUp() {
        for (int i= 0; i < 9; i++ ) {
            board[i]= '-';
        }
    }

    public boolean checkNoDashes(char a, char b, char c) {
        return a != '-' && a == b && b == c;
    }

    public boolean checkRows() {
        for (int i= 0; i < 9; i+= 3) {
            if (checkNoDashes(board[i], board[i + 1], board[i + 2])) { return true; }
        }
        return false;
    }

    public boolean checkColumns() {
        for (int i= 0; i < 3; i++ ) {
            if (checkNoDashes(board[i], board[i + 3], board[i + 6])) { return true; }
        }
        return false;
    }

    public boolean checkDiagonals() {
        return checkNoDashes(board[0], board[4], board[8]) ||
            checkNoDashes(board[2], board[4], board[6]);
    }

    public boolean checkWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    public boolean checkTie() {
        int count= 0;
        for (int i= 0; i < 9; i++ ) {
            if (board[i] == 'X' || board[i] == 'O') {
                count++ ;
            }
        }
        return count == 9;
    }

    public boolean checkTaken(char c, int i) {
        if (board[i] == '-') { return false; }
        return true;
    }

    public void setPosition(int num, char player) {
        board[num]= player;
    }
}