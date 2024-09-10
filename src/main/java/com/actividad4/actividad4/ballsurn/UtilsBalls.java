package com.actividad4.actividad4.ballsurn;

import com.actividad4.actividad4.common.NumberRandomPair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class UtilsBalls {
    private Stack<Double> randomStackNumbers =  new Stack<>();
    private double[] randomArrayNumbers;
    private double[] statisticDataList;
    private HashMap<String, double[]> map;

    public UtilsBalls() {
        randomArrayNumbers = new double[]{0.26, 0.42, 0.95, 0.95, 0.66, 0.17, 0.03, 0.56, 0.83, 0.55};
        statisticDataList = new double[] {0.10, 0.40, 0.50};
        map = new HashMap<>();
        buildStackRandom();
    }
    public void buildStackRandom() {
        System.out.println("buildStackRandomCalled");
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

    public void setRandomStackNumbers(Stack<Double> randomStackNumbers) {
        this.randomStackNumbers = randomStackNumbers;
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
        double[] newRandomArrayNumbers = new double[10];
        for (int i = 0; i < 10; i++) {
            double randomValue = Math.round(random.nextDouble() * 100.0) / 100.0; // Round to 2 decimal places
            data.add(new NumberRandomPair(i+1, randomValue));
            newRandomArrayNumbers[i] = randomValue;
        }
        setRandomArrayNumbers(newRandomArrayNumbers);
        buildStackRandom();
        return data;
    }
    public ObservableList<SecuencePair> generateDataForCumulativeTable(boolean calculateCumulative) {
        ObservableList<SecuencePair> data = FXCollections.observableArrayList();
        if (!calculateCumulative) {
            for (double v : statisticDataList) {// Round to 2 decimal places
                data.add(new SecuencePair(v, 0.0, "[,]", ""));
            }
        } else {
            double cumulative = 0.0;
            double preCumulative = cumulative;
            for (int i = 0; i < statisticDataList.length; i++) {
                if (i == 0) {
                    cumulative = statisticDataList[i];
                } else {
                    preCumulative = cumulative;
                    cumulative = statisticDataList[i] + cumulative;
                }
                double leftLimit = i == 0 ? 0 : preCumulative;
                double rightLimit = cumulative;
                String range = "[ " + leftLimit + ", " + rightLimit + "]";
                switch (i) {
                    case 0:
                        data.add(new SecuencePair(statisticDataList[i], cumulative, range, "Verde"));
                        map.put("Verde", new double[]{leftLimit, rightLimit});
                        break;
                    case 1:
                        data.add(new SecuencePair(statisticDataList[i], cumulative, range, "Rojo"));
                        map.put("Rojo", new double[]{leftLimit, rightLimit});
                        break;
                    case 2:
                        data.add(new SecuencePair(statisticDataList[i], cumulative, range, "Amarillo"));
                        map.put("Amarillo", new double[]{leftLimit, rightLimit});
                        break;
                }
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

    public String getDataInRange(Double value) {
        String dataItem = "";
        for (Map.Entry<String, double[]> entry : map.entrySet()) {
            String key = entry.getKey();
            double[] values = entry.getValue();
            if (value >= values[0] && value < values[1]) {
                dataItem = key;
                break;
            }
        }
        if (dataItem.isEmpty()) {
            System.out.println("Color not found in the limits");
        }
        return dataItem;
    }

    public ObservableList<SecuencePair> generateCumulativeFullData(boolean calculateDataResult) {
        ObservableList<SecuencePair> data = FXCollections.observableArrayList();
        for (int i = 0; i < randomArrayNumbers.length; i++) {
            String ballNumber = Integer.toString(i + 1);
            if (!calculateDataResult) {
                data.add(new SecuencePair(0.0, 0.0, ballNumber, ""));
            } else {
                double random = randomStackNumbers.pop();
                String color = getDataInRange(random);
                data.add(new SecuencePair(random, 0.0, ballNumber, color));
            }
        }
        return data;
    }
}

