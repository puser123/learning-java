package com.learning.java.lld.inmemoryrdbms.command;

import com.learning.java.lld.inmemoryrdbms.model.Row;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseCommand {

    private DatabaseCommandType commandType;

    private String tableName;
    private String dbName;
    private String pkKey;
    private Row row;

    public DatabaseCommand(String command) {
        List<String> commandString =  Arrays.stream(command.split(" ")).collect(Collectors.toList());
        // validate command string.
        this.dbName = commandString.get(commandString.size() -1);
        this.tableName = commandString.get(commandString.size() - 2);
        this.commandType = DatabaseCommandType.getByValue(commandString.get(0));
        row = new Row();
        if(DatabaseCommandType.CREATE_ROW.equals(commandType)) {
            row.setName(commandString.get(1));
            row.setId(Integer.valueOf(commandString.get(2)));
            row.setId(Integer.valueOf(commandString.get(3)));
        } else if(DatabaseCommandType.READ_ROW.equals(this.commandType)) {
            if(commandString.get(1).equalsIgnoreCase("name")) {
                row.setName(commandString.get(2));
            } else if(commandString.get(1).equalsIgnoreCase("id")) {
                row.setAge(Integer.valueOf(commandString.get(2)));
            } else {
                row.setAge(Integer.valueOf(commandString.get(2)));
            }
        } if(DatabaseCommandType.CREATE_TABLE.equals(this.commandType)) {
            pkKey = commandString.get(1);
        }
    }

    public String getDbName() {
        return dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public DatabaseCommandType getCommandType() {
        return commandType;
    }

    public Row getRow() {
        return row;
    }

    public String getPkKey() {
        return pkKey;
    }
}
