package cn.daenx.myadmin.custom.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DemoPeopleUpdVo {
    @NotBlank(message = "ID不能为空")
    private String id;
    /**
     * 备注
     */
    private String remark;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 性别：1=男，0=女
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 到期时间，留空则永久 yyyy-MM-dd HH:mm:ss
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expireTime;
}
