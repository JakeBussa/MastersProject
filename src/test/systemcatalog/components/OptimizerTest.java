package test.systemcatalog.components;

import datastructures.relation.table.Table;
import files.io.FileType;
import files.io.IO;
import files.io.Serializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import systemcatalog.components.Optimizer;
import systemcatalog.components.Parser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Test class for ensuring that the System Catalog's Optimizer is operating as it should be.
 * This will focus on looking at whether the query trees are created correctly, the input's
 * corresponding relational algebra is accurate, and the correct file structures are recommended.
 */
public class OptimizerTest {

    private static List<Table> tables;

    @BeforeAll
    public static void create() {
        tables = Serializer.unSerializeTables(IO.readCurrentData(FileType.CurrentData.CURRENT_TABLES));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT FirstName FROM Customers", // simple query
            "SELECT Customers.FirstName FROM Customers", // simple query with column name prefixed with table name
            "   SELECT CustomerID,   FirstName, LastName  FROM   Customers   ", // many columns in projection
            "SELECT * FROM Customers", // using *
            "SELECT FirstName FROM Customers WHERE CustomerID = 1", // using a where clause
            "SELECT FirstName, LastName FROM Customers WHERE FirstName = \" Genaro   Blah\" AND LastName = \"  Curnutt \" AND CustomerID = 5 AND CustomerID = 7 AND LastName = \"Blaj\"", // where clause with 2 predicates
            "SELECT FirstName, LastName FROM Customers INNER JOIN CustomerPurchaseDetails ON Customers.CustomerID = CustomerPurchaseDetails.CustomerID", // basic join
            "SELECT FirstName, LastName FROM Customers, CustomerPurchaseDetails WHERE Customers.CustomerID = CustomerPurchaseDetails.CustomerID", // basic join with where clause containing join condition
            "SELECT * FROM Customers INNER JOIN CustomerPurchaseDetails ON Customers.CustomerID = CustomerPurchaseDetails.CustomerID", // basic join with *
            "SELECT FirstName, LastName, StoreName FROM Employees INNER JOIN Stores ON EmployeeID = ManagerID", // join that's not prefixed with table name
            "SELECT FirstName, LastName, ProductName, Price, Quantity, PaymentMethod FROM Customers INNER JOIN CustomerPurchaseDetails ON Customers.CustomerID = CustomerPurchaseDetails.CustomerID INNER JOIN Products ON CustomerPurchaseDetails.ProductID = Products.ProductID", // basic join with 3 tables ===
            "SELECT FirstName, LastName, ProductName, Price, Quantity, PaymentMethod FROM Customers, CustomerPurchaseDetails, Products WHERE Customers.CustomerID = CustomerPurchaseDetails.CustomerID AND CustomerPurchaseDetails.ProductID = Products.ProductID", // basic join with 3 tables where join condition is in where clause ===
            "SELECT FirstName, LastName, ProductName, Price, Quantity, PaymentMethod FROM Customers INNER JOIN CustomerPurchaseDetails ON Customers.CustomerID = CustomerPurchaseDetails.CustomerID INNER JOIN Products ON CustomerPurchaseDetails.ProductID = Products.ProductID WHERE Customers.CustomerID = 1 AND Products.ProductID > 1 AND CustomerPurchaseDetails.PaymentMethod = \"blah\"", // basic join with 3 tables along with three predicates in where clause ===
            "SELECT FirstName, LastName, ProductName FROM Customers, Products", // basic cartesian product
            "SELECT MIN(CustomerID) FROM Customers", // simple aggregate function
            "SELECT COUNT(State) FROM Employees GROUP BY State", // aggregate function with group by clause
            "SELECT State, COUNT(State) FROM Employees GROUP BY State", // aggregate function with group by clause and a column to group by
            "SELECT State, COUNT(State) FROM Employees GROUP BY State HAVING COUNT(STATE) > 2", // aggregate function with group by clause and having clause
            "SELECT FirstName, LastName, MIN(Salary), COUNT(State) FROM Employees GROUP BY FirstName, LastName", // aggregate function with more advanced group by clause
            "SELECT State, COUNT(State) FROM Employees WHERE EmployeeID = 1 GROUP BY State HAVING COUNT(State) > 1", // complex aggregate function with where clause
            "SELECT MIN(EmployeeID) FROM Employees INNER JOIN EmployeePurchaseDetails ON Employees.EmployeeID = EmployeePurchaseDetails.EmployeeID", // simple aggregate function with join
            "SELECT EmployeeID, MIN(EmployeeID) FROM Employees INNER JOIN EmployeePurchaseDetails ON Employees.EmployeeID = EmployeePurchaseDetails.EmployeeID GROUP BY EmployeeID", // aggregate function with join and group by clause
            "SELECT EmployeeID, MIN(EmployeeID) FROM Employees INNER JOIN EmployeePurchaseDetails ON Employees.EmployeeID = EmployeePurchaseDetails.EmployeeID GROUP BY EmployeeID HAVING COUNT(CustomerID) = 1", // aggregate function with join, group by, and having clauses
            "SELECT State, COUNT(State) FROM Employees INNER JOIN EmployeePurchaseDetails ON Employees.EmployeeID = EmployeePurchaseDetails.EmployeeID WHERE EmployeeID = 1 GROUP BY State HAVING COUNT(State) > 1", // aggregate function with join, where, group by, and having clauses
            "SELECT State, COUNT(State) FROM Employees INNER JOIN EmployeePurchaseDetails ON Employees.EmployeeID = EmployeePurchaseDetails.EmployeeID INNER JOIN Products ON EmployeePurchaseDetails.ProductID = Products.ProductID WHERE EmployeeID = 1 GROUP BY State HAVING COUNT(State) > 1", // aggregate function with 3 joins, where, group by, and having clause ===
    })
    public void testCreationWithNoVerifier(String input) {

        Parser parser = new Parser();
        System.out.println(input + "\n");
        String[] tokens = Parser.formatAndTokenizeInput(input);

        List<Table> tables = Serializer.unSerializeTables(IO.readCurrentData(FileType.CurrentData.CURRENT_TABLES));
        Optimizer optimizer = new Optimizer();
        optimizer.getQueryTreeStates(tokens, tables);
        System.out.println("=========================================================================================");

        /*String[] tokenizedInput = Parser.formatAndTokenizeInput(input);
        Optimizer optimizer = new Optimizer();
        optimizer.toggleRearrangeLeafNodes();
        List<QueryTree> queryTrees;// = optimizer.getQueryTreeStates(tokenizedInput, tables);

        System.out.println("Query Tree Creation:");
        //System.out.println(queryTrees.get(0).getTreeStructure());
        System.out.println("Cascade Selections:");
        //System.out.println(queryTrees.get(1).getTreeStructure());
        System.out.println("Push Down Selections:");
        //System.out.println(queryTrees.get(2).getTreeStructure());
        System.out.println("Form Joins:");
        //System.out.println(queryTrees.get(3).getTreeStructure());
        System.out.println("Rearrange Leaf Nodes:");
        //System.out.println(queryTrees.get(4).getTreeStructure());
        System.out.println("Push Down Projections:");
        //System.out.println(queryTrees.get(5).getTreeStructure());
        System.out.println("Pipeline Subtrees:");
        StringBuilder sb = new StringBuilder();
        //for(int i = 6; i < queryTrees.size(); i++) { // 6 is the index of where pipelining begins
            //sb.append(queryTrees.get(i).getTreeStructure());
            sb.append("Pipeline Subtrees Again:");
        //}
        sb.delete(sb.length() - 24, sb.length()); // removing "Pipeline Subtrees Again"
        System.out.println(sb.toString());
        //System.out.println("relational algebra: " + optimizer.getNaiveRelationalAlgebra(queryTrees.get(0)));
*/
    }
}