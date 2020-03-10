package com.lessons.filter;

import com.lessons.model.FilterDTO;
import com.lessons.model.FilterInfo;
import com.lessons.model.FiltersListDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * FilterService
 *
 * Used to convert the list of filter strings into a SQL where clause
 *
 * The passed-in list Of String can have any of these formats:
 *    Fieldname <separator> EQUALS          value
 *    Fieldname <separator> GREATER         value
 *    Fieldname <separator> GREATER_EQUAL   value
 *    Fieldname <separator> LESS            value
 *    Fieldname <separator> LESS_EQUAL      value
 *    Fieldname <separator> BETWEEN         value1 <separator> value2                        (applies to dates and numeric fields only)
 *    Fieldname <separator> IN              value1 <separator> value2 <separator> value3....
 *    Fieldname <separator> NOTIN           value1 <separator> value2 <separator> value3....
 *    Fieldname <separator> CONTAINS        value1
 *    Fieldname <separator> ICONTAINS       value1
 *    Fieldname <separator> ISNULL
 *    Fieldname <separator> ISNOTNULL
 *
 */



@Service("com.lessons.filter.FilterService")
public class FilterService {
    private static final Logger logger = LoggerFactory.getLogger(FilterService.class);

    public static final String FILTER_SEPARATOR = "~";

    @PostConstruct
    public void init() {
        logger.debug("init() started.");

    }

    /**
     * Returns false if the passed-in list of filters is invalid in anyway
     */
    public boolean areFiltersValid(List<String> aFilters) {
        logger.debug("areFiltersValid() started.");

        if(aFilters == null){
            return true;
        }


        // Are the filter operation strings valid -- e.g., are they either BETWEEN, IN, CONTIANS, ICONTAINS, ISNULL, or ISNOTNULL

        for(String filter : aFilters){
            String[] parts = StringUtils.split(filter,"~");
            String operator = parts[1];
            FilterOperation filterRules = FilterOperation.getOperation(operator);
            if(filterRules == null){
                return false;
            }

            int expectedTokenCount = filterRules.getTokenCount();
            int actualTokenCount = parts.length;
            String tokenCountRule = filterRules.getCompareOperation();
            if(tokenCountRule.equalsIgnoreCase("=") && expectedTokenCount != actualTokenCount){
                // number of tokens did not match
                return false;
            }
            if(tokenCountRule.equalsIgnoreCase(">=") && expectedTokenCount > actualTokenCount){
                // not enough tokens
                return false;
            }
        }
        // Are filters in the correct format -- e.g., BETWEEN requires 2 values

        // Do the field names correspond to database columns?

        return true;
    }


    /**
     * Return a SqlDetails that holds the SQL and the bind variables
     *
     * Approach:
     *  1) Loop through each fliter string
     *     a) Split-up the string by the separator character
     *     b) Get the operation string
     *     c) Pull the fields out
     *     d) Add the field values to the map of bind variables
     *     e) Append to the SQL string
     *  2) Create a FilterParams object and store the map of bind variables and SQL string in it
     *  3) Return the FilterParams object
     *
     * @param aFilters holds a list of Strings that hold filter operations
     * @return FilterParams object that holds the SQL partial where clause and a map of parameters
     */
    public FilterParams getFilterParamsForFilters(List<String> aFilters) {
        logger.debug("getFilterClause() started.");

        FilterParams filterParams = new FilterParams();
        String whereClause = "";
        Map<String, Object> bindvars = new HashMap<>();
        // Loop through the list of Strings
        int bindVarNumber = 0;
        for(String filter: aFilters){
            // increment the bind var number by because of the between case with dates.
            bindVarNumber = bindVarNumber + 2;

           List<String> parts = Arrays.asList(StringUtils.split(filter,"~"));
           String field = parts.get(0);
           String operation = parts.get(1);

           FilterOperation filterRules = FilterOperation.getOperation(operation);
           String sqlTemplate = filterRules.getSqlOperation();

            if(filterRules.getFilterOperation().equalsIgnoreCase("between")){
                whereClause = whereClause + " " + field + " " + String.format(sqlTemplate, ":bindVar" + bindVarNumber, ":bindVar" + ++bindVarNumber + " AND ");

                // this is the first date
                bindvars.put("bindVar" + bindVarNumber, parts.get(2));

               // this is the second date
               bindvars.put("bindVar" + bindVarNumber, parts.get(3));

            }
            if (filterRules.getFilterOperation().equalsIgnoreCase("isnull") || filterRules.getFilterOperation().equalsIgnoreCase("isnotnull")){
                whereClause = whereClause + " " + field + sqlTemplate + " AND ";
            } else {
                whereClause = whereClause + field + " " + String.format(sqlTemplate, ":bindVar" + bindVarNumber) + " AND ";
                if(filterRules.getSqlOperation().equalsIgnoreCase("in") || filterRules.getSqlOperation().equalsIgnoreCase("notin")){
                    bindvars.put("bindVar" + bindVarNumber, parts.subList(2,parts.size()));
                } else {
                    bindvars.put("bindVar" + bindVarNumber, parts.get(2));
                }
            }
        }

        filterParams.setBindVars(bindvars);
        filterParams.setSql(whereClause.substring(0, whereClause.length() - 4));

        return filterParams;
    }

    /**
     * Convert the passed-in list of Strings into an Order By clause
     *
     * @param aSortFields holds a list of strings in the formt of
     *                          "field>"  --> ORDER BY field ASC
     *                          "field<"  --> ORDER BY field DESC
     * @return
     */
    public String getSqlOrderByClauseForSortFields(List<String> aSortFields) {
        logger.debug("getSqlOrderByClauseForSortFields() started.");
        return null;
    }


    public FilterInfo getSQLInfoFilters(FilterDTO filters){
        String limitClause = "Limit " + filters.getPageSize() +" offset " + filters.getOffset();
        FilterInfo filterInfo = new FilterInfo();
        filterInfo.setLimitClause(limitClause);
        filterInfo.setWhereClause(generateWhereClause(filters.getFilters()));
        return filterInfo;
    }

    private String generateWhereClause(List<FiltersListDTO> filtersList){
        String whereClause = "";
        for (FiltersListDTO filter : filtersList){
            whereClause += filter.getFieldName() +" = " + filter.getFieldValues();
        }
        return whereClause;
    }
}