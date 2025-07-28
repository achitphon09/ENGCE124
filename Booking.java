package com.mycompany.booking;

import java.util.Scanner;

class Reservation {

    static int counter = 2547;
    String id, name, date, time;
    int table;

    public Reservation(String name, int table, String date, String time) {
        this.id = "" + (counter++);
        this.name = name;
        this.table = table;
        this.date = date;
        this.time = time;
    }

    public boolean sameSlot(Reservation r) {
        return table == r.table && date.equals(r.date) && time.equals(r.time);
    }

    public boolean sameCustomer(Reservation r) {
        return name.equalsIgnoreCase(r.name) && sameSlot(r);
    }

    public String toString() {
        return "ID: #" + id + "\n"
                + "Table: " + table + "\n"
                + "Date: " + date + "\n"
                + "Time: " + time;
    }
}

public class Booking {

    static Scanner sc = new Scanner(System.in);
    static Reservation[] list = new Reservation[10];
    static int count = 0;

    static Set activeIDs = new Set();
    static ArrayCollection canceledIDs = new ArrayCollection();

    public static void main(String[] args) {
        while (true) {
            System.out.print("\n(add/cancel/check/exit): ");
            String cmd = sc.nextLine().trim();

            if (cmd.equalsIgnoreCase("add")) {
                add();
            } else if (cmd.equalsIgnoreCase("cancel")) {
                cancel();
            } else if (cmd.equalsIgnoreCase("check")) {
                check();
            } else if (cmd.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    static void add() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Table: ");
        int table = Integer.parseInt(sc.nextLine());
        System.out.print("Date: ");
        String date = sc.nextLine();
        System.out.print("Time: ");
        String time = sc.nextLine();

        Reservation newRes = new Reservation(name, table, date, time);
        list[count++] = newRes;
        int intID = Integer.parseInt(newRes.id);
        activeIDs.add(intID);

        System.out.println("\n" + newRes);

        boolean dup = false;
        boolean dbl = false;

        for (int i = 0; i < count - 1; i++) {
            Reservation r = list[i];
            if (r == null) {
                continue;
            }
            if (r.sameCustomer(newRes)) {
                dup = true;
            } else if (r.sameSlot(newRes)) {
                dbl = true;
            }
        }

        if (dup) {
            System.out.println("Duplicate booking");
        } else if (dbl) {
            System.out.println("Double booking");
        }
    }

    static void cancel() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        int intID = Integer.parseInt(id);

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (list[i] != null && list[i].id.equals(id)) {
                Reservation canceled = list[i];
                list[i] = null;
                activeIDs.remove(intID);
                canceledIDs.add(intID);
                System.out.println("ID: #" + id + " Canceled.");
                found = true;

                for (int j = 0; j < count; j++) {
                    if (list[j] != null && list[j].sameSlot(canceled)) {
                        System.out.println("ID: #" + list[j].id + " is now confirmed.");
                        break;
                    }
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Booking not found.");
        }
    }

    static void check() {
        System.out.print("Table: ");
        int table = Integer.parseInt(sc.nextLine());

        boolean dup = false;
        boolean dbl = false;
        boolean found = false;

        Reservation[] bookings = new Reservation[count];
        int bookingCount = 0;

        for (int i = 0; i < count; i++) {
            Reservation r = list[i];
            if (r != null && r.table == table) {
                bookings[bookingCount++] = r;
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings.");
            return;
        }
        System.out.println("Bookings for Table " + table + ":");
        for (int i = 0; i < bookingCount; i++) {
            Reservation r = bookings[i];
            System.out.println("- ID #" + r.id + ": " + r.name + " on " + r.date + " at " + r.time);
        }
        for (int i = 0; i < bookingCount; i++) {
            Reservation r1 = bookings[i];
            for (int j = i + 1; j < bookingCount; j++) {
                Reservation r2 = bookings[j];

                if (r1.sameCustomer(r2)) {
                    dup = true;
                    System.out.println("Duplicate: " + r1.name + " booked twice at Table " + table);
                    System.out.println("   ID #" + r1.id + " and ID #" + r2.id);
                } else if (r1.sameSlot(r2)) {
                    dbl = true;
                    System.out.println("Double booking at Table " + table + ":");
                    System.out.println("   ID #" + r1.id + " (" + r1.name + ") and ID #" + r2.id + " (" + r2.name + ")");
                }
            }
        }
        if (!dup && !dbl && bookingCount == 1) {
            Reservation r = bookings[0];
            System.out.println("Table " + table + " is booked by " + r.name + " on " + r.date + " at " + r.time);
            System.out.println("No booking conflict.");
        }
    }
}
