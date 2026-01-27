package com.example.demo.doma2;

import java.util.List;

public class PageResult<T> {

    private final List<T> content;

    private final int currentPage;

    private final int totalPages;

    private final int totalCount;

    public PageResult(
            List<T> content,
            int currentPage,
            int totalPages,
            int totalCount) {

        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean hasPrevious() {
        return currentPage > 1;
    }

    public boolean hasNext() {
        return currentPage < totalPages;
    }
}