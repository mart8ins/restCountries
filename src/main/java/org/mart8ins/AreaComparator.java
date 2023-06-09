package org.mart8ins;

import java.util.Comparator;

public class AreaComparator implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        return o2.getArea().compareTo(o1.getArea());

    }
}
