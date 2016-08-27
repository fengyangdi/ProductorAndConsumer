package com.fengyangdi.pac;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GGM on 2016/8/27.
 */
public class StorageWaitAndNotify<T> implements Storage<T>{

    private static final int DEFAULT_SIZE = 10;
    private int maxSize = DEFAULT_SIZE;
    private List<T> list = new ArrayList<>();

    public StorageWaitAndNotify(){

    }

    public StorageWaitAndNotify(int maxSize){
        this.maxSize = maxSize;
    }

    @Override
    public void produce(T obj) {
        synchronized (list){
            while (list.size() == maxSize){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.add(obj);
            list.notifyAll();
        }
    }

    @Override
    public T consume() {
        T val = null;
        synchronized (list){
            while (list.size() == 0){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            val = list.remove(list.size() - 1);
            list.notifyAll();
        }
        return val;
    }
}
