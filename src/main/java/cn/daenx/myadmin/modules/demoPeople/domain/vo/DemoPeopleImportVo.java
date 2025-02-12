package cn.daenx.myadmin.modules.demoPeople.domain.vo;

import cn.daenx.myadmin.framework.annotation.Dict;
import cn.daenx.myadmin.framework.excel.ExcelConverter;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//导出时忽略没有@ExcelProperty的字段
@ExcelIgnoreUnannotated
public class DemoPeopleImportVo {

    /**
     * 姓名
     */
    @ExcelProperty(value = "*" + "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 性别：1=男，0=女
     */
    @ExcelProperty(value = "*" + "性别", converter = ExcelConverter.class)
    @Dict(dictCode = "dict_demo_people_sex", custom = {})
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 到期时间，留空则永久 yyyy-MM-dd HH:mm:ss
     */
    @ExcelProperty(value ="到期时间（留空则永久，格式为yyyy-MM-dd HH:mm:ss）")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expireTime;


    /**
     * 备注
     */
    @ExcelProperty(value ="备注")
    private String remark;

    /**
     * 状态，0=正常，1=禁用
     */
    @ExcelProperty(value = "*" + "状态", converter = ExcelConverter.class)
    @Dict(dictCode = "dict_common_status", custom = {})
    private String status;
}
