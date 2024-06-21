package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Appointment;
import com.ainella.petclinic.models.Clinic;
import com.ainella.petclinic.models.Owner;
import com.ainella.petclinic.services.AppointmentService;
import com.ainella.petclinic.services.OwnerService;
import com.ainella.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PetService petService;
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    public String newAppointment(Principal principal, Model model,@RequestParam Integer clinicId) {
        Integer ownerId = ownerService.getOwnerIdByUsername(principal.getName());
        Appointment appointment = new Appointment();
        appointment.setClinicId(clinicId);
        model.addAttribute("appointment",appointment);
        model.addAttribute("pets",petService.getPetsByOwnerId(ownerId));
        return "appointment";
    }

    @PostMapping()
    public String saveAppointment(Model model, @ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/owner";
    }

}