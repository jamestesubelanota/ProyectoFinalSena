/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;


import edu.app.entity.Usuario;
import edu.app.facade.UsuarioFacadeLocal;
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
@Named(value = "gestionUsuariosView")
@ViewScoped
public class GestionUsuariosView implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    UsuarioSession usuarioSession;
     private Usuario usueditar = new Usuario();
    
     private Usuario usuariocrear = new Usuario();

   

    private ArrayList<Usuario> listarUsuario = new ArrayList<>();

    @PostConstruct
    public void cargarListaUsuarios() {
        listarUsuario.addAll(usuarioFacadeLocal.findAll());

    }
public void cargarUsuario(Usuario usuRemov) {
         usueditar = usuRemov;
}
    
   
    /**
     * Creates a new instance of GestionUsuariosView
     */
    public GestionUsuariosView() {
    }

    public ArrayList<Usuario> getListarUsuario() {
        return listarUsuario;
    }

    public void setListarUsuario(ArrayList<Usuario> listarUsuario) {
        this.listarUsuario = listarUsuario;
    }

    public void eliminarUsuario(Usuario usuRemov) {
        String mensaje = "";
        try {
            usuarioFacadeLocal.remove(usuRemov);
            listarUsuario.remove(usuRemov);
            mensaje = "swal('Usuario Eliminado !!!!', 'Correctamente', 'success');";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionUsuariosView.eliminarUsuario() " + e.getMessage());
            mensaje = "swal('Error eliminando !!!!', 'Usuario', 'error');";
        }

        PrimeFaces.current().executeScript(mensaje);
    }
   
    public void editarUsuario() {
        String mensaje = "";
        try {
            usuarioFacadeLocal.edit(usueditar);
            
            mensaje = "swal('Usuarioeditado!!!!', 'Correctamente', 'success');";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionUsuariosView.editarUsuario() " + e.getMessage());
            mensaje = "swal('Error editando !!!!', 'Usuario', 'error');";
        }

        PrimeFaces.current().executeScript(mensaje);
    }
   
    public void registrarUsuario() {
        String mensaje = "";
        try {
            usuarioFacadeLocal.create(usuariocrear);
            usuariocrear = new Usuario();
            mensaje = "swal('Usuario registrado !!!!', 'Correctamente', 'success');";
            usuariocrear = new Usuario();
        } catch (Exception e) {
            System.out.println("error creando un usuario RegistroRequest:registrarUsuario " + e.getMessage());
            mensaje = "swal('Error registrado !!!!', 'Usuario', 'error');";
        }

        PrimeFaces.current().executeScript(mensaje);
    }

    public Usuario getUsuariocrear() {
        return usuariocrear;
    }

    public void setUsuariocrear(Usuario usuariocrear) {
        this.usuariocrear = usuariocrear;
    }

    public Usuario getUsueditar() {
        return usueditar;
    }

    public void setUsueditar(Usuario usueditar) {
        this.usueditar = usueditar;
    }
}
