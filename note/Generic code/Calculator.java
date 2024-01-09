public class Calculator<T>{
    private T num;
    private T num2;

    //constructor
    private Calculator(T num, T num2){
        this.num = num;
        this.num2 = num2;
    }
    
    public static Calculator<Integer> create(int num1, int num2){
        return new Calculator<Integer>(num1, num2);
    }
     
    public static Calculator<Double> create(double num1, double num2){
        return new Calculator<>(num1, num2);
    }

    public static Calculator<String> create(String num1, String num2){
        return new Calculator<String>(num1, num2);
    }

    public T add(){

        if (this.num instanceof Integer){
            Integer total = (Integer) this.num + (Integer) this.num2;
            return (T) total;
        }
        if (this.num instanceof String){
            String concat = (String) this.num + (String) this.num2;
            return (T) concat;
        }
        return null;
    }
    public static String printHelloWorld(){
        return "HelloWorld";
    }

    public String getHelloWorld(){
        return "HelloWorld";
    }
    
    public static void main(String[] args) {
        
        // Calculator<Integer> temp = Calculator.create(1, 2);
        // System.out.println(temp.add());

        // Calculator<String> temp2 = Calculator.create("1", "2");
        // System.out.println(temp2.add());
        Calculator<Integer> temp = Calculator.create(1, 2);
        System.out.println(temp.getHelloWorld());

    }
}