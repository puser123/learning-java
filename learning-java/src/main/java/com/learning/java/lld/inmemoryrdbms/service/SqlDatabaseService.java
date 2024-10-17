package com.learning.java.lld.inmemoryrdbms.service;

import com.learning.java.lld.inmemoryrdbms.model.Row;

import java.util.*;

public class SqlDatabaseService implements DatabaseService{
    // Write code such a way that your code is easily extensible.
    // Single DB support
    Map<String, Map<String, Row>> tableMap;
    Map<String, String> tablePrimaryKeyMap;
    private String dbName;

    public SqlDatabaseService(String dbName) {
       this.dbName = dbName;
       this.tablePrimaryKeyMap = new HashMap<>();
       this.tableMap = new HashMap<>();
    }

    @Override
    public boolean createTable(String tableName, String pkName) {
        //validate if db exists, otherwise throw error.
        tableMap.put(tableName, new HashMap<>());
        this.tablePrimaryKeyMap.put(tableName, pkName);
        return true;
    }

    @Override
    public boolean createRow(String tableName, Row row) {
        // Need to validate command if row already exists.
        tableMap.get(tableName).put(row.getName(), row);
        return true;
    }
    @Override
    public boolean deleteRow(String tableName, String rowKey) {
        return true;
    }

    @Override
    public List<Row> readByPk(String tableName, String value) {
        return Arrays.asList(tableMap.get(tableName).get(value));
    }

    @Override
    public List<Row> readBySecondaryKey(String tableName, String key, String value) {
        return new ArrayList<>();
    }

    @Override
    public boolean deleteByRowKey( String tableName, String rowKey) {
        return true;
    }

    @Override
    public String getPkAttributeName(String tableName) {
        return tablePrimaryKeyMap.get(tableName);
    }


    @Override
    public boolean isTableExists(String tableName) {
       return tableMap.containsKey(tableName);
    }
}
