package com.actividad4.actividad4.bnkblood.tableresult;

import java.util.ArrayList;
import java.util.Arrays;

public class DataResult {

    private Integer week;
    private Integer inventory;
    private Double bloodRandom;
    private Integer pints;


    private Integer totalAvailability;
    private Double needPatientRandom;
    private Integer patients;
    private ArrayList<Integer> patientsToAttend;

    private ArrayList<Double> patientDemandRandom;
    private ArrayList<Integer> patientDemandBlood;
    private ArrayList<Integer> pintsRest;

    public DataResult(
            Integer week,
            Integer inventory,
            Double bloodRandom,
            Integer pints,
            Integer totalAvailability,
            Double needPatientRandom,
            Integer patients,
            ArrayList<Integer> patientsToAttend,
            ArrayList<Double> patientDemandRandom,
            ArrayList<Integer> patientDemandBlood,
            ArrayList<Integer> pintsRest) {
        this.week = week;
        this.inventory = inventory;
        this.bloodRandom = bloodRandom;
        this.pints = pints;
        this.totalAvailability = totalAvailability;
        this.needPatientRandom = needPatientRandom;
        this.patients = patients;
        this.patientsToAttend = patientsToAttend;
        this.patientDemandRandom = patientDemandRandom;
        this.patientDemandBlood = patientDemandBlood;
        this.pintsRest = pintsRest;
    }

    public Integer getWeek() {
        return week;
    }

    public Integer getInventory() {
        return inventory;
    }

    public Double getBloodRandom() {
        return bloodRandom;
    }

    public Integer getPints() {
        return pints;
    }

    public Integer getTotalAvailability() {
        return totalAvailability;
    }

    public Double getNeedPatientRandom() {
        return needPatientRandom;
    }

    public Integer getPatients() {
        return patients;
    }

    public ArrayList<Integer> getPatientsToAttend() {
        return patientsToAttend;
    }

    public ArrayList<Double> getPatientDemandRandom() {
        return patientDemandRandom;
    }

    public ArrayList<Integer> getPatientDemandBlood() {
        return patientDemandBlood;
    }

    public ArrayList<Integer> getPintsRest() {
        return pintsRest;
    }
}
