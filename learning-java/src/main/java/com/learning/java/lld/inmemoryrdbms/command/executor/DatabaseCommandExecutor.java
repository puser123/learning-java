package com.learning.java.lld.inmemoryrdbms.command.executor;

import com.learning.java.lld.inmemoryrdbms.command.DatabaseCommand;
import com.learning.java.lld.inmemoryrdbms.model.Result;
import com.learning.java.lld.inmemoryrdbms.service.DatabaseService;

public abstract class DatabaseCommandExecutor {

    // this class server as template for other commands, if we don't need template we can use directly without template pattern.

    protected DatabaseService databaseService;
    DatabaseCommandExecutor(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    abstract  boolean isValidCommand(DatabaseCommand databaseCommand) ;

    public Result executeAndValidate(DatabaseCommand databaseCommand) {
        if(!isValidCommand(databaseCommand)) {
            // throw exception
        }
        return executeCommand(databaseCommand);
    }

    // this method will be executed by
    public abstract Result executeCommand(DatabaseCommand databaseCommand);
}
