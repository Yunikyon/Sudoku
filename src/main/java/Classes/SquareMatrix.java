package Classes;

import java.util.LinkedList;
import java.util.Random;

public class SquareMatrix {
//    private Hashtable<Integer, int[][]> subMatrixes;
    private int[][] unsolvedPuzzle;
    private int[][] solution;
    private int nLines;
    private int removedNumbers;
    Random random;

    public SquareMatrix (Difficulty difficulty, int seed){
        random = new Random(seed);
        this.nLines = 9;

        switch (difficulty){
            case EASY:
                removedNumbers = 35;
                break;
            case MEDIUM:
                removedNumbers = 50;
                break;
            default: // HARD
                removedNumbers = 70;
                break;
        }

        unsolvedPuzzle = new int[nLines][nLines];
        solution = new int[nLines][nLines];
        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nLines; j++) {
                solution[i][j] = 0;
            }
        }

        //Diagonal Matrixes - independent matrixes
        fillDiagonalMatrixes();

        //Other Matrixes
        fillOtherMatrixes();

        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nLines; j++) {
                unsolvedPuzzle[i][j] = solution[i][j];
            }
        }

        for (int i = 0; i < removedNumbers; i++) {
            int row = random.nextInt(9);
            int column = random.nextInt(9);

            if(unsolvedPuzzle[row][column]!=0){
                unsolvedPuzzle[row][column] = 0;
            }else{
                i--;
            }
        }


    }

    private void fillOtherMatrixes() {
        boolean success;
        boolean found = false;
        do {
            //Second Matrix
            do {
                success = fillSubMatrix(0, 3);
            } while (!success);

            //Third Matrix
            do {
                success = fillSubMatrix(0, 6);
            } while (!success);

            //Sixth Matrix
            do {
                success = fillSubMatrix(3, 6);
            } while (!success);

            //Fourth Matrix
            do {
                success = fillSubMatrix(3, 0);
            } while (!success);

            //Seventh Matrix
            do {
                success = fillSubMatrix(6, 3);
            } while (!success);

            int tries = 0;
            //Eighth Matrix
            do {
                tries++;
                success = fillSubMatrix(6, 0);
            } while (!success && tries!=8);

            if(tries==8 && success){
                found = true;
            }
        }while (!found);
    }

    private void fillDiagonalMatrixes() {
        fillDiagonalSubMatrix(0, 0);

        //Fifth Matrix
        fillDiagonalSubMatrix(3, 3);

        //Ninth Matrix
        fillDiagonalSubMatrix(6, 6);
    }

    private void fillDiagonalSubMatrix(int minRow, int minColumn){
        boolean isNumberValid;
        int number;

        for (int i = minRow; i < minRow+3; i++) {
//            System.out.println("--------LINE " + i + "----------");
            for (int j = minColumn; j < minColumn+3; j++) {
//                System.out.println("............COLUMN " + j + "............");
                do {
                    number = random.nextInt(9) + 1;

                    isNumberValid = !hasNumberIn3x3Matrix(i, j, number);

                    if (isNumberValid) {
                        solution[i][j] = number;
                    }
                } while (!isNumberValid);
            }
        }
    }


    private boolean fillSubMatrix(int minRow, int minColumn){
        boolean isNumberValid;
        int number;
        LinkedList<Integer> testedNumbers;

        for (int i = minRow; i < minRow+3; i++) {
//            System.out.println("--------LINE " + i + "----------");
            for (int j = minColumn; j < minColumn+3; j++) {
//                System.out.println("............COLUMN " + j + "............");
                testedNumbers = new LinkedList<>();

                do {
                    number = random.nextInt(9) + 1;

                    if(testedNumbers.contains(number)){
                        isNumberValid = false;
                        continue;
                    }

                    isNumberValid = !hasNumberInLine(i, number) && !hasNumberInColumn(j, number)
                            && !hasNumberIn3x3Matrix(i, j, number);

                    if (isNumberValid) {
                        solution[i][j] = number;
                        System.out.println(number);
                    }

                    testedNumbers.add(number);
                } while (!isNumberValid && testedNumbers.size()!=8);

                if(!isNumberValid){ // if the last cell of the 3x3 matrix can't be filled
                    resetSubMatrix(minRow, minColumn);
                    return false;
                }
            }
        }
        return true;
    }

    private void resetSubMatrix(int minRow, int minColumn) {
        for (int i = minRow; i < minRow+3; i++) {
            for (int j = minColumn; j < minColumn+3; j++) {
                solution[i][j] = 0;
            }
        }
    }

    private boolean hasNumberIn3x3Matrix(int row, int column, int number){ //9x9 M
        if(row < 3){
            if(column < 3){
                return findInMatrix(number, 0, 0);
            }else if(column < 6){
                return findInMatrix(number, 0, 3);
            }else{
                return findInMatrix(number, 0, 6);
            }
        }
        else if(row < 6){
            if(column < 3){
                return findInMatrix(number, 3, 0);
            }else if(column < 6){
                return findInMatrix(number, 3, 3);
            }else{
                return findInMatrix(number, 3, 6);
            }
        }else{
            if(column < 3){
                return findInMatrix(number, 6, 0);
            }else if(column < 6){
                return findInMatrix(number, 6, 3);
            }else{
                return findInMatrix(number, 6, 6);
            }
        }
    }

    private boolean findInMatrix(int number, int minRow, int minColumn) {
        for (int i = minRow; i < minRow+3; i++) {
            for (int j = minColumn; j < minColumn+3; j++) {
                if(solution[i][j] == number){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasNumberInLine(int line, int number){
        for (int i = 0; i < nLines; i++) {
            if(solution[line][i] == number){
                return true;
            }
        }
        return false;
    }

    private boolean hasNumberInColumn(int column, int number){
        for (int i = 0; i < nLines; i++) {
            if(solution[i][column] == number){
                return true;
            }
        }
        return false;
    }

    public int[][] getSolution() {
        return solution;
    }

    public int[][] getUnsolvedPuzzle() {
        return unsolvedPuzzle;
    }

    public int getRemovedNumbers() {
        return removedNumbers;
    }
}
