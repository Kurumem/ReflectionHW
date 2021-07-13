package Reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest extends Main {

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


}


