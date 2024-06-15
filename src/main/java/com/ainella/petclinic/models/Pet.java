package com.ainella.petclinic.models;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet{
    private Integer id;
    private Integer speciesId;
    private String breed;
    private Date birthDay;
    private Integer ownerId;
    private String speciesName;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public static class Mapper implements RowMapper<Pet> {
        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pet pet = new Pet();

            pet.setId(rs.getInt("id"));
            pet.setSpeciesId(rs.getInt("species_id"));
            pet.setBreed(rs.getString("breed"));
            pet.setBirthDay(rs.getDate("birth_date"));
            pet.setOwnerId(rs.getInt("owner_id"));
            pet.setSpeciesName(rs.getString("species_name"));
            pet.setName(rs.getString("name"));
            return pet;
        }
    }
}
