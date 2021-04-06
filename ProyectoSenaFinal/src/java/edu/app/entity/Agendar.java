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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "agendar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agendar.findAll", query = "SELECT a FROM Agendar a")
    , @NamedQuery(name = "Agendar.findById", query = "SELECT a FROM Agendar a WHERE a.id = :id")
    , @NamedQuery(name = "Agendar.findByNombreEvento", query = "SELECT a FROM Agendar a WHERE a.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "Agendar.findByFechaEvento", query = "SELECT a FROM Agendar a WHERE a.fechaEvento = :fechaEvento")
    , @NamedQuery(name = "Agendar.findByCliente", query = "SELECT a FROM Agendar a WHERE a.cliente = :cliente")
    , @NamedQuery(name = "Agendar.findByPersonal", query = "SELECT a FROM Agendar a WHERE a.personal = :personal")
    , @NamedQuery(name = "Agendar.findByNota", query = "SELECT a FROM Agendar a WHERE a.nota = :nota")})
public class Agendar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_evento")
    private String nombreEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_evento")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cliente")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "personal")
    private String personal;
    @Size(max = 200)
    @Column(name = "nota")
    private String nota;

    public Agendar() {
    }

    public Agendar(Integer id) {
        this.id = id;
    }

    public Agendar(Integer id, String nombreEvento, Date fechaEvento, String cliente, String personal) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.cliente = cliente;
        this.personal = personal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agendar)) {
            return false;
        }
        Agendar other = (Agendar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Agendar[ id=" + id + " ]";
    }
    
}
