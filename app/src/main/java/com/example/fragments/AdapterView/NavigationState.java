package com.example.fragments.AdapterView;

import java.util.HashMap;

public class NavigationState<T> {
    public static final String PAGE_PRIMER_FRAGMENTO = "PAGE_PRIMER_FRAGMENTO";
    public static final String PAGE_SEGUNDO_FRAGMENTO = "PAGE_SEGUNDO_FRAGMENTO";


    private HashMap<String, T> state = new HashMap<>();

    public T getState(String page) {
        return state.get(page);
    }

    public void putState(String page, T state) {
        this.state.put(page, state);
    }
}
