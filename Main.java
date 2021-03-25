/* Names: Athena Jacob and Victoria Macali
 Date: 2/3/2021
 Description: In this lab we created a program that simulates a game similar to Battleship. The user decides 
 if they would first like to play the game, then enters a row and column to place their shot.  The user 
 continues to play until they have hit the ship at all four spots, which means they have won the game. */
 
 import java.util.Scanner;
 import java.util.Random;
 class Main {

  /* This is the function where we randomly place a 2 by 2 ship on our board. 
  */
  public static void placeShip( String [][] solution){
      Random randomSpace = new Random();
      /* This will create a Row and Column randomly between the numbers 0 to 4. */
      int ROW = randomSpace.nextInt(3);
      int COL = randomSpace.nextInt(3);
      /* The code below will then place our ship utilizing "*" as the symbols representing it. */
      solution[ROW][COL]="*";
      solution[ROW][COL+1]="*";
      solution[ROW+1][COL]="*";
      solution[ROW+1][COL+1]="*";     
  }
 
  /* This is the function that will display our Battleship board where it first gives us our Columns (1-5). It also adds spaces between each number to make them appear on one line in our output. */
  public static void DisplayBoard(String [][] board){
      /* This is where our 2D array will get printed as I said above. */
      System.out.println("\n");
      System.out.println(" 1 2 3 4 5");
      for(int i=0;i<5;i++){
          char format=(char)(65+i);
          System.out.print(format + " ");
          for(int j=0;j<5;j++){
             System.out.print(board[i][j] + " ");
          }
          System.out.println("");
      }
  }
 
  /* This function is where our Row (A-E) will be created and also validated. We ask the user for a letter  between A through E where we make sure its in between those two characters and that it doesn't matter regarding capitalization. */
  public static int getRow(){
      Scanner sc = new Scanner(System.in);
      String userChar;
      int ROW;
      /* This is where the input for the Row will be requested from the user. */
      System.out.print("Enter a Row(A-E): ");
      userChar = sc.nextLine();

      /* checks the user's input for capitalization and it meets between the respected characters (A-E). */
      while(true){
          if(userChar.equals("A") || userChar.equals("a")){
              ROW=0;
              break;
          } else if(userChar.equals("B") || userChar.equals("b")){
              ROW=1;
              break;
          } else if(userChar.equals("C") || userChar.equals("c")){
              ROW=2;
              break;
          } else if(userChar.equals("D") || userChar.equals("d")){
              ROW=3;
              break;
          } else if(userChar.equals("E") || userChar.equals("e")){
              ROW=4;
              break;
          } else {
              System.out.println("Invalid input. Try again");
              userChar = sc.nextLine();
          }
      }
      return ROW;
  }
 
  /* This function asks the user for valid entires ranging from 1-5. If the user has 
  entered an incorrect entry, it will prompt the user to try again. It then returns 
  a valid the column entry to the fireShot() function to place the user's guess */
  
  public static int getCol(){
      Scanner sc = new Scanner(System.in);
      int COL;
      System.out.print("Enter a Column(1-5): ");
      COL = sc.nextInt();
      // The while loop runs until a valid input is given from the user.
      while(true){
          if(COL >= 1 && COL <= 5){// Checks if the user's guess is within the correct range.
            break;
          } else {
              System.out.print("Invalid input. Try again ");
              COL = sc.nextInt(); // takes the user's next guess, loops until valid
          }
      }
      return COL-1;
  }
 
  /* This method uses the input given by getCol() and getRow() to fire a shot at the user's
  coordinates. It will place either an 'x' or '*' at the corresponding spot and check if the 
  area has already been hit by the user */
  
  public static int fireShot(String [][] solution , String [][] board , int ROW , int COL ){
      int target=0;
      String shot1 = solution[ROW][COL];
      String shot2 = board[ROW][COL];
     
      if(shot2.equals("~")){   
          if(shot1.equals("*")) {
              target++;
              board[ROW][COL]="*"; /* If the ship is hit, it will be represented with an "*". If the shot misses, it will be represented with an "x". */
          } else {
              board[ROW][COL]="x";
          }
      } else {
          System.out.println("Area has already been hit.");
      }
      return target; 
  }
 
  /* This method is used to print the menu and get a selected option from the user. The menu 
  has only two options of "Fire Shot" and "Quit". */
  
  public static int menu(){
      Scanner sc = new Scanner(System.in);
      int userInput;
      System.out.println("\nMenu: ");
      System.out.println("1. Fire Shot");
      System.out.println("2. Quit ");
      userInput = sc.nextInt(); // this recieves only an integer from the user to decide if they want to play or quit
     
      while (userInput>0) { // This loops while the user's input is a non-negative integer 1 or 2. 
          if(userInput>3) { // If the user's choice is greater than the options, it will be deemed invalid.
              System.out.print("Invalid input . Please try again\n");
              System.out.println("\nMenu : ");
              System.out.println("1. Fire Shot");
              System.out.println("2. Quit ");      
              userInput = sc.nextInt();
          } else {
              return userInput;
          }
      }
      return 0;
  }
 
  /* The main function is where everything is called in order for the game to run.*/

  public static void main(String arg[]){  
      Scanner sc = new Scanner(System.in);
      int userChoice=1;   
      // Uses a while loop to play as long as the user inputs a 1. 
      while(userChoice==1){
          // This section of the code intializes the 2D array. 
          String[][] board = new String[5][5];
          String[][] solution = new String[5][5];
          int ROW=0,COL=0,target=0;
          for(int i=0;i<5;i++){
              for(int j=0;j<5;j++){
                  board[i][j]="~";
                  solution[i][j]= "~";
              }
          }
          int userInput;
          //The placeShip method is called in order to generate the solution. 
          placeShip(solution);
          // This next block of code calls the menu, DisplayBoard, and getRow, and getCol methods. 
          userInput = menu();
          DisplayBoard(board);
          ROW = getRow();
          COL = getCol();
          //  The while loop calls menu method until user won the game. 
          while(userInput>0){
              if(userInput==1){ 
                  if(target !=4){
                      // The fireShot method is called and returns either a 0 or 1 to determine 
                      // if each shot is a hit or a miss. 
                      int hit = fireShot(solution,board,ROW,COL);
                      target = target + hit;
                      DisplayBoard(board);
                      ROW=getRow();
                      COL=getCol();
                  }                
              } else {
                  // exits the program
                  break;
              }
         // If the user successfully hits all 4 target placeholders, they have won the game! 
       if(target==4){
         System.out.println("You Won!");
         break;
       }    
          userInput=menu();     
          }
          System.out.println("\nWould you like to play again?");
          System.out.println("1. Play Again");
          System.out.println("2. Quit)");
          userChoice=sc.nextInt();
      }
  }
 }
