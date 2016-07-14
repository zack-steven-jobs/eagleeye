package com.cmsz.eagleeye.constant;

/**
 * AlarmController
 * 
 * @author ck-huyong
 * @date 2014年6月18日 10:53:48
 * @Description URL访问路径
 */
public class URL {

    /**
     * 用户管理请求的url begin
     */

    /**
     * 考核管理请求根URL
     */
    public static final String ASSESS_ROOT = "/assess";

    /**
     * 导出操作请求根
     */
    public static final String ASSESS_EXPORT_ROOT = "/assess/export";

    /**
     * 导出操作请求根（迭代二）
     */
    public static final String BUSS_EXPORT_ROOT = "/buss/export";

    /**
     * 加载考核总表以及明细表
     */
    public static final String ASSESS_ALL_URL = ASSESS_ROOT + "/allFileAssess";

    /**
     * 查找所有考核对象的URL
     */
    public static final String ASSESS_GET_ALL = ASSESS_ROOT + "/getAllAssess";

    /**
     * 查找所有省份的URL
     */
    public static final String PROVINCE_GET_ALL = "/province/getAllProvince";

    /**
     * 查找所有文件类型的URL
     */
    public static final String FILETYPE_GET_ALL = "/filetype/getAllFiletype";

    /**
     * 查找所有业务类型的URL
     */
    public static final String BUSINESS_GET_ALL = "/business/getAllbusiness";

    /**
     * 文件考核EXCEL导出功能URL
     * 
     */
    public static final String ASSESS_EXPORT_EXCEL = ASSESS_EXPORT_ROOT + "/exportExcel";

    /**
     * 业务质量指标Excel和Pdf导出功能URL
     */
    public static final String BUSS_EXPORT_EXCEL_AS_PDF = BUSS_EXPORT_ROOT
            + "/exportExcelAsPdf";

    /* 迭代二 */
    /**
     * 查找所有指标类型的URL
     */
    public static final String BUSSTYPE_GET_ALL = "/filetype/getAllBusstype";

    /**
     * 根据条件查询省公司业务质量指标
     */
    public static final String BUSS_ALL_URL = ASSESS_ROOT + "/getBussFile";

    /* ----------------------------------运营巡检URL ------------------------ */

    public static final String OPERATION_ROOT = "/operation";// /operation/biz/statistics/total
    /**
     * 按业务线统计成交总量
     */
    public static final String BIZ_STAT_LINE = "/business/statistics/line";
    /**
     * 按业务线统计成交总量
     */
    public static final String BIZ_STAT_LINE_EXPORT = "/business/statistics/line/export";

    /**
     * 查询业务线基础数据
     */
    public static final String DATA_STAT_BIZ = "/basic/data/business";

    /**
     * 查询省份基础数据
     */
    public static final String DATA_STAT_PROVINCE = "/basic/data/province";

    /**
     * 查询维度基础数据
     */
    public static final String DATA_STAT_DIMENSION = "/basic/data/dimension";

    /**
     * 查询分省业务量
     */
    public static final String DATE_PROVINCE_BUSINESS =
            "/biz/statistics/province/compare";
    /**
     * 查询成交量同比数据统计
     */
    public static final String DATE_STAT_COMPARE = "/business/statistics/compare";
    /**
     * 查询分省业务量占比
     */
    public static final String BIZ_STATISTICS_PERCENT = "/biz/statistics/trade/percent";

    /**
     * 分省业务量占比导出
     */
    public static final String BIZ_STATISTICS_PERCENT_EXPORT =
            "/biz/statistics/trade/percent/export";

    // --------------------------------LIPING-START----------------------------------------
    /**
     * 查询交易量总体情况
     */
    public static final String BIZ_STATISTICS_TOTAL = "/biz/statistics/total";
    /**
     * 交易量总体情况数据导出
     */
    public static final String BIZ_STATISTICS_TOTAL_EXPORT =
            "/biz/statistics/total/export";
    /**
     * 分省分业务数据指标查询
     */
    public static final String BIZ_STATISTICS_PROVICE = "/biz/statistics/province";
    /**
     * 分省分业务数据指标数据导出
     */
    public static final String BIZ_STATISTICS_PROVICE_EXPORT =
            "biz/statistics/province/export";
    // ~~~~~~~~~~告警日志~~~~~~~~~~~~~~~~~~~~~
    /**
     * 业务树查询
     */
    public static final String ALARM_BIZ_TREE = "/alarm/biz/tree";
    public static final String ALARM_LOG_TRANZTYPE_TREE = "/alarm/log/tranzType/tree";
    // ~~~~~~~~~~告警日志~~~~~~~~~~~~~~~~~~~~~

    // --------------------------------LIPING---END----------------------------------------
    /**
     * 分省业务量对比情况导出
     */
    public static final String BIZ_STAT_PROV_COMPA =
            "/biz/statistics/province/compare/export";
    /**
     * 成交量同比数据统计情况导出
     */
    public static final String BIZ_STAT_TOTAL_VOLUME =
            "/business/statistics/compare/export";

    /**
     * 各省交易成功处理率统计情况查询
     */
    public static final String BIZ_STAT_LINE_SUCC_RATE = "/biz/statistics/success/rate";
    /**
     * 各省交易成功处理率统计情况导出
     */
    public static final String BIZ_STAT_LINE_SUCC_RATE_EXPORT =
            "/biz/statistics/success/rate/export";

    /**
     * 告警日志url begin
     */

    /**
     * 用户表格配置信息查询
     */
    public static final String ALARM_CONFIG_QUERY = "/alarm/config/page/query";
    /**
     * 用户表格信息更新
     */
    public static final String ALARM_CONFIG_UPDATE = "/alarm/config/page/update";
    /**
     * 告警日志列表展示
     */
    public static final String ALARM_LOG_QUERY = "/alarm/log/query";
    /**
     * 告警规则新增
     */
    public static final String ALARM_SAVE = "alarm/rule/add";
    /**
     * 告警规则删除
     */
    public static final String ALARM_DEL = "alarm/rule/delete";
    /**
     * 告警规则详情
     */
    public static final String ALARM_QUERY = "alarm/rule/detail";
    /**
     * 告警规则管理查询
     */
    public static final String ALARM_MANAGER = "alarm/rule/manager";
    /**
     * 告警规则重名验证
     */
    public static final String ALARM_CHECK_VALIDATE = "alarm/rule/check/validate";
    /**
     * 告警规则修改
     */
    public static final String ALARM_UPDATE = "alarm/rule/update";
    /**
     * 初始化告警规则
     */
    public static final String ALARM_FILTER_LIST = "/alarm/log/rule/list";
    /**
     * 告警日志列表导出
     */
    public static final String ALARM_LOG_QUERY_EXPORT = "/alarm/log/Export";
    /**
     * 查看告警日志详细信息
     */
    public static final String ALARM_LOG_DETAIL = "/alarm/log/detail";
    /**
     * 规则激活
     */
    public static final String ALARM_RULE_ACTIVATE = "/alarm/rule/activate";
    /**
     * 规则撤销
     */
    public static final String ALARM_RULE_REVOKE = "/alarm/rule/revoke";


}
