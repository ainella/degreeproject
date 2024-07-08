package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Address;
import com.ainella.petclinic.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Country getCountry(String code){
        String query = "select name, code from countries c where code = ?";
        return jdbcTemplate.queryForObject(query, new Country.Mapper(), code);
    }
    public List<Country> getList() {
        String query = "select name, code from countries c";
        return jdbcTemplate.query(query, new Country.Mapper());
    }
}
