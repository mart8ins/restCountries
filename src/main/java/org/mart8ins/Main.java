package org.mart8ins;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    static String baseUrl = "https://restcountries.com/v3.1/";
    static String queryParam = "all?fields=name,population,area";

    public static void main(String[] args) throws IOException, InterruptedException {
        String fetchUrl = baseUrl + queryParam;
        ObjectMapper mapper = new ObjectMapper();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(fetchUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Country[] allCountries = mapper.readValue(response.body(), Country[].class);

        PopulationComparator populationComparator = new PopulationComparator();
        Country[] sortedByPopulation = Country.getTop10Countries(allCountries, populationComparator);
        System.out.println();
        System.out.println("****************** Top 10 countries by population");
        System.out.println();
        for (int i = 0; i < sortedByPopulation.length; i++) {
            System.out.printf("%s with %.0f people. \n", sortedByPopulation[i].getCommonName(), sortedByPopulation[i].getPopulation());
        }

        AreaComparator areaComparator = new AreaComparator();
        Country[] sortedByArea = Country.getTop10Countries(allCountries, areaComparator);
        System.out.println();
        System.out.println("****************** Top 10 countries by area");
        System.out.println();
        for (int i = 0; i < sortedByArea.length; i++) {
            System.out.printf("%s with %.0f square km. \n", sortedByArea[i].getCommonName(), sortedByArea[i].getArea());
        }

        PopulationDensityComparator densityComparator = new PopulationDensityComparator();
        Country[] sortedByDensity = Country.getTop10Countries(allCountries, densityComparator);
        System.out.println();
        System.out.println("****************** Top 10 countries by population density");
        System.out.println();
        for(int i =0; i < sortedByDensity.length; i++) {
            System.out.printf("%s with %.2f people per square km. \n", sortedByDensity[i].getCommonName(), sortedByDensity[i].getPopulationDensity());
        }
    }
}