package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        SimpleData s = new  SimpleData();
        SimpleData simpleData = new SimpleData(7, (byte) 0x07, "123", "7");
        String[] a = new String[]{"1","2","3"};
        WithArrayData withArrayData = new WithArrayData(8, (byte) 0x08, "8", "8",a);

        printFieldsNames(simpleData);

        printFieldsNames(withArrayData);

        printMethodsNames(withArrayData);

        printFieldsValues(simpleData);





    }

    private static void printLine() {
        System.out.println("--------------------------------------------");
    }

    private static void printFieldsNames(Object object){
        Field[] fieldFirst = object.getClass().getDeclaredFields();
        for (Field f : fieldFirst) {
            System.out.println(f.getName() + " as " + f.getType().getCanonicalName());
        }
        Field[] fieldsSecond = object.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fieldsSecond)
        {
            System.out.println(field.getName() + " as " + field.getType().getCanonicalName());
        }
        printLine();
    }

    private static void printMethodsNames(Object object) {
        Method[] methodFirst = object.getClass().getDeclaredMethods();
        for (Method m : methodFirst) {
            System.out.println(m.getName() + " as " + m.getReturnType().getCanonicalName());
        }
        Method[] methodSecond = object.getClass().getSuperclass().getDeclaredMethods();
        for (Method m : methodSecond) {
            System.out.println(m.getName() + " as " + m.getReturnType().getCanonicalName());
        }
        printLine();
    }

    private static void printFieldsValues(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                Object value = f.get(object);
                System.out.println(f.getName() + " as " + f.getType().getCanonicalName() + " = " + value);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void copy(Object source, Object dist) {
        Class<?> sourceClass = source.getClass();
        Class<?> distClass = dist.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            try {
                Field distField = distClass.getDeclaredField(sourceField.getName());
                sourceField.setAccessible(true);
                distField.setAccessible(true);
                distField.set(dist, sourceField.get(source));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    }

