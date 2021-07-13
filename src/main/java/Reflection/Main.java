package Reflection;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        SimpleData s = new  SimpleData();
        SimpleData simpleData = new SimpleData(7, (byte) 0x07, "123", "7");
        SimpleData newSimpleData = new SimpleData();
        String[] a = new String[]{"1","2","3"};
        WithArrayData withArrayData = new WithArrayData(8, (byte) 0x08, "8", "8",a);

        printFieldsNames(simpleData);

        printFieldsNames(withArrayData);

        printMethodsNames(withArrayData);

        printFieldsValues(simpleData);

        Serialization(simpleData);

        copy(simpleData,newSimpleData);

        printFieldsValues(newSimpleData);




    }

    public static void printLine() {
        System.out.println("--------------------------------------------");
    }

    public static void printFieldsNames(Object object){
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

    public static void printMethodsNames(Object object) {
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

    public static void printFieldsValues(Object object) {
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
        printLine();
    }

    public static void copy(Object source, Object dist) {
        Object[] array = new Object[];
        Class<?> sourceClass = source.getClass();
        Class<?> distClass = dist.getClass();
        int i = 0;
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            DoNoCopy noCopy = sourceField.getAnnotation(DoNoCopy.class);
            if (noCopy != null){
                continue;
            }
            try {
                Field distField = distClass.getDeclaredField(sourceField.getName());
                sourceField.setAccessible(true);
                distField.setAccessible(true);
                array[i] = distField.set(dist, sourceField.get(source));
                distField.set(dist, sourceField.get(source));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void Serialization(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        int check =0;
        int res =0;
        for(Field f : fields){
            if (f.getType().isArray()){
            }
            res++;
        }
        switch (check){
            case (0):
                Field[] fields1 = object.getClass().getDeclaredFields();
                String[] str = new  String[res];
                int i =0;
                for (Field f1 : fields1) {
                    if (f1.getType().isArray()) {
                        String[] strings;
                        String str2 = "";
                        try {
                            strings = (String[]) f1.get(object);
                            for (int j = 0; j < strings.length; j++) {
                                str2 += strings[j] + " ";
                            }
                            str[i] = "Имя поля:" + f1.getName() + " Тип поля: " + f1.getType().getCanonicalName() + ": = " + str2;
                            i++;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        f1.setAccessible(true);
                        try {
                            Object value = f1.get(object);
                            str[i] = "Имя поля:" + f1.getName() + " Тип поля: " + f1.getType().getCanonicalName() + ": = " + value;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                    try (PrintWriter pr = new PrintWriter(new FileWriter("List.txt"))) {
                        for (int j = 0; j < res; j++) {
                            pr.println(str[j]);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    }

