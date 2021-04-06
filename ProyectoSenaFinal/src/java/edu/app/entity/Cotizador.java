/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jamesmaldonado
 */
@Entity
@Table(name = "cotizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizador.findAll", query = "SELECT c FROM Cotizador c")
    , @NamedQuery(name = "Cotizador.findByIdcotizacion", query = "SELECT c FROM Cotizador c WHERE c.idcotizacion = :idcotizacion")
    , @NamedQuery(name = "Cotizador.findByFecha", query = "SELECT c FROM Cotizador c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Cotizador.findByDescripcion", query = "SELECT c FROM Cotizador c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Cotizador.findByNombreCliente", query = "SELECT c FROM Cotizador c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Cotizador.findByNombreEmpleado", query = "SELECT c FROM Cotizador c WHERE c.nombreEmpleado = :nombreEmpleado")
    , @NamedQuery(name = "Cotizador.findByNombreEvento", query = "SELECT c FROM Cotizador c WHERE c.nombreEvento = :nombreEvento")})
public class Cotizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcotizacion")
    private Integer idcotizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 55)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Size(max = 45)
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Size(max = 45)
    @Column(name = "nombre_evento")
    private String nombreEvento;
    @JoinColumn(name = "idcli", referencedColumnName = "idclientes")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes idcli;
    @JoinColumn(name = "idevento", referencedColumnName = "ideventos")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eventos idevento;
    @JoinColumn(name = "idper", referencedColumnName = "idpersonal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personal idper;

    public Cotizador() {
    }

    public Cotizador(Integer idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public Cotizador(Integer idcotizacion, Date fecha) {
        this.idcotizacion = idcotizacion;
        this.fecha = fecha;
    }

    public Integer getIdcotizacion() {
        return idcotizacion;
    }

    public void setIdcotizacion(Integer idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Clientes getIdcli() {
        return idcli;
    }

    public void setIdcli(Clientes idcli) {
        this.idcli = idcli;
    }

    public Eventos getIdevento() {
        return idevento;
    }

    public void setIdevento(Eventos idevento) {
        this.idevento = idevento;
    }

    public Personal getIdper() {
        return idper;
    }

    public void setIdper(Personal idper) {
        this.idper = idper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcotizacion != null ? idcotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizador)) {
            return false;
        }
        Cotizador other = (Cotizador) object;
        if ((this.idcotizacion == null && other.idcotizacion != null) || (this.idcotizacion != null && !this.idcotizacion.equals(other.idcotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Cotizador[ idcotizacion=" + idcotizacion + " ]";
    }
    
}
