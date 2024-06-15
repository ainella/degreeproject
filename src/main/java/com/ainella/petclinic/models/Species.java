package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Species {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static class Mapper implements RowMapper<Species> {
        @Override
        public Species mapRow(ResultSet rs, int rowNum) throws SQLException {
            Species species = new Species();

            species.setId(rs.getInt("id"));
            species.setName(rs.getString("name"));
            return species;
        }
    }
}
