package com.application.javaapplication.tools.dosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import com.application.javaapplication.tools.beans.StrBeans;

@Component("global.sql.action")
public class dosqlUtils
{
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 更新数据库内容
     * @param TableName String - 数据库名
     * @param changeList Map - 需要更改的条件
     * @param whereCondition Map - 需要处理的条件
     * @return String
     */
    public String UpdateData(String TableName, Map<String, String> changeList, Map<String, String> whereCondition)
            throws Exception
    {
        StringBuilder sql = new StringBuilder();
        int updateResult;

        String conditionStr = dosqlEnums.UPDATE.getSqlMode() + TableName + dosqlEnums.UPDATE.getSplitStr();

        String whereStr = this.handleWhereCondition(whereCondition, " and ");

        String changeStr = this.handleWhereCondition(changeList, ", ");

        sql.append(conditionStr)
                .append(changeStr)
                .append(dosqlEnums.EXTRA_WHERE.getSqlMode())
                .append(whereStr)
                .append(dosqlEnums.EXTRA_WHERE.getSplitStr());

        try {
            updateResult = jdbcTemplate.update(sql.toString());
        }catch (Exception se) {
            throw new Exception("Update Success : " + se.getMessage());
        }
        if (updateResult == 0) {
            throw new Exception("未更新数据");
        }
        return updateResult + " pieces of data have been successfully updated.";
    }

    /**
     * 新增数据
     * @param TableName String
     * @param Columns Map - ( 'columnsName', 'actualValue' )
     * @return String
     */
    public String InsertData(String TableName, Map<String, String> Columns)
    {
        return "insert";
    }

    public String RemoveData(String TableName, Map<String, String> list)
    {
        return "remove";
    }

    /**
     * 返回List
     * @param TableName - 表名
     * @param list - 查询数组
     * @param rowMapper - RowMapper<T>
     * @return List
     * @throws Exception -
     */
    public <T> List<T> Search(String TableName, Map<String, String> list, RowMapper<T> rowMapper)
            throws Exception
    {
        StringBuilder sql = new StringBuilder();
        String whereCondition = handleWhereCondition(list, " and  ");
        sql.append(dosqlEnums.SEARCH.getSqlMode())
                .append("* ")
                .append(dosqlEnums.SEARCH.getSplitStr())
                .append(TableName)
                .append(dosqlEnums.EXTRA_WHERE.getSqlMode())
                .append(whereCondition)
                .append(dosqlEnums.EXTRA_WHERE.getSplitStr());

        return jdbcTemplate.query(sql.toString(), rowMapper);
    }

    /**
     * List 里面包裹的Map类型数组
     * @param TableName
     * @param list
     * @return List
     * @throws Exception
     */
    public List<Map<String, Object>> SearchForList(String TableName, Map<String, String> list)
            throws Exception
    {
        StringBuilder sql = new StringBuilder();
        String whereCondition = handleWhereCondition(list, "");
        sql.append(dosqlEnums.SEARCH.getSqlMode())
                .append("* ")
                .append(dosqlEnums.SEARCH.getSplitStr())
                .append(TableName)
                .append(dosqlEnums.EXTRA_WHERE.getSqlMode())
                .append(whereCondition)
                .append(dosqlEnums.EXTRA_WHERE.getSplitStr());

        return jdbcTemplate.queryForList(sql.toString());
    }

    /**
     * 单词查询可用
     * @param TableName
     * @param list
     * @return
     * @throws Exception
     */
    public Map<String, Object> SearchForMap(String TableName, Map<String, String> list)
            throws Exception
    {
        StringBuilder sql = new StringBuilder();
        String whereCondition = handleWhereCondition(list, "");
        sql.append(dosqlEnums.SEARCH.getSqlMode())
                .append("* ")
                .append(dosqlEnums.SEARCH.getSplitStr())
                .append(TableName)
                .append(dosqlEnums.EXTRA_WHERE.getSqlMode())
                .append(whereCondition)
                .append(dosqlEnums.EXTRA_WHERE.getSplitStr());

        return jdbcTemplate.queryForMap(sql.toString());
    }

    /**
     * Map<String, String> condition
     * @param condition Map<String, String>
     * @return String
     * @throws Exception
     */
    public String handleWhereCondition( Map<String, String> condition, String BlockStr ) throws Exception
    {
        if (condition.isEmpty()) {
            throw new Exception("无效的数据");
        }
        String where = "";

        StrBeans strBeans = (StrBeans) applicationContext.getBean("str.split.service");

        where += strBeans.splitStr(condition, BlockStr);

        return where;
    }
}
