package com.bsg.upm.constant;

/**
 * 字典项常量类
 * 
 * @author HCK
 *
 */
public class DictConsts {

    /******************************* 数据范围 STA ********************************/
     /**
     * 数据范围:仅本人数据
     */
     public static final String DATA_SCOPE_ONESELF = "oneself";
    
     /**
     * 数据范围:本组所有数据
     */
     public static final String DATA_SCOPE_GROUP = "group";
    
     /**
     * 数据范围:所有数据
     */
     public static final String DATA_SCOPE_ALL = "all";

    /******************************* 数据范围 END ********************************/

    /******************************* 认证方式 STA ********************************/
    /**
     * 认证方式:本地认证
     */
    public static final String AUTH_TYPE_NATIVE = "native";

    /**
     * 认证方式:SSO认证
     */
    public static final String AUTH_TYPE_SSO = "sso";
    /******************************* 认证方式 END ********************************/

    /**
     * 状态:未入库
     */
    public static final String STATUS_OUT = "out";

    /**
     * 状态:启用
     */
    public static final String STATUS_ENABLED = "enabled";

    /**
     * 状态:停用
     */
    public static final String STATUS_DISABLED = "disabled";

    /******************************** 工单类型 STA ********************************/
    /**
     * 工单类型：新工单
     */
    public static final String ORDER_CREATE_TYPE_NEW = "new";
    
    /**
     * MGM接口返回的工单类型：新工单
     */
    public static final String MGM_APP_CREATE_TYPE_NEW = "app-add";
    
    /**
     * MGM接口返回的工单类型：新工单
     */
    public static final String MGM_Unit_CREATE_TYPE_NEW = "unit create";
    
    /**
     * 工单类型：删除单
     */
    public static final String ORDER_CREATE_TYPE_DELETE = "delete";
    
    /**
     * MGM接口返回的工单类型：新工单
     */
    public static final String MGM_APP_DELETE_TYPE_NEW = "app-delete";

    /**
     * 工单类型：扩容单
     */
    public static final String ORDER_CREATE_TYPE_SCALE_UP = "scale_up";

    /**
     * MGM接口返回的工单类型：扩容单
     */
    public static final String MGM_ORDER_CREATE_TYPE_SCALE_UP = "app-resource-edit";
    /**
     * 工单类型：版本变更单
     */
    public static final String ORDER_CREATE_TYPE_IMAGE_UPDATE = "image_update";
    
    /**
     * MGM接口返回的工单类型：版本变更单
     */
    public static final String MGM_ORDER_CREATE_TYPE_IMAGE_UPDATE = "app-image-edit";
    
    /**
     * 工单类型：扩展单(分片扩展)
     */
    public static final String ORDER_CREATE_TYPE_SERV_SCALE_OUT = "serv_scale_out";
    
    /**
     * 工单类型：扩展单(节点扩展)
     */
    public static final String ORDER_CREATE_TYPE_UNIT_SCALE_OUT = "unit_scale_out";

    /******************************** 工单状态 END ********************************/

    /******************************** 工单状态 STA ********************************/
    /**
     * 工单状态：未审批
     */
    public static final String ORDER_STATUS_UNAPPROVED = "unapproved";

    /**
     * 工单状态：审批通过
     */
    public static final String ORDER_STATUS_APPROVED = "approved";

    /**
     * 工单状态：审批拒绝
     */
    public static final String ORDER_STATUS_UNAPPROVE = "unapprove";

    /**
     * 工单状态：执行中
     */
    public static final String ORDER_STATUS_EXECUTING = "executing";

    /**
     * 工单状态：执行成功
     */
    public static final String ORDER_STATUS_SUCCESS = "success";

    /**
     * 工单状态：执行失败
     */
    public static final String ORDER_STATUS_FAILURE = "failure";
    
    /**
     * 工单状态：执行超时
     */
    public static final String ORDER_STATUS_OVERTIME = "overtime";
    
    /**
     * 工单状态：执行失败
     */
    public static final String COMPARE_ORDER_STATUS_FAILED= "failed";
    /******************************** 工单状态 END ********************************/

    /****************************** 服务相关状态 STA ******************************/
    /**
     * 服务状态：passing
     */
    public static final String STATUS_PASSING = "passing";
    
    public static final String STATUS_RUNNING = "running";

    /**
     * 服务状态：warning
     */
    public static final String STATUS_WARNNING = "warning";

    /**
     * 服务状态：critical
     */
    public static final String STATUS_CRITICAL = "critical";

    // /**
    // * 服务状态：unknown
    // */
    // public static final String STATUS_UNKNOWN = "unknown";
    /****************************** 服务相关状态 END ******************************/

    /**************************** 用户模式 STA ****************************/
    /**
     * 用户模式：读写主库
     */
    public static final String MODEL_RW = "rw";

    /**
     * 用户模式：读写分离
     */
    public static final String MODEL_RW_SPLIT = "rw_split";

    /**
     * 用户模式：只读从库
     */
    public static final String MODEL_READ_ONLY = "read_only";
    /**************************** 用户模式 END ****************************/

    /**************************** 备份类型 STA ****************************/
    /**
     * 备份类型：全备
     */
    public static final String BACKUP_TYPE_FULL = "full";

    /**
     * 备份类型：导表备份
     */
    public static final String BACKUP_TYPE_TABLE = "table";
    /**************************** 备份类型 END ****************************/

    /************************* 服务用户认证方式 STA *************************/
    /**
     * 服务用户认证方式 ：密码认证
     */
    public static final String SERV_USER_AUTH_TYPE_PASSWORD = "password";

    /**
     * 服务用户认证方式：dbpm
     */
    public static final String SERV_USER_AUTH_TYPE_DBPM = "DBPM";
    /************************ 服务用户认证方式 END ************************/

    /************************* 磁盘类型STA *************************/
    /**
     * 磁盘类型 ：本地盘
     */
    public static final String DIR_TYPE_LOCAL_HOST = "host";

    /**
     * 磁盘类型：固态盘
     */
    public static final String DIR_TYPE_LOCAL_REMOTE = "remote";
    /************************ 磁盘类型 END ************************/
    
    /**
     *主机监控查询类型：cpu
     */
    public static final String HOST_MONITOR_TYPE_CPU = "host.linux.cpu.avg_usage";
    
    /**
     *主机监控查询类型：mem
     */
    public static final String HOST_MONITOR_TYPE_MEM = "host.linux.mem.usage";
    
}
