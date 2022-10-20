package peaksoft.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.config.DatabaseConfig;
import peaksoft.models.Client;

import java.util.List;
import java.util.Optional;

public class ClientRepository implements AutoCloseable {
    private SessionFactory sessionFactory = DatabaseConfig.createSessionFactory();
    private EntityManagerFactory entityManagerFactory = DatabaseConfig.createEntityManagerFactory();

    public void save(Client client) {
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(client);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<Client> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

       Client client = session.createQuery("select c from Client c " +
                "where c.email = :email", Client.class).
                setParameter("email", email).getSingleResult();

        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(client);
    }

    public Boolean existsByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select case when count(c) > 0 " +
                "then true" +
                " else false end" +
                " from Client c where  c.email = :email", Boolean.class).
                setParameter("email", email);

        Boolean result = (Boolean) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    public List<Client> getAllClient() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Client> clients = entityManager
                .createQuery("select c from Client c", Client.class)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return clients;
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
        entityManagerFactory.close();
    }

}
