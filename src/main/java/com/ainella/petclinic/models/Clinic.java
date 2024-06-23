package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Clinic {
    private Integer id;
    private Integer ownerId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class Mapper implements RowMapper<Clinic> {
        @Override
        public Clinic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Clinic clinic = new Clinic();

            clinic.setId(rs.getInt("id"));
            clinic.setOwnerId(rs.getInt("owner_id"));
            clinic.setName(rs.getString("name"));
            clinic.setEmail(rs.getString("email"));
            clinic.setPhone(rs.getString("phone"));
            clinic.setAddress(rs.getString("address"));
            return clinic;
        }
    }
}
