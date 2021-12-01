package com.company.codementor;

import java.util.List;

public class PaginationHelper<I> {

    private List<I> collection;

    private int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.collection = collection;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return collection.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        final int size = collection.size();
        return size / itemsPerPage + (size % itemsPerPage > 0 ? 1 : 0);
    }

    /**
     * returns the number of items on the current page. page_index is zero based. this method should return -1 for
     * pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        final int size = collection.size();
        final double maxPage = Math.ceil((double) size / itemsPerPage);
        if (pageIndex > maxPage) {
            return -1;
        } else if (pageIndex == maxPage) {
            return size % (pageIndex + 1);
        }
        return itemsPerPage;
    }

    /**
     * determines what page an item is on. Zero based indexes this method should return -1 for itemIndex values that are
     * out of range
     */
    public int pageIndex(int itemIndex) {
        final int size = collection.size();
        if (itemIndex > size - 1) {
            return -1;
        }
        return itemIndex / itemsPerPage;
    }

}
