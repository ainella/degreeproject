package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class AddressService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Address getAddress(Integer id) {
        if(id==null){
            return null;
        }
        String query = """
                select
                        id,
                        country_code,
                        region,
                        city,
                        zip_code,
                        address_line_1,
                        address_line_2,
                        c."name" country_name
                      from
                        address a
                      join countries c on
                        c.code = a.country_code
                      where
                        id = ?""";
        return jdbcTemplate.queryForObject(query, new Address.Mapper(), id);
    }

    public Integer saveAddress(Address address){
        if(address==null){
            return null;
        }
        if(address.getId()==null) {
            String sql = "insert into address (country_code,region,city,zip_code," +
                    "address_line_1,address_line_2) values(?,?,?,?,?,?) returning id";
            return jdbcTemplate.queryForObject(sql,new Object[] {address.getCountryCode(),address.getRegion(),address.getCity(),
                address.getZipCode(),address.getAddressLine1(),address.getAddressLine2()},Integer.class);
        } else {
            jdbcTemplate.update("update address \n" +
                    "set country_code = ?,region = ?, city = ?, zip_code = ?, address_line_1 = ?, address_line_2 = ?\n" +
                    "where id = ?", address.getCountryCode(),address.getRegion(),address.getCity(),address.getZipCode(),
                    address.getAddressLine1(),address.getAddressLine2(),address.getId());
            return address.getId();
        }
    }
}
