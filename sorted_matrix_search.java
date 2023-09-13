public class sorted_matrix_search {
    public static boolean sortedMatrixSearch(int matrix[][], int key) {
        int row = matrix.length - 1, col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (key == matrix[row][col]) {
                System.out.print("Key found at ("+row+","+col+")");
                return true;
            }
            else if (key > matrix[row][col]) {
                col++;
            }
            else{
                row--;
            }
        }
        System.out.println("Key not found!");
        return false;

    }

    public static void main(String[] args) {
        int matrix[][] = { { 10, 20, 30, 40 }, { 155, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };
        int key = 33;
        sortedMatrixSearch(matrix, key);
    }
}
