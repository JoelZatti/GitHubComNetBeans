package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Nota;
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
public class TestePersistirNota {
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirNota() {
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
            Nota n = new Nota();
            n.setNota01(8.0);
            n.setNota02(9.0);
            n.setMedia((n.getNota01() + n.getNota02()) / 2);
            n.setAluno(em.find(Aluno.class, 1));
            n.setDisciplina(em.find(Disciplina.class, 1));
            em.getTransaction().begin();
            em.persist(n);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado com o que ocorreu
        Assert.assertEquals(false, exception);
    }
    
}
