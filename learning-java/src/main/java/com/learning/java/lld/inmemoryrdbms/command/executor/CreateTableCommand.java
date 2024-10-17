package com.learning.java.lld.inmemoryrdbms.command.executor;

import com.learning.java.lld.inmemoryrdbms.command.DatabaseCommand;
import com.learning.java.lld.inmemoryrdbms.model.Result;
import com.learning.java.lld.inmemoryrdbms.service.DatabaseService;

public class CreateTableCommand extends DatabaseCommandExecutor {

    public CreateTableCommand(DatabaseService databaseService) {
        super(databaseService);
    }

    @Override
    boolean isValidCommand(DatabaseCommand databaseCommand) {
       if(this.databaseService.isTableExists(databaseCommand.getTableName())) {
           // throw exception
       }
       return true;
    }

    @Override
    public Result executeCommand(DatabaseCommand databaseCommand) {
        this.databaseService.createTable(databaseCommand.getTableName(),databaseCommand.getPkKey());
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
}
