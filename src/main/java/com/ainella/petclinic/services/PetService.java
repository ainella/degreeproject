package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pet> getPetsByOwnerId(Integer ownerId) {
        String petsQuery = "select p.*, s.\"name\" species_name \n" +
                "from pets p\n" +
                "join species s on s.id = p.species_id \n" +
                "where p.owner_id = ?";
        return jdbcTemplate.query(petsQuery, new Pet.Mapper(), ownerId);
    }
}
