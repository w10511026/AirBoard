package com.airboard.core.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 */
public class BaseMenu {

    public final static String SYSTEM_MANAGE = "SYSTEM_MANAGE";

    enum BaseMenuEnum{

        SYSTEM_MANAGE("SYSTEM_MANAGE", "系统管理");

        @Getter
        public final String type;
        @Getter
        public String name;

        BaseMenuEnum(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public static BaseMenuEnum getEnumByType(String type) {
            for (BaseMenuEnum value : BaseMenuEnum.values()) {
                if (value.getType().equals(type)) {
                    return value;
                }
            }
            return BaseMenuEnum.valueOf(BaseMenuEnum.class, "-");
        }
    }

}
