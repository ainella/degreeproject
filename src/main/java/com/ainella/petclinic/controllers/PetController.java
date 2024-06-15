package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Pet;
import com.ainella.petclinic.models.Species;
import com.ainella.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/id/{id}")
    public String getPetById(@PathVariable("id")Integer id, Model model) {
        String query = "select p.*, s.\"name\" species_name \n" +
                "from pets p\n" +
                "join species s on s.id = p.species_id \n" +
                "where p.id = ?";
        Pet pet = jdbcTemplate.queryForObject(query, new Pet.Mapper(), id);
        model.addAttribute("pet",pet);

        String speciesQuery = "select * from species s ";
        List<Species> species = jdbcTemplate.query(speciesQuery, new Species.Mapper());
        model.addAttribute("species",species);
        return "pet";
    }
    @GetMapping("/new")
    public  String getNewPet(Principal principal, Model model) {
        return "pet";
    }

    @PostMapping("/")
    public String savePet(Principal principal, Model model, @ModelAttribute Pet pet) {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        if(pet.getId() == null) {
            //todo insert
        } else {
            jdbcTemplate.update("update pets \n" +
                    "set species_id  = ?, breed = ?,birth_date  = ? , owner_id  = ?, name  = ?\n" +
                    "where id = ?", pet.getSpeciesId(), pet.getBreed(), pet.getBirthDay(), ownerId, pet.getName(), pet.getId());
        }
        return "redirect:/pet/id/" + pet.getId().toString();
    }

}
