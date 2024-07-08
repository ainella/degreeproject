package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Country {
    private String name;
    private  String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class Mapper implements RowMapper<Country> {
        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();

            country.setName(rs.getString("name"));
            country.setCode(rs.getString("code"));
            return  country;
        }
    }
}
