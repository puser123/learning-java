package com.learning.java.lld.inmemoryrdbms;

import com.learning.java.lld.inmemoryrdbms.command.DatabaseCommand;
import com.learning.java.lld.inmemoryrdbms.command.factory.DatabaseCommandFactory;
import com.learning.java.lld.inmemoryrdbms.model.Result;
import com.learning.java.lld.inmemoryrdbms.service.DatabaseService;
import com.learning.java.lld.inmemoryrdbms.service.SqlDatabaseService;
import com.learning.java.lld.parkinglot.command.Command;
import com.learning.java.lld.parkinglot.command.CommandExecutorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseClient {

    /*
    Create table with a fixed schema (name, id, age)
    Create multiple tables within a database
    Can support multiple database
    Insert, Update, Delete a row
    Index on a specific field, auto incremented id

    The data structure we have is map in java,
    We can create a map of
    // Generally RDBMS is in table format
    //   (name, id, age)
    //   (pankaj, 123, 25)
    //    (shivam, 124, 20)
    //   (ranjeet, 125, 26)
    // Create multiple table within a database.
    // Insert, Update, Delete Row
    // Have index also on specific field.
    // Simple create map of
    // Map<String, Map<String, Row>> tableDbMap
    // Row represents each row in the sql DB.
    // Each database can contain multiple table
    // Map<String, String> dbTableMap;
    // How indexed vs non indexed will work.
     */

    // createRow name id age tableNameValue dbNameValue
    // readRow name pankaj tableNameValue dbNameValue
    // createTable pkKeyName tableNameValue dbNameValue

    // What every information needs to be rapped to the command

    // Improvements
    // - Validation checks
    // Consistency validations
    // readRow support by secondary index.
    // deleteRow support, etc.
    // discuss how indexed and non indexed query will work.


    public static void main(String[] args) {
        DatabaseService databaseService = new SqlDatabaseService("sql-db");

        DatabaseCommandFactory commandFactory = new DatabaseCommandFactory(databaseService);

        DatabaseCommand createTable = new DatabaseCommand("createTable name user sql-db");
        commandFactory.getCommandByType(createTable.getCommandType())
                .executeAndValidate(createTable);

        DatabaseCommand insertRow = new DatabaseCommand("createRow pankaj 1 25 user sql-db");
        commandFactory.getCommandByType(insertRow.getCommandType())
                .executeAndValidate(insertRow);

        DatabaseCommand readRow = new DatabaseCommand("readRow name pankaj user sql-db");
        Result result = commandFactory.getCommandByType(readRow.getCommandType())
                .executeAndValidate(readRow);

        log.info(result.getRows().toString());

    }

}
