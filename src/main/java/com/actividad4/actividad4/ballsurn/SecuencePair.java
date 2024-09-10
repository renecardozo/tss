package com.actividad4.actividad4.ballsurn;

public class SecuencePair {
    private String color;
    private Double statisticsData;
    private String range;
    private Double cumulative;
    public SecuencePair( Double statisticsData, Double cumulative, String range, String color) {
        this.color = color;
        this.statisticsData = statisticsData;
        this.range = range;
        this.cumulative = cumulative;
    }

    public String getColor() {
        return color;
    }

    public Double getStatisticsData() {
        return statisticsData;
    }

    public String getRange() {
        return range;
    }

    public Double getCumulative() {
        return cumulative;
    }
}
