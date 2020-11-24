package ru.geekbrains.lesson3.hw3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_drones"
            , joinColumns = @JoinColumn(name = "customer_id")
            , inverseJoinColumns = @JoinColumn(name = "drone_id"))
    private List<Drone> drones;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public void addDroneToCustomer (Drone drone) {
        if (drones == null)
            drones = new ArrayList<>();
        drones.add(drone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
