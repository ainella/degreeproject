package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Pet;
import com.ainella.petclinic.models.Species;
import com.ainella.petclinic.services.OwnerService;
import com.ainella.petclinic.services.PetService;
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
    @Autowired
    private PetService petService;

    @GetMapping("/id/{id}")
    public String getPetById(@PathVariable("id")Integer id, Model model) {
        Pet pet = petService.getPetById(id);
        model.addAttribute("pet", pet);
        model.addAttribute("species", petService.getSpecies());
        return "pet";
    }
    @GetMapping("/new")
    public  String getNewPet(Principal principal, Model model) {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        Pet pet = new Pet();
        pet.setOwnerId(ownerId);
        model.addAttribute("pet",pet);
        model.addAttribute("species", petService.getSpecies());
        return "pet";
    }

    @PostMapping()
    public String savePet(Principal principal, Model model, @ModelAttribute Pet pet) {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        petService.savePet(pet);
        return "redirect:/owner/pets" + pet.getId().toString();
    }

}
