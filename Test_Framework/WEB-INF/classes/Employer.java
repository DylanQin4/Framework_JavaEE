/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import etu1792.framework.annotation.Url;

import java.util.HashMap;

import etu1792.framework.*;

/**
 *
 * @author ITU
 */
public class Employer {
    int id;
    String nom;

    public Employer(){}

    public Employer(int id , String nom){
        this.setId(id);
        this.setNom(nom);
    }

    @Url(lien="test")
    public ModelView test(){
        Employer[] list_emp = new Employer[3];
        list_emp[0] = new Employer(1,"Emp 1");
        list_emp[1] = new Employer(2,"Emp 2");
        list_emp[2] = new Employer(3,"Emp 3");

        ModelView mv = new ModelView();
        mv.setView("test.jsp");
        mv.addItem("listes", list_emp);
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
}
