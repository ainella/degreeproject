package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Clinic;
import com.ainella.petclinic.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClinicService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AddressService addressService;

    public Clinic getClinic(Integer id) {
        String query = "SELECT * FROM CLINIC WHERE ID = ?";
        Clinic clinic = jdbcTemplate.queryForObject(query, new Clinic.Mapper(), id);
        if (clinic != null) {
            clinic.setAddress(addressService.getAddress(clinic.getAddressId()));
        }
        return clinic;
    }
    public List<Clinic> getList() {
        String query = "SELECT * FROM CLINIC";
        List<Clinic> clinics = jdbcTemplate.query(query, new Clinic.Mapper());
        for(Clinic clinic: clinics ) {
            clinic.setAddress(addressService.getAddress(clinic.getAddressId()));
        }
        return clinics;
    }

    @Transactional
    public void saveClinic(Clinic clinic) {
        Integer addressId = addressService.saveAddress(clinic.getAddress());
        if (clinic.getId() == null) {
            jdbcTemplate.update("insert into clinic (owner_id,name, email,phone, address_id) values(?,?,?,?,?)",
                    clinic.getOwnerId(), clinic.getName(), clinic.getEmail(), clinic.getPhone(), addressId);
        } else {
            jdbcTemplate.update("update clinic \n" +
                    "set name = ?, email = ?,phone = ?, address_id = ?\n" +
                    "where id = ?", clinic.getName(), clinic.getEmail(), clinic.getPhone(),addressId, clinic.getId());
        }
    }

    public List<Clinic> getListByOwnerId(Integer ownerId){
        String query = "select * from clinic where owner_id = ? ";
        List<Clinic> clinics = jdbcTemplate.query(query,new Clinic.Mapper(), ownerId);
        for(Clinic clinic: clinics ) {
            clinic.setAddress(addressService.getAddress(clinic.getAddressId()));
        }
        return clinics;
    }
}
