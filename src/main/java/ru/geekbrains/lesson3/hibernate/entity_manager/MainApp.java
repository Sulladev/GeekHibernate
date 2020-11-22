package ru.geekbrains.lesson3.hibernate.entity_manager;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson3.hibernate.entity.SimpleItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MainApp {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            SimpleItem newSimpleItem = new SimpleItem("Orange", 100);
            em.persist(newSimpleItem);
            em.getTransaction().commit();

            SimpleItem simpleItemFromDb = em.find(SimpleItem.class, newSimpleItem.getId());
            System.out.println(simpleItemFromDb);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
