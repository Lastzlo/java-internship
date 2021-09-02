package task3_classLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MalformedURLException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<Singleton> singletonClass1 = (Class<Singleton>) classLoader.loadClass("task3_classLoader.Singleton");
        final Method getInstance = singletonClass1.getMethod("getInstance");
        Object singleton1 = getInstance.invoke(singletonClass1);
        System.out.println(singleton1);

        File file = new File("D:\\JavaProjects\\java-internship\\home-work-2\\practiceTasks\\target\\classes");
        //convert the file to URL format
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        //load this folder into Class loader
        ClassLoader customClassLoader = new URLClassLoader(urls, classLoader.getParent());

        Class<Singleton> singletonClass2 = (Class<Singleton>) customClassLoader.loadClass("task3_classLoader.Singleton");
        final Method getInstance2 = singletonClass2.getMethod("getInstance");
        Object singleton2 = getInstance2.invoke(singletonClass2);
        System.out.println(singleton2);

        System.out.println(singleton1 == singleton2);
    }

}
