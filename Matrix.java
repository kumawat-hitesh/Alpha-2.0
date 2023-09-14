public class Matrix {

    // Spiral Matrix
    public static void spiralMatrix(int matrix[][]) {
        int startRow = 0;
        int startColumn = 0;
        int endRow = matrix.length - 1;
        int endColumn = matrix[0].length - 1;

        while (startRow <= endRow && startColumn <= endColumn) {
            // top
            for (int j = startColumn; j <= endColumn; j++) {
                System.out.print(matrix[startRow][j] + " ");
            }
            // right
            for (int i = startRow + 1; i <= endRow; i++) {
                System.out.print(matrix[i][endColumn] + " ");
            }
            // bottom
            for (int j = endColumn - 1; j >= startColumn; j--) {
                if (startRow == endRow) {
                    break;
                }
                System.out.print(matrix[endRow][j] + " ");
            }
            // left
            for (int i = endRow - 1; i >= startRow + 1; i--) {
                if (startColumn == endColumn) {
                    break;
                }
                System.out.print(matrix[i][startColumn] + " ");
            }
            startRow++;
            startColumn++;
            endRow--;
            endColumn--;
        }
    }

    // Sorted matrix search
    public static boolean sortedMatrixSearch(int matrix[][], int key) {
        int row = matrix.length - 1, col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (key == matrix[row][col]) {
                System.out.print("Key found at (" + row + "," + col + ")");
                return true;
            } else if (key > matrix[row][col]) {
                col++;
            } else {
                row--;
            }
        }
        System.out.println("Key not found!");
        return false;
    }

    // Diagonal Sum
    public static int diagonalSum(int matrix[][]) {
        int sum = 0;

        // brute force method
        // for (int i = 0; i < matrix.length; i++) { //it has time complexity O(n^2)
        // for (int j = 0; j < matrix[0].length; j++) {
        // if (i == j) {
        // sum += matrix[i][j];
        // }
        // else if (i + j == matrix.length-1) {
        // sum += matrix[i][j];
        // }
        // }
        // }

        for (int i = 0; i < matrix.length; i++) {
            // pd
            sum += matrix[i][i];
            // sd
            if (i != matrix.length - 1 - i)
                sum += matrix[i][matrix.length - 1 - i];
        }
        return sum;
    }

    public static void main(String[] args) {

        // Spiral Matrix
        // try (Scanner sc = new Scanner(System.in);) {
        // System.out.print("Enter row number : ");
        // int n = sc.nextInt();
        // System.out.print("Enter column number : ");
        // int m = sc.nextInt();
        // int matrix[][] = new int[n][m];
        // for (int i = 0; i < matrix.length; i++) {
        // for (int j = 0; j < matrix[0].length; j++) {
        // matrix[i][j] = sc.nextInt();
        // }
        // }

        // int matrix[][] = { { 10, 20, 30, 40 }, { 155, 25, 35, 45 }, { 27, 29, 37, 48
        // }, { 32, 33, 39, 50 } };
        // int key = 33;
        // sortedMatrixSearch(matrix, key);

        // diagonal sum
        // int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13,
        // 14, 15, 16 } };
        // int ds = diagonalSum(matrix);
        // System.out.println(ds);

    }
}
