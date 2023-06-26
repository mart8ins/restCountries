package org.mart8ins;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.*;

public class Country {

    private String commonName;
    private BigDecimal population;
    private BigDecimal area;
    private BigDecimal populationDensity;

    public Country(){
    }

    @JsonProperty("name")
    private void unpackNested(Map<String, Object> name) {
        this.commonName = (String)name.get("common");
    }

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

    public String getCommonName() {
        return commonName;
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
                "commonName='" + commonName + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", populationDensity=" + populationDensity +
                '}';
    }
}
