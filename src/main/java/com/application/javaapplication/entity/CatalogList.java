package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.application.javaapplication.annotationCustomer.Types;

import java.sql.Timestamp;

@lombok.Data
@CustomTable(name = "catalog_list", comment = "文件目录表", indexes = {
        @CustomIndex(name = "catalog_name", columnList = "catalog_name"),
        @CustomIndex(name = "creator", columnList = "creator")
})
public class CatalogList
{
    @CustomTableFields(name = "id", type = Types.iint, OrmId = true, strategy = "AUTO",primaryType = true)
    private int $id;

    @CustomTableFields(name = "catalog_name", type = Types.istring, length = 100, comment = "文件目录名", defaultValues = false)
    private String $catalog_name;

    @CustomTableFields(name = "level", type = Types.istring, length = 10, comment = "文件等级", defaultValues = true)
    private String $level = "0";

    @CustomTableFields(name = "pid", type = Types.iint, length = 10, comment = "父级PID", nullable = true, defaultValues = false)
    private int $pid;

    @CustomTableFields(name = "is_used", type = Types.iboolean, length = 2, comment = "是否在使用中", defaultValues = true)
    private boolean $is_used= true;

    @CustomTableFields(name = "belong_project", type = Types.istring, length = 2, nullable = true, comment = "来源项目，如果是复制项目需要这个字段", defaultValues = false)
    private String $belong_project;

    @CustomTableFields(name = "creator", type = Types.istring, length = 100, comment = "创作人", defaultValues = true )
    private String $creator = "佚名";

    @CustomTableFields(name = "created", type = Types.idatetime, comment = "创建时间")
    private Timestamp $created;

    @CustomTableFields(name = "modified", type = Types.idatetime, comment = "更新时间")
    private Timestamp $modified;
}
