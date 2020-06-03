package com.practice.zhenqi;

import java.util.ArrayList;
import java.util.List;

public class RingQueue implements MyQueue {
    private List<Integer> data;
    private int header;
    private int tailer;
    private int maxLength;
    private int size;

    public RingQueue(int maxLength) {
        this.maxLength = maxLength;
        data = new ArrayList<>(maxLength);
        header = 0;
        tailer = 0;
        size = 0;
    }

    @Override
    public boolean Enqueue(Integer ele) {
        System.out.printf("Enqueue: %d\n", ele);
        if(size < maxLength) {
            if(data.size() < maxLength) {
                data.add(ele);
            } else {
                data.set(tailer, ele);
            }
            tailer = (tailer + 1) % maxLength;
            size++;
            return true;
        } else {
            System.out.println("The queue is full.");
            return false;
        }
    }

    @Override
    public boolean Dequeue() {
        System.out.println("Dequeue:");
        if(size() > 0) {
            header = (header + 1) % maxLength;
            size--;
            return true;
        } else {
            System.out.println("The queue is already empty.");
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.printf("Queue length: %d\n", size());
        for(int i = 0; i < size; i++) {
            if(i == 0) {
                System.out.printf("%d", data.get((header + i) % maxLength));
            } else {
                System.out.printf(", %d", data.get((header + i) % maxLength));
            }
        }
        System.out.print("\n\n");
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(size > 0) {
            return data.get(header);
        } else {
            return -1;
        }
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(size > 0) {
            return data.get((tailer + maxLength - 1) % maxLength);
        } else {
            return -1;
        }
    }
}
