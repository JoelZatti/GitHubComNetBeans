package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
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
public class TestePersistirAluno {
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirAluno() {
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
            Aluno a = new Aluno();
            a.setNome("Joel");
            a.setEmail("joelzatti@gmail.com");
            a.setNascimento(Calendar.getInstance());
            Disciplina d = em.find(Disciplina.class, 1);
            a.getAluno_disciplina().add(d);
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado com o que ocorreu
        Assert.assertEquals(false, exception);
    }
    
}
