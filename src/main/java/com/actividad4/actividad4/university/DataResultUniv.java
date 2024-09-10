package com.actividad4.actividad4.university;

public class DataResultUniv {
    private Integer day;
    private Double random;
    private Integer demand;
    private Double profit;

    public DataResultUniv(Integer day, Double random, Integer demand, Double profit) {
        this.day = day;
        this.random = random;
        this.demand = demand;
        this.profit = profit;
    }

    public Integer getDay() {
        return day;
    }

    public Double getRandom() {
        return random;
    }

    public Integer getDemand() {
        return demand;
    }

    public Double getProfit() {
        return profit;
    }
}
