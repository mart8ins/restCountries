package org.mart8ins;

import java.util.Comparator;

public class PopulationDensityComparator implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        return o2.getPopulationDensity().compareTo(o1.getPopulationDensity());
    }
}
