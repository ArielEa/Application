package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import javax.persistence.Id;

@Setter
@Getter
@Accessors(chain = true)
@TableName("orders")
@CustomTable(name = "orders", indexes = {
        @CustomIndex(name = "idx_tid", unique = true, columnList = "tid"),
        @CustomIndex(name = "idx_private_status", columnList = "private_status"),
        @CustomIndex(name = "idx_order_status", columnList = "order_status")
})
@lombok.Data
public class Orders
{
    @TableField(value = "id")
    @CustomTableFields(name = "id", OrmId = true, strategy = "AUTO", primaryType = true)
    public Integer id;

    @TableField(value = "tid")
    @CustomTableFields(name = "tid", type = "string", length = 255, comment = "订单号")
    public String tid;

    @TableField(value = "private_status")
    @CustomTableFields(name = "private_status", type = "int", length = 5, comment = "订单当前状态, 0 历史订单, 1 正常订单, 2 错误订单， 3 无效订单， 4 隐藏订单", defaultValues = "1")
    public Integer privateStatus;

    @TableField(value = "order_status")
    @CustomTableFields(name = "order_status", type = "int", length = 5, comment = "订单待审核/发货状态, 1 待分配, 2 已分配/待审核 , 3 审核失败, 5 已审核, 6 取消订单, 7 已发货， 8 订单完成（单方面，不包含发货状态）, 9 批量发货中, 10 自动发货中, 11 合并订单发货中",defaultValues = "1")
    public Integer orderStatus;
}
