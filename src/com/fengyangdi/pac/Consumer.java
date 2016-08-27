package com.fengyangdi.pac;

/**
 * Created by GGM on 2016/8/27.
 */
public class Consumer<T>  implements Runnable{

    private Storage<T> storage = null;

    private Class<T> cls = null;

    public Consumer(Storage<T> storage, Class<T> cls){
        this.storage = storage;
        this.cls = cls;
    }

    @Override
    public void run() {
        while (true) {
            T obj = null;
            obj = storage.consume();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费了一个产品");
        }
    }
}