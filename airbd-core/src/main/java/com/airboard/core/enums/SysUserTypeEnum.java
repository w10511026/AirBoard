package com.airboard.core.enums;


import lombok.Getter;
import lombok.Setter;

public enum SysUserTypeEnum {

    REGISTER_USER(1, "普通用户");

    @Getter
    @Setter
    public final Integer type;
    @Getter
    @Setter
    public final String name;

    SysUserTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public SysUserTypeEnum getEnumByType(Integer type) {
        for (SysUserTypeEnum value : SysUserTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
