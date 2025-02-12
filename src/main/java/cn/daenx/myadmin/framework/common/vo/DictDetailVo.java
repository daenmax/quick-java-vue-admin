package cn.daenx.myadmin.framework.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
    * 字典明细
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictDetailVo {

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 字典状态，0=正常，1=禁用
     */
    private String status;

}
