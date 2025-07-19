package com.mycompany.booking;
import java.util.Arrays;
public class ArrayCollection {
    int arr[];
    int count;
    public ArrayCollection() {
        this(5);
        System.out.println("ArrayCollection created.");
    }
    public ArrayCollection(int x) {
        arr = new int[x];
    }
    void remove(int x){
        int index = search(x);
        if(index<arr.length){
            arr[index] = arr[count-1];
            count--;
        }       
    }
    int search(int x){//sentinel
        int i=0;
        int a[];
        a = Arrays.copyOf(arr,count);
        a[a.length-1]=x;    
        for(;i<count;i++){
            if(a[i]==x){
               break; 
            }
        }
        return i;
    }
    void add(int item) {
        if (size() == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[count] = item;
        count++;
    }
    void show() {       
        for (int i = 0; i < count; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    int size() {
        return count;
    }
}
