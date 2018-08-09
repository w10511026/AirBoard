package com.airboard.core.enums;


import lombok.Getter;
import lombok.Setter;

public enum SysUserSexEnum {

    MAN(1, "男"),
    LADY(2, "女");

    @Getter
    @Setter
    public final Integer type;
    @Getter
    @Setter
    public String name;

    SysUserSexEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public SysUserSexEnum getEnumByType(Integer type) {
        for (SysUserSexEnum value : SysUserSexEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
