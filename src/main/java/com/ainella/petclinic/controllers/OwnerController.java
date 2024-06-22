package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Appointment;
import com.ainella.petclinic.models.Clinic;
import com.ainella.petclinic.models.Owner;
import com.ainella.petclinic.models.Pet;
import com.ainella.petclinic.services.AppointmentService;
import com.ainella.petclinic.services.ClinicService;
import com.ainella.petclinic.services.OwnerService;
import com.ainella.petclinic.services.PetService;
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

    @Autowired
    private PetService petService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    public String getOwner(Principal principal, Model model) {
        Owner owner = ownerService.getOwnerByUsername(principal.getName());
        if (owner == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find owner");
        }
        model.addAttribute("owner",owner);
        model.addAttribute("username",principal.getName());

        List<Pet> pets = petService.getPetsByOwnerId(owner.getId());
        model.addAttribute("pets",pets);
        List<Appointment> appointments = appointmentService.getListByOwnerId(owner.getId());
        model.addAttribute("appointments",appointments);

        List<Clinic> clinics = clinicService.getListByOwnerId(owner.getId());
        model.addAttribute("clinics",clinics);
        return "owner";
    }

    @GetMapping("/clinic/id/{id}")
    public String getMyClinic(Principal principal,@PathVariable("id")Integer id, Model model)
    {
        Clinic clinic = clinicService.getClinic(id);
        model.addAttribute("clinic",clinic);
        List<Appointment> appointments = appointmentService.getListByClinicId(id);
        model.addAttribute("appointments",appointments);
        return "my_clinic";
    }

    @PostMapping()
    public String saveOwner(Model model, @ModelAttribute Owner owner) {
        jdbcTemplate.update("update owners \n" +
                "set fullname = ?, address = ?,phone = ?, email = ?\n" +
                "where id = ?",owner.getFullname(),owner.getAddress(),owner.getPhone(),owner.getEmail(),owner.getId());
        return "redirect:/owner";
    }

}
