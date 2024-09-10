package com.actividad4.actividad4.common;

import javafx.beans.property.SimpleDoubleProperty;

public class NumberRandomPair {
    private Integer number;
    private final SimpleDoubleProperty random;

    public NumberRandomPair(Integer number, Double random) {
        this.number = number;
        this.random = new SimpleDoubleProperty(random);
    }
    public void setRandom(Double random) {
        this.random.set(random);
    }
    public Integer getNumber() {
        return number;
    }

    public double getRandom() {
        return random.get();
    }

    public SimpleDoubleProperty randomProperty() {
        return random;
    }

}
