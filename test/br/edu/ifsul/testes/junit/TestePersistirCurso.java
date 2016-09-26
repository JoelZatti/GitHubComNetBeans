package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
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
public class TestePersistirCurso {
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirCurso() {
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
            Curso c = new Curso();
            c.setNome("TSPI");
            c.setSigla("TSPI");
            c.setDescricao("Curso superior");
            c.setAtivo(true);
            c.setInicioAtividades(Calendar.getInstance());
            c.setInstituicao(em.find(Instituicao.class, 1));
            Disciplina d = em.find(Disciplina.class, 1);
            c.getDisciplina().add(d);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado com o que ocorreu
        Assert.assertEquals(false, exception);
    }
    
}
