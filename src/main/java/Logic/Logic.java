package Logic;

import java.io.*;
import java.util.*;
import Classes.*;
import com.opencsv.*;

public class Logic {

    private List<String[]> rows;
    private String file;
    private Map<Integer, Platform> platfs;
    private Map<Integer, Country> countries;

    public Logic(String file) throws IOException {
        this.file = file;
        generateRows();
        this.platfs = new HashMap<>();
        this.countries = new HashMap<>();
        createPlatforms();
        createCountries();
    }

    private void generateRows() throws IOException {

        List<String[]> temp = new CSVReader(new FileReader(this.file)).readAll();
        List<String[]> compList = new ArrayList<>();

        for(String[] row: temp) {
            compList.add(row[0].split(";"));
        }

        this.rows = compList;
    }

    private void createPlatforms() {
        List<Platform> temp = new ArrayList<>();

        for(String[] row: rows) {
            String name = row[2].replaceAll("\"", "");
            String type = row[12].replaceAll("\"", "");
            Platform platform = new Platform(name, type);
            if (name.equals("Platform")) {
                continue;
            }

            int quantity = 0;
            double revenue = 0;

            try {
                quantity = Integer.valueOf(row[13].replaceAll("\"", ""));
                revenue = Double.valueOf(row[15].replaceAll("\"", ""));
            } catch (Exception e) {
            }

            if (temp.contains(platform)) {
                for (int i = 0; i < temp.size(); i++) {
                    if(temp.get(i).equals(platform)) {
                        temp.get(i).setQuantity(temp.get(i).getQuantity() + quantity);
                        temp.get(i).setRevenue(temp.get(i).getRevenue() + revenue);
                    }
                }

                continue;
            }

            platform.setQuantity(quantity);
            platform.setRevenue(revenue);
            temp.add(platform);
        }

        Collections.sort(temp);

        for(int i = 0; i < temp.size(); i++) {
            this.platfs.put(i + 1, temp.get(i));
        }
    }

    private void createCountries() {
        List<Country> temp = new ArrayList<>();

        for(String[] row: rows) {
            String countryName = row[3].replaceAll("\"", "");
            String platName = row[2].replaceAll("\"", "");
            String platType = row[12].replaceAll("\"", "");
            Country country = new Country(countryName);
            Platform platform = new Platform(platName, platType);
            if(countryName.contains("Country")) {
                continue;
            }

            int quantity = 0;
            double revenue = 0;

            try {
                quantity = Integer.valueOf(row[13].replaceAll("\"", ""));
                revenue = Double.valueOf(row[15].replaceAll("\"", ""));
            } catch (Exception e) {
            }

            if(temp.contains(country)) {
                for(int i = 0; i < temp.size(); i++) {
                    if(country.equals(temp.get(i))) {
                        if(temp.get(i).getPlatforms().contains(platform)) {
                            temp.get(i).editPlatform(platform, quantity, revenue);
                        } else if(!temp.get(i).getPlatforms().contains(platform)){
                            platform.setQuantity(quantity);
                            platform.setRevenue(revenue);
                            temp.get(i).addPlatform(platform);
                        }
                    }
                }

                continue;
            }

            platform.setQuantity(quantity);
            platform.setRevenue(revenue);
            country.addPlatform(platform);
            temp.add(country);
        }

        Collections.sort(temp);

        for(int i = 0; i < temp.size(); i++) {
            this.countries.put(i + 1, temp.get(i));
        }
    }

    public Map<Integer, Country> getCountries() {
        return this.countries;
    }

    public int getTotalStreams() {

        int total = 0;

        for(Platform plat: platfs.values()) {
            if(plat.getType().equals("Stream")) {
                total += plat.getQuantity();
            }
        }

        return total;
    }

    public int getTotalDownloads() {

        int total = 0;


        for(Platform plat: platfs.values()) {
            if(plat.getType().equals("Download")) {
                total += plat.getQuantity();
            }
        }

        return total;
    }

    public double getTotalMoney() {

        double total = 0;

        for(Platform plat: platfs.values()) {
            total += plat.getRevenue();
        }

        return total;
    }

    public String spotifyStats() {
        int spotifyTotal = 0;
        double spotifyRev = 0;

        for(Platform plat: platfs.values()) {
            if (plat.getName().contains("Spotify")) {
                spotifyTotal += plat.getQuantity();
                spotifyRev += plat.getRevenue();
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Streams: " + spotifyTotal + "\n\n");
        sb.append("Revenue: " + spotifyRev + "\n");

        return sb.toString();
    }

    public String youtubeStats() {

        int youtubeTotal = 0;
        double youtubeRev = 0;

        for(Platform plat: platfs.values()) {
            if (plat.getName().contains("Youtube")) {
                youtubeTotal += plat.getQuantity();
                youtubeRev += plat.getRevenue();
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Streams: " + youtubeTotal + "\n\n");
        sb.append("Revenue: " + youtubeRev + "\n");

        return sb.toString();
    }

    public Platform getPlatform(int platform) {
        return this.platfs.get(platform);
    }

    public Country getCountry(int country) {
        return this.countries.get(country);
    }

    public Map<Integer, Platform> getPlatforms() {
        return this.platfs;
    }
}
