package com.learning.java.lld.inmemoryrdbms.command.executor;

import com.learning.java.lld.inmemoryrdbms.command.DatabaseCommand;
import com.learning.java.lld.inmemoryrdbms.model.Result;
import com.learning.java.lld.inmemoryrdbms.model.Row;
import com.learning.java.lld.inmemoryrdbms.service.DatabaseService;

import java.util.List;
import java.util.Objects;

public class ReadRowCommand extends DatabaseCommandExecutor {


    public ReadRowCommand(DatabaseService databaseService) {
        super(databaseService);
    }

    @Override
    boolean isValidCommand(DatabaseCommand databaseCommand) {
        return true;
    }

    @Override
    public Result executeCommand(DatabaseCommand databaseCommand) {

        //Check if it is primary key is present
        if(Objects.nonNull(databaseCommand.getRow().getName())) {
            List< Row> rowList = this.databaseService.readByPk(databaseCommand.getTableName(), databaseCommand.getRow().getName());
            Result result = new Result();
            result.setSuccess(true);
            result.setRows(rowList);
            return result;
        }
        return null;
    }
}
