package com.fengyangdi.pac;

/**
 * Created by GGM on 2016/8/27.
 */
public class Productor<T>  implements Runnable{

    private Storage<T> storage = null;

    private Class<T> cls = null;

    public Productor(Storage<T> storage, Class<T> cls){
        this.storage = storage;
        this.cls = cls;
    }

    @Override
    public void run() {
        while (true) {
            T obj = null;
            try {
                obj = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.produce(obj);
            System.out.println("生成了一个产品");
        }
    }
}
