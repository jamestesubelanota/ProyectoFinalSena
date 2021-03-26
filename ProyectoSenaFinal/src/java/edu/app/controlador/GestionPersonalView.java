/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;


import edu.app.entity.Personal;
import edu.app.facade.PersonalFacadeLocal;
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
@Named(value = "gestionPersonalView")
@ViewScoped
public class GestionPersonalView implements Serializable{
    
    
    @Inject   
    UsuarioSession usuarioSession;
    @EJB
    PersonalFacadeLocal personalFacadeLocal;
    private Personal personalCrear = new Personal();
    private Personal personalEditar = new Personal();
    private ArrayList<Personal> listarPersonal =  new ArrayList<>();
    
    
    
    
    @PostConstruct
    public void cargarlistarPersonal(){
    listarPersonal.addAll(personalFacadeLocal.findAll());
    
    }
    public void cargarEditarPersonal(Personal persoremov){
      
     personalEditar = persoremov;
    }

    /**
     * Creates a new instance of GestionPersonalView
     * 
     */
    
     public void registrarPersonal(){
     String mensaje = "";
         try {
             personalFacadeLocal.create(personalCrear);
            Personal personalnuevo = new Personal();
             mensaje = "swal(\"Personal Asignado!\", \"Correctamente !\", \"success\");"; 
         } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionPersonalView.registrarPersonal()" + e.getMessage());
              mensaje = "swal(\"Error Asignado!\", \"Personal !\", \"Error\");"; 
         }
         PrimeFaces.current().executeScript(mensaje);
     }
      public void editarPersonal(){
     String mensaje = "";
         try {
             personalFacadeLocal.edit(personalEditar);
         
             mensaje = "swal(\"Personal Editado!\", \"Correctamente !\", \"success\");"; 
         } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionPersonalView.editarPersonal()" + e.getMessage());
              mensaje = "swal(\"Error Editando!\", \"Personal !\", \"Error\");"; 
         }
         PrimeFaces.current().executeScript(mensaje);
     }
     
      public void elimiarPersonal(Personal persoremov){
     String mensaje = "";
         try {
             personalFacadeLocal.remove(persoremov);
             listarPersonal.remove(persoremov);
          
             mensaje = "swal(\"Personal Eliminado!\", \"Correctamente !\", \"success\");"; 
         } catch (Exception e) {
             System.out.println("edu.app.controlador.GestionPersonalView.elimiarPersonal" + e.getMessage());
              mensaje = "swal(\"Error Eliminando!\", \"Personal !\", \"Error\");"; 
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
            parametro.put("FichaReporte", "1966821");
            parametro.put("UsuarioReporte", usuarioSession.getUsulogin().getNombres() + "" + usuarioSession.getUsulogin().getApellidos() );
            parametro.put("Jimagen", context.getRealPath("vendors/images/reportes.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chispitasMagicas", "root", "toor");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/app/reportes/ReportePersonal.jasper"));
             
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


      
      
      
    public GestionPersonalView() {
    }

    public Personal getPersonalCrear() {
        return personalCrear;
    }

    public void setPersonalCrear(Personal personalCrear) {
        this.personalCrear = personalCrear;
    }

    public Personal getPersonalEditar() {
        return personalEditar;
    }

    public void setPersonalEditar(Personal personalEditar) {
        this.personalEditar = personalEditar;
    }

    public ArrayList<Personal> getListarPersonal() {
        return listarPersonal;
    }

    public void setListarPersonal(ArrayList<Personal> listarPersonal) {
        this.listarPersonal = listarPersonal;
    }
    
}
