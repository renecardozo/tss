package com.actividad4.actividad4.ventilationfan;

public class DataFan {
    private Integer week;
    private Integer weekSales;
    private Double statisticsData;

    public DataFan(Integer week, Integer weekSales, Double statisticsData) {
        this.week = week;
        this.weekSales = weekSales;
        this.statisticsData = statisticsData;
    }

    public Integer getWeek() {
        return week;
    }

    public Integer getWeekSales() {
        return weekSales;
    }

    public Double getStatisticsData() {
        return statisticsData;
    }
}
