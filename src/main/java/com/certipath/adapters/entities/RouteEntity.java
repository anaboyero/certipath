package com.certipath.adapters.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RouteEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    String name;

    public RouteEntity(String name) {
        this.name = name;
    }

    public RouteEntity() {

    }

    public RouteEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}



