package com.application.javaapplication.entity;

import com.application.javaapplication.annotationCustomer.CustomIndex;
import com.application.javaapplication.annotationCustomer.CustomTable;
import com.application.javaapplication.annotationCustomer.CustomTableFields;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

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
    @CustomTableFields(name = "id", type = "smallint", OrmId = true, strategy = "AUTO", primaryType = true)
    public Integer id;

    @TableField(value = "tid")
    @CustomTableFields(name = "tid", type = "string", length = 255, comment = "订单号")
    public String tid;

    @TableField(value = "private_status")
    @CustomTableFields(name = "private_status", type = "smallint", length = 5, comment = "订单当前状态, 0 历史订单, 1 正常订单, 2 错误订单， 3 无效订单， 4 隐藏订单", defaultValues = true)
    public Integer privateStatus = 1;

    @TableField(value = "order_status")
    @CustomTableFields(name = "order_status", type = "smallint", length = 5, comment = "订单待审核/发货状态, 1 待分配, 2 已分配/待审核 , 3 审核失败, 5 已审核, 6 取消订单, 7 已发货， 8 订单完成（单方面，不包含发货状态）, 9 批量发货中, 10 自动发货中, 11 合并订单发货中",defaultValues = true)
    public Integer orderStatus = 1;

    @CustomTableFields(name = "ship_status", type = "smallint", length = 5, comment = "发货状态, 1 发货,  0 未发货", defaultValues = true)
    public Integer shipStatus = 0;

    @CustomTableFields(name = "has_gift", type = "smallint", length = 5, comment = "是否含有礼品, 1 含有， 0 不含", defaultValues = true)
    public Integer hasGift = 0;

    @CustomTableFields(name = "order_owner", type = "int", length = 11, comment = "审核人的id(account_user)", nullable = true)
    public Integer orderOwner;

    @CustomTableFields(name = "refund_status", length = 5, type = "smallint", comment = "退款状态， 1 退款， 0 不退款(正常状态)", defaultValues = true)
    public Integer refundStatus = 0;

    @CustomTableFields(name = "is_mark", type = "smallint", length = 5, comment = "是否标记， 1 标记， 0 不标记", defaultValues = true)
    public Integer isMark = 0;

    @CustomTableFields(name = "is_import", type = "smallint", length = 5, comment = "是否重要订单， 1 重要，0 普通", defaultValues = true)
    public Integer isImport = 0;

    @CustomTableFields(name = "is_split", type = "smallint", length = 5, comment = "是否拆分， 1 拆分，0 不拆分", defaultValues = true)
    public Integer isSplit = 0;

    @CustomTableFields(name = "is_pre_sell", type = "smallint", length = 5, comment = "是否预售订单, 1 预售单，0 普通单", defaultValues = true)
    public Integer isPreSell = 0;

    @CustomTableFields(name = "is_blessing", type = "smallint", length = 5, comment = "是否福袋， 1 是， 0 否", defaultValues = true)
    public Integer isBlessing = 0;

    @CustomTableFields(name = "is_part", type = "smallint", length = 5, comment = "是否部分退款， 1 是，0 否", defaultValues = true)
    public Integer isPart = 0;

    @CustomTableFields(name = "tx_type", type = "string", length = 10, comment = "交易类型, fixed 一口价， cross_border 跨境", defaultValues = true)
    public String txType = "fixed";

    @CustomTableFields(name = "trade_from", type = "string", length = 10, comment = "平台内部来源")
    public String tradeFrom;

    @CustomTableFields(name = "shipping_type", type = "string", length = 20, comment = "发货类型 express：快递， free：平邮，  virtual：虚拟发货， 到店自提。。")
    public String shippingType;

    @CustomTableFields(name = "from_platform", type = "string", length = 20, comment = "平台")
    public String fromPlatform;

    @CustomTableFields(name = "belong_tid", type = "string", length = 50, comment = "关联订单号", nullable = true)
    public String belongTid;

    @CustomTableFields(name = "pay_time", type = "datetime", comment = "付款时间")
    public DateTime payTime;

    @CustomTableFields(name = "has_memo", type = "smallint", comment = "是否订单备注, 1 有备注， 0 无备注", defaultValues = true)
    public Integer hasMemo = 0;

    @CustomTableFields(name = "is_finished", type = "smallint", comment = "订单是否完成, 1 完成， 0 未操作", defaultValues = true)
    public Integer isFinished = 0;

    @CustomTableFields(name = "payment", type = "double", length = 20, comment = "订单价格", defaultValues = true)
    public double payment = 0.00;

    @CustomTableFields(name = "post_fee", type = "double", length = 20, comment = "邮费", defaultValues = true)
    public double postFee = 0.00;

    @CustomTableFields(name = "seller_nick", type = "string", length = 50, comment = "店铺")
    public String sellerNick;

    @CustomTableFields(name = "has_stock", type = "smallint", length = 5, comment = "是否有库存，1 有，0没有", defaultValues = true)
    public Integer hasStock = 1;

    @CustomTableFields(name = "adjust_fee", type = "double", length = 20, comment = "调价价格", defaultValues = true)
    public double adjustFee = 0.00;

    @CustomTableFields(name = "pay_no", type = "string", length = 20, comment = "支付流水号", nullable = true)
    public String payNo;

    @CustomTableFields(name = "buyer_email", type = "string", length = 50, comment = "买家邮箱", nullable = true)
    public String buyerEmail;

    @CustomTableFields(name = "buyer_nick", type = "string", length = 50, comment = "买家昵称", nullable = true)
    public String buyerNick;

    @CustomTableFields(name = "consign_time", type = "datetime", comment = "同步时间", nullable = true)
    public DateTime consignTime;

    @CustomTableFields(name = "buyer_message", type = "string", length = 255, comment = "买家信息，是否有备注", nullable = true)
    public String buyerMessage;

    @CustomTableFields(name = "created", type = "datetime", comment = "创建时间")
    public DateTime created;

    @CustomTableFields(name = "discount_fee", type = "double", length = 20, comment = "折扣价", defaultValues = true)
    public double discountFee = 0.00;

    @CustomTableFields(name = "end_time", type = "datetime", comment = "结束时间", nullable = true)
    public DateTime endTime;

    @CustomTableFields(name = "est_con_time", type = "datetime", comment = "预计发货时间", nullable = true)
    public DateTime estConTime;

    @CustomTableFields(name = "modified", type = "datetime", comment = "更新时间")
    public DateTime modified;

    @CustomTableFields(name = "promotion_details", type = "string", length = 255, comment = "优惠券， 存成串", nullable = true)
    public String promotionDetails;

    @CustomTableFields(name = "received_payment", type = "double",length = 20, comment = "到账实收金额")
    public double receivedPayment;

    @CustomTableFields(name = "receiver_address", type = "string", length = 255, comment = "收货人地址")
    public String receiverAddress;

    @CustomTableFields(name = "receiver_city", type = "string", length = 255, comment = "收货人城市")
    public String receiverCity;

    @CustomTableFields(name = "receiver_district", type = "string", length = 255, comment = "收货人区域")
    public String receiverDistrict;

    @CustomTableFields(name = "receiver_mobile", type = "string", length = 255, comment = "收货人手机")
    public String receiverMobile;

    @CustomTableFields(name = "receiver_phone", type = "string", length = 255, comment = "收货人固话", nullable = true)
    public String receiverPhone;

    @CustomTableFields(name = "receiver_name", type = "string", length = 255, comment = "收货人姓名")
    public String receiverName;

    @CustomTableFields(name = "receiver_state", type = "string", length = 255, comment = "收货人省份")
    public String receiverState;

    @CustomTableFields(name = "receiver_town", type = "string", length = 255, comment = "收货人镇", nullable = true)
    public String receiverTown;

    @CustomTableFields(name = "receiver_zip", type = "string", length = 20, comment = "收货人邮编", nullable = true)
    public String receiverZip;

    @CustomTableFields(name = "seller_flag", type = "smallint", length = 5, comment = "买家备注旗杆 红、黄、绿、蓝、紫 分别对应 1、2、3、4、5 默认0", nullable = true)
    public Integer sellerFlag;

    @CustomTableFields(name = "seller_memo", type = "seller_memo", length = 255, comment = "买家备注", nullable = true)
    public String sellerMemo;

    @CustomTableFields(name = "step_paid_fee", length = 20, type = "double", comment = "分段付款金额", nullable = true)
    public double stepPaidFee;

    @CustomTableFields(name = "credit_card_fee", type = "double", length = 20, comment = "信用卡支付金额", nullable = true)
    public double creditCardFee;

    @CustomTableFields(name = "step_trade_statue", type = "string", length = 20, comment = "分段付款金额状态", nullable = true)
    public String stepTradeStatus;

    @CustomTableFields(name = "tid_str", type = "string", length = 255, comment = "订单号字符串类型", nullable = true)
    public String tidStr;

    @CustomTableFields(name = "total_fee", type = "double", length = 20, comment = "SUN(子订单小计 商品价格 * 商品数量 + 手工调整金额 - 商品优惠)")
    public double totalFee;

    @CustomTableFields(name = "invoice_name", type = "string", length = 100, comment = "发票唯一流水号 对应invoice_order.serial_no", nullable = true)
    public String invoiceName;

    @CustomTableFields(name = "invoice_kind", type = "smallint", length = 5, comment = "发票种类，0=电子发票，1=纸质发票，2=纸质专票")
    public Integer invoiceKind;

    @CustomTableFields(name = "invoice_type", type = "smallint", length = 5, comment = "发票类型，0=未开票，1=蓝票，-1=红票 2=蓝票（蓝票冲红后重新开蓝票， 便于标记筛选）")
    public Integer invoiceType;

    @CustomTableFields(name = "order_tx_fee", type = "double", length = 20, comment = "用户支付的总关税")
    public double orderTaxFee;

    @CustomTableFields(name = "process_status", type = "smallint", length = 5, comment = "订单确认状态， 0 未确认，1 部分拆分，2拆分完成，3余单撤销")
    public Integer processStatus;

    @CustomTableFields(name = "confirm", type = "smallint", length = 5, comment = "分销区订单的审核确认状态 0 待订单组确认 1 待财务确认 2 财务通过（放行至待处理） 3 订单组拒绝 4 财务拒绝; 领样区订单的审核确认状态 10 待品牌负责人审批 11 待财务审批 12 财务通过（放行至待处理） 13 品牌负责人拒绝 14 财务拒绝 ")
    public Integer confirm;

    @CustomTableFields(name = "dispatch_time", type = "datetime", comment = "最后一次分派时间", nullable = true)
    public DateTime dispatchTime;

    @CustomTableFields(name = "order_source", type = "string", length = 50, comment = "订单来源")
    public String orderSource;

    @CustomTableFields(name = "is_cross_border", type = "smallint", length = 5, comment = "是否跨境， 1 是， 0 不是", defaultValues = true)
    public Integer isCrossBorder = 0;

    @CustomTableFields(name = "is_card_error", type = "smallint", length = 5, comment = "身份证错误, 1 错误， 0 正确", defaultValues = true)
    public Integer isCardError = 0;

    @CustomTableFields(name = "card_no", type = "string", length = 255, comment = "身份证号", nullable = true)
    public String cardNo;

    @CustomTableFields(name = "card_name", type = "string", length = 255, comment = "身份证姓名", nullable = true)
    public String cardName;

    @CustomTableFields(name = "is_stop", type = "smallint", length = 5, comment = "是否暂停, 1 暂停，0 正常", defaultValues = true)
    public Integer isStop = 0;

    @CustomTableFields(name = "pay_type", type = "smallint", length = 5, comment = "支付方式(0:在线支付, 1:微信支付, 2:支付宝, 3:银联, 4:货到付款, 5余额支付,6到店支付,10:线下支付,11:门店POS机支付,12:货到付款,99:其它支付)", defaultValues = true)
    public Integer payType = 0;

    @CustomTableFields(name = "order_gen_type", type = "smallint", length = 5, comment = "订单来源， 1 平台，2 手工，5 换货", defaultValues = true)
    public Integer orderGenType = 1;

    @CustomTableFields(name = "source_branch", type = "string", length = 50, comment = "来源网点", nullable = true)
    public String sourceBranch;

    @CustomTableFields(name = "source_distribution", type = "string", length = 50, comment = "分销来源, kol_name", nullable = true)
    public String sourceDistribution;

    @CustomTableFields(name = "kol_id", type = "int", length = 11, comment = "分销来源id 特殊分销来源事业部这里为buyer_id，普通分销来源为极选师id 数据权限", nullable = true)
    public Integer kolId;

    @CustomTableFields(name = "currency", type = "string", length = 50, comment = "币种 人民币：RMB； 港币：HKD；美元：USD ；欧元：EUR", nullable = true, defaultValues = true)
    public String currency = "RMB";

    @CustomTableFields(name = "is_ag", type = "smallint", length = 5, comment = "是否ag,标记淘宝AG订单, 1 是， 0 不是", defaultValues = true)
    public Integer isAg = 0;

    @CustomTableFields(name = "freeze_status", type = "smallint", length = 5, comment = "0未冻结，1冻结，2解冻 (客服解冻为2 前N有礼解冻为0)", nullable = true)
    public Integer freezeStatus = 0;

    @CustomTableFields(name = "assign_status", type = "smallint", length = 5, comment = "分配状态 0未分配 1已分配 2退回暂存 3重复分配", defaultValues = true)
    public Integer assignStatus = 0;

    @CustomTableFields(name = "is_employee_order", type = "smallint", length = 5, comment = "是否员工订单, 1 是， 0 不是", defaultValues = true)
    public Integer isEmployeeOrder = 0;

    @CustomTableFields(name = "traffic_acquisition_souce", type = "string", length = 50, comment = "引流来源", nullable = true)
    public String trafficAcquisitionSource;

    @CustomTableFields(name = "is_credit", type = "smallint", length = 5, comment = "是否赊销, 1 赊账，0 正常", nullable = true, defaultValues = true)
    public Integer isCredit = 0;

    @CustomTableFields(name = "rece_slug", type = "string", length = 100, comment = "用户合并订单", nullable = true)
    public String receSlug;

    @CustomTableFields(name = "buyer_uk", type = "string", length = 50, comment = "会员内部标记")
    public String buyerUk;

    @CustomTableFields(name = "buyer_outer_id", type = "string" ,length = 100, comment = "买家外部平台唯一标识", nullable = true)
    public String buyerOuterId;

    @CustomTableFields(name = "is_group_sale", type = "smallint", length = 5, comment = "是否团购，1 是， 0 否", defaultValues = true)
    public Integer isGroupSale = 0;

    @CustomTableFields(name = "is_seckill", type = "smallint", length = 5, comment = "是否秒杀， 1 是， 0 否", defaultValues = true)
    public Integer isSeckill = 0;

    @CustomTableFields(name = "edited", type = "smallint", length = 5, comment = "是否被手工编辑过 默认0", defaultValues = true)
    public Integer edited = 0;

    @CustomTableFields(name = "declare_status", type = "smallint", length = 5, comment = "申报状态 0: 未申报 1: 申报中 2: 申报完成 -1 申报失败")
    public Integer declareStatus;

    @CustomTableFields(name = "total_num", type = "int", length = 11, comment = "总单品数量")
    public Integer totalNum;

    @CustomTableFields(name = "seller_tax_fee", type = "double", length = 20, comment = "商家代扣税款 发货时添加", nullable = true)
    public double sellerTaxFee;

    @CustomTableFields(name = "creator", type = "int", length = 11, comment = "导入订单所有者id(手工单)", nullable = true)
    public Integer creator;

    @CustomTableFields(name = "reject_reason", type = "string", length = 255, comment = "分销订单最近拒绝的原因", nullable = true)
    public String rejectReason;

    @CustomTableFields(name = "goods_trade_type", type = "smallint", length = 5, comment = "子商品的交易类型 1 大贸 2 跨境 3 混合大贸跨境 ", defaultValues = true)
    public Integer goodsTradeType = 1;

    @CustomTableFields(name = "oo_type", type = "smallint", length = 5, comment = "线上线下类型 1线上 2线下 3部分线下部分线上 4纯线下转线上  关系：经过门店的订单【2,3,4】, 自提标记【2，3】   private_status=2是可变的 该字段不可变", defaultValues = true)
    public Integer ooType = 1;

    @CustomTableFields(name = "offline_status", type = "smallint", length = 5, comment = "1: '待拣货' # 默认线上订单状态,2: '已备好',3: '部分备好',4: '已提走',5: '部分提走',6: '拆分完成'", defaultValues = true)
    public Integer offlineStatus = 1;

    @CustomTableFields(name = "offline_code", type = "string", length = 50, comment = "线下发货仓", nullable = true)
    public String offlineCode;

    @CustomTableFields(name = "is_merge", type = "smallint", length = 5, comment = "是否合并，0 不操作, 1 合并，2 不合并", defaultValues = true)
    public Integer isMerge = 0;

    @CustomTableFields(name = "merge_code", type = "string", length = 50, comment = "合并编码", nullable = true)
    public String mergeCode;

    @CustomTableFields(name = "receiver_country", type = "string", length = 50, comment = "收货人国家", nullable = true)
    public String receiverCountry;

    @CustomTableFields(name = "origin_payment", type = "double", length = 20, comment = "订单原始支付金额", nullable = true, defaultValues = true)
    public double originalPayment = 0.00;

    @CustomTableFields(name = "is_sort_gift", type = "smallint", length = 5, comment = "是否前N有礼 0 未获奖 1 已获奖", defaultValues = true)
    public Integer isSortGift = 0;

    @CustomTableFields(name = "is_live", type = "smallint", length = 5, comment = "是否是小程序直播订单, 1 是，0 不是", defaultValues = true)
    public Integer isLive = 0;

    @CustomTableFields(name = "refund_examine_status", type = "smallint", length = 5, comment = "手工退款单的审批状态, 1 不处理(尚未处理) 2 退款创建 3 请求审批中 4 处理完成 5 拒绝", defaultValues = true)
    public Integer refundExamineStatus = 1;

    @CustomTableFields(name = "is_brand_area", type = "smallint", length = 5, comment = "是否品牌专区 1 是， 0 不是", defaultValues = true)
    public Integer isBrandArea = 0;

    @CustomTableFields(name = "brand_area_extra", type = "string", length = 255, comment = "品牌专区额外信息", nullable = true)
    public String brandAreaExtra;

    @CustomTableFields(name = "outer_plat_extra", type = "string", length = 255, comment = "三方平台一些额外信息", nullable = true)
    public String outerPlatExtra;

    @CustomTableFields(name = "account_code", type = "string", length = 100, comment = "帐套（账户）信息")
    public String accountCode;

    @CustomTableFields(name = "buyer_mobile", type = "string", length = 30, comment = "订购人电话", nullable = true)
    public String buyerMobile;
}
