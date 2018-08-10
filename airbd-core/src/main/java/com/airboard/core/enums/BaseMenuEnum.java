package com.airboard.core.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/8/10
 */
public enum BaseMenuEnum {

    SYSTEM_MANAGE(1, "系统管理");

    @Getter
    @Setter
    public final int type;
    @Getter
    @Setter
    public String name;

    BaseMenuEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static BaseMenuEnum getEnumByType(int type) {
        for (BaseMenuEnum value : BaseMenuEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return BaseMenuEnum.valueOf(BaseMenuEnum.class, "-");
    }
}
