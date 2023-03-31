package etu2000.framework.servlet;

import etu2000.framework.Mapping;
import etu2000.framework.controllers.Url;
import etu2000.framework.util.Utility;
import etu2000.models.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    
    @Override
    public void init() {
        Vector<Class<?>> classes=new Vector<>();
        for(Class clazz : Utility.getAllClasses(classes, "etu2000.models")){
            mappingUrls=Utility.initHashMap(clazz);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (Map.Entry<String, Mapping> entry : mappingUrls.entrySet()) {
                out.println("Key: " + entry.getKey() + ", Class: " + entry.getValue().getClassName() + ", Method: " + entry.getValue().getMethod());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
