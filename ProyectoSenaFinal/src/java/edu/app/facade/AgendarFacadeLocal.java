/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Agendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jamesmaldonado
 */
@Local
public interface AgendarFacadeLocal {

    void create(Agendar agendar);

    void edit(Agendar agendar);

    void remove(Agendar agendar);

    Agendar find(Object id);

    List<Agendar> findAll();

    List<Agendar> findRange(int[] range);

    int count();
    
}
