package com.bsg.upm.check.resultenum;

public interface ChkRsInterface {

    // 错误码共8位,
    // 第1~3位表示模块,
    // 第4~5位表示操作类型,
    // 第6~8位表示检查项目
    public final static int OK = 200;
    public final static int LOGIN = 10000000;
    public final static int SITE = 11000000;
    public final static int SITE_CONFIG = 11100000;
    public final static int BACKUP_STORAGE = 12000000;
    public final static int AREA = 13000000;
    public final static int CLUSTER = 14000000;
    public final static int NETWORKING = 15000000;
    public final static int PORT = 16000000;
    public final static int SOFTWARE_IMAGE = 17000000;
    public final static int IMAGE_TEMPLATE = 17100000;
    public final static int SAN = 18000000;
    public final static int RG = 18100000;
    public final static int HOST = 19000000;

    public final static int BUSINESS_SYSTEM = 31000000;
    public final static int BUSINESS_SUBSYSTEM = 32000000;

    public final static int ORDER_GROUP_CORE = 33000000;

    public final static int UNIT = 40100000;
    public final static int SERV_PARAMETER_CONFIG = 40200000;
    public final static int SERV_BACKUP_STRATEGY = 40300000;
    public final static int SERV_BACKUP_FILE = 40400000;
    
    public final static int UPSQL_SERV_GROUP = 50000000;
    public final static int UPSQL_DATABASE = 50100000;
    public final static int UPSQL_USER = 50200000;
    public final static int UPSQL_DIRECT_USER = 50300000;
    public final static int UPSQL_SHARDING_STRATEGY = 50400000;

    public final static int UPREDIS_SERV_GROUP = 52000000;

    public final static int SCALE = 81000000;
    public final static int ORDER_CONFIG = 82000000;
    public final static int COMBO_GROUP = 83000000;

    public final static int DICT = 91000000;
    public final static int GROUP = 92000000;
    public final static int ROLE = 93000000;
    public final static int ROLE_CFG_OPERATE = 93100000;
    public final static int ROLE_CFG_DATA_SCOPE = 93200000;
    public final static int ROLE_CFG_REPORT = 93300000;
    public final static int ROLE_CFG_RESOURCE = 93400000;
    public final static int ROLE_CFG_OTHERS = 93400000;
    public final static int ROLE_CFG_APP = 93500000;
    public final static int USER = 94000000;

    public final static int CONSUL = 99800000;
    public final static int INTERFACE_CALLING = 99900000;
    
    public final static int SERVERGROUP = 22200000;
    
    public final static int DATABASE = 20000000;
    
    
    public final static int SERVERGROUP_USER = 20200000;
    public final static int SERVERGROUP_UNIT = 20300000;
    
    public final static int BACKUPFILE = 20400000;

    int getCode();

    String getMsg();
}
