package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Clinic;
import com.ainella.petclinic.models.Owner;
import com.ainella.petclinic.models.Pet;
import com.ainella.petclinic.models.Species;
import com.ainella.petclinic.services.ClinicService;
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
@RequestMapping("/clinic")
public class ClinicController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClinicService clinicService;
    @GetMapping("/")
    public  String getList(Model model) {
        List<Clinic> clinics = clinicService.getList();
        model.addAttribute("clinics",clinics);

        return "clinics";
    }
    @GetMapping("/id/{id}")
    public String getClinicById(@PathVariable("id")Integer id, Model model) {
        Clinic clinic = clinicService.getClinic(id);
        model.addAttribute("clinic",clinic);

        return "clinic";
    }
    @PostMapping("/")
    public String saveClinic(Model model, @ModelAttribute Clinic clinic) {
        Clinic clinic = clinicService.saveClinic();
        return "redirect:/clinic/id/" + clinic.getId().toString();
    }
}
