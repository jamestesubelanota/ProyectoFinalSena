/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Eventos;
import edu.app.facade.EventosFacadeLocal;
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
@Named(value = "gestionEventosView")
@ViewScoped
public class GestionEventosView  implements Serializable{
    
    @Inject
     UsuarioSession usuarioSession;
  
    @EJB
    EventosFacadeLocal eventosFacadeLocal;
    private Eventos eventosCrear = new Eventos();
    private Eventos eventosEditar = new Eventos();
    private ArrayList<Eventos> listarEventos =  new ArrayList<> ();
    private ArrayList<Eventos> muestraEventos =  new ArrayList<> ();

    /**
     * Creates a new instance of GestionEventosView
     */
    
    @PostConstruct
    public void cargarListaUsuarios(){
        listarEventos.addAll(eventosFacadeLocal.findAll());
    
    }
    public void cargarMuestraUsuarios(){
        muestraEventos.addAll(eventosFacadeLocal.findAll());
    
    }
    public void cargarListaEvento(Eventos eventRemover){
        eventosEditar = eventRemover;
    }
    public void crearEvento(){
     String mensaje = "";
        try {
            eventosFacadeLocal.create(eventosCrear);
            mensaje = "swal(\"Evento creado!\", \"Correctamente!\", \"success\");";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionEventosView.crearEvento()"+ e.getMessage());
             mensaje = "swal(\"Error creando!\", \"Evento!\", \"error\");";
            
        }
        PrimeFaces.current().executeScript(mensaje);
    }
     public void editarEvento(){
     String mensaje = "";
        try {
            eventosFacadeLocal.edit(eventosEditar);
            mensaje = "swal(\"Evento editado!\", \"Correctamente!\", \"success\");";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionEventosView.editarEvento()"+ e.getMessage());
             mensaje = "swal(\"Error editando!\", \"Evento!\", \"error\");";
            
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void eliminarEvento(Eventos eventRemover){
     String mensaje = "";
        try {
            eventosFacadeLocal.remove(eventRemover);
            listarEventos.remove(eventRemover);
            mensaje = "swal(\"Evento Eliminado!\", \"Correctamente!\", \"success\");";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionEventosView.eliminarEvento()"+ e.getMessage());
             mensaje = "swal(\"Error eliminando!\", \"Evento!\", \"error\");";
            
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
            parametro.put("Usuario", usuarioSession.getUsulogin().getNombres() + "" + usuarioSession.getUsulogin().getApellidos() );
            parametro.put("images", context.getRealPath("vendors/images/chispitas-logo.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chispitasMagicas", "root", "toor");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/app/reportes/EventosReporte.jasper"));
             
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
    
    public GestionEventosView() {
        this.usuarioSession = new UsuarioSession();
    }

    public Eventos getEventosCrear() {
        return eventosCrear;
    }

    public void setEventosCrear(Eventos eventosCrear) {
        this.eventosCrear = eventosCrear;
    }

    public Eventos getEventosEditar() {
        return eventosEditar;
    }

    public void setEventosEditar(Eventos eventosEditar) {
        this.eventosEditar = eventosEditar;
    }

    public ArrayList<Eventos> getListarEventos() {
        return listarEventos;
    }

    public void setListarEventos(ArrayList<Eventos> listarEventos) {
        this.listarEventos = listarEventos;
    }

    public ArrayList<Eventos> getMuestraEventos() {
        return muestraEventos;
    }

    public void setMuestraEventos(ArrayList<Eventos> muestraEventos) {
        this.muestraEventos = muestraEventos;
    }
    
}
