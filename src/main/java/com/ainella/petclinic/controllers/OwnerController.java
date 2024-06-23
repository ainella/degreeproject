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

    //-- Profile ----
    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        Owner owner = ownerService.getOwnerByUsername(principal.getName());
        if (owner == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find profile");
        }
        model.addAttribute("owner", owner);
        return "owner/profile";
    }

    @PostMapping("/profile")
    public String saveOwner(Model model, @ModelAttribute Owner owner) {
        jdbcTemplate.update("update owners \n" +
                "set fullname = ?, address = ?,phone = ?, email = ?\n" +
                "where id = ?", owner.getFullname(),owner.getAddress(),owner.getPhone(),owner.getEmail(),owner.getId());
        return "redirect:/owner/profile";
    }

    //-- Pets ----
    @GetMapping("/pets")
    public String getPets(Principal principal, Model model) {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        List<Pet> pets = petService.getPetsByOwnerId(ownerId);
        model.addAttribute("pets",pets);
        List<Appointment> appointments = appointmentService.getListByOwnerId(ownerId);
        model.addAttribute("appointments",appointments);
        return "owner/pets";
    }

    //-- My Clinic ----
    @GetMapping("/clinic")
    public String getMyClinic(Principal principal, Model model)
    {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        List<Clinic> clinics = clinicService.getListByOwnerId(ownerId);
        if (clinics.isEmpty()) {
            model.addAttribute("clinic", null);
        } else {
            Clinic clinic = clinics.get(0);
            model.addAttribute("clinic", clinic);
            List<Appointment> appointments = appointmentService.getListByClinicId(clinic.getId());
            model.addAttribute("appointments", appointments);
        }
        return "owner/my_clinic";
    }

    @GetMapping("/clinic/edit")
    public String editMyClinic(Principal principal, Model model)
    {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        List<Clinic> clinics = clinicService.getListByOwnerId(ownerId);
        Clinic clinic;
        if (clinics.isEmpty()) {
            clinic = new Clinic();
            clinic.setOwnerId(ownerId);
        } else {
            clinic = clinics.get(0);
        }
        model.addAttribute("clinic", clinic);
        return "owner/edit_clinic";
    }

    @PostMapping("/clinic")
    public String saveClinic(Model model, @ModelAttribute Clinic clinic) {
        clinicService.saveClinic(clinic);
        return "redirect:/owner/clinic";
    }

}
