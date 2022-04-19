// Assignment: Assignment 8
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Restaurant Review Management System “Kelp”. The command-line system allows you to search for a restaurant or cuisine, add a review, list all reviews sorted by stars or cuisine, and serialize/deserialize (save and retrieve) personal reviews.


import java.io.Serializable;

public class Cuisine implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;
    private String signatureDish;
    private String name;

    public Cuisine(String signatureDish, String name) {
        this.name = name;
        this.signatureDish = signatureDish;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " Cuisine\n" +
                "Signature Dish:\t" + signatureDish + '\n';
    }
}
