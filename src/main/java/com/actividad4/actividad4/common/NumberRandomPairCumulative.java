package com.actividad4.actividad4.common;

import java.text.DecimalFormat;

public class NumberRandomPairCumulative extends NumberRandomPair {
    private String range = "[,]";
    private Double cumulative = 0.0;
    private Double rightLimit = 0.0;
    private Double leftLimit = 0.0;
    DecimalFormat df = new DecimalFormat("#.00");

    public NumberRandomPairCumulative(Integer number, Double random, String range,
                                      Double cumulative, Double leftLimit, Double rightLimit) {
        super(number, random);
        this.range = range;
        this.cumulative = cumulative;
        this.rightLimit = rightLimit;
        this.leftLimit = leftLimit;
    }

    public String getRange() {
        return "[ " + String.format("%.2f", this.leftLimit) + ", " + String.format("%.2f", this.rightLimit) + " ]";
    }

    public Double getCumulative() {
        return this.cumulative;
    }

    public void setRange(String range) {
        this.range = range;
    }
    public void setCumulative(Double cumulative) {
        this.cumulative = cumulative;
    }
}
