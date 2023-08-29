package Task2.com.example;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ParameterInfo {
    String name();
    String description();
}

public class Calculator {
    @ParameterInfo(name = "num1", description = "Перший операнд")
    private double num1;

    @ParameterInfo(name = "num2", description = "Другий операнд")
    private double num2;

    public Calculator(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    /**
     * Додає два числа.
     * @return Результат додавання.
     */
    public double add() {
        return num1 + num2;
    }

    /**
     * Віднімає друге число від першого.
     * @return Результат віднімання.
     */
    public double subtract() {
        return num1 - num2;
    }

    /**
     * Множить два числа.
     * @return Результат множення.
     */
    public double multiply() {
        return num1 * num2;
    }

    /**
     * Ділить перше число на друге.
     * @return Результат ділення.
     */
    public double divide() {
        if (num2 != 0) {
            return num1 / num2;
        } else {
            throw new ArithmeticException("Ділення на нуль!");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator(10, 5);

        Class<?> calculatorClass = calculator.getClass();
        Field[] fields = calculatorClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ParameterInfo.class)) {
                ParameterInfo annotation = field.getAnnotation(ParameterInfo.class);
                System.out.println("Поле: " + field.getName());
                System.out.println("Опис: " + annotation.description());
                System.out.println("---------------------------");
            }
        }
    }
}
