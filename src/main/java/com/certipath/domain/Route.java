package com.certipath.domain;

public class Route {
    String id;
    String name;

    public Route() {
    }

    public Route(String name) {
        this.name = name;
    }

    public Route(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
