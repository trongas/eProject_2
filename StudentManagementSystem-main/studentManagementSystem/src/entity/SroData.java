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
    private int sro_id;
    private String sro_name;
    private int age;
    private String gender;
    private int phone_number;
    private String email;

    public SroData(String sro_name,int sro_id ,int age, String gender, int phone_number, String email) {
        this.sro_name = sro_name;
        this.sro_id = sro_id;
        this.age = age;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
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

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getSro_id() {
        return sro_id;
    }

    public void setSro_id(int sro_id) {
        this.sro_id = sro_id;
    }
    
}
