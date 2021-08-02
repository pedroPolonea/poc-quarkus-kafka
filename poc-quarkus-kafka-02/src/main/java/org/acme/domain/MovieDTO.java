package org.acme.domain;

public class MovieDTO {
    public String title;
    public int year;

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
