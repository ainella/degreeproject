package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Owner;
import com.ainella.petclinic.models.Pet;
import com.ainella.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OwnerService ownerService;

    @GetMapping()
    public String getOwner(Principal principal, Model model) {
        Owner owner = ownerService.getOwnerByUsername(principal.getName());
        if (owner == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find owner");
        }
        model.addAttribute("owner",owner);
        model.addAttribute("username",principal.getName());

        String petsQuery = "select p.*, s.\"name\" species_name \n" +
                "from pets p\n" +
                "join species s on s.id = p.species_id \n" +
                "where p.owner_id = ?";
        List<Pet> pets = jdbcTemplate.query(petsQuery, new Pet.Mapper(), owner.getId());
        model.addAttribute("pets",pets);

        return "owner";
    }
    @PostMapping()
    public String saveOwner(Model model, @ModelAttribute Owner owner) {
        jdbcTemplate.update("update owners \n" +
                "set fullname = ?, address = ?,phone = ?, email = ?\n" +
                "where id = ?",owner.getFullname(),owner.getAddress(),owner.getPhone(),owner.getEmail(),owner.getId());
        return "redirect:/owner";
    }

}
