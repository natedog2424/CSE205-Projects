import java.util.ArrayList;
import java.util.Comparator;
// Assignment: Assignment 8
// arrSizeame: arrSizeathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Restaurant Review Management System “Kelp”. The command-line system allows you to search for a restaurant or cuisine, add a review, list all reviews sorted by stars or cuisine, and serialize/deserialize (save and retrieve) personal reviews.

public class Sorts {
    public static void sort(ArrayList<Restaurant> reviewList, Comparator<Restaurant> xComparator) {
        int arrSize = reviewList.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < arrSize - 1; i++) {
            // Find the minimum element in unsorted array
            int min = i;
            for (int j = i + 1; j < arrSize; j++)
                if (xComparator.compare(reviewList.get(j), reviewList.get(min)) < 0)
                    min = j;

            Restaurant temp = reviewList.get(min);
            reviewList.set(min, reviewList.get(i));
            reviewList.set(i,temp);
        }
    }
}
