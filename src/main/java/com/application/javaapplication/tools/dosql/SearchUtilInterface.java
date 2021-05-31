package com.application.javaapplication.tools.dosql;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

public interface SearchUtilInterface
{
    <T> SearchUtilInterface getTableName(Class<T> element)
        throws Exception;

     SearchUtilInterface createQueryBuilder(String TableAliasName)
        throws Exception;

     SearchUtilInterface select(Map<Integer, String> Columns)
         throws Exception;

     SearchUtilInterface where(String Columns, String handleType, String Alias)
         throws Exception;

     SearchUtilInterface andWhere(String Columns, String handleType, String Alias)
         throws Exception;

     SearchUtilInterface setParameter(String Alias, String Value)
         throws Exception;

     <k, v> SearchUtilInterface setParameters(Map<k, v> condition)
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

    String getSQL()
        throws Exception;

    <T> List<T> getResult(RowMapper<T> rowMapper)
        throws Exception;

    <k, v> Map<k, v> getOneForMap()
        throws Exception;

    <T> T getOneForObject(RowMapper<T> rowMapper)
        throws Exception;
}
