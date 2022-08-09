/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vm.kioskenhanced2;

/**
 *
 * @author Admin
 */
public class CardData {

    private String imageSrc;
    private String patientName;
    private String doctorName;
    private String departmentName;
    private String apptTime;

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    @Override
    public String toString() {
        return "CardData{" + "imageSrc=" + imageSrc + ", patientName=" + patientName + ", doctorName=" + doctorName + ", departmentName=" + departmentName + ", apptTime=" + apptTime + '}';
    }

}
