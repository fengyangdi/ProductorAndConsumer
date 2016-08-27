package com.fengyangdi.pac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by GGM on 2016/8/27.
 */
public class StorageCondition<T> implements Storage<T> {

    private static final int DEFAULT_SIZE = 10;
    private int maxSize = DEFAULT_SIZE;
    private List<T> list = new ArrayList<>();

    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    public StorageCondition(int maxSize){
        this.maxSize = maxSize;
    }

    public StorageCondition(){
    }

    @Override
    public void produce(T obj) {
        lock.lock();
        try{
            while (list.size() == maxSize){
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.add(obj);
            empty.signalAll();

        }finally {
            lock.unlock();
        }
    }

    @Override
    public T consume() {
        T val = null;
        lock.lock();
        try{
            while (list.size() == 0){
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            val = list.remove(list.size() - 1);
            full.signalAll();

        }finally {
            lock.unlock();
        }
        return val;
    }
}
