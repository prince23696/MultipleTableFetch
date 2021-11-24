package com.MultipleTableFetch.Dto;

public class APIResponse<T> {

    int recordCount;
    T response;

    public APIResponse() {
    }

    public APIResponse(int recordCount, T response) {
        this.recordCount = recordCount;
        this.response = response;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "recordCount=" + recordCount +
                ", response=" + response +
                '}';
    }
}
