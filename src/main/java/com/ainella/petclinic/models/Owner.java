package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Owner {
    private Integer id;
    private String fullname;
    private String address;
    private String phone;
    private String email;

    private String username;

    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Mapper implements RowMapper<Owner> {
        @Override
        public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Owner owner = new Owner();
            owner.setId(rs.getInt("id"));
            owner.setFullname(rs.getString("fullname"));
            owner.setAddress(rs.getString("address"));
            owner.setPhone(rs.getString("phone"));
            owner.setEmail(rs.getString("email"));
            owner.setUsername(rs.getString("username"));
            return owner;
        }
    }
}
