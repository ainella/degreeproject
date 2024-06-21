package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Appointment;
import com.ainella.petclinic.models.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Appointment getAppointment(Integer id) {
        String query = "SELECT * FROM Appointments WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new Appointment.Mapper(), id);
    }
    public List<Appointment> getList() {
        String query = "SELECT * FROM Appointments";
        return jdbcTemplate.query(query, new Appointment.Mapper());
    }
    public void saveAppointment(Appointment appointment){
        if(appointment.getId()==null) {
            jdbcTemplate.update("insert into appointments (pet_id,clinic_id,date_,reason) values(?,?,?,?)",
                    appointment.getPetId(),appointment.getClinicId(),appointment.getDate(),appointment.getReason());
        }
        else {
            jdbcTemplate.update("update appointments \n" +
                    "set pet_id = ?, clinic_id = ?,date_ = ?, reason = ?\n" +
                    "where id = ?",appointment.getPetId(),appointment.getClinicId(),appointment.getDate(),appointment.getReason(),appointment.getId());
        }

    }
    public List<Appointment> getListById(Integer id){
        String query = "select * from clinic where owner_id = ? ";
        return jdbcTemplate.query(query,new Appointment.Mapper(), getListById(id));
    }
}
