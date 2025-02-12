package cn.daenx.myadmin.modules.system.domain.po;

import cn.daenx.myadmin.framework.common.vo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统配置
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_config")
public class SystemConfig extends BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 参数名
     */
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 参数值
     */
    @TableField(value = "config_value")
    private String configValue;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

}
