package com.fas.fcdsystem.singletonDP;

import java.io.Serializable;

public class LazyAlfrescoSingleton implements Serializable {
    private static LazyAlfrescoSingleton instance = null;

    //prevent instantiation from other classes
    private LazyAlfrescoSingleton() {
    }

    // a static method to get the instance
    public static LazyAlfrescoSingleton getInstance() {
        if (instance == null) {
            instance = new LazyAlfrescoSingleton();
        }
        return instance;
    }

    // prevent cloning
    protected Object readResolve() {
        return instance;
    }
}



