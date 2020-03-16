package com.study.shiwu.databaseioc;

import com.study.shiwu.enums.DataBaseType;

//保存一个线程安全的dataBaseType容器
public class DatabaseContextHolder {
    private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<>();


    public static void setDatabaseType(DataBaseType type) {
        System.out.println("切换到{}数据源:  "+type);
        contextHolder.set(type);
    }


    public static DataBaseType getDatabaseType() {
        return (contextHolder.get());
    }
    public static void clearDatabaseType(){
        contextHolder.remove();
    }

}
