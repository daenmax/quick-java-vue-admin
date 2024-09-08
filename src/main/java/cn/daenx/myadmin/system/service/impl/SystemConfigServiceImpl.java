package cn.daenx.myadmin.system.service.impl;

import cn.daenx.myadmin.common.exception.MyException;
import cn.daenx.myadmin.system.cache.ConfigCache;
import cn.daenx.myadmin.system.domain.po.SystemConfig;
import cn.daenx.myadmin.system.domain.vo.SystemConfigUpdVo;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.daenx.myadmin.system.mapper.SystemConfigMapper;
import cn.daenx.myadmin.system.service.SystemConfigService;


@Service
@Slf4j
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {
    @Resource
    private SystemConfigMapper systemConfigMapper;
    @Resource
    private ConfigCache configCache;
    @Value("${server.port}")
    private Integer port;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 邮箱配置-查询
     *
     * @return
     */
    @Override
    public SystemConfig getInfo() {
        SystemConfig systemConfig = systemConfigMapper.selectById("1");
        if (systemConfig == null) {
            systemConfig = new SystemConfig();
            systemConfig.setId("1");
            systemConfig.setManagerApiLock("0");
            String backApiUrl = "http://127.0.0.1:" + port;
            if (ObjectUtil.isNotEmpty(contextPath)) {
                backApiUrl = backApiUrl + contextPath;
            }
            systemConfig.setBackApiUrl(backApiUrl);
            systemConfigMapper.insert(systemConfig);
        }
        return systemConfig;
    }

    /**
     * 邮箱配置-查询-从内存中
     *
     * @return
     */
    @Override
    public SystemConfig getInfoFromCache() {
        return configCache.getConfig();
    }

    /**
     * 邮箱配置-修改
     *
     * @param vo
     */
    @Override
    public void editInfo(SystemConfigUpdVo vo) {
        LambdaUpdateWrapper<SystemConfig> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemConfig::getId, vo.getId());
        updateWrapper.set(SystemConfig::getManagerApiLock, vo.getManagerApiLock());
        updateWrapper.set(SystemConfig::getManagerApiKey, vo.getManagerApiKey());
        updateWrapper.set(SystemConfig::getBackApiUrl, vo.getBackApiUrl());
        int rows = systemConfigMapper.update(new SystemConfig(), updateWrapper);
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
        SystemConfig config = getInfo();
        configCache.setConfig(config);
    }
}
