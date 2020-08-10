package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner=new Scanner(System.in);
//    char [][] ticTacToe = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'X', 'O'}};
//        //System.out.println(Arrays.toString(ticTacToe));
//        for (int i = 0; i < 3; i++){
//            for ( int j = 0; j < 3; j++) {
//                System.out.print(ticTacToe[i][j] + " ");
//            }
//            System.out.println("");
//        }
        // write your code here
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>get a string and make a char array>>>>>>>
//        //3X3 2 dimentional array getting input from user in a single
//        //line of string and breaking them and storing in char arryy
//        char [][] ticTacToe =  new char[3][3];
//        Scanner scanner = new Scanner(System.in);
//        char [] inputArray = new char[9];
//        System.out.println("Enter cells: ");
//        //reading input to a string
//        String str =  scanner.nextLine();
//        //getting chars from string in storing in char array
//        for (int i = 0; i < inputArray.length; i++) {
//            inputArray[i] = str.charAt(i);
//        }
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>get a string and make a char array>>>>>>>
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>converting char array to 3X3 array>>>>>>
        //storing char array elements into 3X3 array
//        int count = 0;
//        for (int i = 0; i < 3; i++){
//            for ( int j = 0; j < 3; j++) {
//                ticTacToe[i][j] = inputArray[count++];
//            }
//        }
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>converting char array to 3X3 array>>>>>>
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.creating empty 3X3 array for start the game
        char[][] ticTacToe = new char[3][3];
        for (int i = 0; i < 3; i++){
            for ( int j = 0; j < 3; j++) {
                ticTacToe[i][j] = '_';
            }
        }

        //printing 3X3 array in formar
        printGame(ticTacToe);
        //.................asking uesr about next move..........
        char start ='X';//X starts the game

        while(true) {
            play(scanner, ticTacToe, start);
            if(start == 'X'){
                start = 'O';
            }else if (start == 'O'){
                start = 'X';
            }
            printGame(ticTacToe);
            //.................asking uesr about next move..........

            //playing game and displaying results
            //check for x wins
            boolean xWin = checkResult('X', ticTacToe);
            //check for y wins
            boolean OWin = checkResult('O', ticTacToe);

            //check empty cells
            boolean isEmptyCells = checkEmptyCells(ticTacToe);
            //check fot x s more then o s and vice versa
            boolean moreXorO = checkForMoreXsOs(ticTacToe);
            //System.out.println(isEmptyCells);
            if (!xWin && !OWin && isEmptyCells) {
                //System.out.println("Game not finished");
                continue;
            }
            if (xWin) {
                System.out.println("X wins");
            }
            if (OWin) {
                System.out.println("O wins");
            }

            if (!xWin && !OWin && !isEmptyCells) {
                System.out.println("Draw");
            }
            if (xWin && OWin || moreXorO) {
                System.out.println("Impossible");
            }
            break;
        }
    }

    private static void play(Scanner scanner, char[][] ticTacToe, char ch) {
        int moveCoordinatei;
        int moveCoordinatej;
        while(true) {
            System.out.println("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                moveCoordinatei = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (scanner.hasNextInt()) {
                moveCoordinatej = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (moveCoordinatei < 1 || moveCoordinatei > 3 || moveCoordinatej < 1 || moveCoordinatej > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            else{

                boolean moveSuccessful = insertMove(moveCoordinatei,moveCoordinatej,ticTacToe,ch);
                if(!moveSuccessful) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

            }
         break;
        }
    }

    //insert next move fiels
    private static boolean insertMove(int moveCoordinatei, int moveCoordinatej, char[][] ticTacToe,char ch) {
        char moveFieldValue = ticTacToe[3 - moveCoordinatej][moveCoordinatei - 1];
        if(moveFieldValue=='_') {
            ticTacToe[3 - moveCoordinatej][moveCoordinatei - 1] = ch;
            return true;
        }
        return false;
        //System.out.println("insered at "+moveCoordinatei+" "+moveCoordinatej);
    }
//peinting the game in format
    private static void printGame(char[][] ticTacToe) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTacToe[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }
    //check if x s more than o s and verse versa
    public static boolean checkForMoreXsOs(char[][] array){
        int xCount=0,ocount =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == 'X') {
                    xCount++;
                }else if(array[i][j] == 'O') {
                    ocount++;
                }
            }
        }
        if(xCount > ocount+1 || ocount> xCount+1) {
            return true;
        }
        return false;
    }
    //check for empty cells
    public static boolean checkEmptyCells(char[][] array) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == '_') {
                    return true;
                }
            }
        }
        return false;
    }
    //check for wins(o or x)
    public static boolean checkResult(char ch, char[][] array) {
        int count = 0;
        int colCount =0;
        boolean result =false;
        for (int i = 0; i < 3; i++) {
            count =0;colCount=0;
            for (int j = 0; j < 3; j++) {
                //check in rowa
                if(array[i][j] == ch) {
                    count++;
                }
                //check in cols
                if(array[j][i] == ch){
                    colCount++;
                }
                if(count == 3 || colCount ==3) {
//                        System.out.println(ch + " wins");
//                        result = true;
//                        break;
                    return true;
                }
//                }else {
//                    count =0 ;
//                    colCount =0
            }
            if(result == true){
                break;
            }
        }
        //check in diagonals
        count = 0;
        int countLeftDiag =0;
        for(int i= 0;i< 3;i++){
            if(array[i][i]==ch) {
                count++;
            }
            if(array[i][3-1-i] == ch) {
                countLeftDiag++;
            }
            if(count == 3 || countLeftDiag == 3){
                return true;
            }
        }
        return false;
    }

}

