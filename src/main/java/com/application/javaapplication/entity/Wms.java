package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

@Data
@Accessors(chain = true)
@TableName("wms")
@CustomTable(name = "wms", indexes = {
        @CustomIndex(name = "wms_bn", columnList = "wms_bn", unique = true),
        @CustomIndex(name = "owner_code", columnList = "owner_code", unique = true)
})
public class Wms
{
    @CustomTableFields(name = "id", type = "smallint", OrmId = true, strategy = "AUTO", primaryType = true)
    public Integer id;

    @CustomTableFields(name = "wms_bn", type = "string", length = 100, comment = "wms（货主）编码")
    public String wmsBn;

    @CustomTableFields(name = "wms_name", type = "string", length = 100, comment = "wms（货主）名称")
    public String wmsName;

    @CustomTableFields(name = "bind_status", type = "smallint", length = 2, comment = "", nullable = true)
    public Integer bindStatus;

    @CustomTableFields(name = "wms_type", type = "smallint", length = 2, comment = "仓储适配器 1 奇门WMS(默认), 2 自有WMS, 3 跨境WMS, 4 三方WMS")
    public Integer wmsType;

    @CustomTableFields(name = "customer_id", type = "string", length = 100, comment = "customerId, 仓储联调编码")
    public String customerId;

    @CustomTableFields(name = "support_batch", type = "smallint", length = 2, comment = "是否开启货主批次概念, 1 批次，0 不开启")
    public Integer supportBatch;

    @CustomTableFields(name = "platform", type = "string", length = 100, comment = "平台, 货主来源平台", nullable = true)
    public String platform;

    @CustomTableFields(name = "platform_code", type = "string", length = 100, comment = "来源平台编码", nullable = true)
    public String platformCode;

    @CustomTableFields(name = "app_id", type = "string", length = 100, comment = "app id", nullable = true)
    public String appId;

    @CustomTableFields(name = "app_key", type = "string", length = 100 , comment = "app key", nullable = true)
    public String appKey;

    @CustomTableFields(name = "secret", type = "string", length = 100, comment = "密钥", nullable = true)
    public String secret;

    @CustomTableFields(name = "url", type = "string", length = 100, comment = "接口请求地址（ERP）", nullable = true)
    public String url;

    @CustomTableFields(name = "callback_url", type = "string", length = 100, comment = "回传接口（WMS）", nullable = true)
    public String callbackUrl;

    @CustomTableFields(name = "format", type = "smallint", length = 2, comment = "数据类型,1 xml,2 json,3 base64_encode,4 URLEncode")
    public Integer format;

    @CustomTableFields(name = "status", type = "smallint", length = 2, comment = "状态, 1 使用 , 2 弃用(不可恢复)")
    public Integer status;

    @CustomTableFields(name = "creator", type = "string", length = 100, comment = "创建人", nullable = true)
    public String creator;

    @CustomTableFields(name = "owner_code", type = "string", length = 100, comment = "货主编码")
    public String ownerCode;

    @CustomTableFields(name = "wms_angle", type = "smallint", length = 2, comment = "货主角度，1 wms货主角度， 2 wms下的仓库角度(warehouse-ownerCode)")
    public Integer wmsAngle;

    @CustomTableFields(name = "wms_attr", type = "smallint", length = 2, comment = "货主属性，1 普通货主，2 申报货主")
    public Integer wmsAttr;

    @CustomTableFields(name = "account_code", type = "string", length = 100, comment = "帐套编码")
    public String accountCode;

    @CustomTableFields(name = "created", type = "datetime", comment = "创建时间")
    public DateTime created;

    @CustomTableFields(name = "modified", type = "datetime", comment = "更新时间")
    public DateTime modified;

}
