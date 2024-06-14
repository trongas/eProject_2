/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author Asus
 */
public class SroData {
    private int sro_id;
    private String sro_name;
    private LocalDate birthDate;
    private String gender;
    private String phone_number;
    private String email;
    private String cccd;

    public SroData(int sro_id, String sro_name, LocalDate birthDate, String gender, String phone_number, String email, String cccd) {
        this.sro_id = sro_id;
        this.sro_name = sro_name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.cccd = cccd;
    }

    public int getSro_id() {
        return sro_id;
    }

    public void setSro_id(int sro_id) {
        this.sro_id = sro_id;
    }

    public String getSro_name() {
        return sro_name;
    }

    public void setSro_name(String sro_name) {
        this.sro_name = sro_name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    
}