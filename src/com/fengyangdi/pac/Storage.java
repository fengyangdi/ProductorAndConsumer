package com.fengyangdi.pac;

import java.util.ArrayList;
import java.util.List;

/**
 * 仓库的接口
 * Created by GGM on 2016/8/27.
 */
public interface Storage<T> {
    public void produce(T obj);
    public T consume();
}
