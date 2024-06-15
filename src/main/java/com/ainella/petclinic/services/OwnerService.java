package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Owner getOwnerByUsername(String username) {
        String query = "SELECT * FROM OWNERS WHERE username = ?";
        return jdbcTemplate.queryForObject(query, new Owner.Mapper(), username);
    }

    public Integer getOwnerIdByUsername(String username) {
        String query = "SELECT id FROM OWNERS WHERE username = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, username);
    }
}
