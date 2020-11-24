package ru.geekbrains.lesson3.hw3;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson3.hw3.entity.Customer;
import ru.geekbrains.lesson3.hw3.entity.Drone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Drone.class)
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        clear(em);

        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Customer customer1 = new Customer("Tom");
            Customer customer2 = new Customer("Ann");
            Customer customer3 = new Customer("Ron");
            Drone drone1 = new Drone("Mavic", 1200);
            Drone drone2 = new Drone("Phantom", 1400);
            Drone drone3 = new Drone("Autel",2000);

            customer1.addDroneToCustomer(drone1);
            customer1.addDroneToCustomer(drone2);
            customer2.addDroneToCustomer(drone1);
            customer3.addDroneToCustomer(drone3);

            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);

            transaction.commit();

//            Drone drone = em.find(Drone.class, drone1.getId());
//            System.out.println(drone);
//
//            Customer customer = em.find(Customer.class, customer2.getId());
//            System.out.println(customer);


//            List customers = em.createQuery("FROM Customer").getResultList();
//            System.out.println(customers);
//
//            List drones = em.createQuery("FROM Drone").getResultList();
//            System.out.println(drones);

            Customer customer = em.find(Customer.class, customer3.getId());
            System.out.println(customer);
            System.out.println(customer.getDrones());

//            Drone drone = em.find(Drone.class, drone3.getId());
//            System.out.println(drone);
//            System.out.println(drone.getCustomers());

            transaction.begin();
            em.remove(customer);
            transaction.commit();

            List customers = em.createQuery("FROM Customer").getResultList();
            System.out.println(customers);

        } finally {
            factory.close();
            if (em != null) {
                em.close();
            }
        }
    }

    private static void clear(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Customer").executeUpdate();
        em.createQuery("DELETE FROM Drone").executeUpdate();
        em.getTransaction().commit();
    }
}
