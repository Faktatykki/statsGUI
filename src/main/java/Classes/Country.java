package Classes;

import java.util.*;

public class Country implements Comparable<Country> {

    private String name;
    private List<Platform> platfs;

    public Country(String name) {
        this.name = name;
        this.platfs = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getStreams() {

        int total = 0;

        for(Platform plat: platfs) {
            if(plat.getType().equals("Stream")) {
                total += plat.getQuantity();
            }
        }

        return total;
    }

    public double getRevenue() {
        double total = 0;

        for(Platform plat: platfs) {
            total += plat.getRevenue();
        }

        return total;
    }

    public int getDownloads() {
        int total = 0;

        for(Platform plat: platfs) {
            if(plat.getType().equals("Download")) {
                total += plat.getQuantity();
            }
        }

        return total;
    }

    public void addPlatform(Platform platform) {
        platfs.add(platform);
        Collections.sort(platfs);
    }

    public void editPlatform(Platform platform, int quantity, double revenue) {
        int index = platfs.indexOf(platform);

        platfs.get(index).setQuantity(platfs.get(index).getQuantity() + quantity);
        platfs.get(index).setRevenue(platfs.get(index).getRevenue() + revenue);
        Collections.sort(platfs);
    }

    public void setPlatforms(List<Platform> platfs) {
        this.platfs = platfs;
    }

    public List<Platform> getPlatforms() {
        return this.platfs;
    }

    public String getTopPlatforms() {
        StringBuilder sb = new StringBuilder();

        sb.append("Most popular platforms\n\n");

        Comparator<Platform> comp = Comparator.comparing(Platform::getQuantity).reversed();

        Collections.sort(this.platfs, comp);

        for(int i = 0; i < this.platfs.size(); i++) {
            if(i == 10) {
                break;
            }

            sb.append(i + 1 + ". " + this.platfs.get(i).getName() + " (" + this.platfs.get(i).getQuantity() + " " + this.platfs.get(i).getType() + "s)\n");
        }

        sb.append("\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Country: " + this.getName() + "\n\n"
                + "Streams: " + this.getStreams() + "\n\n" +
                "Downloads: " + this.getDownloads() + "\n\n" +
                "Revenue: " + this.getRevenue() + "\n\n\n" +
                getTopPlatforms();
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + this.name.hashCode();
        hash = 31 * hash + this.platfs.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Country)) {
            return false;
        }

        Country comp = (Country) obj;

        if(!comp.getName().equals(this.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int compareTo(Country country) {
        return this.name.compareToIgnoreCase(country.getName());
    }
}
