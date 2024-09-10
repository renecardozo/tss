package com.actividad4.actividad4.ventilationfan;

import com.actividad4.actividad4.bnkbnb.BnbData;
import com.actividad4.actividad4.common.NumberRandomPair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class UtilsVentilation {
    private Stack<Double> randomStackNumbers = new Stack<>();
    private double[] randomArrayNumbers = new double[] {
            0.10, 0.24, 0.03, 0.32, 0.23, 0.59, 0.95, 0.34, 0.34, 0.51,
            0.08, 0.48, 0.66, 0.97, 0.03, 0.96, 0.46, 0.74, 0.77, 0.44
    };
    private int[] weekSales = new int[] { 6, 5, 9, 12, 8, 7, 3};
    private double[] statisticDataList = new double[weekSales.length];
    private HashMap<Integer, double[]> map = new HashMap<>();
    private Integer start = 4;

    public UtilsVentilation() {
        calculateStatics();
        buildStackRandom();
    }

    private void calculateStatics() {
        int total = Arrays.stream(weekSales).sum();
        for (int i = 0; i < weekSales.length; i++) {
            statisticDataList[i] = (double) weekSales[i] / total;
        }
    }

    public void buildStackRandom() {
        if (!randomStackNumbers.isEmpty()) {
            randomStackNumbers.clear();
        }
        for (int i = randomArrayNumbers.length - 1; i >= 0 ; i--) {
            randomStackNumbers.push(randomArrayNumbers[i]);
        }
    }

    public void setRandomArrayNumbers(double[] randomArrayNumbers) {
        this.randomArrayNumbers = randomArrayNumbers;
    }
    public ObservableList<NumberRandomPair> fillRandomData() {
        ObservableList<NumberRandomPair> data = FXCollections.observableArrayList();
        for (int i = 0; i < randomArrayNumbers.length; i++) {
            data.add(new NumberRandomPair(i, randomArrayNumbers[i]));
        }
        return data;
    }

    public ObservableList<NumberRandomPair> generateRandomData() {
        ObservableList<NumberRandomPair> data = FXCollections.observableArrayList();
        Random random = new Random();
        double[] newRandomArrayNumbers = new double[20];
        for (int i = 0; i < 20; i++) {
            double randomValue = Math.round(random.nextDouble() * 100.0) / 100.0; // Round to 2 decimal places
            data.add(new NumberRandomPair(i+1, randomValue));
            newRandomArrayNumbers[i] = randomValue;
        }
        setRandomArrayNumbers(newRandomArrayNumbers);
        buildStackRandom();
        return data;
    }

    public ObservableList<DataCumulative> generateDataForCumulativeTable(boolean calculateCumulative) {
        ObservableList<DataCumulative> data = FXCollections.observableArrayList();
        double cumulative = 0.0;
        double preCumulative = cumulative;
        int week = start;
        for (int i = 0; i < statisticDataList.length; i++) {
            if (!calculateCumulative) {
                data.add(new DataCumulative(statisticDataList[i], 0.0, "[,]", 0));
            } else {
                if (i == 0) {
                    cumulative = statisticDataList[i];
                } else {
                    preCumulative = cumulative;
                    cumulative = statisticDataList[i] + cumulative;
                }
                double leftLimit = i == 0 ? 0 : preCumulative;
                double rightLimit = cumulative;
                String range = "[ " + String.format("%.2f", leftLimit)  + ", " + String.format("%.2f", rightLimit) + "]";
                data.add(new DataCumulative(statisticDataList[i], cumulative, range, week));
                map.put(week, new double[]{leftLimit, rightLimit});
                week = week + 1;
            }
        }
        return data;
    }
    public Integer getDataInRange(Double value) {
        Integer dataItem = 0;
        for (Map.Entry<Integer, double[]> entry : map.entrySet()) {
            Integer key = entry.getKey();
            double[] values = entry.getValue();
            if (value >= values[0] && value < values[1]) {
                dataItem = key;
                break;
            }
        }
        if (dataItem == 0) {
            System.out.println("Time not found in the limits");
        }
        return dataItem;
    }

    public ObservableList<DataFan> generateDataForStatistics(boolean calculateStatistics) {
        ObservableList<DataFan> data = FXCollections.observableArrayList();
        int week = start;
        for (int i = 0; i < statisticDataList.length; i++) {
            if (!calculateStatistics) {
                data.add(new DataFan(week, 0, 0.0));
            } else {
                data.add(new DataFan(week, weekSales[i], statisticDataList[i]));
            }
            week++;
        }
        return data;
    }

    public ObservableList<DataResult> generateDataResult(boolean calculateResult) {
        ObservableList<DataResult> data = FXCollections.observableArrayList();
        for (int week = 0; week < randomArrayNumbers.length; week++) {
            if (!calculateResult) {
                data.add(new DataResult(week, 0.0, 0));
            } else {
                Double random = randomStackNumbers.pop();
                Integer salesFan = getDataInRange(random);
                data.add(new DataResult(week, random, salesFan));
            }
        }
        return data;
    }
}
