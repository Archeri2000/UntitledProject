package main.com.entities;

public class SalesCounter {
    private int counter = 0;
    public SalesCounter(){}
    public SalesCounter(int start){
        counter = start;
    }
    public int get(){
        return counter;
    }
    public void increment(int val){
        counter += val;
    }
}
