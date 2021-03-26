/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Registropago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jamesmaldonado
 */
@Local
public interface RegistropagoFacadeLocal {

    void create(Registropago registropago);

    void edit(Registropago registropago);

    void remove(Registropago registropago);

    Registropago find(Object id);

    List<Registropago> findAll();

    List<Registropago> findRange(int[] range);

    int count();
    
}
