// Assignment: Assignment 10
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Class that creates a linked list object.

public class LinkedList {
    Table first;
    int size;
    public LinkedList(){
        first = null;
        size = 0;
    }

    public void add(int numberOfGuests, String name){
        Table newGuest = new Table(numberOfGuests, name);
        if(first == null){
            first = newGuest;
        }
        else{
            Table pointer = first;
            while(pointer.next != null)
                pointer = pointer.next;

            pointer.next = newGuest;
        }
        size++;
    }

    //removeFirst() method removes the first reservation on the waitlist. Return the updated Table object. Make sure to handle the case where the waitlist is empty.

    public Table removeFirst(){
        if(first == null)
            return new Table.EmptyTable();
        else{
            Table temp = first;
            first = first.next;
            size--;
            return temp;
        }
    }

    //removeLast() removes the reservation at the end of the waitlist.

    public Table removeLast(){
        if(first == null)
            return new Table.EmptyTable();
        else if(first.next == null){
            Table temp = first;
            first = null;
            size--;
            return temp;
        }
        else{
            Table pointer = first;
            while(pointer.next.next != null)
                pointer = pointer.next;

            Table temp = pointer.next;
            pointer.next = null;
            size--;
            return temp;
        }
    }

    //removeMiddle() removes the reservation at the middle of list by their name.

    public Table removeMiddle(String name){
        if(first == null)
            return new Table.EmptyTable();
        else if(first.name.equals(name)){
            Table temp = first;
            first = first.next;
            size--;
            return temp;
        }
        else{
            Table pointer = first;
            while(pointer.next != null && !pointer.next.name.equals(name))
                pointer = pointer.next;

            if(pointer.next == null)
                return new Table.EmptyTable();
            else{
                Table temp = pointer.next;
                pointer.next = pointer.next.next;
                size--;
                return temp;
            }
        }
    }

    //getPosition() finds and returns the index of the reservation with the given name. Return -1 if the reservation is not found.

    public int getPosition(String name){
        if(first == null)
            return -1;
        else{
            Table pointer = first;
            int index = 0;
            while(pointer != null && !pointer.name.equals(name)){
                pointer = pointer.next;
                index++;
            }
            if(pointer == null)
                return -1;
            else
                return index;
        }
    }

    //removeGuest() remove reservation with given name using getPosition(), removeMiddle(), removeFirst(), and removeLast() methods.

    public Table removeGuest(String name){
        int position = getPosition(name);
        if(position == 0)
            return removeFirst();
        else if(position == size - 1)
            return removeLast();
        else if(position == -1)
            return new Table.EmptyTable();
        else
            return removeMiddle(name);
    }

    //getNumberOfParties() checks how many parties with a specified number of guests are on the waitlist. If there are none return -1.
    public int getNumberOfParties(int numberOfGuests){
        if(first == null)
            return -1;
        else{
            Table pointer = first;
            int count = 0;
            while(pointer != null){
                if(pointer.guests == numberOfGuests)
                    count++;
                pointer = pointer.next;
            }
            return count;
        }
    }

    //listReservations() return a string listing all reservations on the waitlist using the toString() method. 
    //Also returns a string stating the total number of reservations.
    //If there are no reservations return "No reservations in the queue at this time"

    public String listReservations(){
        if(first == null)
            return "No reservations in queue at this time.\n";
        else{
            Table pointer = first;
            String list = "";
            while(pointer != null){
                list += pointer.toString();
                pointer = pointer.next;
            }
            return list + "\nTotal reservations: " + size + ".\n";
        }
    }
    
}
