// Assignment: Assignment 10
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Class that creates a table object.

public class Table {
    String name;
    int guests;
    Table next;

    public Table(int n, String name){
        this.name = name;
        guests = n;
        next = null;
    }

    public String toString(){
        return "\nReservation for " + name + ": party of "
                + guests + ".\n";
    }


    public static class EmptyTable extends Table{

        public EmptyTable() {
            super(-1, "");
        }
    }
}
