package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Appointment;
import com.ainella.petclinic.models.ClinicRecord;
import com.ainella.petclinic.services.AppointmentService;
import com.ainella.petclinic.services.ClinicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/reception")
public class ReceptionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClinicRecordService clinicRecordService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment/{id}")
    public String newRecord(Principal principal, Model model, @PathVariable Integer id) {
        Appointment appointment = appointmentService.getAppointment(id);
        ClinicRecord record = new ClinicRecord();
        record.setPetId(appointment.getPetId());
        record.setAppointmentId(id);
        model.addAttribute("record",record);
        model.addAttribute("appointment",appointment);
        return "reception";
    }

    @PostMapping
    public  String saveRecord(Model model,@ModelAttribute ClinicRecord clinicRecord ,Principal principal) {
        clinicRecord.setDate(new Date());
        clinicRecordService.saveClinicRecord(clinicRecord);
        appointmentService.deleteAppointment(clinicRecord.getAppointmentId());
        return "redirect:/owner/pets";
    }
}
