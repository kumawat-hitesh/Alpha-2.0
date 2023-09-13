public class MethodOverriding {
    public static void main(String[] args) {
        Deer d1 =new Deer();
        d1.eat();
    }
}
class Animal{
    void eat(){
        System.out.println("Eat anything");
    }
}
class Deer{
    void eat(){
        System.out.println("Eat grass");
    }
}