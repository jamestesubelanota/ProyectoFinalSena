/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Clientes;
import edu.app.facade.ClientesFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jamesmaldonado
 */
@Named(value = "gestionClientesView")
@ViewScoped
public class GestionClientesView implements Serializable {

    @EJB
    @Inject
    ClientesFacadeLocal clientesFacadeLocal;
    private Clientes clientesCrear = new Clientes();
    private Clientes ClientesEdit = new Clientes();
    private ArrayList<Clientes> listarClientes = new ArrayList<>();

    @PostConstruct
    public void cargarListaClientes() {

        listarClientes.addAll(clientesFacadeLocal.findAll());
    }
    
     public void CargareLSClientes(Clientes clientesRemov){
        ClientesEdit = clientesRemov;
     }

    /**
     * Creates a new instance of GestionClientesView
     */
    public GestionClientesView() {
    }
    
    
    public void  crearCliente(){
        String mensaje = "";
        try {
            clientesFacadeLocal.create(clientesCrear);
         
            mensaje = "swal(\"Cliente Creando!\", \"Correctamente!\", \"success\");";
            
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionClientesView.crearCliente()" + e.getMessage());
             mensaje = "swal(\"Error Creando !\", \"Cliente!\", \"error\");";
        }
    
        PrimeFaces.current().executeScript(mensaje);
    
    }
    public void  editarCliente(){
        String mensaje = "";
        try {
            clientesFacadeLocal.edit(ClientesEdit);
         
            mensaje = "swal(\"Cliente Editado!\", \"Correctamente!\", \"success\");";
            
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionClientesView.editarCliente()" + e.getMessage());
             mensaje = "swal(\"Error editando !\", \"Cliente!\", \"error\");";
        }
    
        PrimeFaces.current().executeScript(mensaje);
    
    }
    
    
    public void eliminarClientes(Clientes clientesRemov){
    String mensjae = "";
    
        try {
            clientesFacadeLocal.remove(clientesRemov);
            listarClientes.remove(clientesRemov);
            mensjae = "swal(\"Cliente Eliminado!\", \"Correctamente!\", \"success\");";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionClientesView.eliminarClientes()" + e.getMessage());
             mensjae = "swal(\" Error Eliminado!\", \"Correctamente!\", \"error\");";
        }
    PrimeFaces.current().executeScript(mensjae);
    }
    

    public Clientes getClientesEdit() {
        return ClientesEdit;
    }

    public void setClientesEdit(Clientes ClientesEdit) {
        this.ClientesEdit = ClientesEdit;
    }

    public ArrayList<Clientes> getListarClientes() {
        return listarClientes;
    }

    public void setListarClientes(ArrayList<Clientes> listarClientes) {
        this.listarClientes = listarClientes;
    }

    public Clientes getClientesCrear() {
        return clientesCrear;
    }

    public void setClientesCrear(Clientes clientesCrear) {
        this.clientesCrear = clientesCrear;
    }

}
