package cn.daenx.myadmin.custom.domain.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


@Data
public class DemoPeopleCopyInfoVo {

    private List<String> ids;
    /**
     * 姓名是否脱敏，0=否，1=是
     */
    @NotBlank(message = "姓名是否脱敏 不能为空")
    private String hideName;

    /**
     * 生成格式
     * 变量：【姓名】、【年龄】、【性别】、【信息】
     */
    private String format;
}
