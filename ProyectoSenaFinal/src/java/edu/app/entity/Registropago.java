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
@Table(name = "registropago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registropago.findAll", query = "SELECT r FROM Registropago r")
    , @NamedQuery(name = "Registropago.findByIdregistropago", query = "SELECT r FROM Registropago r WHERE r.idregistropago = :idregistropago")
    , @NamedQuery(name = "Registropago.findByEstado", query = "SELECT r FROM Registropago r WHERE r.estado = :estado")
    , @NamedQuery(name = "Registropago.findByDescripcion", query = "SELECT r FROM Registropago r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Registropago.findByFecha", query = "SELECT r FROM Registropago r WHERE r.fecha = :fecha")})
public class Registropago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregistropago")
    private Integer idregistropago;
    @Column(name = "estado")
    private Boolean estado;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idcotiza", referencedColumnName = "idcotizacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cotizador idcotiza;
    @JoinColumn(name = "idper", referencedColumnName = "idpersonal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personal idper;

    public Registropago() {
    }

    public Registropago(Integer idregistropago) {
        this.idregistropago = idregistropago;
    }

    public Registropago(Integer idregistropago, Date fecha) {
        this.idregistropago = idregistropago;
        this.fecha = fecha;
    }

    public Integer getIdregistropago() {
        return idregistropago;
    }

    public void setIdregistropago(Integer idregistropago) {
        this.idregistropago = idregistropago;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cotizador getIdcotiza() {
        return idcotiza;
    }

    public void setIdcotiza(Cotizador idcotiza) {
        this.idcotiza = idcotiza;
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
        hash += (idregistropago != null ? idregistropago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registropago)) {
            return false;
        }
        Registropago other = (Registropago) object;
        if ((this.idregistropago == null && other.idregistropago != null) || (this.idregistropago != null && !this.idregistropago.equals(other.idregistropago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Registropago[ idregistropago=" + idregistropago + " ]";
    }
    
}
