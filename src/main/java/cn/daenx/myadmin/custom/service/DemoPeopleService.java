package cn.daenx.myadmin.custom.service;

import cn.daenx.myadmin.custom.domain.po.DemoPeople;
import cn.daenx.myadmin.custom.domain.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface DemoPeopleService extends IService<DemoPeople>{

    /**
     * 分页列表
     *
     * @param vo
     * @return
     */
    IPage<DemoPeople> getPage(DemoPeoplePageVo vo);

    /**
     * 全部数据
     *
     * @param vo
     * @return
     */
    List<DemoPeople> getAll(DemoPeoplePageVo vo);

    /**
     * 检查是否存在，已存在返回true
     *
     * @param key
     * @param value
     * @param nowId 排除ID
     * @return
     */
    Boolean checkKeyExist(String key, String value, String nowId);

    /**
     * 新增
     *
     * @param vo
     */
    DemoPeople addInfo(DemoPeopleAddVo vo);

    /**
     * 批量新增
     *
     * @param vos
     * @return
     */
    Integer addInfoBatch(List<DemoPeopleAddVo> vos);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    DemoPeople getInfo(String id);

    /**
     * 修改
     *
     * @param vo
     */
    void editInfo(DemoPeopleUpdVo vo);

    /**
     * 删除
     *
     * @param ids
     */
    void deleteByIds(List<String> ids);

    /**
     * 全部删除
     */
    void deleteAll();

    /**
     * 呼叫测试
     *
     * @param id
     */
    void testCall(String id);

    /**
     * 复制人员信息
     *
     * @param vo
     * @return
     */
    Map<String, Object> copyInfo(DemoPeopleCopyInfoVo vo);

    /**
     * 导入
     *
     * @param list
     */
    void importData(List<DemoPeopleXlsVo> list);
}
