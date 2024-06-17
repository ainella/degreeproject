package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClinicService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Clinic getClinic(Integer id) {
        String query = "SELECT * FROM CLINIC WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new Clinic.Mapper(), id);
    }
    public List<Clinic> getList() {
        String query = "SELECT * FROM CLINIC";
        return jdbcTemplate.query(query, new Clinic.Mapper());
    }
    public void saveClinic(Clinic clinic){
        jdbcTemplate.update("update clinic \n" +
                "set name = ?, email = ?,phone = ?, address = ?\n" +
                "where id = ?",clinic.getName(),clinic.getEmail(),clinic.getPhone(),clinic.getAddress(),clinic.getId());
    }
    public List<Clinic> getListByOwnerId(Integer ownerId){
        String query = "select * from clinic where owner_id = ? ";
        return jdbcTemplate.query(query,new Clinic.Mapper(), ownerId);
    }
}
