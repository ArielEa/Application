package com.application.javaapplication.tools.dosql;

import com.application.javaapplication.tools.Contains;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SearchExtends extends dosqlUtils implements SearchUtilInterface
{
    private SearchExtends QueryBuilder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StringBuilder SqlQueryBuilder;

    public String SpaceSplit = " ";

    public String TableName = "";

    public String FullTableName = "";

    public String TableAliasName = "";

    public String ColumnStr = " * ";

    public String AddColumnStr = "";

    public boolean whereUsed = false;

    public Map<String, Map<String, String>> WhereCondition = new HashMap<String, Map<String, String>>();

    public Map<String, String> ParameterCondition = new HashMap<String, String>();

    public String WhereResult = "";

    public String AndWhereCondition = "";

    public String LeftJoinStr = "";

    public String AddGroupBy = "";

    public String OrderBy = "";

    public String LimitResult = "";

    {
        SqlQueryBuilder = new StringBuilder();

        SqlQueryBuilder.append(dosqlEnums.SEARCH.getSqlMode());
    }

    @Override
    public <T> SearchUtilInterface getTableName(Class<T> element)
            throws Exception
    {
        String tableName = element.getAnnotation(TableName.class).value();

        if (tableName.equals("") ) {
            throw new Exception("Table Schema Error");
        }
        this.TableName = tableName;
        return this;
    }

    @Override
    public SearchUtilInterface createQueryBuilder(String TableAliasName)
            throws Exception
    {
        if(TableName == null || TableName.isEmpty()) {
            throw new Exception("Please Setting Table Config");
        }
        this.TableAliasName = TableAliasName;
        this.FullTableName = TableName + " " + TableAliasName + " ";
        return this;
    }

    @Override
    public SearchUtilInterface select(Map<Integer, String> Columns) throws Exception
    {
        StringBuilder columnStr = new StringBuilder();

        List<Integer> keys = new ArrayList<Integer>(Columns.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);

            String value = Columns.get(key);

            columnStr.append(value);

            if (i != keys.size() - 1) {
                columnStr.append(",");
            }
        }
        this.ColumnStr = columnStr.toString();
        return this;
    }

    @Override
    public SearchUtilInterface addSelect(Map<Integer, String> Columns) throws Exception
    {
        StringBuilder addColumnStr = new StringBuilder();

        List<Integer> addKeys = new ArrayList<Integer>(Columns.keySet());
        Collections.sort(addKeys);
        for (int i = 0; i <= addKeys.size(); i++) {
            int key = addKeys.get(i);
            String value = Columns.get(key);
            addColumnStr.append(",").append(value);
            if (i != addKeys.size() - 1) {
                addColumnStr.append(",");
            }
        }
        this.AddColumnStr = addColumnStr.toString();
        return this;
    }

    @Override
    public SearchUtilInterface where(String Columns, String handleType, String Alias)
            throws Exception
    {
        if (this.whereUsed == true) {
            throw new Exception("Repeat action");
        }
        this.whereUsed = true;
        if (Alias.isEmpty() || Columns.isEmpty() || handleType.isEmpty()) {
            throw new Exception("where parameter loused");
        }
        Map<String, String> aliasWhere = new HashMap<String, String>() {{
            put(Columns, handleType);
        }};
        this.WhereCondition.put(Alias, aliasWhere);
        return this;
    }

    @Override
    public SearchUtilInterface andWhere(String Columns, String handleType, String Alias)
            throws Exception
    {
        if (Alias.isEmpty() || Columns.isEmpty() || handleType.isEmpty()) {
            throw new Exception("where parameter loused");
        }
        Map<String, String> aliasWhere = new HashMap<String, String>() {{
            put(Columns, handleType);
        }};
        this.WhereCondition.put(Alias, aliasWhere);
        return this;
    }

    @Override
    public SearchUtilInterface setParameter(String Alias, String Value)
            throws Exception
    {
        Map<String, String> parameterValues = this.WhereCondition.get(Alias);

        if (parameterValues.isEmpty()) {
            throw new Exception("No parameterValues");
        }

        String Column = "", actionSymbolExplain = "";

        for(Map.Entry<String, String> para : parameterValues.entrySet()) {
            Column = para.getKey();

            String actionSymbol = parameterValues.get(Column);

            actionSymbolExplain = Contains.ExplainEnums(actionSymbol);
        }
        String SplitWhereCondition = Column+actionSymbolExplain+"'"+Value+"'";
        this.ParameterCondition.put(Alias, SplitWhereCondition);
        return this;
    }

    @Override
    public <k, v> SearchUtilInterface setParameters(Map<k, v> condition) throws Exception
    {
        if (!this.ParameterCondition.isEmpty()) {
            throw new Exception("Unable to operate;");
        }
        if (condition.isEmpty()) {
            throw new Exception("Parameters is empty");
        }
        Map<String, Map<String, String>> WherePara = this.WhereCondition;

        for (Map.Entry<k, v> con : condition.entrySet() ) {
            String Column = con.getKey().toString();
            String Value = con.getValue().toString();

            Map<String, String> CurrentWhere;

            CurrentWhere = WherePara.get(Column);

            String ChildColumn = "", actionSymbolExplain = "";

            for(Map.Entry<String, String> para : CurrentWhere.entrySet()) {
                ChildColumn = para.getKey();

                String actionSymbol = CurrentWhere.get(ChildColumn);

                actionSymbolExplain = Contains.ExplainEnums(actionSymbol);
            }
            String SplitWhereCondition = ChildColumn+actionSymbolExplain+"'"+Value+"'";

            this.ParameterCondition.put(Column, SplitWhereCondition);
        }
        return this;
    }

    @Override
    public SearchUtilInterface leftJoin(String tableName, String joinAlias, String ConditionType, Map<String, String> ContactCondition)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface addGroupBy(String Column)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface orderBy(String Column, String SortType)
            throws Exception
    {
        if (Column.equals("") || SortType.equals("")) {
            throw new Exception("Order By Exception without mode explain");
        }

        StringBuilder OrderByStr = new StringBuilder();
        OrderByStr.append(SpaceSplit)
                .append("Order by ")
                .append(Column)
                .append(SpaceSplit)
                .append(SortType);
        this.OrderBy = OrderByStr.toString();
        return this;
    }

    @Override
    public SearchUtilInterface setFirstResult(Integer firstResult)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface setMaxResult(Integer maxResult)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface getQuery() throws Exception
    {
        Map<String, String> para = this.ParameterCondition;
        StringBuilder columnStr = new StringBuilder();
        columnStr.append(" where ");
        if (!this.ParameterCondition.isEmpty()) {
            List<String> keys = new ArrayList<String>(para.keySet());
            Collections.sort(keys);
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                String value = para.get(key);
                columnStr.append(value);
                if (i != keys.size() - 1) {
                    columnStr.append(" and ");
                }
            }
        }
        SqlQueryBuilder.append(ColumnStr)
                .append(AddColumnStr)
                .append(dosqlEnums.SEARCH.getSplitStr())
                .append(FullTableName)
                .append(SpaceSplit)
                .append(columnStr.toString())
                .append(OrderBy);
        return this;
    }

    @Override
    public String getSQL() throws Exception
    {
        return SqlQueryBuilder.toString();
    }

    @Override
    public <T> List<T> getResult(RowMapper<T> rowMapper) throws Exception
    {
        return jdbcTemplate.query(SqlQueryBuilder.toString(), rowMapper);
    }

    @Override
    public <k, v> Map<k, v> getOneForMap() throws Exception
    {
        Map ResultMap = new HashMap();

        ResultMap = jdbcTemplate.queryForMap(SqlQueryBuilder.toString());

        return ResultMap;
    }

    @Override
    public <T> T getOneForObject(RowMapper<T> rowMapper) throws Exception
    {
        return jdbcTemplate.queryForObject(SqlQueryBuilder.toString(), rowMapper);
    }
}
