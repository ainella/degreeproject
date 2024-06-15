package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PetService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pet getPetByUsername(String name) {
        String query = "SELECT * FROM Pets WHERE username = ?";
        return jdbcTemplate.queryForObject(query, new Pet.Mapper(), name);
    }

    public Integer getPetIdByUsername(String name) {
        String query = "SELECT id FROM Pets WHERE username = ?";
        return jdbcTemplate.queryForObject(query, Integer.class,name);
    }
}
