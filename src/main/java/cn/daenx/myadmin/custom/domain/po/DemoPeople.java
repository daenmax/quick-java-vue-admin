package cn.daenx.myadmin.custom.domain.po;

import cn.daenx.myadmin.common.vo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试人员
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "demo_people")
public class DemoPeople extends BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private String age;

    /**
     * 性别：1=男，0=女
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 到期时间，留空则永久 yyyy-MM-dd HH:mm:ss
     */
    @TableField(value = "expire_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expireTime;
}