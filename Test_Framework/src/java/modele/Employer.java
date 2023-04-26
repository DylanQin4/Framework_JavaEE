package modele;

import etu1792.framework.annotation.Url;
import etu1792.framework.*;
import java.sql.Date;


public class Employer {
    int id;
    String nom;
    Date date;

    public Employer(){}

    public Employer(int id , String nom , Date date){
        this.setId(id);
        this.setNom(nom);
        this.setDate(date);
    }

    @Url(lien="all_employer")
    public ModelView findAll(){
        Employer[] list_emp = new Employer[3];
        list_emp[0] = new Employer(1,"Emp 1" , Date.valueOf("2000-01-01"));
        list_emp[1] = new Employer(2,"Emp 2" , Date.valueOf("2000-01-01"));
        list_emp[2] = new Employer(3,"Emp 3" , Date.valueOf("2000-01-01"));

        ModelView mv = new ModelView();
        mv.setView("listEmployer.jsp");
        mv.addItem("listes", list_emp);
        return mv;
    }

    @Url(lien="save_employer")
    public ModelView save(){
        ModelView mv = new ModelView();
        mv.setView("saveEmp.jsp");
        mv.addItem("employer",this);
        return mv;
    }
    
    @Url(lien="employer_by_Id")
    public ModelView getEmployerById(int id){
        Employer[] list_emp = new Employer[3];
        list_emp[0] = new Employer(1,"Emp 1" , Date.valueOf("2000-01-01"));
        list_emp[1] = new Employer(2,"Emp 2" , Date.valueOf("2000-01-01"));
        list_emp[2] = new Employer(3,"Emp 3" , Date.valueOf("2000-01-01"));

        ModelView mv = new ModelView();
        mv.setView("info_employer.jsp");
        
        for(int i=0 ; i<list_emp.length ; i++){
            if(list_emp[i].getId()==id){
                mv.addItem("employer",list_emp[i]);
            }
        }
        
        return mv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
}
