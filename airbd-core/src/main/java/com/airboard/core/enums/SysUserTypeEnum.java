package com.airboard.core.enums;


public enum SysUserTypeEnum {

    REGISTER_USER(1, "普通用户");

    public final int type;
    public final String name;

    SysUserTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public SysUserTypeEnum getEnumByType(int type) {
        for (SysUserTypeEnum value : SysUserTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return null;
    }
}
