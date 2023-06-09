package org.mart8ins;

import java.util.Comparator;

public class PopulationComparator implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        return o2.getPopulation().compareTo(o1.getPopulation());
    }
}
