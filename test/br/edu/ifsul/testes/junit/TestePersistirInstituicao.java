package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Instituicao;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Joel
 */
public class TestePersistirInstituicao {
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirInstituicao() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TRABALHO-1-2016-2-5N1-JoelPU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    @Test
    public void teste() {
        boolean exception = false;
        try {
            Instituicao it = new Instituicao();
            it.setNome("IFSUL");
            it.setAnoFundacao(Calendar.getInstance());
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado com o que ocorreu
        Assert.assertEquals(false, exception);
    }
    
}
