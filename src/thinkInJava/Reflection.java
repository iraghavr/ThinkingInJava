package thinkInJava;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;

/**
 * Created by liuda on 2016/10/6.
 */
public class Reflection {
    public static Set<String> analysize(Class<?> enumClass) {
        out.println("---------Analyzing-----" + enumClass + "------------");
        out.println("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces())
            out.println(t);
        out.println("Base: " + enumClass.getSuperclass());
        out.println("Methods: ");
        Set<String> methods = new TreeSet<String>();
        for (Method m : enumClass.getMethods())
            methods.add(m.getName());
        out.println(methods);
        return methods;
    }

}
