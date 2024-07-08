package com.ainella.petclinic.services;

import com.ainella.petclinic.models.ClinicRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicRecordService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClinicRecord getById(Integer id) {
        String query = "select * from clinic_records where id = ?";
        return jdbcTemplate.queryForObject(query, new ClinicRecord.Mapper(), id);
    }

    public List<ClinicRecord> getListByPetId(Integer id) {
        String query = """
                select cr.id ,cr.date_ ,cr.diagnose ,cr.treatment ,cr.appointments_id
                from clinic_records cr
                join appointments a on a.id = cr.appointments_id
                where a.pet_id = ?
                """;
        return jdbcTemplate.query(query, new ClinicRecord.Mapper(), id);
    }

    public void saveClinicRecord(ClinicRecord clinicRecord) {
        if(clinicRecord.getId()==null) {
            jdbcTemplate.update("insert into clinic_records (appointment_id,date_,diagnose,treatment) values(?,?,?,?)",
                    clinicRecord.getAppointmentId(),clinicRecord.getDate(),clinicRecord.getDiagnose(),clinicRecord.getTreatment());
        }
        else {
            jdbcTemplate.update("update clinic_records \n" +
                    "set  appointment_id = ?,date_ = ?,diagnose = ?, treatment = ? \n" +
                    "where id = ?",clinicRecord.getAppointmentId(),clinicRecord.getDate(),clinicRecord.getDiagnose(),
                    clinicRecord.getTreatment(),clinicRecord.getId());
        }
    }
}
