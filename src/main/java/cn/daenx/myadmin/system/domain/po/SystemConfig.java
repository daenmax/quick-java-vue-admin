package cn.daenx.myadmin.system.domain.po;

import cn.daenx.myadmin.common.vo.BaseEntity;
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
     * 后台管理API开关，0=开启，1=关闭
     */
    @TableField(value = "manager_api_lock")
    private String managerApiLock;

    /**
     * 后台管理API秘钥
     */
    @TableField(value = "manager_api_key")
    private String managerApiKey;

    /**
     * 后端API地址
     */
    @TableField(value = "back_api_url")
    private String backApiUrl;

}
