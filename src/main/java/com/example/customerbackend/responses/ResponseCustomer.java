package com.example.customerbackend.responses;

import java.util.List;

import com.example.customerbackend.documents.CustomerDocument;

public class ResponseCustomer {
    private List<CustomerDocument> customers;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private long totalElements;

    public ResponseCustomer() {
    }

    public ResponseCustomer(List<CustomerDocument> customers, int totalPages, int pageNumber, int pageSize,
            long totalElements) {
        this.customers = customers;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    public List<CustomerDocument> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDocument> customers) {
        this.customers = customers;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

}
