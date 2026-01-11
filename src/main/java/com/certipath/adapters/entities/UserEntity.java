package com.certipath.adapters.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    String name;

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
