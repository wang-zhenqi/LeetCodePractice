package com.practice.zhenqi;

import java.util.ArrayList;
import java.util.List;

public class SimpleQueue implements MyQueue {
    private List<Integer> data;
    private int header;

    public SimpleQueue() {
        data = new ArrayList<>();
        header = -1;
    }

    @Override
    public boolean Enqueue(Integer ele) {
        System.out.printf("Enqueue: %d\n", ele);
        data.add(ele);
        return true;
    }

    @Override
    public boolean Dequeue() {
        System.out.println("Dequeue:");
        if(isEmpty()) {
            return false;
        }
        header++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        if(header >= data.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return data.size() - header - 1;
    }

    @Override
    public void display() {
        System.out.printf("Queue length: %d\n", size());
        for(int i = 0; i < size(); i++) {
            if(i == 0) {
                System.out.printf("%d", data.get(header + i + 1));
            } else {
                System.out.printf(", %d", data.get(header + i + 1));
            }
        }
        System.out.print("\n\n");
    }
}
