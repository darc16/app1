package com.lessons.filter;

import java.util.Arrays;
import java.util.List;

public enum FilterOperation {

//     Fieldname <separator> EQUALS          value
//     Fieldname <separator> GREATER         value
//     Fieldname <separator> GREATER_EQUAL   value
//     Fieldname <separator> LESS            value
//     Fieldname <separator> LESS_EQUAL      value
//     Fieldname <separator> BETWEEN         value1 <separator> value2                        (applies to dates and numeric fields only)
//     Fieldname <separator> IN              value1 <separator> value2 <separator> value3....
//    Fieldname <separator> NOTIN           value1 <separator> value2 <separator> value3....
//    Fieldname <separator> CONTAINS        value1
//     Fieldname <separator> ICONTAINS       value1
//     Fieldname <separator> ISNULL
//     Fieldname <separator> ISNOTNULL

    EQUALS("EQUALS","=", 3, " = %s"),
    GREATER("GREATER", "",3, " > :%s::Integer"),
    GREATER_EQUAL("GREATER_EQUAL", "=",3, " >= :%s::Integer"),
    LESS("LESS", "=",3, " < :%s::Integer"),
    LESS_EQUAL("LESS_EQUAL", "=",3, " <= :%s::Integer"),
    BETWEEN("BETWEEN", "=",4, " BETWEEN %s AND %s"),
    IN("IN", ">=",3, " IN (%s)"),
    NOTIN("NOTIN", ">=",3, " NOT IN (%s)"),
    CONTAINS("CONTAINS", "=",3, " LIKE % %s %"),
    ICONTAINS("ICONTAINS", "=",3, " ILIKE % %s %"),
    ISNULL("ISNULL", "=",2, " IS NULL"),
    ISNOTNULL("ISNOTNULL", "=",2, " IS NOT NULL");

    private int tokenCount;
    private String compareOperation;
    private String filterOperation;
    private String sqlOperation;

    public String getSqlOperation() {
        return sqlOperation;
    }

    public void setSqlOperation(String sqlOperation) {
        this.sqlOperation = sqlOperation;
    }

    public int getTokenCount() {
        return tokenCount;
    }

    public String getCompareOperation() {
        return compareOperation;
    }

    public String getFilterOperation() {
        return filterOperation;
    }

    private FilterOperation(String filterOperation, String compareOperation, int tokenCount, String sqlOperation){
        this.compareOperation = compareOperation;
        this.filterOperation = filterOperation;
        this.tokenCount = tokenCount;
        this.sqlOperation = sqlOperation;
    }

    public static FilterOperation getOperation(String operationName){
       List<FilterOperation> allOps = Arrays.asList(FilterOperation.values());
       for(FilterOperation operation : allOps){
           if(operation.filterOperation.equalsIgnoreCase(operationName)){
               return operation;
           }
       }
       return null;
    }

}
