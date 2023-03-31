package etu2000.framework.util;

import etu2000.framework.Mapping;
import etu2000.framework.controllers.Url;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utility {
    
    public static Vector<Class<?>> getAllClasses(Vector<Class<?>> classes, String packagename){
        if(classes==null){
            classes=new Vector<>();
        }
        ClassLoader loader=Thread.currentThread().getContextClassLoader();
        String path=packagename.replace(".", "/");
        URL resource=loader.getResource(path);
        File file=new File(resource.getFile());
        String[] files=file.list();
        for(int i=0; i<files.length; i++){
            File fichier = new File(file.getAbsolutePath() + File.separator + files[i]);
            if(fichier.isFile()){
                if(files[i].endsWith(".class")){
                    try {
                        classes.add(Class.forName(packagename+"."+files[i].replace(".class", "")));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                getAllClasses(classes, packagename+"."+fichier.getName());
            }
        }
        return classes;
    }
    
    public static Vector<Method> getAllMethodAnnoted(Class classe){
        Method[] methods= classe.getDeclaredMethods();
        Vector<Method> rep = new Vector<Method>();
        for (Method method : methods){
            if (method.getAnnotations().length>0){
                rep.add(method);
            }
        }
        return rep;
    }
    
    public static HashMap<String, Mapping> initHashMap(Class classe){
        HashMap<String, Mapping> map=new HashMap<>();
        Vector<Method> allMethod=Utility.getAllMethodAnnoted(classe);
        for(Method methode : allMethod){
            if(methode.getAnnotations()[0] instanceof Url){
                Mapping value=new Mapping(classe.getSimpleName(), methode.getName());
                String key=((Url)(methode.getAnnotations()[0])).value();
                map.putIfAbsent(key, value);
            }
        }
        return map;
    }
}
