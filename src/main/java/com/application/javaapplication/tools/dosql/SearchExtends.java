package com.application.javaapplication.tools.dosql;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SearchExtends extends dosqlUtils implements SearchUtilInterface
{
    private SearchExtends QueryBuilder;

    @Override
    public <T> SearchUtilInterface getTableName(Class<T> element)
            throws Exception
    {
        return null;
    }

    @Override
    public SearchUtilInterface createBuilder(String TableName)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface select(Map<Integer, String> Columns)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface where(String Columns, String handleType, String Alias)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface setParameter(String Alias, String Value)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface setParameters(Map<String, String> condition)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface addWhere(String Columns, String handleType, String Alias)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface leftJoin(String tableName, String joinAlias, String ConditionType, Map<String, String> ContactCondition)
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface addSelect(Map<Integer, String> Columns)
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
    public SearchUtilInterface getQuery()
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface getSQL()
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface getResult()
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface getOneForMap()
            throws Exception
    {
        return this;
    }

    @Override
    public SearchUtilInterface getOneForObject()
            throws Exception
    {
        return this;
    }
}
