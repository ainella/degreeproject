package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
    private Integer id;
    private String country;
    private String region;
    private String city;
    private String zipCode;
    private String addressLine1;
    private String addressLine2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public static class Mapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();

            address.setId(rs.getInt("id"));
            address.setCountry(rs.getString("country"));
            address.setRegion(rs.getString("region"));
            address.setCity(rs.getString("city"));
            address.setZipCode(rs.getString("zip_code"));
            address.setAddressLine1(rs.getString("address_line_1"));
            address.setAddressLine2(rs.getString("address_line_2"));
            return address;
        }
    }
}
