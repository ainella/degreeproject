package com.ainella.petclinic.services;

import com.ainella.petclinic.models.Appointment;
import com.ainella.petclinic.models.ClinicRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicRecordService {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<ClinicRecord> getListById(Integer id) {
        String query = "select * from clinic_records where id = ?";
        return jdbcTemplate.query(query, new ClinicRecord.Mapper(),id);
    }

    public void saveClinicRecord(ClinicRecord clinicRecord) {
        if(clinicRecord.getId()==null) {
            jdbcTemplate.update("insert into clinic_records (pet_id,date_,diagnose,treatment) values(?,?,?,?)",
                    clinicRecord.getPetId(),clinicRecord.getDate(),clinicRecord.getDiagnose(),clinicRecord.getTreatment());
        }
        else {
            jdbcTemplate.update("update clinic_records \n" +
                    "set pet_id = ?, date_ = ?,diagnose = ?, treatment = ?\n" +
                    "where id = ?",clinicRecord.getPetId(),clinicRecord.getDate(),clinicRecord.getDiagnose(),
                    clinicRecord.getTreatment(),clinicRecord.getId());
        }
    }
}
