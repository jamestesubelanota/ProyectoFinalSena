/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Usuario;
import edu.app.facade.UsuarioFacadeLocal;
import edu.app.utilidades.Email;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jamesmaldonado
 */
@Named(value = "registroRequest")
@RequestScoped
public class RegistroRequest implements Serializable{
    @EJB
            UsuarioFacadeLocal usuarioFacadeLocal;
    
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    @PostConstruct
            public void listaUser(){
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
            }
    
    String correo = "";

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
    
    
    Usuario usuReg = new Usuario();

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    /**
     * Creates a new instance of RegistroRequest
     */
    public RegistroRequest() {
    }
    
    public void registrarUsuario() {
        String mensaje ="";
        try {
                usuarioFacadeLocal.create(usuReg);
                mensaje = "swal(\"registered user!\");";
                usuReg = new Usuario();
         
                
        } catch (Exception e) {
            System.out.println("No se registro vergaa : RegistroRequest : registrarUsuario" + e.getLocalizedMessage());
           
              mensaje = "swal(\"error registered!\");";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    
     public void recuperarClave() {
        String mensaje ="";
        try {
               Usuario usuarioRecuperado = usuarioFacadeLocal.recuperarClave(correo);
               
                if (usuarioRecuperado.getIdusuario() == null) {
                  mensaje = "swal(\"mail not found!\", \"try again!\", \"error\");";
            }
                else{
                Email.sendClaves(usuarioRecuperado.getCorreo(), usuarioRecuperado.getNombres() + "" + usuarioRecuperado.getApellidos() , usuarioRecuperado.getClave());
                    mensaje = "swal(\"Key sent!\", \"to your email!\", \"success\");";
                  
                }
               
              
         
                
        } catch (Exception e) {
            
            System.out.println("No se registro : RegistroRequest : recuperarClave " + e.getLocalizedMessage());
           
              mensaje = "swal(\"It was not found!\", \"no email registered with this address !\", \"erro\");!\");";
              
        }
        this.correo = "";
        PrimeFaces.current().executeScript(mensaje);
    }
     
     public void correoMasivo(){
         String mensaje ="";
         try {
             
             for(Usuario usuarioEmail : listaUsuarios){
             Email.sendBienvenido(usuarioEmail.getCorreo(),usuarioEmail.getNombres()+" "+usuarioEmail.getApellidos(), usuarioEmail.getCorreo(),usuarioEmail.getClave());
              
             
             
             }
              mensaje= "swal(\"mensajes enviados!\", \"bien!\", \"success\");";
         } catch (Exception e) {
                mensaje= "swal(\"mensajesno  enviados!\", \"error!\", \"error\");";
                System.out.println("correomasivo error" + e.getMessage());
         }
         PrimeFaces.current().executeScript(mensaje);
     
     
     }
     
     
}
