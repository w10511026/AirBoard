package com.airboard.core.enums;


import lombok.Getter;
import lombok.Setter;

public enum SysUserSexEnum {

    MAN(1, "男"),
    LADY(2, "女");

    @Getter
    @Setter
    public final int type;
    @Getter
    @Setter
    public String name;

    SysUserSexEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static SysUserSexEnum getEnumByType(int type) {
        for (SysUserSexEnum value : SysUserSexEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return SysUserSexEnum.valueOf(SysUserSexEnum.class, "-");
    }
}
