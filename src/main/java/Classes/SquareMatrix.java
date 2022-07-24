package Classes;

import java.util.LinkedList;
import java.util.Random;

public class SquareMatrix {
//    private Hashtable<Integer, int[][]> subMatrixes;
    private int[][] matrix;
    private int nLines;
    Random random;

    public SquareMatrix (Difficulty difficulty, int seed){
        random = new Random(seed);
        boolean success;

        if(difficulty == Difficulty.EASY) { //9x9 Matrix
            this.nLines = 9;

            matrix = new int[nLines][nLines];
            for (int i = 0; i < nLines; i++) {
                for (int j = 0; j < nLines; j++) {
                    matrix[i][j] = 0;
                }
            }

            //Diagonal Matrixes - independent matrixes
            fillDiagonalMatrixes();

            //Other Matrixes
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
            System.out.println("--------LINE " + i + "----------");
            for (int j = minColumn; j < minColumn+3; j++) {
                System.out.println("............COLUMN " + j + "............");
                do {
                    number = random.nextInt(9) + 1;
                    isNumberValid = !hasNumberIn3x3Matrix(i, j, number);
                    if (isNumberValid) {
                        matrix[i][j] = number;
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
            System.out.println("--------LINE " + i + "----------");
            for (int j = minColumn; j < minColumn+3; j++) {
                System.out.println("............COLUMN " + j + "............");
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
                        matrix[i][j] = number;
                        System.out.println(number);
                    }
                    testedNumbers.add(number);
                } while (!isNumberValid && testedNumbers.size()!=8);
                if(!isNumberValid){
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
                matrix[i][j] = 0;
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
                if(matrix[i][j] == number){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasNumberInLine(int line, int number){
        for (int i = 0; i < nLines; i++) {
            if(matrix[line][i] == number){
                return true;
            }
        }
        return false;
    }

    private boolean hasNumberInColumn(int column, int number){
        for (int i = 0; i < nLines; i++) {
            if(matrix[i][column] == number){
                return true;
            }
        }
        return false;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
