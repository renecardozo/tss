package com.actividad4.actividad4.ventilationfan;

public class DataResult {
    private Integer week;
    private Double random;
    private Integer salesFan;

    public DataResult(Integer week, Double random, Integer salesFan) {
        this.week = week;
        this.random = random;
        this.salesFan = salesFan;
    }

    public Integer getWeek() {
        return week;
    }

    public Double getRandom() {
        return random;
    }

    public Integer getSalesFan() {
        return salesFan;
    }
}
