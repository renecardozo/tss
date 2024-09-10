package com.actividad4.actividad4.bnkbnb;

public class BnbData {
    private Double statisticData;
    private Double cumulative;
    private String range;
    private Integer time;

    public BnbData(Double statisticData, Double cumulative, String range, Integer time) {
        this.statisticData = statisticData;
        this.cumulative = cumulative;
        this.range = range;
        this.time = time;
    }

    public Double getStatisticData() {
        return statisticData;
    }

    public Double getCumulative() {
        return cumulative;
    }

    public String getRange() {
        return range;
    }

    public Integer getTime() {
        return time;
    }
}
