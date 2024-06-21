package com.ainella.petclinic.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Appointment {
    Integer id;
    Integer petId;
    Integer clinicId;
    @DateTimeFormat (pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;
    String reason;

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

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public static class Mapper implements RowMapper<Appointment> {
        @Override
        public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Appointment appointment = new Appointment();

            appointment.setId(rs.getInt("id"));
            appointment.setPetId(rs.getInt("pet_id"));
            appointment.setClinicId(rs.getInt("clinic_id"));
            appointment.setDate(rs.getDate("date_"));
            appointment.setReason(rs.getString("reason"));
            return appointment;
        }
    }


}
