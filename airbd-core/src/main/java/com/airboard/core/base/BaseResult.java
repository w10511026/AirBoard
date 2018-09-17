package com.airboard.core.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description 通用返回结果
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/28
 */
@ApiModel(description = "通用返回结果")
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
    private Boolean success;
    @Getter
    @Setter
    private Integer total;
    @Getter
    @Setter
    private T rows;

    public BaseResult() {
    }

    public BaseResult(Boolean success) {
        this.success = success;
    }

    public BaseResult(T data) {
        this.data = data;
        this.setSuccess(data);
    }

    public BaseResult(String message) {
        this.message = message;
        this.success = false;
    }

    public BaseResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
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

    public void setData(IPage<T> page) {
        this.total = Math.toIntExact(page.getTotal());
        this.rows = (T) page.getRecords();
        this.setSuccess((T) page.getRecords());
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
