package br.edu.ifsul.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joel Zatti joelzatti@gmail.com
 */
public class EntityManagerUtil {
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static EntityManager getEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("TRABALHO-1-2016-2-5N1-JoelPU");
        }
        if (em == null) {
            em = factory.createEntityManager();
        }
        return em;
    }

}
