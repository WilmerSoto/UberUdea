/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.entity.Conductor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wilmer Soto
 */
@Local
public interface ConductorFacadeLocal {

    void create(Conductor conductor);

    void edit(Conductor conductor);

    void remove(Conductor conductor);

    Conductor find(Object id);

    List<Conductor> findAll();

    List<Conductor> findRange(int[] range);
    
    List<Conductor> getAllConductores();
    
    boolean doesCedulaExist(String cedula);

    int count();
    
}
