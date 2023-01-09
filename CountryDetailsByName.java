import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;

public class CountryDetailsByName {
    private final HttpClient httpClient =   HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you know country full name : (Y/N): ");
        String yesOrNo = sc.next();

        boolean fullName = Objects.equals(yesOrNo, "Y") || Objects.equals(yesOrNo, "y");
        if(Objects.equals(yesOrNo, "N") || Objects.equals(yesOrNo, "n")){
            fullName = false;
        }

        // country name input.
        System.out.println("Enter country name: ");
        String countryName = sc.next();

        CountryDetailsByName obj = new CountryDetailsByName();
        obj.getCountryDetails(countryName, fullName);
    }
    private void getCountryDetails(String countryName, boolean fullKnown) throws Exception{
        String url = "https://restcountries.com/v3.1/name/" + countryName + "?fullText=true";
        if(!fullKnown) {
            url = "https://restcountries.com/v3.1/name/" + countryName;
        }

        // request builder.
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 404){
            System.out.println("No countries found. Please check spell!");
        }
        if(response.statusCode() == 200) {
            JSONArray array = new JSONArray(response.body());
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                System.out.println("country : " + jsonObject.getJSONObject("name").getString("common"));
                System.out.println("Currencies : " + jsonObject.getJSONObject("currencies"));
                System.out.println("Languages : " + jsonObject.getJSONObject("languages"));
                System.out.println("Continents : " + jsonObject.getJSONArray("continents"));
                System.out.println("Area : " + jsonObject.getLong("area"));
                System.out.println("Population : " + jsonObject.getLong("population"));
                System.out.println("----------------------------------");
            }
        }


    }
}
