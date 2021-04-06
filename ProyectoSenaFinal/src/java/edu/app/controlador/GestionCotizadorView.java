/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Cotizador;

import edu.app.facade.CotizadorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jamesmaldonado
 */
@Named(value = "gestionCotizadorView")
@ViewScoped
public class GestionCotizadorView implements Serializable {
    
    @EJB    
    CotizadorFacadeLocal cotizadorFacadelocal;
    Cotizador crearRegistroCotizador = new Cotizador();
    Cotizador edtiarRegistroCotizador = new Cotizador();
    ArrayList<Cotizador>listacotizafor = new ArrayList();
        
   
   

    /**
     * Creates a new instance of GestionCotizadorView
     */
    public GestionCotizadorView() {
    }
    
}
