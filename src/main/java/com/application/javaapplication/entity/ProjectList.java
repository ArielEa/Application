package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.application.javaapplication.annotationCustomer.Types;
import org.joda.time.DateTime;

import java.sql.Date;
import java.sql.Timestamp;

@lombok.Data
@CustomTable(name = "project_list", comment = "项目列表", indexes = {
        @CustomIndex(name = "name", columnList = "name"),
        @CustomIndex(name= "code", columnList = "code"),
        @CustomIndex(name= "attribute", columnList = "attribute")
})
public class ProjectList {

    @CustomTableFields(name = "id", OrmId = true, primaryType = true, type = Types.iint)
    private int id;

    @CustomTableFields(name = "name", type = Types.istring, length = 100, comment = "项目名称")
    private String name;

    @CustomTableFields(name = "code", type = Types.istring, length = 100, unique = true, comment = "项目编码（唯一）")
    private String code;

    @CustomTableFields(name = "attribute", type = Types.iint, length = 2, comment = "项目种类， 1 常规项目， 2 表格，3 单页", defaultValues = true)
    private int attribute = 1;

    @CustomTableFields(name = "nature", type = Types.istring, length = 50, comment = "项目属性， private 私密， public 公开， protected 保护（仅开放给制定人群）", defaultValues = true )
    private String nature = "public";

    @CustomTableFields(name = "description", type = Types.istring, length = 255, comment = "项目描述，不超过255汉字")
    private String description;

    @CustomTableFields(name = "source", type = Types.iint, length = 2, comment = "项目来源，1 正常创建， 2 复制项目，3 导入项目, 4 自动生成", defaultValues = true)
    private int source = 1;

    @CustomTableFields(name = "import_file_nature", type = Types.istring, comment = "导进来的文件类型（尚未完善）")
    private String import_file_nature;

    @CustomTableFields(name = "copy_source_name", type = Types.istring, length = 100, comment = "复制源头文件名")
    private String copy_source_name;

    @CustomTableFields(name = "copy_new_name", type = Types.istring, length = 100, comment = "复制后的重新命名")
    private String copy_new_name;

    @CustomTableFields(name = "created", type = Types.idatetime, comment = "创建时间")
    private DateTime created;

    @CustomTableFields(name = "modified", type = Types.idatetime, comment = "更新时间")
    private DateTime modified;

}
