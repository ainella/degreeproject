package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Address;
import com.ainella.petclinic.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Address getAddress(Integer id) {
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

}
