// Assignment: Assignment 8
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Restaurant Review Management System “Kelp”. The command-line system allows you to search for a restaurant or cuisine, add a review, list all reviews sorted by stars or cuisine, and serialize/deserialize (save and retrieve) personal reviews.


import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;

    ArrayList<Restaurant> reviewList;

    public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    public int restaurantExists(String name, String location){
        for (int i = 0; i < reviewList.size(); i++) {
            Restaurant restaurant = reviewList.get(i);
            if(restaurant.getRestaurantName().compareTo(name) == 0 && restaurant.getLocation().compareTo(location) == 0) return i;
        }
        return -1;
    }

    public ArrayList<Integer> cuisineExists(String name){
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int i = 0; i < reviewList.size(); i++){
            Cuisine cuisine = reviewList.get(i).getCuisine();
            if(cuisine.getName().compareTo(name) == 0) out.add(i);
        }
        if (out.size() == 0) out.add(-1);
        return out;
    }

    public Restaurant getRestaurant(int index){
        return reviewList.get(index);
    }

    /**
     * Add a Restaurant object to the reviewList and return true if such an object
     * is added successfully. Otherwise, return false. Two restaurants are
     * considered duplicated if they have exactly the same restaurant name and
     * cuisine name.
     * 
     * @param  restaurantName the name of the restaurant
     * @param  stars the number of stars for the restaurant
     * @param  review   the restaurant review
     * @param  priceRange    the integer price range
     * @param  cuisineName  the Cuisine's name
     * @param  location   the restaurant location (street address)
     * @param  signatureDish  most famous dish
     * @return            true if the operation is successful; false otherwise
     */
    public boolean addReview(String restaurantName, int stars, String review, String priceRange, String cuisineName, String location, String signatureDish) {
        if (restaurantExists(restaurantName, location) == -1) {
            int price = priceRange.length();
            Cuisine newCuisine = new Cuisine(signatureDish, cuisineName);
            Restaurant newRestaurant = new Restaurant(restaurantName, stars, review, price, location, newCuisine);
            reviewList.add(newRestaurant);
            return true;
        }
        return false;
    }

    public boolean removeReview(String name, String location){
        int index = restaurantExists(name, location);
        if(index != -1){
            reviewList.remove(index);
            return true;
        }
        return false;
    }

    public void sortByRating(){
        Sorts.sort(reviewList, new ReviewRatingComparator());
    }

    public void sortByCuisine(){
        Sorts.sort(reviewList, new ReviewCuisineComparator());
    }

    public String listReviews(){
        String out = "";
        for (Restaurant restaurant : reviewList) {
            out += restaurant.toString();
        }
        return out;
    }

    public void closeReviewManager(){
        reviewList.clear();
    }

}
