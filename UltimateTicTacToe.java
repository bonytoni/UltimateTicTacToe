//TicTacToe.java is needed for the game to work
//Keiry Gao and Tony Chen Period 8 APCS Ms. Qiu
import java.util.Scanner;
public class UltimateTicTacToe{
  private static TicTacToe[] collection;
  private static char[] gameStatus;
  private static char player;
  public static void main(String[] args){
    Scanner kb = new Scanner(System.in);
    collection = new TicTacToe[9];
    gameStatus = new char[9];
    player = 'X';
    for (int i = 0; i < 9; i++){
      collection[i] = new TicTacToe();
      collection[i].setUp();
      gameStatus[i] = '-';
    }
    printBoardGame();
    System.out.println("These are the positions of the game.");
    System.out.println("0 1 2");
    System.out.println("3 4 5");
    System.out.println("6 7 8");
    System.out.println();
    System.out.println("Player " + player + ", please enter the number corresponding to the board that you want to start.");
    int boardNum = kb.nextInt();
    System.out.println("Please enter the number corresponding to the position on the board.");
    int posNum = kb.nextInt();
    collection[boardNum].setPosition(posNum, player);
    boardNum = posNum;
    printBoardGame();
    switchPlayer();
    boolean freebie = false;
    while (!checkGSWin()){
      if (freebie == true){
        System.out.println("Player " + player + ", you can place anywhere on the board that is available.");
        boardNum = kb.nextInt();
        while (gameStatus[boardNum] != '-'){
          System.out.println("Player " + gameStatus[boardNum] + " has already won this board. Choose another board to place your " + player);
          boardNum = kb.nextInt();
        }
        freebie = false;
      }
      System.out.println("Player " + player + ", please enter the number corresponding to the position that you want to place.");
      posNum = kb.nextInt();
      while(collection[boardNum].checkTaken(player, posNum)){
        System.out.println("The position you have entered is already taken. Please try again.");
        posNum = kb.nextInt();
      }
      collection[boardNum].setPosition(posNum, player);
      printBoardGame();
      updateGameStatus();
      printGameStatus();
      if (checkGSWin()){
        System.out.println("GG! Player " + player + " has won the game!");
        break;
      }
      if (checkGSTie()){
        System.out.println("Tie. No one won.");
        break;
      }
      boardNum = posNum;
      if (gameStatus[boardNum] != '-'){
        freebie = true;
      }
      switchPlayer();
    }
    kb.close();
  }
  public static void printBoardGame(){
    for (int i = 0; i < 9; i+=3){
      for (int j = 0; j < 9; j+=3){
        System.out.print(collection[i].getBoard()[j] + "|" + collection[i].getBoard()[j+1] + "|" + collection[i].getBoard()[j+2]);
        System.out.print("    ");
        System.out.print(collection[i+1].getBoard()[j] + "|" + collection[i+1].getBoard()[j+1] + "|" + collection[i+1].getBoard()[j+2]);
        System.out.print("    ");
        System.out.print(collection[i+2].getBoard()[j] + "|" + collection[i+2].getBoard()[j+1] + "|" + collection[i+2].getBoard()[j+2]);
        System.out.println();
      }
      System.out.println();
    }
  }
  public static void switchPlayer(){
    if (player == 'X')
      player = 'O';
    else
      player = 'X';
  }
  public static void updateGameStatus(){
    for (int i = 0; i < 9; i++){
      if (collection[i].checkWin() && gameStatus[i] == '-')
        gameStatus[i] = player;
    }
  }
  public static void printGameStatus(){
    System.out.println("Current Game Status");
    for (int i = 0; i < 9; i+=3){
      System.out.println(gameStatus[i] + " " + gameStatus[i+1] + " " + gameStatus[i+2]);
    }
  }
  public static boolean checkDash(char a, char b, char c){
    return ((a != '-') && (a == b) && (b == c));
  }
  public static boolean checkGSRows(){
    for (int i = 0; i < 9; i+=3){
      if (checkDash(gameStatus[i], gameStatus[i+1], gameStatus[i+2])){
      return true;
      }
    }
    return false;
  }
  public static boolean checkGSColumns(){
    for (int i = 0; i < 3; i++){
      if (checkDash(gameStatus[i], gameStatus[i+3], gameStatus[i+6])){
      return true;
      }
    }
    return false;
  }
  public static boolean checkGSDiagonals(){
    return (checkDash(gameStatus[0], gameStatus[4], gameStatus[8]) || checkDash(gameStatus[2], gameStatus[4], gameStatus[6]));
  }
  public static boolean checkGSWin(){
    return (checkGSRows() || checkGSColumns() || checkGSDiagonals());
  }
  public static boolean checkGSTie(){
    int count = 0;
    for (int i = 0; i < 9; i++){
      if (gameStatus[i] == 'X' || gameStatus[i] == 'O'){
        count++;
      }
    }
    return (count == 9);
  }
}
