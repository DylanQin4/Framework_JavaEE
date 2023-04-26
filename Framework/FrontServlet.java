package etu1792.framework.servlet;

import etu1792.framework.Mapping;
import etu1792.framework.ModelView;
import etu1792.framework.annotation.Url;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletConfig;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.lang.annotation.Annotation;
import java.sql.Date;

public class FrontServlet extends HttpServlet { 

    HashMap<String,Mapping> mappingUrls;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String pkg = this.getInitParameter("package");
        HashMap<String,Mapping> mappingUrl =  this.allMappingUrls(pkg);
        this.setMappingUrls(mappingUrl);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String servletName = request.getServletPath().substring(1);

        HashMap<String,Mapping> mappingUrls = this.getMappingUrls();
        Set<String> mappingUrlsKeys = mappingUrls.keySet();

        for(String key : mappingUrlsKeys){
            if(key.equals(servletName)){
                try {
                    Class<?> classMapping = Class.forName(this.getInitParameter("package")+"."+mappingUrls.get(key).getClassName());
                    Object objet = classMapping.newInstance();
                    
                    this.setObject(request,response,objet);
                    this.dispatchModelView(request , response , objet , key);

                } catch (Exception ex) {
                    out.println(ex);
                }
            }
        }
    }

    public void setObject(HttpServletRequest request , HttpServletResponse response , Object objet )
            throws Exception{

        Field[] attributs = objet.getClass().getDeclaredFields();
        String[] setters = new String[attributs.length];
        for(int i=0 ; i<attributs.length ; i++){
            setters[i] = "set"+attributs[i].getName().substring(0,1).toUpperCase()+attributs[i].getName().substring(1);
        }

        for(int i=0 ; i<attributs.length ; i++){
            String parameter = request.getParameter(attributs[i].getName());
            if(parameter!=null){
                Method set = objet.getClass().getDeclaredMethod(setters[i], attributs[i].getType());
                if(attributs[i].getType() == Integer.TYPE){
                    set.invoke(objet,Integer.parseInt(parameter));
                }
                if(attributs[i].getType() == Double.TYPE){
                    set.invoke(objet,Double.parseDouble(parameter));
                }
                if(attributs[i].getType() == Float.TYPE){
                    set.invoke(objet,Float.parseFloat(parameter));
                }
                if(attributs[i].getType() == Date.class){
                    set.invoke(objet,Date.valueOf(parameter));
                }
                if(attributs[i].getType() == String.class){
                    set.invoke(objet,parameter);
                }
            }
        }

    }

    public void dispatchModelView(HttpServletRequest request , HttpServletResponse response , Object objet , String mappingUrlkey)
            throws Exception{
        Method method = objet.getClass().getMethod(mappingUrls.get(mappingUrlkey).getMethod());
        
        ModelView mv = (ModelView)method.invoke(objet);
        Set<String> mvKeys = mv.getData().keySet();
        for(String mvKey : mvKeys){
            request.setAttribute(mvKey , mv.getData().get(mvKey));
        }

        RequestDispatcher dispat = request.getRequestDispatcher(mv.getView());
        dispat.forward(request,response);
    }

    public HashMap<String, Mapping> allMappingUrls(String pckg){
        HashMap<String, Mapping> mappingUrl = new HashMap<String, Mapping>();

        ServletContext context = getServletContext();
        String path = "/WEB-INF/classes/"+pckg;

        Set<String> classNames = context.getResourcePaths(path);
        for (String className : classNames) {
            if (className.endsWith(".class")) {
                String fullClassName = className.substring(0, className.length() - 6);
                int taille = fullClassName.split("/").length;
                fullClassName = fullClassName.split("/")[taille-2]+"."+fullClassName.split("/")[taille-1];
                try {
                    Class<?> myClass = Class.forName(fullClassName);

                    Method[] methods = myClass.getDeclaredMethods();
                    for (int i = 0; i < methods.length; i++) {
                        Annotation[] annotations = methods[i].getAnnotations();
                        for (int j = 0; j < annotations.length; j++) {
                            if(annotations[j].annotationType()==Url.class)
                            {
                                Url url=(Url)annotations[j];
                                Mapping map=new Mapping(myClass.getSimpleName(),methods[i].getName());
                                mappingUrl.put(url.lien(),map);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        return mappingUrl;
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

    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }
}
