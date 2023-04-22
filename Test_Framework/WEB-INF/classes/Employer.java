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

    @Url(lien="test")
    public ModelView test(){
        ModelView mv = new ModelView();
        mv.setView("test.jsp");
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
