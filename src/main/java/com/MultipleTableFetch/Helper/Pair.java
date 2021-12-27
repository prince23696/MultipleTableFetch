package com.MultipleTableFetch.Helper;

public class Pair {
    private final int min;
    private final int fetchCount;

    public Pair(int min, int fetchCount) {
        super();
        this.min = min;
        this.fetchCount = fetchCount;
    }

    public int getMin() {
        return min;
    }

    public int getFetchCount() {
        return fetchCount;
    }

}