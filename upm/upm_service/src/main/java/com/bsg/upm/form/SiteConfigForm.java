package com.bsg.upm.form;

import java.io.Serializable;

public class SiteConfigForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 是否开启告警
     */
    private Boolean alarmOn;

    /**
     * 告警地址
     */
    private String alarmAddr;

    /**
     * 是否开启发送邮件
     */
    private Boolean mailOn;

    /**
     * 邮件服务器
     */
    private String mailHost;

    /**
     * 协议
     */
    private String mailProtocol;

    /**
     * 端口
     */
    private Integer mailPort;

    /**
     * 发件人
     */
    private String mailUsername;

    /**
     * 发件人邮箱密码
     */
    private String mailPassword;

    /**
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取是否开启告警
     * 
     * @return alarmOn 是否开启告警
     */
    public Boolean getAlarmOn() {
        return alarmOn;
    }

    /**
     * 设置是否开启告警
     * 
     * @param alarmOn
     *            是否开启告警
     */
    public void setAlarmOn(Boolean alarmOn) {
        this.alarmOn = alarmOn;
    }

    /**
     * 获取告警地址
     * 
     * @return alarmAddr 告警地址
     */
    public String getAlarmAddr() {
        return alarmAddr;
    }

    /**
     * 设置告警地址
     * 
     * @param alarmAddr
     *            告警地址
     */
    public void setAlarmAddr(String alarmAddr) {
        this.alarmAddr = alarmAddr;
    }

    /**
     * 获取是否开启发送邮件
     * 
     * @return mailOn 是否开启发送邮件
     */
    public Boolean getMailOn() {
        return mailOn;
    }

    /**
     * 设置是否开启发送邮件
     * 
     * @param mailOn
     *            是否开启发送邮件
     */
    public void setMailOn(Boolean mailOn) {
        this.mailOn = mailOn;
    }

    /**
     * 获取邮件服务器
     * 
     * @return mailHost 邮件服务器
     */
    public String getMailHost() {
        return mailHost;
    }

    /**
     * 设置邮件服务器
     * 
     * @param mailHost
     *            邮件服务器
     */
    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    /**
     * 获取协议
     * 
     * @return mailProtocol 协议
     */
    public String getMailProtocol() {
        return mailProtocol;
    }

    /**
     * 设置协议
     * 
     * @param mailProtocol
     *            协议
     */
    public void setMailProtocol(String mailProtocol) {
        this.mailProtocol = mailProtocol;
    }

    /**
     * 获取端口
     * 
     * @return mailPort 端口
     */
    public Integer getMailPort() {
        return mailPort;
    }

    /**
     * 设置端口
     * 
     * @param mailPort
     *            端口
     */
    public void setMailPort(Integer mailPort) {
        this.mailPort = mailPort;
    }

    /**
     * 获取发件人
     * 
     * @return mailUsername 发件人
     */
    public String getMailUsername() {
        return mailUsername;
    }

    /**
     * 设置发件人
     * 
     * @param mailUsername
     *            发件人
     */
    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    /**
     * 获取发件人邮箱密码
     * 
     * @return mailPassword 发件人邮箱密码
     */
    public String getMailPassword() {
        return mailPassword;
    }

    /**
     * 设置发件人邮箱密码
     * 
     * @param mailPassword
     *            发件人邮箱密码
     */
    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SiteConfigForm [siteId=" + siteId + ", alarmOn=" + alarmOn + ", alarmAddr=" + alarmAddr + ", mailOn="
                + mailOn + ", mailHost=" + mailHost + ", mailProtocol=" + mailProtocol + ", mailPort=" + mailPort
                + ", mailUsername=" + mailUsername + ", mailPassword=" + mailPassword + "]";
    }

}
