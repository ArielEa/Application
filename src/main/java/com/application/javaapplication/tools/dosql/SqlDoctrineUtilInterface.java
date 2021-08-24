package com.application.javaapplication.tools.dosql;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

public interface SqlDoctrineUtilInterface
{
    <T> SqlDoctrineUtilInterface getTableName(Class<T> element)
        throws Exception;

     SqlDoctrineUtilInterface createQueryBuilder(String TableAliasName)
        throws Exception;

     SqlDoctrineUtilInterface select(Map<Integer, String> Columns)
         throws Exception;

     SqlDoctrineUtilInterface where(String Columns, String handleType, String Alias)
         throws Exception;

     SqlDoctrineUtilInterface andWhere(String Columns, String handleType, String Alias)
         throws Exception;

     SqlDoctrineUtilInterface setParameter(String Alias, String Value)
         throws Exception;

     <k, v> SqlDoctrineUtilInterface setParameters(Map<k, v> condition)
         throws Exception;

     SqlDoctrineUtilInterface leftJoin(String tableName, String joinAlias, String ConditionType, Map<String, String> ContactCondition)
         throws Exception;

     SqlDoctrineUtilInterface addSelect(Map<Integer, String> Columns)
         throws Exception;

    SqlDoctrineUtilInterface setFirstResult(Integer firstResult)
        throws Exception;

    SqlDoctrineUtilInterface setMaxResult(Integer maxResult)
        throws Exception;

    SqlDoctrineUtilInterface orderBy(String Column, String SortType)
        throws Exception;

    SqlDoctrineUtilInterface addGroupBy(String Column)
        throws Exception;

    SqlDoctrineUtilInterface getQuery()
        throws Exception;

    SqlDoctrineUtilInterface beginTransaction()
        throws Exception;

    SqlDoctrineUtilInterface commit()
        throws Exception;

    SqlDoctrineUtilInterface rollback()
        throws Exception;

    SqlDoctrineUtilInterface clear()
        throws Exception;

    SqlDoctrineUtilInterface flush()
        throws Exception;

    String getSQL()
        throws Exception;

    <T> List<T> getResult(RowMapper<T> rowMapper)
        throws Exception;

    <k, v> Map<k, v> getOneForMap()
        throws Exception;

    <T> T getOneForObject(RowMapper<T> rowMapper)
        throws Exception;

    Integer find(Integer value)
        throws Exception;

    <k, v> Map<k, v> findOneBy(Map<k, v> condition)
        throws Exception;
}
