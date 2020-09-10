package ztest.utilities;

import datastructures.relation.table.Table;
import datastructures.trees.querytree.QueryTree;
import org.junit.jupiter.api.Test;
import utilities.QueryCost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CostTest {

    @Test
    public void question1() {

        // produce first temp (Owner Table)
        int recordSize = 63;
        int numRecords = 3954;
        int blockingFactor = QueryCost.blockingFactor(recordSize); // 16
        int blocks = QueryCost.blocks(numRecords, blockingFactor); // 248
        int CUr = QueryCost.unsortedRange(blocks);
        assertEquals(248, CUr);

        // produce temp1 with btree on RentAmount
        numRecords = 3954;
        int keySize = 4; // rentAmount |r|
        int degree = QueryCost.degree(keySize); // 86
        int levels = QueryCost.levels(numRecords, degree); // 3
        int terminalLevelNodes = QueryCost.terminalLevelNodes(numRecords, degree); // 94
        int CBr = QueryCost.secondaryBTreeRange(levels, terminalLevelNodes, numRecords); // 2027
        assertEquals(2027, CBr);
    }

    @Test
    public void question2A() {

        // produce temp1 with btree on Site.OwnerID (Owner Table)

        // Owner table
        int recordSize = 63;
        int ownerNumRecords = 3954;
        int blockingFactor = QueryCost.blockingFactor(recordSize);
        int ownerBlocks = QueryCost.blocks(ownerNumRecords, blockingFactor);

        // Site table
        int siteNumRecords = 5811;
        int keySize = 4;
        int degree = QueryCost.degree(keySize);
        int siteOwnerIDLevels = QueryCost.levels(siteNumRecords, degree);
        int siteOwnerIDForeignKeySelectivity = QueryCost.foreignKeySelectivity(3954, 5811);

        int joinCost = QueryCost.bTreeJoin(ownerBlocks, ownerNumRecords, siteOwnerIDLevels, siteOwnerIDForeignKeySelectivity);
        //System.out.println(joinCost);
    }

    @Test
    public void question2B() {

        // product temp1 with btree on Owner.OwnerID

        // Site table
        int recordSize = 44;
        int siteNumRecords = 5811;
        int blockingFactor = QueryCost.blockingFactor(recordSize); // 23
        int siteBlocks = QueryCost.blocks(siteNumRecords, blockingFactor); // 253

        // Owner table
        int ownerNumRecords = 3954;
        int keySize = 4; // Owner.OwnerID |r|
        int degree = QueryCost.degree(keySize);
        int ownerOwnerIDLevels = QueryCost.levels(ownerNumRecords, degree);
        int ownerOwnerIDForeignKeySelectivity = QueryCost.foreignKeySelectivity(5811, 3954);

        int joinCost2 = QueryCost.bTreeJoin(siteBlocks, siteNumRecords, ownerOwnerIDLevels, ownerOwnerIDForeignKeySelectivity);
        //System.out.println(joinCost2);
    }

    @Test
    public void question3A() {

        // WTDC of temp1

        // site table with btree on status
        int siteNumRecs = 5811;
        int keySizeOfStatus = 10;
        int statusDegree = QueryCost.degree(keySizeOfStatus);
        int statusLevels = QueryCost.levels(siteNumRecs, statusDegree);
        int statusTerminalLevelNodes = QueryCost.terminalLevelNodes(siteNumRecs, statusDegree);

        int recordSize = 19;
        int distinctValues = 14;
        int selectivity = QueryCost.selectivity(siteNumRecs, distinctValues); // substitute selectivity for numRecs
        int blockingFactor = QueryCost.blockingFactor(recordSize);
        int blocks = QueryCost.blocks(selectivity, blockingFactor);

        // product temp1
        int CBn = QueryCost.secondaryBTreeNonUnique(statusLevels, statusDegree, selectivity);

        int CJ2 = QueryCost.bTreeJoin(blocks, selectivity, 3, 1);
        System.out.println(CJ2);

    }


    /*@Test
    public void question4() {

    }

    @Test
    public void question5() {

    }*/
}