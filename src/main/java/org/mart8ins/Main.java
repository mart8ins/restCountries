package org.mart8ins;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonNode;

public class Main {
    static String baseUrl = "https://restcountries.com/v3.1/";
    static String queryParam = "all?fields=name,population,area";

    public static void main(String[] args) {

        String fetchUrl = baseUrl + queryParam;
        HttpResponse<JsonNode> response = Unirest.get(fetchUrl).asJson();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(response.getBody().toString());

        Country[] allCountries = gson.fromJson(jsonElement, Country[].class);

        PopulationComparator populationComparator = new PopulationComparator();
        Country[] sortedByPopulation = Country.getTop10Countries(allCountries, populationComparator);
        System.out.println();
        System.out.println("****************** Top 10 countries by population");
        System.out.println();
        for (int i = 0; i < sortedByPopulation.length; i++) {
            System.out.printf("%s with %.0f people. \n", sortedByPopulation[i].getName().getCommon(), sortedByPopulation[i].getPopulation());
        }

        AreaComparator areaComparator = new AreaComparator();
        Country[] sortedByArea = Country.getTop10Countries(allCountries, areaComparator);
        System.out.println();
        System.out.println("****************** Top 10 countries by area");
        System.out.println();
        for (int i = 0; i < sortedByArea.length; i++) {
            System.out.printf("%s with %.0f square km. \n", sortedByArea[i].getName().getCommon(), sortedByArea[i].getArea());
        }

        PopulationDensityComparator densityComparator = new PopulationDensityComparator();
        Country[] sortedByDensity = Country.getTop10Countries(allCountries, densityComparator);
        System.out.println();
        System.out.println("****************** Top 10 countries by population density");
        System.out.println();
        for(int i =0; i < sortedByDensity.length; i++) {
            System.out.printf("%s with %.2f people per square km. \n", sortedByDensity[i].getName().getCommon(), sortedByDensity[i].getPopulationDensity());
        }
    }
}