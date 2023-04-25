/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1792.framework.servlet;



import etu1792.framework.Mapping;
import etu1792.framework.ModelView;
import etu1792.framework.annotation.Url;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletContext;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.annotation.Annotation;

/**
 *
 * @author ITU
 */
public class FrontServlet extends HttpServlet { 
    HashMap<String,Mapping> mappingUrls;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // String pkg = this.getInitParameter("package_modele");
        HashMap<String,Mapping> mappingUrl =  new HashMap<String,Mapping>();
        // Obtenez le ServletContext
        ServletContext context = getServletContext();
        // Obtenez tous les noms des fichiers dans le package pkg
        String path = "/WEB-INF/classes/"+this.getInitParameter("package");

        Set<String> classNames = context.getResourcePaths(path);
        for (String className : classNames) {
            if (className.endsWith(".class")) {
                // Supprimez l'extension ".class" du nom de fichier pour obtenir le nom de classe
                String fullClassName = className.substring(0, className.length() - 6);
                int taille = fullClassName.split("/").length;
                fullClassName = fullClassName.split("/")[taille-2]+"."+fullClassName.split("/")[taille-1];
                try {
                    // Chargez la classe
                    Class<?> myClass = Class.forName(fullClassName);
                    // Appelez une méthode de l'objet
                    Method[] m = myClass.getDeclaredMethods();
                    for (int i = 0; i < m.length; i++) {
                        Annotation[] a = m[i].getAnnotations();
                        for (int j = 0; j < a.length; j++) {
                            if(a[j].annotationType()==Url.class)
                            {
                                Url u=(Url)a[j];
                                Mapping map=new Mapping(myClass.getSimpleName(),m[i].getName());
                                mappingUrl.put(u.lien(),map);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Impossible de charger la classe " + fullClassName + ": " + e.getMessage());
                }
            }
        }

        this.setMappingUrls(mappingUrl);
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lienMapping = String.valueOf(request.getRequestURL());
        String[] split  = lienMapping.split("/");

        PrintWriter out = response.getWriter();
        HashMap<String,Mapping> mappingUrls = this.getMappingUrls();
        Set keys = mappingUrls.keySet();
        Iterator itr = keys.iterator();

        while(itr.hasNext()){
            String key = (String) itr.next();
            if(key.equals(split[4])){
                try {
                    Class<?> classMapping = Class.forName("modele."+mappingUrls.get(key).getClassName());
                    Object objet = classMapping.newInstance();
                    Method method = objet.getClass().getMethod(mappingUrls.get(key).getMethod());
                    
                    ModelView mv = (ModelView)method.invoke(objet);
                    Set keyMv = mv.getData().keySet();
                    Iterator itr2 = keyMv.iterator();
                    while(itr2.hasNext()){
                        String keyMvString = (String)itr2.next();
                        request.setAttribute(keyMvString , mv.getData().get(keyMvString));
                    }

                    RequestDispatcher dispat = request.getRequestDispatcher(mv.getView());
                    dispat.forward(request,response);
                    
                } catch (Exception ex) {
                    out.println(ex.getMessage());
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold> 
    
    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }
    
    // public static HashMap<String,Mapping> getAllMapping(String pkg){
    //     HashMap<String , Mapping> mappingUrls = new HashMap<String,Mapping>();
    //     Set<Method> method = new Reflections(pkg,new MethodAnnotationsScanner()).getMethodsAnnotatedWith(Url.class);
    //     Iterator<Method> itr = method.iterator();
    //     while(itr.hasNext()){
    //         Method m = itr.next();
            
    //         Mapping tempMapping = new Mapping();
    //         tempMapping.setClassName(m.getDeclaringClass().getSimpleName());
    //         tempMapping.setMethod(m.getName());
            
    //         Url url = m.getAnnotation(Url.class);
    //         String cle = url.lien();
            
    //         mappingUrls.put(cle, tempMapping);
    //     }
    //     return mappingUrls;
    // }
}
