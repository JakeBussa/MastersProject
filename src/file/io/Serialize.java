package file.io;

import datastructure.relation.table.Table;
import datastructure.relation.table.component.Column;
import datastructure.relation.table.component.FileStructure;
import datastructure.relation.table.component.TableData;
import datastructure.user.component.TablePrivileges;
import datastructure.user.User;
import datastructure.relation.table.component.DataType;
import datastructure.user.component.Privilege;

import java.util.ArrayList;

/**
 * Responsible for serializing and un-serializing all data so that it can be used in
 * a meaningful way. This essentially saves the states of all objects used so that they
 * be accessed later when the application is relaunched.
 * Right now, methods of this class is accessed statically for convenience. May change later.
 */
public class Serialize {

    /**
     * Formats raw user file data into something usable. Basically takes the contents of
     * the user file, parsers it, and returns a list of Users.
     * @param serializedUsers is data about the users in the system
     * @return list of users within the system
     */
    public static ArrayList<User> unSerializeUsers(String serializedUsers) {

        String[] lines = serializedUsers.split("\n");

        // remove any leading or trailing whitespace
        for(int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        ArrayList<User> users = new ArrayList<>();

        // "has a" relationship working downward
        // data associated with a single user
        User user = new User();

        // acts as a passable table privileges list too
        ArrayList<TablePrivileges> tablePrivilegesList = new ArrayList<>();

        // data associated with a single tablePrivilegesList or passableTablePrivilegesList
        TablePrivileges tablePrivileges = new TablePrivileges();

        // data associated with a single tablePrivileges
        ArrayList<String> updateColumns = new ArrayList<>();
        ArrayList<String> referenceColumns = new ArrayList<>();

        for(int i = 0; i < lines.length; i++) {

            String currentLine = lines[i];

            // lines that contain "DONE" determine when to add and instantiate variables
            switch(currentLine) {
                case "USER DONE":
                    users.add(user);
                    user = new User();
                    break;
                case "TABLE PRIVILEGES LIST DONE":
                    user.setTablePrivilegesList(tablePrivilegesList);
                    tablePrivilegesList = new ArrayList<>();
                    break;
                case "PASSABLE TABLE PRIVILEGES LIST DONE":
                    user.setPassableTablePrivilegesList(tablePrivilegesList);
                    tablePrivilegesList = new ArrayList<>();
                    break;
                case "TABLE PRIVILEGES DONE":
                    tablePrivilegesList.add(tablePrivileges);
                    tablePrivileges = new TablePrivileges();
                    break;
                case "UPDATE PRIVILEGE DONE":
                    tablePrivileges.setUpdateColumns(updateColumns);
                    updateColumns = new ArrayList<>();
                    break;
                case "REFERENCES PRIVILEGE DONE":
                    tablePrivileges.setReferenceColumns(referenceColumns);
                    referenceColumns = new ArrayList<>();
                    break;
                // lines that don't contain "DONE" have some form of data to add
                default: {
                    String[] tokens = currentLine.split(": ");
                    String type = tokens[0];
                    String data = tokens[1];

                    switch(type) {
                        case "User":
                            user.setUsername(data);
                            break;
                        case "TablePrivilegesList":
                        case "PassableTablePrivilegesList":
                            continue;
                        case "TablePrivileges":
                            tablePrivileges.setTableName(data);
                            break;
                        case "Privilege":
                            tablePrivileges.grantPrivilege(Privilege.convertToPrivilege(data));
                            break;
                        case "UpdateColumns":
                            String[] updateColumnsTokens = data.split("\\s+");
                            for(String updateColumn : updateColumnsTokens) {
                                updateColumns.add(updateColumn);
                            }
                            break;
                        case "ReferencesColumns":
                            String[] referenceColumnsTokens = data.split("\\s+");
                            for(String referenceColumn : referenceColumnsTokens) {
                                referenceColumns.add(referenceColumn);
                            }
                            break;
                        default: {
                            System.out.println("In Utilities.createUserData()");
                            System.out.println("Unknown Input @ Line "+ i + ": " + currentLine);
                            return new ArrayList<>();
                        }
                    }

                    break;
                }
            }
        }

        return users;
    }

    /**
     * Takes all the users within the system and converts all of their data into
     * a string which will eventually be written out to disk. Stores every users'
     * state so that it can be restored when the application is re-launched.
     * @param users the users within the system
     * @return string representation of all the users within the system to write out
     */
    public static String serializedUsers(ArrayList<User> users) {

        StringBuilder toSerialize = new StringBuilder();

        for(User user : users) {

            toSerialize.append("User: ").append(user.getUsername()).append("\n");

            ArrayList<TablePrivileges> tablePrivilegesList = user.getTablePrivilegesList();
            toSerialize.append("\t").append("TablePrivilegesList: ");

            if(! tablePrivilegesList.isEmpty()) {

                toSerialize.append("NOT EMPTY").append("\n");

                for(TablePrivileges tablePrivileges : tablePrivilegesList) {

                    toSerialize.append("\t\t").append("TablePrivileges: ").
                            append(tablePrivileges.getTableName()).append("\n");

                    ArrayList<Privilege> privileges = tablePrivileges.getPrivileges();

                    for(Privilege privilege : privileges) {

                        toSerialize.append("\t\t\t").append("Privilege: ").append(privilege).append("\n");

                        if(privilege == Privilege.UPDATE) {

                            ArrayList<String> updateColumns = tablePrivileges.getUpdateColumns();
                            toSerialize.append("\t\t\t\t").append("UpdateColumns: ");

                            for(String updateColumn : updateColumns) {
                                toSerialize.append(updateColumn).append(" ");
                            }

                            // remove " "
                            toSerialize.deleteCharAt(toSerialize.length() - 1);

                            toSerialize.append("\n");
                            toSerialize.append("\t\t\t").append("UPDATE PRIVILEGE DONE").append("\n");
                        }

                        if(privilege == Privilege.REFERENCES) {

                            ArrayList<String> referenceColumns = tablePrivileges.getReferenceColumns();
                            toSerialize.append("\t\t\t\t").append("ReferencesColumns: ");

                            for(String referenceColumn : referenceColumns) {
                                toSerialize.append(referenceColumn).append(" ");
                            }

                            // remove " "
                            toSerialize.deleteCharAt(toSerialize.length() - 1);

                            toSerialize.append("\n");
                            toSerialize.append("\t\t\t").append("REFERENCES PRIVILEGE DONE").append("\n");
                        }
                    }

                    toSerialize.append("\t\t").append("TABLE PRIVILEGES DONE").append("\n");
                }

            } else {
                toSerialize.append("EMPTY").append("\n");
            }

            toSerialize.append("\t").append("TABLE PRIVILEGES LIST DONE").append("\n");

            // huge repeat of a bunch for passable table privileges
            ArrayList<TablePrivileges> passableTablePrivilegesList = user.getPassableTablePrivilegesList();
            toSerialize.append("\t").append("PassableTablePrivilegesList: ");

            if(! passableTablePrivilegesList.isEmpty()) {

                toSerialize.append("NOT EMPTY").append("\n");

                for(TablePrivileges tablePrivileges : passableTablePrivilegesList) {

                    toSerialize.append("\t\t").append("TablePrivileges: ").
                            append(tablePrivileges.getTableName()).append("\n");

                    ArrayList<Privilege> privileges = tablePrivileges.getPrivileges();

                    for(Privilege privilege : privileges) {

                        toSerialize.append("\t\t\t").append("Privilege: ").append(privilege).append("\n");

                        if(privilege == Privilege.UPDATE) {

                            ArrayList<String> updateColumns = tablePrivileges.getUpdateColumns();
                            toSerialize.append("\t\t\t\t").append("UpdateColumns: ");

                            for(String updateColumn : updateColumns) {
                                toSerialize.append(updateColumn).append(" ");
                            }

                            // remove " "
                            toSerialize.deleteCharAt(toSerialize.length() - 1);

                            toSerialize.append("\n");
                            toSerialize.append("\t\t\t").append("UPDATE PRIVILEGE DONE").append("\n");
                        }

                        if(privilege == Privilege.REFERENCES) {

                            ArrayList<String> referenceColumns = tablePrivileges.getReferenceColumns();
                            toSerialize.append("\t\t\t\t").append("ReferencesColumns: ");

                            for(String referenceColumn : referenceColumns) {
                                toSerialize.append(referenceColumn).append(" ");
                            }

                            // remove " "
                            toSerialize.deleteCharAt(toSerialize.length() - 1);

                            toSerialize.append("\n");
                            toSerialize.append("\t\t\t").append("REFERENCES PRIVILEGE DONE").append("\n");
                        }
                    }

                    toSerialize.append("\t\t").append("TABLE PRIVILEGES DONE").append("\n");
                }

            } else {
                toSerialize.append("EMPTY").append("\n");
            }

            toSerialize.append("\t").append("PASSABLE TABLE PRIVILEGES LIST DONE").append("\n");
            toSerialize.append("USER DONE").append("\n");
        }

        return toSerialize.toString();
    }

    /**
     * Formats raw table file data into something usable. Basically takes the contents of
     * the tables file, parsers it, and returns a list of Tables.
     * @param serializedTables is data about the tables in the system
     * @return list of tables within the system
     */
    public static ArrayList<Table> unSerializeTables(String serializedTables) {

        String[] lines = serializedTables.split("\n");

        // remove any leading or trailing whitespace
        for(int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        // "has a" relationship going downward
        ArrayList<Table> tables = new ArrayList<>();
        Table table = new Table();

        ArrayList<Column> columnList = new ArrayList<>();
        Column column = new Column();

        ArrayList<String> foreignKeyList = new ArrayList<>();

        for(int i = 0; i < lines.length; i++) {

            String currentLine = lines[i];

            // lines that contain "DONE" determine when to add and instantiate variables
            switch(currentLine) {
                case "TABLE DONE":
                    tables.add(table);
                    table = new Table();
                    break;
                case "COLUMN LIST DONE":
                    table.setColumns(columnList);
                    columnList = new ArrayList<>();
                    break;
                case "COLUMN DONE":
                    columnList.add(column);
                    column = new Column();
                    break;
                case "FOREIGN KEY LIST DONE":
                    table.setForeignKeys(foreignKeyList);
                    foreignKeyList = new ArrayList<>();
                    break;
                // lines that don't contain "DONE" have some form of data to add
                default: {
                    String[] tokens = currentLine.split(": ");
                    String type = tokens[0];
                    String data = tokens[1];

                    switch(type) {
                        case "Table":
                            table.setTableName(data);
                            break;
                        case "ColumnList":
                            continue;
                        case "Column":
                            String[] columnTokens = data.split("\\s+");
                            String columnName = columnTokens[0];
                            DataType dataType = DataType.convertToDataType(columnTokens[1]);
                            int size = Integer.parseInt(columnTokens[2]);
                            FileStructure fileStructure = FileStructure.convertToFileStructure(columnTokens[3]);
                            column = new Column(columnName, dataType, size, fileStructure);
                            break;
                        case "PrimaryKey":
                            table.setPrimaryKey(data);
                            break;
                        case "ForeignKeyList":
                            String[] foreignKeyTokens = data.split("\\s+");
                            for(String foreignKey : foreignKeyTokens) {
                                foreignKeyList.add(foreignKey);
                            }
                            break;
                        case "IsClusteredWith":
                            table.setClusteredWith(data);
                            break;
                        case "TableDataFilename":
                            String serializedTableData = IO.readTableData(Filename.TABLE_DATA, data);
                            TableData tableData = unSerializeTableData(serializedTableData, table);
                            table.setTableData(tableData);
                            break;
                        default: {
                            System.out.println("In Utilities.createUserData()");
                            System.out.println("Unknown Input @ Line " + i + ": " + currentLine);
                            return new ArrayList<>();
                        }
                    }
                }
            }
        }

        return tables;
    }

    /**
     * Takes all the tables within the system and converts all of their data into
     * a string which will eventually be written out to disk. Stores every tables'
     * state so that it can be restored when the application is re-launched.
     * @param tables a list of tables within the system
     * @return string representation of all the tables within the system to write out
     */
    public static String serializeTables(ArrayList<Table> tables) {

        StringBuilder toSerialize = new StringBuilder();

        for(Table table : tables) {

            String tableName = table.getTableName();
            toSerialize.append("Table: ").append(tableName).append("\n");

            ArrayList<Column> columns = table.getColumns();
            toSerialize.append("\t").append("ColumnList: ");

            if(! columns.isEmpty()) {

                toSerialize.append("NOT EMPTY").append("\n");

                for(Column column : columns) {

                    String columnName = column.getName();
                    String dataType = column.getDataType().toString();
                    String size = Integer.toString(column.size());
                    String fileStructure = column.getFileStructure().toString();
                    toSerialize.append("\t\t").append("Column: ").append(columnName).append(" ").append(dataType).
                            append(" ").append(size).append(" ").append(fileStructure).append("\n");
                    toSerialize.append("\t\t").append("COLUMN DONE").append("\n");
                }

                toSerialize.append("\n");

            } else {
                toSerialize.append("EMPTY").append("\n");
            }

            // remove last "\n"
            toSerialize.deleteCharAt(toSerialize.length() - 1);
            toSerialize.append("\t").append("COLUMN LIST DONE").append("\n");

            toSerialize.append("\t").append("PrimaryKey: ").append(table.getPrimaryKey()).append("\n");

            ArrayList<String> foreignKeyList = table.getForeignKeys();
            toSerialize.append("\t").append("ForeignKeyList: ");

            if(! foreignKeyList.isEmpty()) {

                for(String foreignKey : foreignKeyList) {
                    toSerialize.append(foreignKey).append(" ");
                }

                // remove " "
                toSerialize.deleteCharAt(toSerialize.length() - 1);
                toSerialize.append("\n");

            } else {
                toSerialize.append("EMPTY").append("\n");
            }

            toSerialize.append("\t").append("FOREIGN KEY LIST DONE").append("\n");
            toSerialize.append("\t").append("IsClusteredWith: ").append(table.getClusteredWith()).append("\n");
            toSerialize.append("\t").append("TableDataFilename: ").append(table.getTableName()).append(".txt").append("\n");
            toSerialize.append("TABLE DONE").append("\n");
        }

        // remove "\n"
        toSerialize.deleteCharAt(toSerialize.length() - 1);

        return toSerialize.toString();
    }

    /**
     * Formats raw table data (ie. the rows and columns of a table) into something usable.
     * Basically takes the contents of a table data file, parsers it, and returns the Table Data.
     * @param serializedTableData is data from a single table
     * @param table contains information about what the table data means
     * @return the table data
     */
    public static TableData unSerializeTableData(String serializedTableData, Table table) {

        // string.split() will be very useful here
        String[] tableRows = serializedTableData.split("\n");

        int numRows = tableRows.length;
        int numCols = table.getNumCols();

        // used for determining where to split each column in a row
        ArrayList<Integer> columnSizes = new ArrayList<>();
        ArrayList<Integer> columnNameLengths = new ArrayList<>();

        // used for formatting purposes
        ArrayList<Integer> paddingAmountList = new ArrayList<>();

        // figuring out padding amounts and adding to the list of column sizes
        for(Column column : table.getColumns()) {

            int columnNameLength = column.getName().length();
            int maxNumSpaces = column.size();

            if(columnNameLength > maxNumSpaces) {
                paddingAmountList.add(columnNameLength);
            } else {
                paddingAmountList.add(column.size());
            }

            // add these guys for later
            columnSizes.add(column.size());
            columnNameLengths.add(columnNameLength);
        }

        // skip the column names and dashed lines
        String[][] splitTableData = new String[numRows - 2][numCols];

        for(int rows = 2; rows < numRows; rows++) {

            int beginIndex = 0;

            for(int cols = 0; cols < numCols; cols++) {

                int columnNameLength = table.getColumns().get(cols).getName().length();
                int columnSize = Math.max(columnNameLength, columnSizes.get(cols));
                int endIndex = columnSize + beginIndex;

                String formattedColumn = "";

                // last column can be too small for endIndex
                if(cols == numCols - 1) {
                    formattedColumn = tableRows[rows].substring(beginIndex);
                }else {
                    formattedColumn = tableRows[rows].substring(beginIndex, endIndex).trim();
                }

                splitTableData[rows - 2][cols] = formattedColumn;

                // + 1 to account for that extra space between columns,
                beginIndex = endIndex + 1;
            }
        }

        // convert 2D array to 2D array list for Table Data
        ArrayList<ArrayList<String>> tableData = new ArrayList<>();

        for(int rows = 0; rows < numRows - 2; rows++) {

            ArrayList<String> columns = new ArrayList<>();

            for(int cols = 0; cols < numCols; cols++) {
                columns.add(splitTableData[rows][cols].trim());
            }

            tableData.add(columns);
        }

        return new TableData(paddingAmountList, tableData);
    }

    /**
     * Takes the table data of a table and converts it into a string which will
     * eventually be written out to disk. This means a single tables' table data
     * can be restored when the application is re-launched.
     * @param table contains information about what the table data means
     * @return string representation of the table data to write out
     */
    public static String serializeTableData(Table table) {

        StringBuilder toSerialize = new StringBuilder();

        // to make things pretty and organized
        StringBuilder columnNames = new StringBuilder();
        StringBuilder dashes = new StringBuilder();

        // adding column names so we know what we're looking at
        for(Column column : table.getColumns()) {

            String columnName = column.getName();

            StringBuilder spaces = new StringBuilder();
            int columnNameLength = columnName.length();
            int maxNumSpaces = column.size();
            int numSpacesToPad = maxNumSpaces - columnNameLength;
            int numDashes = Math.max(maxNumSpaces, columnNameLength);

            boolean needsPadding = numSpacesToPad > 0;

            if(needsPadding) {
                for(int i = 0; i < numSpacesToPad; i++) {
                    spaces.append(" ");
                }
            }

            for(int i = 0; i < numDashes; i++) {
                dashes.append("-");
            }

            columnNames.append(columnName).append(spaces).append(" ");
            dashes.append(" ");
        }

        toSerialize.append(columnNames).append("\n");
        toSerialize.append(dashes).append("\n");

        // add the table data
        TableData tableData = table.getTableData();
        toSerialize.append(tableData.toString());

        return toSerialize.toString();
    }
}