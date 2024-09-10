package com.actividad4.actividad4.bnkbnb;

import com.actividad4.actividad4.ballsurn.SecuencePair;
import com.actividad4.actividad4.common.NumberRandomPair;
import com.actividad4.actividad4.common.NumberRandomPairCumulative;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class UtilsBnb {
    public static Stack<Double> randomStackNumbers = new Stack<>();
    public static double[] randomArrayNumbers = new double[] {
            0.50, 0.52, 0.28, 0.37, 0.68, 0.82, 0.36, 0.69, 0.90, 0.98,
            0.62, 0.96, 0.27, 0.33, 0.50, 0.50, 0.18, 0.88, 0.36, 0.90
    };
    private double[] statisticDataList;
    private HashMap<Integer, double[]> map = new HashMap<>();
    private Integer start;

    public UtilsBnb(double[] statisticDataList, Integer start) {
        this.statisticDataList = statisticDataList;
        this.start = start;
        UtilsBnb.buildStackRandom();
    }
    public static void buildStackRandom() {
        if (!randomStackNumbers.isEmpty()) {
            randomStackNumbers.clear();
        }
        for (int i = randomArrayNumbers.length - 1; i >= 0 ; i--) {
            randomStackNumbers.push(randomArrayNumbers[i]);
        }
    }
    public static void setRandomArrayNumbers(double[] randomArrayNumbers) {
        UtilsBnb.randomArrayNumbers = randomArrayNumbers;
    }
    public static ObservableList<NumberRandomPair> generateRandomData() {
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
        Integer count = start;
        for (int i = 0; i < statisticDataList.length; i++) {
            if (!calculateCumulative) {
                data.add(new BnbData(statisticDataList[i], 0.0, "[,]", count));
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
                count++;
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

    public static ObservableList<NumberRandomPair> fillRandomData() {
        ObservableList<NumberRandomPair> data = FXCollections.observableArrayList();
        for (int i = 0; i < randomArrayNumbers.length; i++) {
            data.add(new NumberRandomPair(i, randomArrayNumbers[i]));
        }
        return data;
    }

}
