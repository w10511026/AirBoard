package com.airboard.core.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/8/10
 */
public enum PermissionTypeEnum {

    MENU(1, "菜单"),
    PERMISSION(2, "权限");

    @Getter
    @Setter
    public final int type;
    @Getter
    @Setter
    public String name;

    PermissionTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static PermissionTypeEnum getEnumByType(int type) {
        for (PermissionTypeEnum value : PermissionTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return PermissionTypeEnum.valueOf(PermissionTypeEnum.class, "-");
    }
}
