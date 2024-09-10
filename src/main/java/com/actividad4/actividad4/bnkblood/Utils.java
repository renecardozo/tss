package com.actividad4.actividad4.bnkblood;

import com.actividad4.actividad4.common.NumberRandomPair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.jshell.execution.Util;

import java.util.Random;
import java.util.Stack;

public class Utils {
    private static final Stack<Double> randomStackNumbers =  new Stack<>();
    public static double[] getDefaultRandomNumbers() {
        return new double[]{0.74, 0.85, 0.21, 0.06, 0.71, 0.31, 0.28, 0.96, 0.02, 0.72, 0.12,
                0.67, 0.53, 0.44, 0.23, 0.16, 0.16, 0.40, 0.83, 0.65, 0.34, 0.82};
    }
    public static void buildStackRandom(double[] randomList) {
        if (!randomStackNumbers.isEmpty()) {
            randomStackNumbers.clear();
        }
        for (int i = randomList.length - 1; i >= 0 ; i--) {
            randomStackNumbers.push(randomList[i]);
        }
    }
    public static Stack<Double> getRandomStack() {
        return randomStackNumbers;
    }
    public static double[] getStatisticsData() {
        return new double[] {0.15, 0.20, 0.25, 0.15, 0.15, 0.10 };
    }
    public static ObservableList<NumberRandomPair> generateData() {
        ObservableList<NumberRandomPair> data = FXCollections.observableArrayList();
        Random random = new Random();
        double[] newRandomList = new double[35];
        for (int i = 0; i < 35; i++) {
            double randomValue = Math.round(random.nextDouble() * 100.0) / 100.0; // Round to 2 decimal places
            data.add(new NumberRandomPair(i+1, randomValue));
            newRandomList[i] = randomValue;
        }
        Utils.buildStackRandom(newRandomList);
        return data;
    }
    public static ObservableList<NumberRandomPair> fillDefaultRandomData() {
        ObservableList<NumberRandomPair> data = FXCollections.observableArrayList();
        for (int i = 0; i < Utils.getDefaultRandomNumbers().length; i++) {
            data.add(new NumberRandomPair(i, Utils.getDefaultRandomNumbers()[i]));
        }
        return data;
    }

}
