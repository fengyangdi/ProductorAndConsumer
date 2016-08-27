package com.fengyangdi.pac;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by GGM on 2016/8/27.
 */
public class BlockStorage<T> implements Storage<T> {

    private BlockingQueue<T> queue = null;
    private static final int DEFAULT_SIZE = 10;

    public BlockStorage(){
        queue = new LinkedBlockingQueue<>(DEFAULT_SIZE);
    }

    public BlockStorage(int maxSize){
        queue = new LinkedBlockingQueue<>(maxSize);
    }

    @Override
    public void produce(T obj) {
        try {
            queue.put(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T consume() {
        T val = null;
        try {
            val = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return val;
    }
}
