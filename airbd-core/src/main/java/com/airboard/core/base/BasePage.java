package com.airboard.core.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePage implements Serializable {

    public final static Integer PAGE_INDEX = 1;
    public final static Integer PAGE_SIZE = 10;
    public final static String SORT_FIELD = "create_time";
    public final static String SORT_ORDER = "desc";

    private Integer pageIndex;

    private Integer pageSize;

    private String sortField;

    private String sortOrder;

    public BasePage() {
        this.pageIndex = PAGE_INDEX;
        this.pageSize = PAGE_SIZE;
        this.sortField = SORT_FIELD;
        this.sortOrder = SORT_ORDER;
    }

}
