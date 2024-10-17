package com.learning.java.lld.inmemoryrdbms.command;

public enum DatabaseCommandType {

    READ_ROW("readRow"),
    CREATE_TABLE("createTable"),
    CREATE_ROW("createRow");

    public final String value;

     DatabaseCommandType(String readByPk) {
        this.value = readByPk;
    }

    public static DatabaseCommandType getByValue(String value) {
        for(DatabaseCommandType type: values()) {
            if(type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

}
