package com.bsg.upm.enums;

/**
 * 数据范围
 * 
 * @author HCK
 * @date 2018年5月14日
 */
public enum DataScopeEnum implements BaseEnum<String> {
    //
    ONESELF("oneself", "仅本人数据"),
    //
    GROUP_SAME_ROLE("group_same_role", "本组同角色数据"),
    //
    GROUP("group", "本组数据"),
    //
    ALL("all", "所有数据");

    private String value;
    private String displayName;

    DataScopeEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    /**
     * 获取value
     * 
     * @return value value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value
     * 
     * @param value
     *            value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取displayName
     * 
     * @return displayName displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置displayName
     * 
     * @param displayName
     *            displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static DataScopeEnum getEnum(String value) {
        for (DataScopeEnum dataScope : DataScopeEnum.values()) {
            if (dataScope.getValue().equals(value)) {
                return dataScope;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value);
    }

}
