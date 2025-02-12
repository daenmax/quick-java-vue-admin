package cn.daenx.myadmin.modules.demoPeople.service;

import cn.daenx.myadmin.framework.common.vo.ComStatusUpdVo;
import cn.daenx.myadmin.modules.demoPeople.domain.po.DemoPeople;
import cn.daenx.myadmin.modules.demoPeople.domain.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface DemoPeopleService extends IService<DemoPeople> {

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
     * 分页列表
     *
     * @param vo
     * @return
     */
    IPage<DemoPeople> getPage(DemoPeoplePageVo vo);

    /**
     * 导出
     *
     * @param vo
     * @return
     */
    List<DemoPeopleExportDto> exportData(DemoPeoplePageVo vo);

    /**
     * 导入
     *
     * @param list
     */
    Integer importData(List<DemoPeopleImportVo> list);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    DemoPeople getInfo(String id);

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
    void delAll();

    /**
     * 修改状态
     *
     * @param vo
     */
    void changeStatus(ComStatusUpdVo vo);

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
}
