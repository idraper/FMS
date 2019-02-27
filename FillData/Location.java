package FillData;

public class Location {

    private final String country;
    private final String city;
    private final double latitutde;
    private final double longitude;

    public Location(String country, String city, double latitutde, double longitude) {
        this.country = country;
        this.city = city;
        this.latitutde = latitutde;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getLatitutde() {
        return latitutde;
    }

    public double getLongitude() {
        return longitude;
    }
}
