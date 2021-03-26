package edu.app.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.app.entity.Inventario;
import edu.app.facade.InventarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jamesmaldonado
 */
@Named(value = "gestionInventarioView")
@ViewScoped
public class GestionInventarioView implements Serializable {
    
    
     @EJB
     InventarioFacadeLocal inventarioFacadelocal;
     Inventario invenatarioCrear = new Inventario();
     Inventario invenatarioEditar = new Inventario();
     ArrayList<Inventario>listaInventario = new ArrayList<>();
     
     @PostConstruct
     public void cagarListaInventario(){
     
     listaInventario.addAll(inventarioFacadelocal.findAll());
     }
     
     public void cargarEdicionInventario(Inventario inveRemov){
     invenatarioEditar = inveRemov;
     
     
     }
     
     
     
     public void registrarObjeto(){
      String mensaje= "";
         try {
             inventarioFacadelocal.create(invenatarioCrear);
             Inventario objetonuevo = new Inventario();
              mensaje = "swal(\"create object !\", \"gob job !\", \"success\");"; 
         } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionInventarioView.registrarObjetol()" + e.getMessage());
              mensaje = "swal(\"Error no fount!\", \"object !\", \"Error\");"; 
         }
     
         PrimeFaces.current().executeScript(mensaje);
     }
     
      public void editarObjeto(){
      String mensaje= "";
         try {
             inventarioFacadelocal.edit(invenatarioEditar);
             Inventario objetonuevo = new Inventario();
              mensaje = "swal(\"Edir object !\", \"gob job !\", \"success\");"; 
         } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionInventarioView.editarObjetol()" + e.getMessage());
              mensaje = "swal(\"Error no fount!\", \"object !\", \"Error\");"; 
         }
     
         PrimeFaces.current().executeScript(mensaje);
     }
      
      
      public void eliminarObjeto(Inventario inventarioRemov){
      String mensaje= "";
         try {
             inventarioFacadelocal.remove(inventarioRemov);
             Inventario objetonuevo = new Inventario();
             listaInventario.remove(inventarioRemov);
              mensaje = "swal(\"delete object !\", \"gob job !\", \"success\");"; 
         } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionInventarioView.editarObjetol()" + e.getMessage());
              mensaje = "swal(\"Error no fount!\", \"object !\", \"Error\");"; 
         }
     
         PrimeFaces.current().executeScript(mensaje);
     }

    public Inventario getInvenatarioCrear() {
        return invenatarioCrear;
    }

    public void setInvenatarioCrear(Inventario invenatarioCrear) {
        this.invenatarioCrear = invenatarioCrear;
    }

    public Inventario getInvenatarioEditar() {
        return invenatarioEditar;
    }

    public void setInvenatarioEditar(Inventario invenatarioEditar) {
        this.invenatarioEditar = invenatarioEditar;
    }

    public ArrayList<Inventario> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(ArrayList<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }
     
     

    /**
     * Creates a new instance of GestionInventarioView
     */
    public GestionInventarioView() {
    }
    
}
