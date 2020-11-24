package ru.geekbrains.lesson3.hw3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_drones"
            , joinColumns = @JoinColumn(name = "drone_id")
            , inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers;

    public Drone() {
    }

    public Drone(String title, int price) {
        this.title = title;
        this.price = price;
    }

//    public void addCustomerToDrone (Customer customer) {
//        if (customers == null)
//            customers = new ArrayList<>();
//        customers.add(customer);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
