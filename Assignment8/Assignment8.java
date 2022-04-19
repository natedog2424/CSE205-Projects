// Assignment: Assignment 8
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Restaurant Review Management System “Kelp”. The command-line system allows you to search for a restaurant or cuisine, add a review, list all reviews sorted by stars or cuisine, and serialize/deserialize (save and retrieve) personal reviews.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Assignment8 {
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Restaurant and Cuisine information
        String restaurantName, cuisineName;
        String review = null, location, signatureDish, priceRange;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Restaurant manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result
        boolean opResult;

        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Restaurant Review
                        System.out.print("Please enter the restaurant information:\n");
                        System.out.print("Enter the restaurant name:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the price range:\n");
                        priceRange = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the cuisine name:\n");
                        cuisineName = stdin.readLine().trim();
                        System.out.print("Enter the location:\n");
                        location = stdin.readLine().trim();
                        System.out.print("Enter the signature dish\n");
                        signatureDish = stdin.readLine().trim();

                        /*********************************************************************
                         * Complete the code by calling the addReview method. *
                         * If the review has been added successfully, show *
                         * "Restaurant added\n" on screen, otherwise "Restaurant NOT added\n" *
                         **********************************************************************/
                        if (reviewManager.addReview(restaurantName, rating, review, priceRange, cuisineName, location,
                                signatureDish)) {
                            System.out.print("Restaurant added\n");
                        } else {
                            System.out.print("Restaurant NOT added\n");
                        }
                        break;

                    case 'D': // Search a Restaurant
                        System.out.print("Please enter the restaurant name to search:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the restaurant's location':\n");
                        location = stdin.readLine().trim();

                        /*********************************************************************
                         * Complete the code. If a restaurant review exists, print *
                         * "Restaurant found. Here's the review:\n" *
                         * Otherwise, print "Restaurant not found. Please try again\n" *
                         **********************************************************************/

                        int index = reviewManager.restaurantExists(restaurantName, location);
                        if (index != -1) {
                            System.out.print("Restaurant found. Here's the review:\n");
                            System.out.println(reviewManager.getRestaurant(index).getReview());
                        } else {
                            System.out.print("Restaurant not found. Please try again\n");
                        }
                        break;
                    case 'E': // Search a cuisine
                        System.out.print("Please enter the cuisine name to search:\n");
                        cuisineName = stdin.readLine().trim();

                        /*******************************************************************************
                         * Complete the code. If a cuisine is found, show on the screen how many *
                         * restaurants match that cuisine by printing *
                         * "%s Restaurants matching %s cuisine were found:\n" followed by the reviews. *
                         * Otherwise, print "Cuisine: %s was NOT found\n" *
                         ******************************************************************************/

                        ArrayList<Integer> reviews = reviewManager.cuisineExists(cuisineName);
                        if (reviews.get(0) == -1) {
                            System.out.printf("Cuisine: %s was NOT found\n", cuisineName);
                        } else {
                            System.out.printf("%s Restaurants matching %s cuisine were found:\n", reviews.size(),
                                    cuisineName);
                            for (Integer i : reviews) {
                                System.out.print(reviewManager.getRestaurant(i).toString());
                            }
                            System.out.println();
                        }
                        break;
                    case 'L': // List restaurant's reviews
                        if (reviewManager.reviewList.size() != 0){
                            System.out.print("\n" + reviewManager.listReviews() + "\n");
                        } else {
                            System.out.println("\n\nNo Reviews available\n\n");
                        }
                        break;

                    
                    case 'N':
                        reviewManager.sortByRating();
                        System.out.println("sorted by rating");
                        break;

                    case 'P':
                        reviewManager.sortByCuisine();
                        System.out.println("sorted by cuisine");
                        break;

                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the restaurant name of the review to remove:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the location to remove:\n");
                        location = stdin.readLine().trim();

                        /*******************************************************************************
                         * Complete the code. If a review for a certain restaurant at a given location *
                         * is found, remove the review and print that it was removed. Otherwise *
                         * print that it was NOT removed. *
                         *******************************************************************************/
                        if (reviewManager.removeReview(restaurantName, location)) {
                            System.out.println(restaurantName + ", " + location + " was removed");
                        } else {
                            System.out.println(restaurantName + ", " + location + " was NOT removed");
                        }
                        break;
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("Restaurant management system was reset\n");
                        break;

                    case 'U': // Write restaurant names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the restaurant:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = restaurantName + "\n" + review + "\n";

                        /*************************************************************************************
                         * Add a try and catch block to write the string outMsg into the user-specified
                         * file *
                         * Then, print in the screen that the file " is written\n" *
                         * In case of an IO Exception, print "Write string inside the file error\n" *
                         *************************************************************************************/
                        try {
                            FileWriter fw = new FileWriter(outFilename);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter outFile = new PrintWriter(bw);
                            outFile.print(outMsg);
                            System.out.println(outFilename + " is written");
                            outFile.close();
                        } catch (IOException e) {
                            System.out.println("Write string inside the file error");
                        }
                        break;
                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();

                        /******************************************************************************************
                         * Add a try and catch block to read from the above text file. Confirm that the
                         * file *
                         * " was read\n" and then print "The contents of the file are:\n" followed by
                         * the contents *
                         * In case of an IO Exception, print "Read string from file error\n" *
                         * In case of a file not found exception, print that the file " was not found\n"
                         * *
                         ******************************************************************************************/
                        try {
                            String line;
                            String out = "";
                            FileReader fr = new FileReader(inFilename);
                            BufferedReader inFile = new BufferedReader(fr);
                            line = inFile.readLine();
                            while (line != null) {
                                out += line;
                                out += "\n";
                                line = inFile.readLine();
                            }
                            inFile.close();
                            System.out.println(inFilename + " was read");
                            System.out.print("The contents of the file are:\n" + out);
                            System.out.println("\n");
                        } catch (IOException e) {
                            System.out.println(inFilename + " was not found");
                        }
                        break;
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();

                        /****************************************************************************
                         * Add a try and catch block to serialize ReviewManager to a data file. *
                         * Catch two exceptions and print the corresponding messages on the screen: *
                         * "Not serializable exception\n" *
                         * "Data file written exception\n" *
                         ****************************************************************************/
                        try {
                            // Build the stream that can write objects (not text) to a disk
                            FileOutputStream bytesToDisk = new FileOutputStream(outFilename);
                            ObjectOutputStream objectToBytes = new ObjectOutputStream(bytesToDisk);

                            objectToBytes.writeObject(reviewManager);
                            // Do NOT forget to close the output stream
                            objectToBytes.close();
                        } catch (NotSerializableException e) {
                            System.out.println("Not serializable exception");
                        } catch (IOException e) {
                            System.out.println("Data file written exception");
                        }
                        break;
                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();

                        /*****************************************************************************
                         * Add a try and catch block to deserialize ReviewManager from a data file. *
                         * Catch three exceptions and print the corresponding messages on the screen:*
                         * "Class not found exception\n" *
                         * "Not serializable exception\n" *
                         * "Data file read exception\n" *
                         ****************************************************************************/
                        try {
                            FileInputStream diskToStreamOfBytes = new FileInputStream(inFilename);
                            ObjectInputStream objectToBytes = new ObjectInputStream(diskToStreamOfBytes);

                            Object reviewObject = null;
                            try {
                                reviewObject = objectToBytes.readObject();
                            } catch (ClassNotFoundException cnfe) {
                                System.out.println("Class not found exception");
                            }
                            reviewManager = (ReviewManager) reviewObject;
                            // Close input files also.
                            objectToBytes.close();
                            System.out.println(inFilename + " was read");
                        }catch (NotSerializableException e) {
                            System.out.println("Not serializable exception");
                        } catch (IOException e) {
                            System.out.println("Data file read exception");
                        }
                        break;
                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        } catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to Kelp! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) restaurants.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a restaurant\n" + "E\t\tSearch for a cuisine\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by cuisine\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
}
