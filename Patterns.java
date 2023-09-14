public class Patterns {

    // zero one triangle
    public static void zeroOneTri(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    // hollow rectangle
    public static void holRect(int row, int col) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (i == 1 || i == row || j == 1 || j == col) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // hollow rhombus
    public static void holRho(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= n; j++) {
                if (i == 1 || i == n || j == 1 || j == n) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // inverted half pyramid
    public static void invHalPyrd(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // inverted rotated pyramid
    public static void invRot(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    // floyds triangle
    public static void floydTriangle(int n) {
        int counter = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(counter + " ");
                counter++;
            }
            System.out.println();
        }
    }

    // butterfly pattern
    public static void butterflyPattern(int n) {
        // upper half
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) { // for star
                System.out.print("*");
            }
            for (int k = 1; k <= (2 * (n - i)); k++) { // for space
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) { // for star
                System.out.print("*");
            }
            System.out.println();
        }
        // lower half
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) { // for star
                System.out.print("*");
            }
            for (int k = 1; k <= (2 * (n - i)); k++) { // for space
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) { // for star
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // diamond pattern
    public static void diamond(int n) {
        // upper half
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // lower half
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // solid rhombus pattern
    public static void rhombus(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // for (int i = 1; i <= 5; i++) {
        // for (int j = 1; j <= i; j++) {
        // System.out.print("*");
        // }
        // System.out.println();
        // }
        // for (int i = 1; i <= 5; i++) {
        // for (int j = 1; j < 5 - i + 1; j++) {
        // System.out.print("*");
        // }
        // System.out.println();
        // }

        // int size = 10;
        // for (int i = 1; i <= size; i++) {
        // for (int j = i; j <= size; j++) {
        // System.out.print(" ");
        // }
        // for (int j = 1; j <= i; j++) {
        // System.out.print("* ");
        // }
        // System.out.println();
        // }

        // for (int i = size - 1; i >= 1; i--) {
        // for (int j = i; j <= size; j++) {
        // System.out.print(" ");
        // }
        // for (int j = 1; j <= i; j++) {
        // System.out.print("* ");
        // }
        // System.out.println();
        // }

        // for (int i = 0; i < 5; i++) {
        // for (int j = 1; j <= i; j++) {
        // System.out.print(j);
        // }
        // System.out.println();
        // }

        // char ch = 'A';
        // for (int i = 0; i < 5; i++) {
        // for (int j = 0; j < i; j++) {
        // System.out.print(ch);
        // ch++;
        // }
        // System.out.println();
        // }

        // zeroOneTri(5);
        // holRect(10,20 );
        // holRho(8);
        // invHalPyrd(5);
        // invRot(4);
        // floydTriangle(10);
        // butterflyPattern(10);
        // diamond(10);
        // rhombus(5 );
    }
}
