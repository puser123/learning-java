package com.learning.java.lld.inmemoryrdbms.command.executor;

import com.learning.java.lld.inmemoryrdbms.command.DatabaseCommand;
import com.learning.java.lld.inmemoryrdbms.model.Result;
import com.learning.java.lld.inmemoryrdbms.model.Row;
import com.learning.java.lld.inmemoryrdbms.service.DatabaseService;

public class CreateRowCommand extends DatabaseCommandExecutor {

    public CreateRowCommand(DatabaseService databaseService) {
        super(databaseService);
    }

    @Override
    boolean isValidCommand(DatabaseCommand databaseCommand) {
        return true;
    }

    @Override
    public Result executeCommand(DatabaseCommand databaseCommand) {

        this.databaseService.createRow(databaseCommand.getTableName(), databaseCommand.getRow());
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
}
