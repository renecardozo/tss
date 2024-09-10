package com.actividad4.actividad4.bnkbnb;

public class BnbDataResult {
    private Integer client;
    private Double randomClient;
    private Integer intervalTimeArrived;
    private String arrivedTimeFormatted;
    private Integer arrivedTime;
    private Double randomService;
    private Integer timeService;
    private String startServiceFormatted;
    private Integer startService;
    private String endServiceFormatted;
    private Integer endService;
    private Integer waitTime;
    private Integer wasteTime;

    private String pivotTime = "09:";

    public BnbDataResult(
            Integer client,
            Double randomClient,
            Integer intervalTimeArrived,
            Integer arrivedTime,
            Double randomService,
            Integer timeService,
            Integer startService,
            Integer endService,
            Integer waitTime,
            Integer wasteTime) {
        this.client = client;
        this.randomClient = randomClient;
        this.intervalTimeArrived = intervalTimeArrived;
        this.arrivedTime = arrivedTime;
        this.arrivedTimeFormatted = this.pivotTime + (this.arrivedTime == 0 ? "00" : (this.arrivedTime < 9 ? "0" + this.arrivedTime : this.arrivedTime));
        this.randomService = randomService;
        this.timeService = timeService;
        this.startService = startService;
        this.startServiceFormatted = this.pivotTime + (this.startService == 0 ? "00" : (this.startService < 9 ? "0" + this.startService : this.startService));
        this.endService = endService;
        this.endServiceFormatted = this.pivotTime + (this.endService == 0 ? "00" : (this.endService < 9 ? "0" + this.endService : this.endService));
        this.waitTime = waitTime;
        this.wasteTime = wasteTime;
    }

    public Integer getClient() {
        return client;
    }

    public Double getRandomClient() {
        return randomClient;
    }

    public Integer getIntervalTimeArrived() {
        return intervalTimeArrived;
    }

    public String getArrivedTimeFormatted() {
        return arrivedTimeFormatted;
    }

    public Integer getArrivedTime() {
        return arrivedTime;
    }

    public Double getRandomService() {
        return randomService;
    }

    public Integer getTimeService() {
        return timeService;
    }

    public String getStartServiceFormatted() {
        return startServiceFormatted;
    }

    public Integer getStartService() {
        return startService;
    }

    public String getEndServiceFormatted() {
        return endServiceFormatted;
    }

    public Integer getEndService() {
        return endService;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public Integer getWasteTime() {
        return wasteTime;
    }

    public String getPivotTime() {
        return pivotTime;
    }
}
