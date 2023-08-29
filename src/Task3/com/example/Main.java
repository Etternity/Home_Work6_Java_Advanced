package Task3.com.example;
import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface BaseAnnotation {
    String value();
}

@BaseAnnotation("Перша аннотація")
class Parent {
    public void show() {
        System.out.println("Це батьківський клас");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface AdditionalAnnotation {
    String info();
}
@AdditionalAnnotation(info = "Додаткова інформація")
class Child extends Parent {
    @Override
    public void show() {
        System.out.println("Це дочірній клас");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {
    String name();
}

@Retention(RetentionPolicy.RUNTIME)
@interface FieldAnnotation {
    String type();
}

class AnnotatedClass {
    @MethodAnnotation(name = "myMethod")
    public void myMethod() {
        System.out.println("Це аннотований метод");
    }

    @FieldAnnotation(type = "String")
    public String annotatedField;
}

public class Main {
    public static void main(String[] args) {

        Class<?> childClass = Child.class;
        BaseAnnotation baseAnnotation = childClass.getAnnotation(BaseAnnotation.class);
        if (baseAnnotation != null) {
            System.out.println("Успадкована аннотація: " + baseAnnotation.value());
        }


        AdditionalAnnotation additionalAnnotation = childClass.getAnnotation(AdditionalAnnotation.class);
        if (additionalAnnotation != null) {
            System.out.println("Додаткова аннотація: " + additionalAnnotation.info());
        }

        Class<?> annotatedClass = AnnotatedClass.class;
        try {
            MethodAnnotation methodAnnotation = annotatedClass.getMethod("myMethod").getAnnotation(MethodAnnotation.class);
            if (methodAnnotation != null) {
                System.out.println("Аннотація методу: " + methodAnnotation.name());
            }

            FieldAnnotation fieldAnnotation = annotatedClass.getField("annotatedField").getAnnotation(FieldAnnotation.class);
            if (fieldAnnotation != null) {
                System.out.println("Аннотація поля: " + fieldAnnotation.type());
            }
        } catch (NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
