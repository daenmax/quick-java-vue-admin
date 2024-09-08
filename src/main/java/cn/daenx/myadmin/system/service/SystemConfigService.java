package cn.daenx.myadmin.system.service;

import cn.daenx.myadmin.system.domain.po.SystemConfig;
import cn.daenx.myadmin.system.domain.vo.SystemConfigUpdVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SystemConfigService extends IService<SystemConfig> {
    /**
     * 邮箱配置-查询
     *
     * @return
     */
    SystemConfig getInfo();

    /**
     * 邮箱配置-查询-从内存中
     *
     * @return
     */
    SystemConfig getInfoFromCache();

    /**
     * 刷新缓存
     */
    void initCache();

    /**
     * 邮箱配置-修改
     *
     * @param vo
     */
    void editInfo(SystemConfigUpdVo vo);
}
