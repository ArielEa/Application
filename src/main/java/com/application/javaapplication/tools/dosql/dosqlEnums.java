package com.application.javaapplication.tools.dosql;

public enum dosqlEnums
{
    UPDATE("UPDATE ", " SET "),
    INSERT("INSERT INTO ", " VALUE "),
    REMOVE("DELETE ", " FROM "),
    SEARCH("SELECT ", " FROM "),
    EXTRA_WHERE(" where ", ";");

    private String sqlMode;

    private String splitStr;

    dosqlEnums(String sqlMode, String splitStr)
    {
        this.sqlMode = sqlMode;
        this.splitStr = splitStr;
    }

    public String getSqlMode()
    {
        return sqlMode;
    }

    public String getSplitStr()
    {
        return splitStr;
    }
}
