package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("warehouse")
@Accessors(chain = true)
@CustomTable(name = "warehouse", indexes = {
        @CustomIndex(name = "warehouse_bn", columnList = "warehouse_bn", unique = true),
        @CustomIndex(name = "warehouse_name", columnList = "warehouse_name")
})
public class Warehouse
{
    @CustomTableFields(name = "id", type = "smallint", OrmId = true, strategy = "AUTO", primaryType = true)
    public Integer id;

    @CustomTableFields(name = "warehouse_name", type = "string", length = 100, comment = "仓库名")
    public String warehouseName;

    @CustomTableFields(name = "warehouse_bn", type = "string", length = 100, comment = "仓库编码")
    public String warehouseBn;

    @CustomTableFields(name = "warehouse_owner", type = "string", length = 100, comment = "仓库负责人")
    public String warehouseOwner;

    @CustomTableFields(name = "warehouse_type", type = "int", length = 5, comment = "仓库归属，1 自建仓， 2 三方仓")
    public Integer warehouseType;

    @CustomTableFields(name = "warehouse-weight", type = "int", length = 5, comment = "仓库权重")
    public Integer warehouseWeight;

    @CustomTableFields(name = "memo", type = "string", length = 255, comment = "备注", nullable = true)
    public String memo;

    @CustomTableFields(name = "communication_zip", type = "string", length = 20, comment = "zip邮编", nullable = true)
    public String communicationZip;

    @CustomTableFields(name = "communication_address", type = "string", length = 255, comment = "仓库联系地址")
    public String communicationAddress;

    @CustomTableFields(name = "communication_name", type = "string", length = 50, comment = "仓库联系人姓名，不同于仓库负责人，这个是指联系对象")
    public String communicationName;

    @CustomTableFields(name = "communication_mobile", type = "string", length = 11, comment = "仓库联系人手机号，注意，我这儿必须严格校验手机号格式")
    public String communicationMobile;

    @CustomTableFields(name = "communication_phone", type = "string", length = 20, nullable = true, comment = "仓库联系人固话（可选）， 作为二次验证使用")
    public String communicationPhone;

    @CustomTableFields(name = "communication_gender", type = "string", length = 10, nullable = true, comment = "性别，理论上是保密的")
    public String communicationGender;

    @CustomTableFields(name = "communication_province", type = "string", length = 10, comment = "仓库所在省份")
    public String communicationProvince;

    @CustomTableFields(name = "communication_city", type = "string", length = 20, comment = "仓库所在城市(市)")
    public String communicationCity;

    @CustomTableFields(name = "communication_district", type = "string", length = 20, comment = "仓库所在区（镇）")
    public String communicationDistrict;

    @CustomTableFields(name = "is_cross_border", type = "int", length = 2, comment = "仓库属性， 1 跨境，0 大贸")
    public Integer isCrossBorder;

    @CustomTableFields(name = "wms_id", type = "int", length = 11, comment = "仓储id， 货主id")
    public Integer wmsId;

    @CustomTableFields(name = "pid", type = "int", nullable = true, length = 11, comment = "父级仓库id， 前提是，必须残仓，这个才有用")
    public Integer pid;

    @CustomTableFields(name = "stock_type", type = "smallint", length = 11, comment = "仓库正残属性1 正仓， 2 报废仓， 没有残仓")
    public Integer stockType;

    @CustomTableFields(name = "warehouse_attr", type = "smallint", length = 2, comment = "仓库线上/下属性，1 线上，2 线下", defaultValues = true)
    public Integer warehouseAttr = 1;

    @CustomTableFields(name = "support_batch", type = "smallint", length = 2, comment = "仓库批次管理， 1 批次开启，0 没有批次", defaultValues = true)
    public Integer supportBatch = 0;

    @CustomTableFields(name = "owner_code", type = "string", nullable = true, length = 100, comment = "货主编码（可选）")
    public String ownerCode;

    @CustomTableFields(name = "is_bonded", type = "smallint", length = 2, comment = "是否绑定保税，1 不保税，2 保税",defaultValues = true)
    public Integer isBonded = 1;

    @CustomTableFields(name = "is_used", type = "smallint", length = 2, comment = "是否使用中，1 使用中/生效中， 2 无效/废弃", defaultValues = true)
    public Integer isUsed = 1;

    @CustomTableFields(name = "is_declare", type = "smallint", length = 2, comment = "是否申报， 1 申报， 2 不申报/普通跨境仓", defaultValues = true)
    public Integer isDeclare = 1;

    @CustomTableFields(name = "declare_company_name", type = "string", length = 100, comment = "申报物流公司名称", nullable = true)
    public String declareCompanyName;

    @CustomTableFields(name = "declare_logistics_code", type = "string", length = 100, comment = "申报物流公司编码", nullable = true)
    public String declareLogisticsCode;

    @CustomTableFields(name = "offline_store_code", type = "string", length = 100, comment = "线下仓库编码", nullable = true)
    public String offlineStoreCode;

    @CustomTableFields(name = "offline_store_name", type = "string", length = 100, comment = "线下仓库名称", nullable = true)
    public String offlineStoreName;

    @CustomTableFields(name = "account_code", type = "string", length = 100, comment = "帐套编码")
    public String accountCode;
}
