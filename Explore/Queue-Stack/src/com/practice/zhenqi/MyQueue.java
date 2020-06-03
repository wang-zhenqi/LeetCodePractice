package com.practice.zhenqi;

import java.util.ArrayList;
import java.util.List;

public interface MyQueue {
    public boolean Enqueue(Integer ele);

    public boolean Dequeue();

    public boolean isEmpty();

    public int size();

    public void display();
}
