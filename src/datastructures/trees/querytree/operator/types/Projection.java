package datastructures.trees.querytree.operator.types;

import datastructures.trees.querytree.operator.Operator;

import java.util.ArrayList;
import java.util.List;

public class Projection extends Operator {

    private final List<String> columnNames;

    public Projection(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public Projection(Projection toCopy) {
        this.columnNames = new ArrayList<>();
        this.columnNames.addAll(toCopy.columnNames);
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    @Override
    public Type getType() {
        return Type.PROJECTION;
    }

    @Override
    public List<String> getReferencedColumnNames() {
        return columnNames;
    }

    @Override
    public Operator copy(Operator operator) {
        return new Projection((Projection) operator);
    }

    @Override
    public String toString() {

        StringBuilder print = new StringBuilder();

        print.append("π").append(" (");

        if(columnNames.size() == 1) {
            print.append(columnNames.get(0));
        } else {
            for(String columnName : columnNames) {
                print.append(columnName).append(", ");
            }
            // remove ", "
            print.deleteCharAt(print.length() - 1);
            print.deleteCharAt(print.length() - 1);
        }

        print.append(")");

        return print.toString();
    }
}