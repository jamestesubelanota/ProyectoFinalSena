/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Agendar;
import edu.app.facade.AgendarFacadeLocal;
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
@Named(value = "gestionAgendarView")
@ViewScoped
public class GestionAgendarView implements Serializable{

    
    
    
    
    
    @Inject
    UsuarioSession usuarioSession;
     
     
    @EJB
    AgendarFacadeLocal agendarFacadeLocal;
    
            Agendar agregarAg = new Agendar();
            Agendar editarAgen = new Agendar();
    private ArrayList<Agendar> listarAgenda = new ArrayList<>();    
    
    @PostConstruct
    public void cagarListica(){
    listarAgenda.addAll(agendarFacadeLocal.findAll());
    
    }
    
    
    
    
    public void cagarListaAgrnda(Agendar agendaRemov){
        editarAgen = agendaRemov;
    
    }
    
    
    //metodos 
    public void crearAgenda(){
    
        String mensaje ="";
        try {
            agendarFacadeLocal.create(agregarAg);
            Agendar nuevo = new Agendar();
            mensaje = "swal(\"EVento agendado !\", \"Correctamente!\", \"success\");";
            
            
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionAgendarView.crearAgenda()");
             mensaje = "swal(\"EVento agendado !\", \"Correctamente!\", \"error\");";
        }
    PrimeFaces.current().executeScript(mensaje);
    }
    
    public void editarAgenda(){
    
        String mensaje ="";
        try {
            agendarFacadeLocal.edit(editarAgen);
            mensaje = "swal(\"EVento editado !\", \"Correctamente!\", \"success\");";
            
            
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionAgendarView.crearAgenda()");
             mensaje = "swal(\"EVento no editado !\", \"Correctamente!\", \"error\");";
        }
    PrimeFaces.current().executeScript(mensaje);
    }
    
     public void eliminarClientes(Agendar agendaRemov){
    String mensjae = "";
    
        try {
            agendarFacadeLocal.remove(agendaRemov);
            listarAgenda.remove(agendaRemov);
            mensjae = "swal(\"agenda Eliminado!\", \"Correctamente!\", \"success\");";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.GestionClientesView.eliminarClientes()" + e.getMessage());
             mensjae = "swal(\" Error Eliminado!\", \"Correctamente!\", \"error\");";
        }
    PrimeFaces.current().executeScript(mensjae);
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
            parametro.put("images", context.getRealPath("/assets/img/logo nuevo.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chispitasMagicas", "root", "toor");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/app/reportes/reportagenda.jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista Usuarios.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.getionEVENTOSiew.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.webapp1966781a.controlador.gestionEvenetos.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.webapp1966781a.controlador.GestionEventos.descargaReporte() " + q.getMessage());
        }
 
    }

    public Agendar getAgregarAg() {
        return agregarAg;
    }

    public void setAgregarAg(Agendar agregarAg) {
        this.agregarAg = agregarAg;
    }

    public Agendar getEditarAgen() {
        return editarAgen;
    }

    public void setEditarAgen(Agendar editarAgen) {
        this.editarAgen = editarAgen;
    }

    public ArrayList<Agendar> getListarAgenda() {
        return listarAgenda;
    }

    public void setListarAgenda(ArrayList<Agendar> listarAgenda) {
        this.listarAgenda = listarAgenda;
    }
    /**
     * Creates a new instance of GestionAgendarView
     */
    public GestionAgendarView() {
    }
    
}
