package com.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "service_platform",
            joinColumns = @JoinColumn(name = "platform_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;

    public Platform() {
    }

    public Platform(String name) {
        this.name = name;
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addServiceToPlatform(Service service) {
        if (services == null) {
            services = new ArrayList<>();
        }
        services.add(service);
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
