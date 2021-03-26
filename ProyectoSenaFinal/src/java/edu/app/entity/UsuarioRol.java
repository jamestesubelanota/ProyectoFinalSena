/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jamesmaldonado
 */
@Entity
@Table(name = "usuarioRol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")
    , @NamedQuery(name = "UsuarioRol.findByIdusuarioRol", query = "SELECT u FROM UsuarioRol u WHERE u.idusuarioRol = :idusuarioRol")})
public class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuarioRol")
    private Integer idusuarioRol;
    @JoinColumn(name = "idRol", referencedColumnName = "idrol")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol idRol;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idusuario;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer idusuarioRol) {
        this.idusuarioRol = idusuarioRol;
    }

    public Integer getIdusuarioRol() {
        return idusuarioRol;
    }

    public void setIdusuarioRol(Integer idusuarioRol) {
        this.idusuarioRol = idusuarioRol;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuarioRol != null ? idusuarioRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.idusuarioRol == null && other.idusuarioRol != null) || (this.idusuarioRol != null && !this.idusuarioRol.equals(other.idusuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.UsuarioRol[ idusuarioRol=" + idusuarioRol + " ]";
    }
    
}
