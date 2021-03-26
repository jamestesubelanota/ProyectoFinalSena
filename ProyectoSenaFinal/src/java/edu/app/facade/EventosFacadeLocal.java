/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Eventos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jamesmaldonado
 */
@Local
public interface EventosFacadeLocal {

    void create(Eventos eventos);

    void edit(Eventos eventos);

    void remove(Eventos eventos);

    Eventos find(Object id);

    List<Eventos> findAll();

    List<Eventos> findRange(int[] range);

    int count();
    
}
