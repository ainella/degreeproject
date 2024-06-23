package com.ainella.petclinic.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClinicRecord {
    Integer id;
    Integer petId;
    Date date;
    String diagnose;
    String treatment;
    Integer appointmentId;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public static class Mapper implements RowMapper<ClinicRecord> {
        @Override
        public ClinicRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClinicRecord clinicRecord = new ClinicRecord();

            clinicRecord.setId(rs.getInt("id"));
            clinicRecord.setPetId(rs.getInt("pet_id"));
            clinicRecord.setDate(rs.getDate("date_"));
            clinicRecord.setDiagnose(rs.getString("diagnose"));
            clinicRecord.setTreatment(rs.getString("treatment"));
            return clinicRecord;
        }
    }
}
