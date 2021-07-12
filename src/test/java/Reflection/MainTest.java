package Reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    Main main = new Main();
    SimpleData simpleData = new SimpleData();
    WithCollectionData WithCollectionData = new WithCollectionData();
    @Test
    public void printFieldsNamesTest(){


        Field[] fieldsSimple = simpleData.getClass().getDeclaredFields();
        Field[] fieldsCollection = WithCollectionData.getClass().getDeclaredFields();
        String[] listSimple = {"int","byte","java.lang.Object","java.lang.String"};
        String[] listCollection = {"java.util.List","java.util.Map"};
        int i = 0;
        for (Field f : fieldsSimple) {
            Object name = f.getType().getCanonicalName();
            assertSame(name, listSimple[i]);
            i++;
        }
        i = 0;
        for (Field f : fieldsCollection) {
            Object name = f.getType().getCanonicalName();
            assertSame(name, listCollection[i]);
            i++;
        }
    }
    /*
    @Test
    public void printMethodsNamesTest(){


        Method[] methodSimple = simpleData.getClass().getDeclaredMethods();
        Method[] methodCollection = WithCollectionData.getClass().getDeclaredMethods();
        String[] listSimple = {"void","void","byte","java.lang.Object","void","int"};
        String[] listCollection = {"java.util.Map","void","void","java.util.List"};
        int i = 0;
        for (Method m : methodSimple) {
            Object name = m.getReturnType().getCanonicalName();
            assertSame(name, listSimple[i]);
            i++;
        }
        i = 0;
        for (Method m : methodCollection) {
            Object name = m.getReturnType().getCanonicalName();
            assertSame(name, listCollection[i]);
            i++;
        }*/


    }
/*
    @Test
    public void printFieldsValueTest() throws IllegalAccessException {
        Field[] fields = simpleData.getClass().getDeclaredFields();
        Object[] listSimple = {7, (byte) 0x07, "123", "7"};
        int i = 0;
        for (Field f : fields) {
            f.setAccessible(true);
            Object value = f.get(simpleData);
            System.out.println(value);

            assertEquals(value,listSimple[i]);
            i++;
        }
    }
}*/