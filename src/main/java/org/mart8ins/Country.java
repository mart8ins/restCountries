package org.mart8ins;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Country {

    public static Country[] getTop10Countries(Country[] countries, Comparator comparator){
        List<Country> listOfCountries = new ArrayList<>();
        for(int i = 0; i < countries.length; i++) {
            listOfCountries.add(countries[i]);
        }
        Collections.sort(listOfCountries, comparator);
        Country[] sortedCountries = new Country[10];
        for (int i = 0; i < 10; i++) {
            sortedCountries[i] = listOfCountries.get(i);
        }
        return sortedCountries;
    }

    private Name name;
    private BigDecimal population;
    private BigDecimal area;
    private BigDecimal populationDensity;

    public Country(){
    }

    public Name getName() {
        return name;
    }
    public BigDecimal getPopulation() {
        return population;
    }

    public BigDecimal getArea() {
        return area;
    }

    public BigDecimal getPopulationDensity() {
        if(populationDensity == null) {
            this.populationDensity = calculatePopulationDensity();
        }
        return populationDensity;
    }

    private BigDecimal calculatePopulationDensity(){
        if(population.equals(new BigDecimal(0))) {
            return new BigDecimal(0);
        } else {
            return population.divide(area, 2);
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                ", population=" + population +
                ", area=" + area +
                ", populationDensity=" + populationDensity +
                '}';
    }

}
