package com.airboard.core.enums;


import lombok.Getter;
import lombok.Setter;

public enum SysUserStatusEnum {

    NORMAL(1, "正常"),
    UNABLE(2, "停用");

    @Getter
    @Setter
    public final Integer type;
    @Getter
    @Setter
    public String name;

    SysUserStatusEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public SysUserStatusEnum getEnumByType(Integer type) {
        for (SysUserStatusEnum value : SysUserStatusEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
