package org.lukos.api.domain;

import javax.inject.Singleton;

@Singleton
public class Member{

    private String name = "";
    private int points = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
