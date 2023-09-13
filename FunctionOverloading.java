public class FunctionOverloading {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.sum(5, 3));
        System.out.println(calc.sum((float)1.5,(float)2.6));
        System.out.println(calc.sum(5, 3,7));
    }
}

class Calculator{
    int sum(int a, int b){
        return a + b;
    }
    float sum(float a, Float b){
        return a + b;
    }
    int sum(int a, int b, int c){
        return a + b + c;
    }
}