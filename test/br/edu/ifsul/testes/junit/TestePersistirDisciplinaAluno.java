package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Curso;
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
public class TestePersistirDisciplinaAluno {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirDisciplinaAluno() {
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
            Disciplina d = em.find(Disciplina.class, 2);
            Aluno a = em.find(Aluno.class, 1);            
            d.getDisciplina_aluno().add(a);
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado com o que ocorreu
        Assert.assertEquals(false, exception);
    }

}
