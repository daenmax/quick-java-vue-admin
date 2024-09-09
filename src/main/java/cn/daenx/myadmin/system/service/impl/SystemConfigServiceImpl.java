package cn.daenx.myadmin.system.service.impl;

import cn.daenx.myadmin.common.exception.MyException;
import cn.daenx.myadmin.system.cache.ConfigCache;
import cn.daenx.myadmin.system.domain.po.SystemConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.daenx.myadmin.system.mapper.SystemConfigMapper;
import cn.daenx.myadmin.system.service.SystemConfigService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {
    @Resource
    private SystemConfigMapper systemConfigMapper;
    @Resource
    private ConfigCache configCache;

    /**
     * 查询
     *
     * @return
     */
    @Override
    public Map<String, String> getInfo() {
        List<SystemConfig> list = systemConfigMapper.selectList(new LambdaQueryWrapper<>());
        Map<String, String> map = new HashMap<>();
        for (SystemConfig systemConfig : list) {
            map.put(systemConfig.getConfigKey(), systemConfig.getConfigValue());
        }
        return map;
    }

    /**
     * 查询-从内存中
     *
     * @return
     */
    @Override
    public Map<String, String> getInfoFromCache() {
        return configCache.getConfig();
    }

    /**
     * 修改
     *
     * @param map
     */
    @Override
    public void editInfo(Map<String, String> map) {
        int rows = 0;
        for (String key : map.keySet()) {
            LambdaUpdateWrapper<SystemConfig> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(SystemConfig::getConfigKey, key);
            updateWrapper.set(SystemConfig::getConfigValue, map.get(key));
            rows = rows + systemConfigMapper.update(new SystemConfig(), updateWrapper);

        }
        if (rows < 1) {
            throw new MyException("修改失败");
        }
        initCache();
    }

    /**
     * 刷新缓存
     */
    @Override
    public void initCache() {
        configCache.setConfig(getInfo());
    }
}
