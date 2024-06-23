package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Pet;
import com.ainella.petclinic.models.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pet getPetById(Integer id) {
        String petsQuery = "select p.*, s.\"name\" species_name \n" +
                "from pets p\n" +
                "join species s on s.id = p.species_id \n" +
                "where p.id = ?";
        return jdbcTemplate.queryForObject(petsQuery, new Pet.Mapper(), id);
    }

    public List<Pet> getPetsByOwnerId(Integer ownerId) {
        String petsQuery = "select p.*, s.\"name\" species_name \n" +
                "from pets p\n" +
                "join species s on s.id = p.species_id \n" +
                "where p.owner_id = ?";
        return jdbcTemplate.query(petsQuery, new Pet.Mapper(), ownerId);
    }

    public void savePet(Pet pet) {
        if(pet.getId() == null) {
            jdbcTemplate.update("insert into pets(owner_id, species_id, breed, birth_date, name) values(?,?,?,?,?)",
                    pet.getOwnerId(), pet.getSpeciesId(), pet.getBreed(), pet.getBirthDay(), pet.getName());
        } else {
            jdbcTemplate.update("update pets \n" +
                    "set species_id = ?, breed = ?, birth_date = ?, name = ?\n" +
                    "where id = ?", pet.getSpeciesId(), pet.getBreed(), pet.getBirthDay(),  pet.getName(), pet.getId());
        }
    }

    public List<Species> getSpecies() {
        String query = "select * from species s ";
        return jdbcTemplate.query(query, new Species.Mapper());
    }


}
