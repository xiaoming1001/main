package com.study.shiwu.databaseioc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("当前数据源为："+DatabaseContextHolder.getDatabaseType());
        return DatabaseContextHolder.getDatabaseType();
    }
}
