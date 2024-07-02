package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserDetailsManager userDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AddressService addressService;

    public Owner getOwnerByUsername(String username) {
        String query = "SELECT * FROM OWNERS WHERE username = ?";
        Owner owner = jdbcTemplate.queryForObject(query, new Owner.Mapper(), username);
        if(owner != null) {
            owner.setAddress(addressService.getAddress(owner.getAddressId()));
        }
        return owner;
    }

    public Integer getOwnerIdByUsername(String username) {
        String query = "SELECT id FROM OWNERS WHERE username = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, username);
    }

    @Transactional
    public void createOwner(Owner owner) {
        //Create the user
        UserDetails user = User.builder()
                .username(owner.getUsername())
                .password(passwordEncoder.encode(owner.getPassword()))
                .roles("USER")
                .build();
        //Save the user
        userDetailsManager.createUser(user);
        //Save owner profile
        jdbcTemplate.update("insert into owners(fullname, address, phone, email, username)\n" +
                "values(?,?,?,?,?)",
                owner.getFullname(), owner.getAddress(), owner.getPhone(), owner.getEmail(), owner.getUsername());
    }

}
