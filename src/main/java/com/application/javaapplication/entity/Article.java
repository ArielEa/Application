package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

@lombok.Data
@TableName("article")
public class Article
{
    @CustomTableFields(name = "id", type = "smallint", OrmId = true, strategy = "AUTO", primaryType = true)
    private int id;

    @CustomTableFields(name = "words", type = "text", nullable = true, comment = "文章主体")
    private String words;

    @CustomTableFields(name = "author", type = "string", length = 50, comment = "作者", defaultValues = true)
    private String author = "佚名";

    @CustomTableFields(name = "assistant", type = "string", length = 50, nullable = true, comment = "协助者")
    private String assistant;

    @CustomTableFields(name = "contributor", type = "string", length = 100, nullable = true, comment = "贡献者")
    private String contributor;

    @CustomTableFields(name = "thumb_up", type = "int", length = 11, comment = "点赞数", defaultValues = true)
    private int thumbUp = 0;

    @CustomTableFields(name = "bookmarks", type = "string", length = 100, comment = "书签「json」")
    private String bookmarks;

    @CustomTableFields(name = "intimate", type = "bool", length = 2, comment = "是够展示, true 展示, false 不展示", defaultValues = true)
    private boolean intimate = true;

    @CustomTableFields(name = "is_edit", type = "bool", length = 2, comment = "是否被编辑过, true or false", defaultValues = true)
    private boolean isEdit;

    @CustomTableFields(name = "edit_times", type = "int", length = 11, comment = "编辑的次数", defaultValues = true)
    private int editTimes = 0;

    @CustomTableFields(name = "edit_time", type = "datetime", comment = "编辑时间（第一次撰写时间）")
    private Timestamp editTime;

    @CustomTableFields(name = "update_time", type = "datetime", comment = "更新时间")
    private Timestamp updateTime;

    @CustomTableFields(name = "dead_line", type = "datetime", comment = "截稿日期")
    private Timestamp deadLine;
}
