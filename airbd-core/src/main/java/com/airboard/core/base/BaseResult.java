package com.airboard.core.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/28
 */
@ApiModel(description = "返回响应数据")
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 6732312763880543546L;
    @Getter
    @ApiModelProperty(value = "成功数据集")
    private T data;
    @Getter
    @Setter
    @ApiModelProperty(value = "错误信息")
    private String message;
    @Getter
    @ApiModelProperty(value = "是否成功")
    private boolean success;

    public BaseResult() {
    }

    public BaseResult(T data) {
        this.data = data;
        this.setSuccess(data);
    }

    public BaseResult(String message) {
        this.message = message;
        this.success = false;
    }

    private void setSuccess(T data) {
        if (null == data) {
            this.success = false;
        } else {
            this.success = true;
        }
    }

    public void setData(T data) {
        this.data = data;
        this.setSuccess(data);
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}