/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Usuario;
import edu.app.facade.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jamesmaldonado
 */
@Named(value = "usuarioSession")
@SessionScoped
public class UsuarioSession implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    Usuario usulogin = new Usuario();
    String correo;
    String Clave;

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public Usuario getUsulogin() {
        return usulogin;
    }

    public void setUsulogin(Usuario usulogin) {
        this.usulogin = usulogin;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public void validarUsuario() {

        String mensajes = "";

        try {
            usulogin = usuarioFacadeLocal.loginUsuario(correo, Clave);
            if (usulogin.getIdusuario() == null) {
                mensajes = "swal(\"warning!\", \"user not found!\", \"error\");";

            } else {
                mensajes = "swal(\"warning!\", \"registered user!\", \"success\");";
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect("../misDatos/index.xhtml");
            }
        } catch (Exception e) {
            mensajes = "swal(\"warning!\", \"user not found!\", \"error\");";
        }
        PrimeFaces.current().executeScript(mensajes);
    }

    public void actualizaMisDatos() {

        String mensajes = "";

        try {
            usuarioFacadeLocal.edit(usulogin);
            mensajes = "swal(\"warning!\", \"updated user!\", \"success\");";

        } catch (Exception e) {
            mensajes = "swal(\"warning!\", \"user not updated!\", \"error\");";
        }
        PrimeFaces.current().executeScript(mensajes);
    }

    /**
     * Creates a new instance of UsuarioSession
     */
    public UsuarioSession() {
    }

}
