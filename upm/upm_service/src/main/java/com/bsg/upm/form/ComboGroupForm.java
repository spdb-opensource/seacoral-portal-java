package com.bsg.upm.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComboGroupForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sequence;

    /**
     * 描述
     */
    private String description;

    /**
     * 套餐
     */
    private List<ComboForm> combos = new ArrayList<>();

    /**
     * 获取类型
     * 
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type
     *            类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取套餐名称
     * 
     * @return name 套餐名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置套餐名称
     * 
     * @param name
     *            套餐名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取显示顺序
     * 
     * @return sequence 显示顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置显示顺序
     * 
     * @param sequence
     *            显示顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取描述
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取套餐
     * 
     * @return combos 套餐
     */
    public List<ComboForm> getCombos() {
        return combos;
    }

    /**
     * 设置套餐
     * 
     * @param combos
     *            套餐
     */
    public void setCombos(List<ComboForm> combos) {
        this.combos = combos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ComboGroupForm [type=" + type + ", name=" + name + ", sequence=" + sequence + ", description="
                + description + ", combos=" + combos + "]";
    }

}
