public class call_by_value {
    public static void changeA(int a){
        a = 10;
        System.out.println("\nChanged value in function : "+a);
    }
    public static void main(String[] args) {
        int a = 5;
        System.out.println("\nOriginal value of A : "+a);
        changeA(a);
        System.out.println("\nvalue of A after come back to main function : "+a+"\n");
    }
}
