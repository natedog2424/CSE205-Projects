import java.io.Serializable;

// Assignment: Assignment 8
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Restaurant Review Management System “Kelp”. The command-line system allows you to search for a restaurant or cuisine, add a review, list all reviews sorted by stars or cuisine, and serialize/deserialize (save and retrieve) personal reviews.

public class Restaurant implements Serializable{
    private long serialVersionUID = 205L;

    private String restaurantName;
    private int stars;
    private String review;
    private int priceRange;
    private String location;
    private Cuisine cuisine;

    public Restaurant(String name, int stars, String review, int priceRange, String location, Cuisine cuisine){
        this.cuisine = cuisine;
        this.location = location;
        this.priceRange = priceRange;
        this.review = review;
        this.stars = stars;
        this.restaurantName = name;
    }

    public String getRestaurantName(){
        return restaurantName;
    }

    public int getStars() {
        return stars;
    }

    public String getReview() {
        return review;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public String getLocation() {
        return location;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    @Override
    public String toString() {
        String out = restaurantName + " restaurant\n";
        for(int i = 0; i < stars; i++){
            out += "*";
        }
        out += "\t\t";
        for(int i = 0; i < priceRange; i++){
            out += "$";
        }
        out += "\n" + cuisine.toString() + "Location: " + location + "\nReview:\t" + review + "\n\n";
        return out;
    }
}
