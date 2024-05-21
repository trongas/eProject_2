/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Asus
 */
public class SroData {
    private String sro_name;
    private int age;
    private String gender;
    private String phone_number;
    private String email;
    private String cccd;

    public SroData(String sro_name ,int age, String gender, String phone_number, String email, String cccd) {
        this.sro_name = sro_name;
        this.age = age;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.cccd = cccd;
    }

    public String getSro_name() {
        return sro_name;
    }

    public void setSro_name(String sro_name) {
        this.sro_name = sro_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
