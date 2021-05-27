package com.application.javaapplication.tools.dosql;

import java.sql.SQLException;
import java.util.Map;

public interface SearchUtilInterface
{
    <T> SearchUtilInterface getTableName(Class<T> element)
        throws Exception;

     SearchUtilInterface createBuilder(String TableName)
        throws Exception;

     SearchUtilInterface select(Map<Integer, String> Columns)
         throws Exception;

     SearchUtilInterface where(String Columns, String handleType, String Alias)
         throws Exception;

     SearchUtilInterface addWhere(String Columns, String handleType, String Alias)
         throws Exception;

     SearchUtilInterface setParameter(String Alias, String Value)
         throws Exception;

     SearchUtilInterface setParameters(Map<String, String > condition)
         throws Exception;

     SearchUtilInterface leftJoin(String tableName, String joinAlias, String ConditionType, Map<String, String> ContactCondition)
         throws Exception;

     SearchUtilInterface addSelect(Map<Integer, String> Columns)
         throws Exception;

    SearchUtilInterface setFirstResult(Integer firstResult)
        throws Exception;

    SearchUtilInterface setMaxResult(Integer maxResult)
        throws Exception;

    SearchUtilInterface orderBy(String Column, String SortType)
        throws Exception;

    SearchUtilInterface addGroupBy(String Column)
        throws Exception;

    SearchUtilInterface getQuery()
        throws Exception;

    SearchUtilInterface getSQL()
        throws Exception;

    SearchUtilInterface getResult()
        throws Exception;

    SearchUtilInterface getOneForMap()
        throws Exception;

    SearchUtilInterface getOneForObject()
        throws Exception;
}
