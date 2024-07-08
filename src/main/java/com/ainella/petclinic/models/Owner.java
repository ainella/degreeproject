package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Owner {
    private Integer id;
    private String firstname;
    private String lastname;
    private String middlename;
    private Address address;
    private String phone;
    private String email;

    private String username;

    private String password;

    private Integer addressId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public static class Mapper implements RowMapper<Owner> {
        @Override
        public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Owner owner = new Owner();
            owner.setId(rs.getInt("id"));
            owner.setFirstname(rs.getString("firstname"));
            owner.setLastname(rs.getString("lastname"));
            owner.setMiddlename(rs.getString("middlename"));
            owner.setAddressId(rs.getInt("address_id"));
            if(rs.wasNull()){
                owner.setAddressId(null);
            }
            owner.setPhone(rs.getString("phone"));
            owner.setEmail(rs.getString("email"));
            owner.setUsername(rs.getString("username"));
            return owner;
        }
    }
}
