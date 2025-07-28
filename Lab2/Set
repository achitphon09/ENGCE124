package com.mycompany.booking;
import java.util.Arrays;

public class Set {

    int arr[];
    int count;

    public Set() {
        this(5);
        System.out.println("Set created.");
    }

    public Set(int x) {
        arr = new int[x];
    }

    void add(int item) {
        int i = search(item);
        if (size() == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        if (i == count) {
            arr[count] = item;
            count++;
        }

    }

    void remove(int x) {
        int index = search(x);
        if (index < arr.length) {
            arr[index] = arr[count - 1];
            count--;
        }
    }

    int search(int x) {//sentinel
        int i = 0;
        int a[];
        if (count != 0) {
            a = Arrays.copyOf(arr, count+1);
            a[a.length-1] = x;
            for (; i < count; i++) {
                if (a[i] == x) {
                    break;
                }
            }
        }
        return i;
    }

    int size() {
        return count;
    }

    void show() {
        for (int i = 0; i < count; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
