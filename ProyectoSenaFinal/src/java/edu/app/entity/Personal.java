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
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p")
    , @NamedQuery(name = "Personal.findByIdpersonal", query = "SELECT p FROM Personal p WHERE p.idpersonal = :idpersonal")
    , @NamedQuery(name = "Personal.findByNombres", query = "SELECT p FROM Personal p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Personal.findByApellidos", query = "SELECT p FROM Personal p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Personal.findByDirecion", query = "SELECT p FROM Personal p WHERE p.direcion = :direcion")
    , @NamedQuery(name = "Personal.findByCelular", query = "SELECT p FROM Personal p WHERE p.celular = :celular")
    , @NamedQuery(name = "Personal.findByCorreo", query = "SELECT p FROM Personal p WHERE p.correo = :correo")
    , @NamedQuery(name = "Personal.findByTDocumento", query = "SELECT p FROM Personal p WHERE p.tDocumento = :tDocumento")
    , @NamedQuery(name = "Personal.findByNumeroIdentificacion", query = "SELECT p FROM Personal p WHERE p.numeroIdentificacion = :numeroIdentificacion")
    , @NamedQuery(name = "Personal.findByCargo", query = "SELECT p FROM Personal p WHERE p.cargo = :cargo")
    , @NamedQuery(name = "Personal.findByGenero", query = "SELECT p FROM Personal p WHERE p.genero = :genero")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonal")
    private Integer idpersonal;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "direcion")
    private String direcion;
    @Size(max = 45)
    @Column(name = "celular")
    private String celular;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;
    @Size(max = 45)
    @Column(name = "tDocumento")
    private String tDocumento;
    @Size(max = 45)
    @Column(name = "numeroIdentificacion")
    private String numeroIdentificacion;
    @Size(max = 45)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 45)
    @Column(name = "genero")
    private String genero;
    @OneToMany(mappedBy = "idper", fetch = FetchType.LAZY)
    private Collection<Cotizador> cotizadorCollection;
    @OneToMany(mappedBy = "idper", fetch = FetchType.LAZY)
    private Collection<Registropago> registropagoCollection;

    public Personal() {
    }

    public Personal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTDocumento() {
        return tDocumento;
    }

    public void setTDocumento(String tDocumento) {
        this.tDocumento = tDocumento;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @XmlTransient
    public Collection<Cotizador> getCotizadorCollection() {
        return cotizadorCollection;
    }

    public void setCotizadorCollection(Collection<Cotizador> cotizadorCollection) {
        this.cotizadorCollection = cotizadorCollection;
    }

    @XmlTransient
    public Collection<Registropago> getRegistropagoCollection() {
        return registropagoCollection;
    }

    public void setRegistropagoCollection(Collection<Registropago> registropagoCollection) {
        this.registropagoCollection = registropagoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonal != null ? idpersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.idpersonal == null && other.idpersonal != null) || (this.idpersonal != null && !this.idpersonal.equals(other.idpersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Personal[ idpersonal=" + idpersonal + " ]";
    }
    
}
