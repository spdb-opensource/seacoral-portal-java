package com.bsg.upm.dto;

public class DisplayDTO {

    private String code;
    private String display;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "DisplayDTO [code=" + code + ", display=" + display + "]";
    }

}
