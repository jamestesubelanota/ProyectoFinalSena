/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Mensaje;
import edu.app.facade.MensajeFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jamesmaldonado
 */
@Named(value = "gestionMensajesView")
@ViewScoped
public class GestionMensajesView implements Serializable{
    @Inject
    UsuarioSession usuarioSession;
    
    @EJB
    MensajeFacadeLocal mensajeFacadeLocal ;
    private Mensaje crearMensaje = new Mensaje();
    private Mensaje EditarMensaje = new Mensaje();
   
    private ArrayList<Mensaje>listarMensaje = new ArrayList();
    
    @PostConstruct
    public void listarmensajes(){
    listarMensaje.addAll(mensajeFacadeLocal.findAll());
    }

    public void cargarMesajes(Mensaje mensas){
     EditarMensaje = mensas;
    }
    
    public void eliminarMensaje(Mensaje removermensaje){
       String mensajito ="";
        try {
            mensajeFacadeLocal.remove(removermensaje);
            listarMensaje.remove(removermensaje);
            Mensaje nuevoMensaje = new Mensaje();
            
            mensajito = "swal(\"deleted message!\", \"gob job!\", \"success\");";
            
        } catch (Exception e) {
             mensajito = "swal(\"error message!\", \"Delete!\", \"error\");";
        }
           PrimeFaces.current().executeScript(mensajito);
    
    }
    /**
     * Creates a new instance of GestionMensajesView
     */
    public GestionMensajesView() {
    }

    public Mensaje getCrearMensaje() {
        return crearMensaje;
    }

    public void setCrearMensaje(Mensaje crearMensaje) {
        this.crearMensaje = crearMensaje;
    }

    public Mensaje getEditarMensaje() {
        return EditarMensaje;
    }

    public void setEditarMensaje(Mensaje EditarMensaje) {
        this.EditarMensaje = EditarMensaje;
    }

    public ArrayList<Mensaje> getListarMensaje() {
        return listarMensaje;
    }

    public void setListarMensaje(ArrayList<Mensaje> listarMensaje) {
        this.listarMensaje = listarMensaje;
    }
    
    
    public void regitrarMensaje(){
    
    String mensaje = "" ;
        try {
            mensajeFacadeLocal.create(crearMensaje);
            Mensaje nuevoMensaje = new Mensaje();
            mensaje = "swal(\"mensaje creado!\", \"buen trabajo!\", \"success\");";
            
            
        } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionMensajeslView.regitrarMensaje()" + e.getMessage());
               mensaje = "swal(\"mensaje nocreado!\", \"try again!\", \"error\");";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    
      public void descargaReporte() throws IOException  {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
 
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
 
        try {
            Map parametro = new HashMap();
            parametro.put("Ficha", "1966821");
            parametro.put("Usuarior", usuarioSession.getUsulogin().getNombres() + "" + usuarioSession.getUsulogin().getApellidos() );
            parametro.put("Imagenes", context.getRealPath("vendors/images/chispitas-logo.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chispitasMagicas", "root", "toor");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/app/reportes/ReporteMensajes.jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista Usuarios.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }
 
    }
    

}
