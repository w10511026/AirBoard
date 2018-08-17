package com.airboard.client.enums;


import lombok.Getter;
import lombok.Setter;

public enum SysUserStatusEnum {

    NORMAL(1, "正常"),
    UNABLE(2, "停用");

    @Getter
    @Setter
    public final int type;
    @Getter
    @Setter
    public String name;

    SysUserStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static SysUserStatusEnum getEnumByType(int type) {
        for (SysUserStatusEnum value : SysUserStatusEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return SysUserStatusEnum.valueOf(SysUserStatusEnum.class, "-");
    }
}
