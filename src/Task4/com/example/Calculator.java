package Task4.com.example;
public class Calculator {
    @Math(num1 = 100, num2 = 200)
    public void mathSum(int num1, int num2) {
        int sum = num1 + num2;
        System.out.println("Sum: " + sum);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.performMathSum();
    }

    private void performMathSum() {

        try {
            Math annotation = Calculator.class.getDeclaredMethod("mathSum", int.class, int.class)
                    .getAnnotation(Math.class);

            if (annotation != null) {
                int num1 = annotation.num1();
                int num2 = annotation.num2();
                mathSum(num1, num2);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
