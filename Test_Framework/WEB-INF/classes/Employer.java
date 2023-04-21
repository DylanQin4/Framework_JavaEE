/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import etu2043.framework.annotation.Url;

import java.util.HashMap;

import etu2043.framework.*;

/**
 *
 * @author ITU
 */
public class Employer {
    int id;
    String nom;

    @Url(lien="test")
    public String test(){
        return "Test";
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
