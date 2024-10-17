package com.learning.java.lld.inmemoryrdbms.command.factory;

import com.learning.java.lld.inmemoryrdbms.command.DatabaseCommandType;
import com.learning.java.lld.inmemoryrdbms.command.executor.CreateRowCommand;
import com.learning.java.lld.inmemoryrdbms.command.executor.CreateTableCommand;
import com.learning.java.lld.inmemoryrdbms.command.executor.DatabaseCommandExecutor;
import com.learning.java.lld.inmemoryrdbms.command.executor.ReadRowCommand;
import com.learning.java.lld.inmemoryrdbms.service.DatabaseService;

import java.util.HashMap;
import java.util.Map;

public class DatabaseCommandFactory {

    private Map<DatabaseCommandType, DatabaseCommandExecutor> commandExecutorMap;

    public DatabaseCommandFactory(DatabaseService databaseService) {
        commandExecutorMap= new HashMap<>();
        commandExecutorMap.put(DatabaseCommandType.READ_ROW , new ReadRowCommand(databaseService));
        commandExecutorMap.put(DatabaseCommandType.CREATE_ROW , new CreateRowCommand(databaseService));
        commandExecutorMap.put(DatabaseCommandType.CREATE_TABLE , new CreateTableCommand(databaseService));
    }

    public DatabaseCommandExecutor getCommandByType(DatabaseCommandType databaseCommandType) {
        return commandExecutorMap.get(databaseCommandType);
    }
}
