public class even_number {

    public static boolean isEven(int n){
        if(n % 2 != 0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println( isEven(56756));
    }
}
