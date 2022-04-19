import java.util.Comparator;

// Assignment: Assignment 8
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Restaurant Review Management System “Kelp”. The command-line system allows you to search for a restaurant or cuisine, add a review, list all reviews sorted by stars or cuisine, and serialize/deserialize (save and retrieve) personal reviews.

public class ReviewCuisineComparator implements Comparator<Restaurant>{
    public int compare(Restaurant first, Restaurant second){
        int value = first.getCuisine().getName().compareTo(second.getCuisine().getName());
        if (value != 0) return value;
        value = first.getPriceRange() - second.getPriceRange();
        if (value != 0) return value;
        value = first.getRestaurantName().compareTo(second.getRestaurantName());
        if (value != 0) return value;
        value = first.getLocation().compareTo(second.getLocation());
        if (value != 0) return value;
        value = first.getReview().compareTo(second.getReview());
        return value;
    }
}