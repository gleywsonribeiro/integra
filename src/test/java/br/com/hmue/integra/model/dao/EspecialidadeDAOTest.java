/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.model.dao;

import br.com.hmue.integra.model.Especialidade;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gleywson
 */
public class EspecialidadeDAOTest {
    
    public EspecialidadeDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of atualizar method, of class EspecialidadeDAO.
     */
    @org.junit.Test
    public void testAtualizar() {
        System.out.println("atualizar");
        Especialidade e = null;
        try {
            e = new EspecialidadeDAO().buscar(30L);
            e.setDescricao("TECNOLOGICA");
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadeDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        EspecialidadeDAO instance = new EspecialidadeDAO();
        instance.atualizar(e);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class EspecialidadeDAO.
     */
    @org.junit.Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        Long codigo = 30L;
        EspecialidadeDAO instance = new EspecialidadeDAO();
        Especialidade expResult = new Especialidade();
        
        expResult.setCodigo(30l);
//        expResult.setDescricao();
        
        Especialidade result = instance.buscar(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
