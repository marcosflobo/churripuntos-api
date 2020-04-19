package org.lukos.api.domain;

import lombok.Data;


@Data
public class Member {

    private String name = "";
    private int points = 0;

    public String toString () {

        return "name: " + getName() + " - points: " + getPoints();
    }

}
