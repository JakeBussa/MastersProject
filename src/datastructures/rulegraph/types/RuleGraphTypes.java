package datastructures.rulegraph.types;

import datastructures.rulegraph.RuleGraph;

/**
 * The only purpose of this class is to return RuleGraphs of a particular type.
 * This class just exists to keep code cleaner. Also code is written with
 * readability as a priority, so it's easy to see what the heck is happening.
 */
public final class RuleGraphTypes {

    // can't instantiate me!
    private RuleGraphTypes() {}

    public static RuleGraph getQueryRuleGraph() {

        RuleGraph queryRuleGraph = new RuleGraph();

        queryRuleGraph.addRule("SELECT",       false, 0);
        queryRuleGraph.addRule("*",            false, 1);
        queryRuleGraph.addRule("ColumnName",   true,  2);
        queryRuleGraph.addRule("MIN",          false, 3);
        queryRuleGraph.addRule("MAX",          false, 4);
        queryRuleGraph.addRule("AVG",          false, 5);
        queryRuleGraph.addRule("COUNT",        false, 6);
        queryRuleGraph.addRule("SUM",          false, 7);
        queryRuleGraph.addRule("(",            false, 8);
        queryRuleGraph.addRule("ColumnName",   true,  9);
        queryRuleGraph.addRule(")",            false, 10);
        queryRuleGraph.addRule(",",            false, 11);
        queryRuleGraph.addRule("FROM",         false, 12);
        queryRuleGraph.addRule("TableName",    true,  13);
        queryRuleGraph.addRule(",",            false, 14);
        queryRuleGraph.addRule("TableName",    true,  15);
        queryRuleGraph.addRule("INNER",        false, 16);
        queryRuleGraph.addRule("JOIN",         false, 17);
        queryRuleGraph.addRule("TableName",    true,  18);
        queryRuleGraph.addRule("ON",           false, 19);
        queryRuleGraph.addRule("ColumnName",   true,  20);
        queryRuleGraph.addRule("=",            false, 21);
        queryRuleGraph.addRule("!=",           false, 22);
        queryRuleGraph.addRule(">",            false, 23);
        queryRuleGraph.addRule("<",            false, 24);
        queryRuleGraph.addRule(">=",           false, 25);
        queryRuleGraph.addRule("<=",           false, 26);
        queryRuleGraph.addRule("ColumnName",   true,  27);
        queryRuleGraph.addRule("WHERE",        false, 28);
        queryRuleGraph.addRule("ColumnName",   true,  29);
        queryRuleGraph.addRule("=",            false, 30);
        queryRuleGraph.addRule("!=",           false, 31);
        queryRuleGraph.addRule(">",            false, 32);
        queryRuleGraph.addRule("<",            false, 33);
        queryRuleGraph.addRule(">=",           false, 34);
        queryRuleGraph.addRule("<=",           false, 35);
        queryRuleGraph.addRule("NumericValue", true,  36);
        queryRuleGraph.addRule("\"",           false, 37);
        queryRuleGraph.addRule("StringValue",  true,  38);
        queryRuleGraph.addRule("\"",           false, 39);
        queryRuleGraph.addRule("AND",          false, 40);
        queryRuleGraph.addRule("GROUP",        false, 41);
        queryRuleGraph.addRule("BY",           false, 42);
        queryRuleGraph.addRule("ColumnName",   true,  43);
        queryRuleGraph.addRule(",",            false, 44);
        queryRuleGraph.addRule("HAVING",       false, 45);
        queryRuleGraph.addRule("MIN",          false, 46);
        queryRuleGraph.addRule("MAX",          false, 47);
        queryRuleGraph.addRule("AVG",          false, 48);
        queryRuleGraph.addRule("COUNT",        false, 49);
        queryRuleGraph.addRule("SUM",          false, 50);
        queryRuleGraph.addRule("(",            false, 51);
        queryRuleGraph.addRule("ColumnName",   true,  52);
        queryRuleGraph.addRule(")",            false, 53);
        queryRuleGraph.addRule("=",            false, 54);
        queryRuleGraph.addRule("!=",           false, 55);
        queryRuleGraph.addRule(">",            false, 56);
        queryRuleGraph.addRule("<",            false, 57);
        queryRuleGraph.addRule(">=",           false, 58);
        queryRuleGraph.addRule("<=",           false, 59);
        queryRuleGraph.addRule("NumericValue", true,  60);
        queryRuleGraph.addRule("\"",           false, 61);
        queryRuleGraph.addRule("StringValue",  true,  62);
        queryRuleGraph.addRule("\"",           false, 63);
        queryRuleGraph.addRule("AND",          false, 64);
        queryRuleGraph.addRule(";",            false, 65);

        queryRuleGraph.setChildren(0,  1, 2, 3, 4, 5, 6, 7);
        queryRuleGraph.setChildren(1,  12);
        queryRuleGraph.setChildren(2,  11, 12);
        queryRuleGraph.setChildren(3,  8);
        queryRuleGraph.setChildren(4,  8);
        queryRuleGraph.setChildren(5,  8);
        queryRuleGraph.setChildren(6,  8);
        queryRuleGraph.setChildren(7,  8);
        queryRuleGraph.setChildren(8,  9);
        queryRuleGraph.setChildren(9,  10);
        queryRuleGraph.setChildren(10, 11, 12);
        queryRuleGraph.setChildren(11, 2, 3, 4, 5, 6, 7);
        queryRuleGraph.setChildren(12, 13);
        queryRuleGraph.setChildren(13, 14, 16, 28, 41, 65);
        queryRuleGraph.setChildren(14, 15);
        queryRuleGraph.setChildren(15, 14, 28, 41, 65);
        queryRuleGraph.setChildren(16, 17);
        queryRuleGraph.setChildren(17, 18);
        queryRuleGraph.setChildren(18, 19);
        queryRuleGraph.setChildren(19, 20);
        queryRuleGraph.setChildren(20, 21, 22, 23, 24, 25, 26);
        queryRuleGraph.setChildren(21, 27);
        queryRuleGraph.setChildren(22, 27);
        queryRuleGraph.setChildren(23, 27);
        queryRuleGraph.setChildren(24, 27);
        queryRuleGraph.setChildren(25, 27);
        queryRuleGraph.setChildren(26, 27);
        queryRuleGraph.setChildren(27, 16, 28, 41, 65);
        queryRuleGraph.setChildren(28, 29);
        queryRuleGraph.setChildren(29, 30, 31, 32, 33, 34, 35);
        queryRuleGraph.setChildren(30, 36, 37);
        queryRuleGraph.setChildren(31, 36, 37);
        queryRuleGraph.setChildren(32, 36, 37);
        queryRuleGraph.setChildren(33, 36, 37);
        queryRuleGraph.setChildren(34, 36, 37);
        queryRuleGraph.setChildren(35, 36, 37);
        queryRuleGraph.setChildren(36, 40, 41, 65);
        queryRuleGraph.setChildren(37, 38);
        queryRuleGraph.setChildren(38, 39);
        queryRuleGraph.setChildren(39, 40, 41, 65);
        queryRuleGraph.setChildren(40, 29);
        queryRuleGraph.setChildren(41, 42);
        queryRuleGraph.setChildren(42, 43);
        queryRuleGraph.setChildren(43, 44, 45, 65);
        queryRuleGraph.setChildren(44, 43);
        queryRuleGraph.setChildren(45, 46, 47, 48, 49, 50);
        queryRuleGraph.setChildren(46, 51);
        queryRuleGraph.setChildren(47, 51);
        queryRuleGraph.setChildren(48, 51);
        queryRuleGraph.setChildren(49, 51);
        queryRuleGraph.setChildren(50, 51);
        queryRuleGraph.setChildren(51, 52);
        queryRuleGraph.setChildren(52, 53);
        queryRuleGraph.setChildren(53, 54, 55, 56, 57, 58, 59);
        queryRuleGraph.setChildren(54, 60, 61);
        queryRuleGraph.setChildren(55, 60, 61);
        queryRuleGraph.setChildren(56, 60, 61);
        queryRuleGraph.setChildren(57, 60, 61);
        queryRuleGraph.setChildren(58, 60, 61);
        queryRuleGraph.setChildren(59, 60, 61);
        queryRuleGraph.setChildren(60, 64, 65);
        queryRuleGraph.setChildren(61, 62);
        queryRuleGraph.setChildren(62, 63);
        queryRuleGraph.setChildren(63, 64, 65);
        queryRuleGraph.setChildren(64, 46, 47, 48, 49, 50);
        queryRuleGraph.setChildren(65);

        return queryRuleGraph;
    }

    public static RuleGraph getCreateTableRuleGraph() {

        RuleGraph createTableRuleGraph = new RuleGraph();

        createTableRuleGraph.addRule("CREATE",      false, 0);
        createTableRuleGraph.addRule("TABLE",       false, 1);
        createTableRuleGraph.addRule("TableName",   true,  2);
        createTableRuleGraph.addRule("(",           false, 3);
        createTableRuleGraph.addRule("ColumnName",  true,  4);
        createTableRuleGraph.addRule("NUMBER",      false, 5);
        createTableRuleGraph.addRule("CHAR",        false, 6);
        createTableRuleGraph.addRule("DATE",        false, 7);
        createTableRuleGraph.addRule("(",           false, 8);
        createTableRuleGraph.addRule("Size",        true,  9);
        createTableRuleGraph.addRule(",",           false, 10);
        createTableRuleGraph.addRule("DecimalSize", true,  11);
        createTableRuleGraph.addRule(")",           false, 12);
        createTableRuleGraph.addRule(",",           false, 13);
        createTableRuleGraph.addRule(")",           false, 14);
        createTableRuleGraph.addRule(";",           false, 15);

        createTableRuleGraph.setChildren(0,  1);
        createTableRuleGraph.setChildren(1,  2);
        createTableRuleGraph.setChildren(2,  3);
        createTableRuleGraph.setChildren(3,  4);
        createTableRuleGraph.setChildren(4,  5, 6, 7);
        createTableRuleGraph.setChildren(5,  8);
        createTableRuleGraph.setChildren(6,  8);
        createTableRuleGraph.setChildren(7,  13, 14);
        createTableRuleGraph.setChildren(8,  9);
        createTableRuleGraph.setChildren(9,  10, 12);
        createTableRuleGraph.setChildren(10, 11);
        createTableRuleGraph.setChildren(11, 12);
        createTableRuleGraph.setChildren(12, 13, 14);
        createTableRuleGraph.setChildren(13, 4);
        createTableRuleGraph.setChildren(14, 15);
        createTableRuleGraph.setChildren(15);

        return createTableRuleGraph;
    }

    public static RuleGraph getAlterTableRuleGraph() {

        RuleGraph alterTableRuleGraph = new RuleGraph();

        alterTableRuleGraph.addRule("ALTER",      false, 0);
        alterTableRuleGraph.addRule("TABLE",      false, 1);
        alterTableRuleGraph.addRule("TableName",  true,  2);
        alterTableRuleGraph.addRule("MODIFY",     false, 3);
        alterTableRuleGraph.addRule("ADD",        false, 4);
        alterTableRuleGraph.addRule("DROP",       false, 5);
        alterTableRuleGraph.addRule("ColumnName", true,  6);
        alterTableRuleGraph.addRule("NUMBER",     false, 7);
        alterTableRuleGraph.addRule("CHAR",       false, 8);
        alterTableRuleGraph.addRule("DATE",       false, 9);
        alterTableRuleGraph.addRule("(",          false, 10);
        alterTableRuleGraph.addRule("Size",       true,  11);
        alterTableRuleGraph.addRule(")",          false, 12);
        alterTableRuleGraph.addRule("FOREIGN",    false, 13);
        alterTableRuleGraph.addRule("PRIMARY",    false, 14);
        alterTableRuleGraph.addRule("KEY",        false, 15);
        alterTableRuleGraph.addRule("ColumnName", true,  16);
        alterTableRuleGraph.addRule(";",          false, 17);

        alterTableRuleGraph.setChildren(0,  1);
        alterTableRuleGraph.setChildren(1,  2);
        alterTableRuleGraph.setChildren(2,  3, 4, 5);
        alterTableRuleGraph.setChildren(3,  6);
        alterTableRuleGraph.setChildren(4,  6, 13, 14);
        alterTableRuleGraph.setChildren(5,  13, 14, 16);
        alterTableRuleGraph.setChildren(6,  7, 8, 9);
        alterTableRuleGraph.setChildren(7,  10);
        alterTableRuleGraph.setChildren(8,  10);
        alterTableRuleGraph.setChildren(9,  17);
        alterTableRuleGraph.setChildren(10, 11);
        alterTableRuleGraph.setChildren(11, 12);
        alterTableRuleGraph.setChildren(12, 17);
        alterTableRuleGraph.setChildren(13, 15);
        alterTableRuleGraph.setChildren(14, 15);
        alterTableRuleGraph.setChildren(15, 16);
        alterTableRuleGraph.setChildren(16, 17);

        return alterTableRuleGraph;
    }

    public static RuleGraph getDropTableRuleGraph() {

        RuleGraph dropTableRuleGraph = new RuleGraph();

        dropTableRuleGraph.addRule("DROP",      false, 0);
        dropTableRuleGraph.addRule("TABLE",     false, 1);
        dropTableRuleGraph.addRule("TableName", true,  2);
        dropTableRuleGraph.addRule(";",         false, 3);

        dropTableRuleGraph.setChildren(0, 1);
        dropTableRuleGraph.setChildren(1, 2);
        dropTableRuleGraph.setChildren(2, 3);
        dropTableRuleGraph.setChildren(3);

        return dropTableRuleGraph;
    }

    public static RuleGraph getInsertRuleGraph() {

        RuleGraph insertRuleGraph = new RuleGraph();

        insertRuleGraph.addRule("INSERT",       false, 0);
        insertRuleGraph.addRule("INTO",         false, 1);
        insertRuleGraph.addRule("TableName",    true,  2);
        insertRuleGraph.addRule("VALUES",       false, 3);
        insertRuleGraph.addRule("(",            false, 4);
        insertRuleGraph.addRule("NumericValue", true,  5);
        insertRuleGraph.addRule("\"",           false, 6);
        insertRuleGraph.addRule("StringValue",  true,  7);
        insertRuleGraph.addRule("\"",           false, 8);
        insertRuleGraph.addRule(",",            false, 9);
        insertRuleGraph.addRule(")",            false, 10);
        insertRuleGraph.addRule(";",            false, 11);

        insertRuleGraph.setChildren(0,  1);
        insertRuleGraph.setChildren(1,  2);
        insertRuleGraph.setChildren(2,  3);
        insertRuleGraph.setChildren(3,  4);
        insertRuleGraph.setChildren(4,  5, 6);
        insertRuleGraph.setChildren(5,  9, 10);
        insertRuleGraph.setChildren(6,  7);
        insertRuleGraph.setChildren(7,  8);
        insertRuleGraph.setChildren(8,  9, 10);
        insertRuleGraph.setChildren(9,  5, 6);
        insertRuleGraph.setChildren(10, 11);
        insertRuleGraph.setChildren(11);

        return insertRuleGraph;
    }

    public static RuleGraph getDeleteRuleGraph() {

        RuleGraph deleteRuleGraph = new RuleGraph();

        deleteRuleGraph.addRule("DELETE",       false, 0);
        deleteRuleGraph.addRule("FROM",         false, 1);
        deleteRuleGraph.addRule("TableName",    true,  2);
        deleteRuleGraph.addRule("WHERE",        false, 3);
        deleteRuleGraph.addRule("ColumnName",   true,  4);
        deleteRuleGraph.addRule("=",            false, 5);
        deleteRuleGraph.addRule("!=",           false, 6);
        deleteRuleGraph.addRule(">",            false, 7);
        deleteRuleGraph.addRule("<",            false, 8);
        deleteRuleGraph.addRule(">=",           false, 9);
        deleteRuleGraph.addRule("<=",           false, 10);
        deleteRuleGraph.addRule("NumericValue", true,  11);
        deleteRuleGraph.addRule("\"",           false, 12);
        deleteRuleGraph.addRule("StringValue",  true,  13);
        deleteRuleGraph.addRule("\"",           false, 14);
        deleteRuleGraph.addRule(";",            false, 15);

        deleteRuleGraph.setChildren(0,  1);
        deleteRuleGraph.setChildren(1,  2);
        deleteRuleGraph.setChildren(2,  3);
        deleteRuleGraph.setChildren(3,  4);
        deleteRuleGraph.setChildren(4,  5, 6, 7, 8, 9, 10);
        deleteRuleGraph.setChildren(5,  11, 12);
        deleteRuleGraph.setChildren(6,  11, 12);
        deleteRuleGraph.setChildren(7,  11, 12);
        deleteRuleGraph.setChildren(8,  11, 12);
        deleteRuleGraph.setChildren(9,  11, 12);
        deleteRuleGraph.setChildren(10, 11, 12);
        deleteRuleGraph.setChildren(11, 15);
        deleteRuleGraph.setChildren(12, 13);
        deleteRuleGraph.setChildren(13, 14);
        deleteRuleGraph.setChildren(14, 15);
        deleteRuleGraph.setChildren(15);

        return deleteRuleGraph;
    }

    public static RuleGraph getUpdateRuleGraph() {

        RuleGraph updateRuleGraph = new RuleGraph();

        updateRuleGraph.addRule("UPDATE",       false, 0);
        updateRuleGraph.addRule("TableName",    true,  1);
        updateRuleGraph.addRule("SET",          false, 2);
        updateRuleGraph.addRule("ColumnName",   true,  3);
        updateRuleGraph.addRule("=",            false, 4);
        updateRuleGraph.addRule("NumericValue", true,  5);
        updateRuleGraph.addRule("\"",           false, 6);
        updateRuleGraph.addRule("StringValue",  true,  7);
        updateRuleGraph.addRule("\"",           false, 8);
        updateRuleGraph.addRule("WHERE",        false, 9);
        updateRuleGraph.addRule("ColumnName",   true,  10);
        updateRuleGraph.addRule("=",            false, 11);
        updateRuleGraph.addRule("NumericValue", true,  12);
        updateRuleGraph.addRule("\"",           false, 13);
        updateRuleGraph.addRule("StringValue",  true,  14);
        updateRuleGraph.addRule("\"",           false, 15);
        updateRuleGraph.addRule(";",            false, 16);

        updateRuleGraph.setChildren(0,  1);
        updateRuleGraph.setChildren(1,  2);
        updateRuleGraph.setChildren(2,  3);
        updateRuleGraph.setChildren(3,  4);
        updateRuleGraph.setChildren(4,  5, 6);
        updateRuleGraph.setChildren(5,  9);
        updateRuleGraph.setChildren(6,  7);
        updateRuleGraph.setChildren(7,  8);
        updateRuleGraph.setChildren(8,  9);
        updateRuleGraph.setChildren(9,  10);
        updateRuleGraph.setChildren(10, 11);
        updateRuleGraph.setChildren(11, 12, 13);
        updateRuleGraph.setChildren(12, 16);
        updateRuleGraph.setChildren(13, 14);
        updateRuleGraph.setChildren(14, 15);
        updateRuleGraph.setChildren(15, 16);
        updateRuleGraph.setChildren(16);

        return updateRuleGraph;
    }

    public static RuleGraph getGrantRuleGraph() {

        RuleGraph grantRuleGraph = new RuleGraph();

        grantRuleGraph.addRule("GRANT",      false, 0);
        grantRuleGraph.addRule("ALTER",      false, 1);
        grantRuleGraph.addRule("DELETE",     false, 2);
        grantRuleGraph.addRule("INDEX",      false, 3);
        grantRuleGraph.addRule("INSERT",     false, 4);
        grantRuleGraph.addRule("SELECT",     false, 5);
        grantRuleGraph.addRule("UPDATE",     false, 6);
        grantRuleGraph.addRule("REFERENCES", false, 7);
        grantRuleGraph.addRule("ALL",        false, 8);
        grantRuleGraph.addRule("PRIVILEGES", false, 9);
        grantRuleGraph.addRule(",",          false, 10);
        grantRuleGraph.addRule("(",          false, 11);
        grantRuleGraph.addRule("ColumnName", true,  12);
        grantRuleGraph.addRule(",",          false, 13);
        grantRuleGraph.addRule(")",          false, 14);
        grantRuleGraph.addRule("(",          false, 15);
        grantRuleGraph.addRule("ColumnName", true,  16);
        grantRuleGraph.addRule(",",          false, 17);
        grantRuleGraph.addRule(")",          false, 18);
        grantRuleGraph.addRule("ON",         false, 19);
        grantRuleGraph.addRule("TableName",  true,  20);
        grantRuleGraph.addRule("TO",         false, 21);
        grantRuleGraph.addRule("UserName",   true,  22);
        grantRuleGraph.addRule(",",          false, 23);
        grantRuleGraph.addRule("WITH",       false, 24);
        grantRuleGraph.addRule("GRANT",      false, 25);
        grantRuleGraph.addRule("OPTION",     false, 26);
        grantRuleGraph.addRule(";",          false, 27);

        grantRuleGraph.setChildren(0,  1, 2, 3, 4, 5, 6, 7, 8);
        grantRuleGraph.setChildren(1,  10, 19);
        grantRuleGraph.setChildren(2,  10, 19);
        grantRuleGraph.setChildren(3,  10, 19);
        grantRuleGraph.setChildren(4,  10, 19);
        grantRuleGraph.setChildren(5,  10, 19);
        grantRuleGraph.setChildren(6,  11);
        grantRuleGraph.setChildren(7,  15);
        grantRuleGraph.setChildren(8,  9);
        grantRuleGraph.setChildren(9,  19);
        grantRuleGraph.setChildren(10, 1, 2, 3, 4, 5, 6, 7);
        grantRuleGraph.setChildren(11, 12);
        grantRuleGraph.setChildren(12, 13, 14);
        grantRuleGraph.setChildren(13, 12);
        grantRuleGraph.setChildren(14, 10, 19);
        grantRuleGraph.setChildren(15, 16);
        grantRuleGraph.setChildren(16, 17, 18);
        grantRuleGraph.setChildren(17, 16);
        grantRuleGraph.setChildren(18, 10, 19);
        grantRuleGraph.setChildren(19, 20);
        grantRuleGraph.setChildren(20, 21);
        grantRuleGraph.setChildren(21, 22);
        grantRuleGraph.setChildren(22, 23, 24, 27);
        grantRuleGraph.setChildren(23, 22);
        grantRuleGraph.setChildren(24, 25);
        grantRuleGraph.setChildren(25, 26);
        grantRuleGraph.setChildren(26, 27);
        grantRuleGraph.setChildren(27);

        return grantRuleGraph;
    }

    public static RuleGraph getRevokeRuleGraph() {

        RuleGraph revokeRuleGraph = new RuleGraph();

        revokeRuleGraph.addRule("REVOKE",     false, 0);
        revokeRuleGraph.addRule("ALTER",      false, 1);
        revokeRuleGraph.addRule("DELETE",     false, 2);
        revokeRuleGraph.addRule("INDEX",      false, 3);
        revokeRuleGraph.addRule("INSERT",     false, 4);
        revokeRuleGraph.addRule("SELECT",     false, 5);
        revokeRuleGraph.addRule("UPDATE",     false, 6);
        revokeRuleGraph.addRule("REFERENCES", false, 7);
        revokeRuleGraph.addRule("ALL",        false, 8);
        revokeRuleGraph.addRule("PRIVILEGES", false, 9);
        revokeRuleGraph.addRule(",",          false, 10);
        revokeRuleGraph.addRule("(",          false, 11);
        revokeRuleGraph.addRule("ColumnName", true,  12);
        revokeRuleGraph.addRule(",",          false, 13);
        revokeRuleGraph.addRule(")",          false, 14);
        revokeRuleGraph.addRule("(",          false, 15);
        revokeRuleGraph.addRule("ColumnName", true,  16);
        revokeRuleGraph.addRule(",",          false, 17);
        revokeRuleGraph.addRule(")",          false, 18);
        revokeRuleGraph.addRule("ON",         false, 19);
        revokeRuleGraph.addRule("TableName",  true,  20);
        revokeRuleGraph.addRule("FROM",       false, 21);
        revokeRuleGraph.addRule("UserName",   true,  22);
        revokeRuleGraph.addRule(",",          false, 23);
        revokeRuleGraph.addRule(";",          false, 24);

        revokeRuleGraph.setChildren(0,  1, 2, 3, 4, 5, 6, 7, 8);
        revokeRuleGraph.setChildren(1,  10, 19);
        revokeRuleGraph.setChildren(2,  10, 19);
        revokeRuleGraph.setChildren(3,  10, 19);
        revokeRuleGraph.setChildren(4,  10, 19);
        revokeRuleGraph.setChildren(5,  10, 19);
        revokeRuleGraph.setChildren(6,  11);
        revokeRuleGraph.setChildren(7,  15);
        revokeRuleGraph.setChildren(8,  9);
        revokeRuleGraph.setChildren(9,  19);
        revokeRuleGraph.setChildren(10, 1, 2, 3, 4, 5, 6, 7);
        revokeRuleGraph.setChildren(11, 12);
        revokeRuleGraph.setChildren(12, 13, 14);
        revokeRuleGraph.setChildren(13, 12);
        revokeRuleGraph.setChildren(14, 10, 19);
        revokeRuleGraph.setChildren(15, 16);
        revokeRuleGraph.setChildren(16, 17, 18);
        revokeRuleGraph.setChildren(17, 16);
        revokeRuleGraph.setChildren(18, 10, 19);
        revokeRuleGraph.setChildren(19, 20);
        revokeRuleGraph.setChildren(20, 21);
        revokeRuleGraph.setChildren(21, 22);
        revokeRuleGraph.setChildren(22, 23, 24);
        revokeRuleGraph.setChildren(23, 22);
        revokeRuleGraph.setChildren(24);

        return revokeRuleGraph;
    }

    public static RuleGraph getBuildFileStructureRuleGraph() {

        RuleGraph secondaryBTreeRuleGraph = new RuleGraph();

        secondaryBTreeRuleGraph.addRule("BUILD",      false, 0);
        secondaryBTreeRuleGraph.addRule("HASH",       false, 1);
        secondaryBTreeRuleGraph.addRule("SECONDARY",  false, 2);
        secondaryBTreeRuleGraph.addRule("CLUSTERED",  false, 3);
        secondaryBTreeRuleGraph.addRule("TABLE",      false, 4);
        secondaryBTreeRuleGraph.addRule("BTREE",      false, 5);
        secondaryBTreeRuleGraph.addRule("ON",         false, 6);
        secondaryBTreeRuleGraph.addRule("ColumnName", true,  7);
        secondaryBTreeRuleGraph.addRule("IN",         false, 8);
        secondaryBTreeRuleGraph.addRule("TableName",  true,  9);
        secondaryBTreeRuleGraph.addRule("FILE",       false, 10);
        secondaryBTreeRuleGraph.addRule("ON",         false, 11);
        secondaryBTreeRuleGraph.addRule("TableName",  true,  12);
        secondaryBTreeRuleGraph.addRule("AND",        false, 13);
        secondaryBTreeRuleGraph.addRule("TableName",  true,  14);
        secondaryBTreeRuleGraph.addRule(";",          false, 15);

        secondaryBTreeRuleGraph.setChildren(0, 1, 2, 3);
        secondaryBTreeRuleGraph.setChildren(1, 4);
        secondaryBTreeRuleGraph.setChildren(2, 5);
        secondaryBTreeRuleGraph.setChildren(3, 5, 10);
        secondaryBTreeRuleGraph.setChildren(4, 6);
        secondaryBTreeRuleGraph.setChildren(5, 6);
        secondaryBTreeRuleGraph.setChildren(6, 7);
        secondaryBTreeRuleGraph.setChildren(7, 8);
        secondaryBTreeRuleGraph.setChildren(8, 9);
        secondaryBTreeRuleGraph.setChildren(9, 15);
        secondaryBTreeRuleGraph.setChildren(10, 11);
        secondaryBTreeRuleGraph.setChildren(11, 12);
        secondaryBTreeRuleGraph.setChildren(12, 13);
        secondaryBTreeRuleGraph.setChildren(13, 14);
        secondaryBTreeRuleGraph.setChildren(14, 15);
        secondaryBTreeRuleGraph.setChildren(15);

        return secondaryBTreeRuleGraph;
    }

    public static RuleGraph getRemoveFileStructureRuleGraph() {

        RuleGraph removeFileStructureRuleGraph = new RuleGraph();

        removeFileStructureRuleGraph.addRule("REMOVE",     false, 0);
        removeFileStructureRuleGraph.addRule("FILE",       false, 1);
        removeFileStructureRuleGraph.addRule("STRUCTURE",  false, 2);
        removeFileStructureRuleGraph.addRule("ON",         false, 3);
        removeFileStructureRuleGraph.addRule("ColumnName", true,  4);
        removeFileStructureRuleGraph.addRule("IN",         false, 5);
        removeFileStructureRuleGraph.addRule("TableName",  true,  6);
        removeFileStructureRuleGraph.addRule("CLUSTERED",  false, 7);
        removeFileStructureRuleGraph.addRule("FILE",       false, 8);
        removeFileStructureRuleGraph.addRule("ON",         false, 9);
        removeFileStructureRuleGraph.addRule("TableName",  true,  10);
        removeFileStructureRuleGraph.addRule("AND",        false, 11);
        removeFileStructureRuleGraph.addRule(";",          false, 12);

        removeFileStructureRuleGraph.setChildren(0, 1, 7);
        removeFileStructureRuleGraph.setChildren(1, 2);
        removeFileStructureRuleGraph.setChildren(2, 3);
        removeFileStructureRuleGraph.setChildren(3, 4);
        removeFileStructureRuleGraph.setChildren(4, 5);
        removeFileStructureRuleGraph.setChildren(5, 6);
        removeFileStructureRuleGraph.setChildren(6, 12);
        removeFileStructureRuleGraph.setChildren(7, 8);
        removeFileStructureRuleGraph.setChildren(8, 9);
        removeFileStructureRuleGraph.setChildren(9, 10);
        removeFileStructureRuleGraph.setChildren(10, 11);
        removeFileStructureRuleGraph.setChildren(11, 6);
        removeFileStructureRuleGraph.setChildren(12);

        return removeFileStructureRuleGraph;
    }
}