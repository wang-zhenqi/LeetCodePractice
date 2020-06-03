package com.practice.zhenqi;

public class MovingAverage {
    private int header;
    private int tailer;
    private int size;
    private int windowSize;
    int[] queue;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        windowSize = size;
        queue = new int[windowSize];
        for(int q : queue) {
            q = 0;
        }
        this.size = 0;
        header = 0;
        tailer = 0;
    }

    public double next(int val) {
        //if queue is full, dequeue
        if(size == windowSize) {
            header = (header + 1) % windowSize;
            size--;
        }

        //queue is not full, enqueue
        queue[tailer] = val;
        tailer = (tailer + 1) % windowSize;
        size++;

        int sum = 0;
        for(int i = 0; i < size; i++) { //sum up the elements
            sum += queue[(header + i) % windowSize];
        }
        return (double)sum / size;
    }
}