package edu.app.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.app.entity.Inventario;
import edu.app.facade.InventarioFacadeLocal;
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
@Named(value = "gestionInventarioView")
@ViewScoped
public class GestionInventarioView implements Serializable {
    
    
    @Inject   
    UsuarioSession usuarioSession;
     
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
            parametro.put("Jimagen", context.getRealPath("assets/img/logo.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chispitasMagicas", "root", "toor");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/app/reportes/reportInventario.jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista Usuarios.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.gestionIvnetarioView.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.webapp1966781a.controlador.gestionIvnetarioView.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.webapp1966781a.controlador.gestionIvnetarioView.descargaReporte() " + q.getMessage());
        }
 
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
