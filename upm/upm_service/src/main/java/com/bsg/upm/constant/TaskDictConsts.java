package com.bsg.upm.constant;

/**
 * 字典项常量类
 * 
 * @author HCK
 *
 */
public class TaskDictConsts {

    /**
     * 任务对象类型：镜像
     */
    public static final String OBJ_TYPE_IMAGE = "image";

    /**
     * 任务对象类型：主机
     */
    public static final String OBJ_TYPE_HOST = "host";

    /**
     * 任务对象类型：服务组
     */
    public static final String OBJ_TYPE_SERV_GROUP = "serv_group";

    /**
     * 任务对象类型：服务
     */
    public static final String OBJ_TYPE_SERV = "serv";

    /**
     * 任务对象类型：服务
     */
    public static final String OBJ_TYPE_UNIT = "unit";

    /**
     * 任务动作类型：入库
     */
    public static final String ACTION_TYPE_IN = "in";

    /**
     * 任务动作类型：出库
     */
    public static final String ACTION_TYPE_OUT = "out";

    /**
     * 任务动作类型：创建
     */
    public static final String ACTION_TYPE_CREATE = "create";
    
    /**
     * 任务动作类型：LINK
     */
    public static final String ACTION_TYPE_LINK = "link";

    /**
     * 任务动作类型：启动
     */
    public static final String ACTION_TYPE_START = "start";

    /**
     * 任务动作类型：停止
     */
    public static final String ACTION_TYPE_STOP = "stop";

    /**
     * 任务动作类型：迁移
     */
    public static final String ACTION_TYPE_MIGRATE = "migrate";

    /**
     * 任务动作类型：重建
     */
    public static final String ACTION_TYPE_REBUILD = "rebuild";
    
    /**
     * 任务动作类型：隔离
     */
    public static final String ACTION_TYPE_ISOLATE = "isolate";
    
    /**
     * 任务动作类型：回切
     */
    public static final String ACTION_TYPE_RECOVER = "recover";
    
    /**
     * 任务动作类型：切换
     */
    public static final String ACTION_TYPE_ROLESWITCH = "roleswitch";

    /**
     * 任务动作类型：备份
     */
    public static final String ACTION_TYPE_BACKUP = "backup";

    /**
     * 任务动作类型：还原
     */
    public static final String ACTION_TYPE_RESTORE = "restore";

    /**
     * 任务动作类型：扩容
     */
    public static final String ACTION_TYPE_SCALE_UP = "scale_up";
    
    /**
     * 任务动作类型：分片扩展-分片准备
     */
    public static final String ACTION_TYPE_SERV_SCALE_OUT_CREATE = "serv_scale_out_create";
    
    /**
     * 任务动作类型：分片扩展-开始数据迁移
     */
    public static final String ACTION_TYPE_SERV_SCALE_OUT_START_MIGRATE = "serv_scale_out_start_migrate";
    
    /**
     * 任务动作类型：分片扩展-切换检查
     */
    public static final String ACTION_TYPE_SERV_SCALE_OUT_SWITCH_CHECK = "serv_scale_out_switch_check";
    
    /**
     * 任务动作类型：分片扩展-切换分片
     */
    public static final String ACTION_TYPE_SERV_SCALE_OUT_SWITCH = "serv_scale_out_switch";
    
    /**
     * 任务动作类型：分片扩展-回收旧分片
     */
    public static final String ACTION_TYPE_SERV_SCALE_OUT_RECYCLE = "serv_scale_out_recycle";
    
    /**
     * 任务动作类型：清理
     */
    public static final String ACTION_TYPE_CLEAR = "clear";

    /**
     * 任务动作类型：版本变更
     */
    public static final String ACTION_TYPE_IMAGE_UPDATE = "image_update";

    /**
     * 任务动作类型：删除
     */
    public static final String ACTION_TYPE_REMOVE = "remove";
    
    /**
     * 任务动作类型：配置同步
     */
    public static final String ACTION_TYPE_SYNC_CFG = "sync_cfg";
    
    /**
     * 任务动作类型：库表同步
     */
    public static final String ACTION_TYPE_SYNC_DB = "sync_db";

    public static final String STATUS_NOT_RUNNING = "not_running";

    public static final String STATUS_RUNNING = "running";

    public static final String STATUS_SUCCESS = "success";

    public static final String STATUS_FAILURE = "failure";

    public static final String STATUS_UNKNOWN = "unknown";

}
