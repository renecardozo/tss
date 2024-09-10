package com.actividad4.actividad4.ventilationfan;

public class DataCumulative {
    private Double statisticsData;
    private Double cumulative;
    private String range;
    private Integer fanSales;

    public DataCumulative(Double statisticsData, Double cumulative, String range, Integer fanSales) {
        this.statisticsData = statisticsData;
        this.cumulative = cumulative;
        this.range = range;
        this.fanSales = fanSales;
    }

    public Double getStatisticsData() {
        return statisticsData;
    }

    public Double getCumulative() {
        return cumulative;
    }

    public String getRange() {
        return range;
    }

    public Integer getFanSales() {
        return fanSales;
    }
}
