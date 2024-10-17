package com.learning.java.lld.inmemoryrdbms.service;

import com.learning.java.lld.inmemoryrdbms.model.Row;

import java.util.List;

public interface DatabaseService {

    boolean createTable(String tableName, String pkName);

    boolean createRow(String tableName, Row row);

    boolean deleteRow(String tableName, String rowKey);

    List<Row> readByPk(String tableName, String value);

    List<Row> readBySecondaryKey(String tableName, String key, String value);

    boolean deleteByRowKey(String tableName, String rowKey);

    String getPkAttributeName(String tableName);

    boolean isTableExists(String tableName);
}
