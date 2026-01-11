package com.certipath.adapters.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RouteEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    String id;
    String name;
}

