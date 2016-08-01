/**
 * Created by Suwadith on 7/16/2016.
 */


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //Created static variables including the array and scanner,
    // so it can be used in all the static methods without being declared again
    static String choice;
    static String answer;
    static String customerName;
    static int roomNumber = 0;
    static String[] hotel = new String[10];
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        initialize(hotel);
        menu();

    }

    private static void initialize(String hotelRef[]) {

        //Hotel array will only have Null characters at the beginning.
        //Assigning a String value of "e" to all of it's elements.
        for (int x = 0; x < 10; x++) {
            hotelRef[x] = "e";
        }
    }

    public static void menu() {

        System.out.println("======================================================");
        System.out.println("*            Hotel Management System                 *");
        System.out.println("======================================================");
        System.out.println("* V. View all the rooms                              *");
        System.out.println("* A. Add customer to room                            *");
        System.out.println("* E. Display Empty rooms                             *");
        System.out.println("* D. Delete customer from room                       *");
        System.out.println("* F. Find room from customer name                    *");
        System.out.println("* S. Store program array data into a text file       *");
        System.out.println("* L. Load program data back from the file            *");
        System.out.println("* O. View rooms Ordered alphabetically by name       *");
        System.out.println("* Q. Quit Program                                    *");
        System.out.println("======================================================");
        System.out.println("");

        System.out.println("Choose one of the options from above. (E.g: Type 'V' to view all the rooms)");

        do {
            System.out.println();
            System.out.print("Choice : ");
            choice = input.next();
            String selection = choice.toLowerCase(); //This will convert the input value to lowercase. this will help avoid case sensitive issues.

            switch (selection) {

                case "v":
                    viewRooms();
                    break;
                case "a":

                    addCustomer();
                    break;

                case "e":
                    displayEmptyRooms();
                    break;

                case "d":
                    deleteCustomer();
                    break;

                case "f":
                    findRoom();
                    break;

                case "s":
                    storeData();
                    break;

                case "l":
                    retrieveData();
                    break;

                case "o":
                    alphabeticalOrder();
                    break;

                case "q":
                    System.out.println("Thanks");
                    break;

                default:
                    System.out.println("Invalid input! Please Enter one of these letters: V,A,E,D,F,S,L,O,Q");
            }
            //viewRooms();
        }
        while (!(choice.equalsIgnoreCase("v") || choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("e") || choice.equalsIgnoreCase("d") ||
                choice.equalsIgnoreCase("f") || choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("l") || choice.equalsIgnoreCase("o") ||
                choice.equalsIgnoreCase("q"))); //condition to only let valid range of inputs through.

    }

    private static void viewRooms() {

        while (roomNumber < 10) {
            for (int x = 0; x < 10; x++) {
                //This will display the room number and the current owner's name
                if (!(hotel[x].equals("e"))) {
                    System.out.println("Room No. " + x + " is occupied by Mr. " + hotel[x]);
                    //This will display the rooms which are currently Empty
                } else {
                    System.out.println("Room No. " + x + " is empty");
                }
            }
            break;//This statement is just being used to break the loop
        }
        System.out.println("");
        menu();
    }

    private static void addCustomer() {

        boolean invalidRoomNumber;//declaring a boolean value so it is easier to break or catch data from a loop

        do {
            invalidRoomNumber = false;
            try {

                System.out.println("Enter room number (0-9)");
                roomNumber = input.nextInt();

                //if the room is already occupied this message will get printed
                if (!(hotel[roomNumber].equals("e"))) {
                    invalidRoomNumber = true;
                    System.out.println("This room is occupied by: Mr. " + hotel[roomNumber]);
                    System.out.println("");

                    //if the room is empty and the input value is within the range it accepts the input
                } else if (roomNumber >= 0 && roomNumber < 10) {
                    invalidRoomNumber = false;

                    //if the input exceeds the range then this error message will be displayed
                } else {
                    invalidRoomNumber = true;
                    System.out.println("Invalid input! Please Enter a value between 0-9");
                    System.out.println("");
                }

                //if the input is not an integer value then this will catch it
            } catch (InputMismatchException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 0-9");
                System.out.println("");
                input.next();

                //if the input is out of the range of the hotel array this will catch it
            } catch (IndexOutOfBoundsException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 0-9");
                System.out.println("");
                input.next();
            }
        } while (invalidRoomNumber);//these steps will follow if all of the above is valid
        System.out.println("Enter the name of the customer :"); /*+ roomNumber +*/
        customerName = input.next();
        hotel[roomNumber] = customerName;

        //this will let you choose whether to add more data or not
        do {
            System.out.println("Do you want to continue adding records?(Y/N)");
            answer = input.next();
            String selection = answer.toLowerCase();

            switch (selection) {
                case "y":
                    addCustomer();

                case "n":
                    System.out.println("");
                    menu();
            }

        } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));

    }

    private static void displayEmptyRooms() {

        //this method will display all the empty rooms
        for (int x = 0; x < 10; x++) {
            if (hotel[x].equals("e")) {
                System.out.println("room " + x + " is empty");
            }
        }
        System.out.println("");
        menu();
    }

    private static void deleteCustomer() {

        boolean invalidInput;
        do {
            invalidInput = false;
            try {
                System.out.println("please enter the Room's number which you want to vacate");
                roomNumber = input.nextInt();

                //if the hotel room is not empty then this will delete the customer from that room
                if (!(hotel[roomNumber].equals("e"))) {
                    invalidInput = false;
                    hotel[roomNumber] = "e";

                    //if the room is already empty this message will be displayed
                } else {
                    invalidInput = true;
                    System.out.println("Room " + roomNumber + " is already Empty");
                    System.out.println("");
                }

                //if the input is not an integer value then this will catch it
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Invalid input! Please Enter a value between 0-9");
                System.out.println("");
                input.next();

                //if the input is out of the range of the hotel array this will catch it
            } catch (IndexOutOfBoundsException e) {
                invalidInput = true;
                System.out.println("Invalid room number. Please enter a value between 0-9");
                input.next();
            }

        } while (invalidInput);//This will print the room's number which has been successfully vacated
        System.out.println("Room " + roomNumber + " has successfully been vacated");

        System.out.println("");
        menu();
    }

    private static void findRoom() {

        System.out.println("Please enter the name of the customer");
        boolean found = false;
        String find = input.next();


        for (int n = 0; n < 10; n++) {
            //used equalsIgnoreCase to avoid case sensitive issues while searching for a customer
            //this method will find the room's number which is currently being occupied by the mentioned customer
            if (hotel[n].equalsIgnoreCase(find)) {
                found = true;
                System.out.println("Mr. " + find + " is staying in room No. " + n);
                System.out.println("");
                menu();
            }
        }

        //this will let ou know if the customer is not there in the database
        if (found == false) {
            System.out.println(find + " doesn't exist on our database");
            System.out.println("");
            menu();
        }

    }

    private static void alphabeticalOrder() {

        int index = 0;
        String[] names = new String[10];

        //copy hotel array data to names array
        for (int x = 0; x < 10; x++) {
            names[x] = hotel[x].toLowerCase();//used this to avoid case sensitive issues.
        }

        //used Bubble sort
        for (int i = 0; i < names.length - 1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (names[j].compareTo(names[i]) < 0) {
                    String temp = names[j];
                    names[j] = names[i];
                    names[i] = temp;
                }
            }
        }

        //This will add the list of names in ascending order in our new array
        for (int x = 0; x < names.length; x++) {
            if (!(names[x].equals("e"))) {

                for (int i = 0; i < hotel.length; i++) {
                    if (hotel[i].toLowerCase().equals(names[x])) {
                        index = i;
                    }
                }
                System.out.println("Mr. " + names[x] + " is staying in room No. " + index);
            }
        }
        System.out.println("");
        menu();
    }

    private static void storeData() {

        try {
            //saving Data and overwriting
            BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", false));
            for (int x = 0; x < hotel.length; x++) {
                String file;
                file = hotel[x];
                // writes Empty room if it find "e" on the array
                if (file.equals("e")) {
                    bw.write("Empty Room " + x);

                    // writes the name of the customer from the array
                } else {
                    bw.write(file);
                }
                bw.newLine(); //Line Seperator
                bw.flush(); //Flushes the stream.

            }

            //will catch this exception if the Text file is not found
        } catch (IOException e) {
            System.err.println("File not found!");
        }

        //message to show the user that the array data has been saved to a Text file successfully
        System.out.println("Data successfully saved!");
        System.out.println("");
        menu();

    }

    private static void retrieveData() {

        try {
            //reading Data from the Text File
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));

            for (int x = 0; x < hotel.length; x++) {
                String read = reader.readLine(); //Reads String value stored in the Text File
                if (read.equals("Empty Room " + x)) {
                    read = "e";
                }
                //Stored the data in the Hotel Array
                hotel[x] = read;
            }

            //will catch this exception if the Text file is not found
        } catch (IOException e) {
            System.err.println("File not found!");
        }
        //Displays this message if the file is found and successfully loaded the data back from it
        System.out.println("File successfully loaded");
        System.out.println("");
        menu();
    }
}

