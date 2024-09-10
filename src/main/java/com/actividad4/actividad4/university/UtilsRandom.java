package com.actividad4.actividad4.university;

import com.actividad4.actividad4.bnkbnb.BnbData;
import com.actividad4.actividad4.bnkbnb.UtilsBnb;
import com.actividad4.actividad4.common.NumberRandomPair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class UtilsRandom {
    private Stack<Double> randomStackNumbers = new Stack<>();
    private double[] randomArrayNumbers = new double[] {
            0.07, 0.60, 0.77, 0.49, 0.76, 0.95, 0.51, 0.16,
            0.14, 0.85, 0.40, 0.42, 0.52, 0.39, 0.73, 0.89,
            0.88, 0.24, 0.01, 0.11
    };
    private double[] statisticDataList = new double[] {0.15, 0.22, 0.24, 0.21, 0.18};
    private HashMap<Integer, double[]> map = new HashMap<>();
    private Integer start;

    public UtilsRandom() {
        buildStackRandom();
    }

    public void buildStackRandom() {
        if (!randomStackNumbers.isEmpty()) {
            randomStackNumbers.clear();
        }
        for (int i = randomArrayNumbers.length - 1; i >= 0 ; i--) {
            randomStackNumbers.push(randomArrayNumbers[i]);
        }
    }

    public Stack<Double> getRandomStackNumbers() {
        return randomStackNumbers;
    }

    public double[] getRandomArrayNumbers() {
        return randomArrayNumbers;
    }
    public void setRandomArrayNumbers(double[] randomArrayNumbers) {
        this.randomArrayNumbers = randomArrayNumbers;
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
    public ObservableList<BnbData> generateDataForCumulativeTable(boolean calculateCumulative) {
        ObservableList<BnbData> data = FXCollections.observableArrayList();
        double cumulative = 0.0;
        double preCumulative = cumulative;
        Integer count = 2300;
        for (int i = 0; i < statisticDataList.length; i++) {
            if (!calculateCumulative) {
                data.add(new BnbData(statisticDataList[i], 0.0, "[,]", 0));
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
                data.add(new BnbData(statisticDataList[i], cumulative, range, count));
                map.put(count, new double[]{leftLimit, rightLimit});
                count = count + 100;
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
    public ObservableList<DataResultUniv> calculateDataResultUni(Integer programsPivot) {
        ObservableList<DataResultUniv> data = FXCollections.observableArrayList();
        buildStackRandom();
        for (int i = 0; i < 10; i++) {
            Integer day = i + 1;
            if (programsPivot > 0 ) {
                Double random = randomStackNumbers.pop();
                Integer demand = getDataInRange(random);
                double profit = 0.0;
                if (demand < programsPivot) {
                    profit = (2 * demand) - (0.8 * programsPivot);
                }
                if (demand >= programsPivot) {
                    profit = (2 * programsPivot) - (0.8 * programsPivot);
                }
                data.add(new DataResultUniv(day, random, demand, profit));
            } else {
                data.add(new DataResultUniv(day, 0.0, 0, 0.0));
            }

        }
        return data;
    }

    public ObservableList<NumberRandomPair> fillRandomData() {
        ObservableList<NumberRandomPair> data = FXCollections.observableArrayList();
        for (int i = 0; i < randomArrayNumbers.length; i++) {
            data.add(new NumberRandomPair(i, randomArrayNumbers[i]));
        }
        return data;
    }
}
