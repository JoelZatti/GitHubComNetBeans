package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.modelo.Nota;
import br.edu.ifsul.modelo.Professor;
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
public class TestePersistirDisciplina {
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirDisciplina() {
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
            Disciplina d = new Disciplina();
            d.setNome("DAW");
            d.setDescricao("Desenvolvimento de páginas WEB");
            d.setCurso(em.find(Curso.class, 1));
            d.setCargaHoraria(40.56);
            d.setConhecimentosMinimos("TI");
            Nota n = new Nota();
            n.setNota01(8.0);
            n.setNota02(9.0);
            n.setMedia((n.getNota01() + n.getNota02()) / 2);
            n.setAluno(em.find(Aluno.class, 1));
            //Nota n = em.find(Nota.class, 1);
            d.getNotas().add(n);
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
