package ru.geekbrains.lesson3.hw3;

import org.hibernate.Cache;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson3.hw3.entity.Customer;
import ru.geekbrains.lesson3.hw3.entity.Drone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Drone.class)
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Customer customer1 = new Customer("Tom");
            Drone drone1 = new Drone("Mavic", 1200);
            Drone drone2 = new Drone("Phantom", 1400);

            customer1.addDroneToCustomer(drone1);
            customer1.addDroneToCustomer(drone2);

            em.persist(customer1);

            transaction.commit();

        } finally {
            factory.close();
            if (em != null) {
                em.close();
            }
        }
    }
}
