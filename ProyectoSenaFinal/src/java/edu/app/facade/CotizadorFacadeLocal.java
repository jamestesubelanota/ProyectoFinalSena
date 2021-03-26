/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Cotizador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jamesmaldonado
 */
@Local
public interface CotizadorFacadeLocal {

    void create(Cotizador cotizador);

    void edit(Cotizador cotizador);

    void remove(Cotizador cotizador);

    Cotizador find(Object id);

    List<Cotizador> findAll();

    List<Cotizador> findRange(int[] range);

    int count();
    
}
