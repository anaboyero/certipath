package com.certipath.adapters;

import com.certipath.adapters.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface RouteRepository extends JpaRepository <RouteEntity, String> {

}
