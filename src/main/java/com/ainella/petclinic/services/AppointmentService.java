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
        String query = """
                select a.* , p."name" as pet_name, c."name" as clinic_name , s."name" as species_name
                from appointments a
                join pets p on p.id = a.pet_id
                join clinic c on c.id = a.clinic_id
                join species s on s.id = p.species_id
                where a.id = ?""";
        return jdbcTemplate.queryForObject(query, new Appointment.Mapper(), id);
    }

    public void deleteAppointment(Integer id){
        String query = "delete from appointments where id = ?";
        jdbcTemplate.update(query, id);
    }

    public List<Appointment> getListByOwnerId(Integer ownerId) {
        String query = "select a.* , p.\"name\" as pet_name, c.\"name\" as clinic_name , s.\"name\" as species_name\n" +
                "from appointments a \n" +
                "join pets p on p.id = a.pet_id \n" +
                "join clinic c on c.id = a.clinic_id \n" +
                "join species s on s.id = p.species_id \n" +
                "where p.owner_id = ?";
        return jdbcTemplate.query(query, new Appointment.Mapper(),ownerId);
    }

    public List<Appointment> getListByClinicId(Integer clinicId) {
        String query = "select a.* , p.\"name\" as pet_name, c.\"name\" as clinic_name , s.\"name\" as species_name\n" +
                "from appointments a \n" +
                "join pets p on p.id = a.pet_id \n" +
                "join clinic c on c.id = a.clinic_id \n " +
                "join species s on s.id = p.species_id  \n" +
                "where a.clinic_id = ?";
        return jdbcTemplate.query(query, new Appointment.Mapper(),clinicId);
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
}
