package com.application.javaapplication.tools.dosql;

import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.entity.Wms;
import com.application.javaapplication.tools.Contains;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class SqlDoctrineExtends extends dosqlUtils implements SqlDoctrineUtilInterface
{
    private SqlDoctrineExtends QueryBuilder;

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

    public String PersistSql = "";

    {
        SqlQueryBuilder = new StringBuilder();

        SqlQueryBuilder.append(dosqlEnums.SEARCH.getSqlMode());
    }

    @Autowired
    private ColumnListUtils columnListUtils;

    @Autowired
    private RowMapperUtils rowMapperUtils;

    private Class<?> element;

    @Override
    public <T> SqlDoctrineUtilInterface getTableName(Class<T> element)
            throws Exception
    {
        String tableName = element.getAnnotation(CustomTable.class).name();

        this.element = element;

        String prefix = element.getAnnotation(CustomTable.class).TablePrefix();

        if (tableName.equals("") ) {
            throw new Exception("Table Schema Error");
        }
        this.TableName = prefix + "_" + tableName;
        return this;
    }

    @Override
    public SqlDoctrineUtilInterface createQueryBuilder(String TableAliasName)
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
    public SqlDoctrineUtilInterface select(Map<Integer, String> Columns) throws Exception
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
    public SqlDoctrineUtilInterface addSelect(Map<Integer, String> Columns) throws Exception
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
    public SqlDoctrineUtilInterface where(String Columns, String handleType, String Alias)
            throws Exception
    {
        if (this.whereUsed) {
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
    public SqlDoctrineUtilInterface andWhere(String Columns, String handleType, String Alias)
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
    public SqlDoctrineUtilInterface setParameter(String Alias, String Value)
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
    public <k, v> SqlDoctrineUtilInterface setParameters(Map<k, v> condition) throws Exception
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
    public SqlDoctrineUtilInterface leftJoin(String tableName, String joinAlias, String ConditionType, Map<String, String> ContactCondition)
            throws Exception
    {
        return this;
    }

    @Override
    public SqlDoctrineUtilInterface addGroupBy(String Column)
            throws Exception
    {
        return this;
    }

    @Override
    public SqlDoctrineUtilInterface orderBy(String Column, String SortType)
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
    public SqlDoctrineUtilInterface setFirstResult(Integer firstResult)
            throws Exception
    {
        return this;
    }

    @Override
    public SqlDoctrineUtilInterface setMaxResult(Integer maxResult)
            throws Exception
    {
        return this;
    }

    @Override
    public SqlDoctrineUtilInterface getQuery() throws Exception
    {
        Map<String, String> para = this.ParameterCondition;
        StringBuilder columnStr = new StringBuilder();
        if (!para.isEmpty()) {
            columnStr.append(" where ");
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
    public SqlDoctrineUtilInterface persist(Object element) throws Exception
    {
        List<String> columns = columnListUtils.getChangeList(element);

        StringBuilder columnsStr = new StringBuilder();
        StringBuilder valueStr = new StringBuilder();
        for (int i = 0; i< columns.size(); i++) {
            String columnKey = columns.get(i);

            if (columnKey == "id") {
                continue;
            }
            String ObjectKey = "get" + StringUtils.capitalize(columnKey);

            Method existMethod = element.getClass().getMethod(ObjectKey);

            if (existMethod.invoke(element) instanceof DateTime) {
                //转换成对应的时间格式
                valueStr.append("\"" + ((DateTime) existMethod.invoke(element)).toString("YYYY-MM-dd HH:mm:ss") + "\", ");
            } else {
                valueStr.append("\"" + existMethod.invoke(element) + "\", ");
            }
            columnsStr.append(columnKey+", ");
        }

        String NewColumnsStr = "("+ columnsStr.substring(0, columnsStr.length() - 2) +")";
        String NewValueStr = "("+valueStr.substring(0, valueStr.length() - 2)+")";

        String tableName = element.getClass().getAnnotation(CustomTable.class).name();

        StringBuilder InsertSqlQueryBuilder = new StringBuilder();

        InsertSqlQueryBuilder.append(dosqlEnums.INSERT.getSqlMode())
                .append(tableName)
                .append(NewColumnsStr)
                .append(AddColumnStr)
                .append(dosqlEnums.INSERT.getSplitStr())
                .append(FullTableName)
                .append(SpaceSplit)
                .append(NewValueStr)
                .append(";");

        PersistSql = InsertSqlQueryBuilder.toString();

        return this;
    }

    @Override
    public String getSQL() throws Exception
    {
        return SqlQueryBuilder.toString();
    }

    @Override
    public SqlDoctrineUtilInterface beginTransaction() throws Exception
    {
        jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);
        return this;
    }

    @Override
    public SqlDoctrineUtilInterface commit() throws Exception {
        return null;
    }

    @Override
    public SqlDoctrineUtilInterface rollback() throws Exception {
        return null;
    }

    @Override
    public SqlDoctrineUtilInterface clear() throws Exception {
        return null;
    }

    @Override
    public SqlDoctrineUtilInterface flush() throws Exception {

        if (!PersistSql.equals("")) {
            this.jdbcTemplate.execute(PersistSql.toString());
        }
        return null;
    }

    @Override
    public <T> List<T> getResult() throws Exception
    {
        RowMapper<Object> newRowMapper = rowMapperUtils.getRowMapper(element);

//        try {
//            jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);
//            jdbcTemplate.update("update oms set", new Object[]{"name", "lac"});
//            jdbcTemplate.getDataSource().getConnection().commit();
//        }catch (SQLException se) {
//            jdbcTemplate.getDataSource().getConnection().rollback();
//        } finally {
//            try {
//                jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        return (List<T>) jdbcTemplate.query(SqlQueryBuilder.toString(), newRowMapper);
    }

    @Override
    public <k, v> Map<k, v> getOneForMap() throws Exception
    {
        Map ResultMap;

        ResultMap = jdbcTemplate.queryForMap(SqlQueryBuilder.toString());

        return ResultMap;
    }

    @Override
    public <T> T getOneForObject(RowMapper<T> rowMapper) throws Exception
    {
        return jdbcTemplate.queryForObject(SqlQueryBuilder.toString(), rowMapper);
    }

    @Override
    public Integer find(Integer value)
            throws Exception
    {
        // 通过id查找，返回对象
        return null;
    }


    @Override
    public <k, v> Map<k, v> findOneBy(Map<k, v> condition)
            throws Exception
    {
        // 返回对象
        return null;
    }

    @Override
    public <T> List<T> getOneForObject() {
        return null;
    }
}
