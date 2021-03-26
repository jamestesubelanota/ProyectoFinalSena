/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Cotizador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jamesmaldonado
 */
@Stateless
public class CotizadorFacade extends AbstractFacade<Cotizador> implements CotizadorFacadeLocal {

    @PersistenceContext(unitName = "ProyectoSenaFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizadorFacade() {
        super(Cotizador.class);
    }
    
}
