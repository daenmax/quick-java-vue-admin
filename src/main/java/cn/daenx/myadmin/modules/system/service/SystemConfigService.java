package cn.daenx.myadmin.modules.system.service;

import cn.daenx.myadmin.modules.system.domain.po.SystemConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SystemConfigService extends IService<SystemConfig> {
    /**
     * 查询
     *
     * @return
     */
    Map<String, String> getInfo();

    /**
     * 查询-从内存中
     *
     * @return
     */
    Map<String, String> getInfoFromCache();

    /**
     * 刷新缓存
     */
    void initCache();

    /**
     * 修改
     *
     * @param map
     */
    void editInfo(Map<String, String> map);
}
