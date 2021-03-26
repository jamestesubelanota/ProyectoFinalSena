/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jamesmaldonado
 */
@Entity
@Table(name = "eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e")
    , @NamedQuery(name = "Eventos.findByIdeventos", query = "SELECT e FROM Eventos e WHERE e.ideventos = :ideventos")
    , @NamedQuery(name = "Eventos.findByNombre", query = "SELECT e FROM Eventos e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Eventos.findByDescripcion", query = "SELECT e FROM Eventos e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Eventos.findByPrecio", query = "SELECT e FROM Eventos e WHERE e.precio = :precio")})
public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideventos")
    private Integer ideventos;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private Long precio;
    @OneToMany(mappedBy = "idevento", fetch = FetchType.LAZY)
    private Collection<Cotizador> cotizadorCollection;

    public Eventos() {
    }

    public Eventos(Integer ideventos) {
        this.ideventos = ideventos;
    }

    public Integer getIdeventos() {
        return ideventos;
    }

    public void setIdeventos(Integer ideventos) {
        this.ideventos = ideventos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Cotizador> getCotizadorCollection() {
        return cotizadorCollection;
    }

    public void setCotizadorCollection(Collection<Cotizador> cotizadorCollection) {
        this.cotizadorCollection = cotizadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideventos != null ? ideventos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.ideventos == null && other.ideventos != null) || (this.ideventos != null && !this.ideventos.equals(other.ideventos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Eventos[ ideventos=" + ideventos + " ]";
    }
    
}
