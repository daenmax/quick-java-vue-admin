package cn.daenx.myadmin.modules.demoPeople.service.impl;

import cn.daenx.myadmin.framework.common.exception.MyException;
import cn.daenx.myadmin.framework.common.utils.MyUtil;
import cn.daenx.myadmin.framework.common.vo.ComStatusUpdVo;
import cn.daenx.myadmin.modules.demoPeople.domain.vo.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.daenx.myadmin.modules.demoPeople.mapper.DemoPeopleMapper;
import cn.daenx.myadmin.modules.demoPeople.domain.po.DemoPeople;
import cn.daenx.myadmin.modules.demoPeople.service.DemoPeopleService;

@Service
public class DemoPeopleServiceImpl extends ServiceImpl<DemoPeopleMapper, DemoPeople> implements DemoPeopleService {
    @Resource
    private DemoPeopleMapper demoPeopleMapper;


    /**
     * 检查是否存在，已存在返回true
     *
     * @param key
     * @param value
     * @param nowId 排除ID
     * @return
     */
    @Override
    public Boolean checkKeyExist(String key, String value, String nowId) {
        QueryWrapper<DemoPeople> wrapper = new QueryWrapper<>();
        wrapper.eq(key, value);
        wrapper.ne(ObjectUtil.isNotEmpty(nowId), "id", nowId);
        return demoPeopleMapper.exists(wrapper);
    }

    private LambdaQueryWrapper<DemoPeople> getWrapper(DemoPeoplePageVo vo) {
        LambdaQueryWrapper<DemoPeople> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(vo.getRemark()), DemoPeople::getRemark, vo.getRemark());
        wrapper.like(ObjectUtil.isNotEmpty(vo.getName()), DemoPeople::getName, vo.getName());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getAge()), DemoPeople::getAge, vo.getAge());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getSex()), DemoPeople::getSex, vo.getSex());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getStatus()), DemoPeople::getStatus, vo.getStatus());
        String startTime = vo.getStartTime();
        String endTime = vo.getEndTime();
        wrapper.between(ObjectUtil.isNotEmpty(startTime) && ObjectUtil.isNotEmpty(endTime), DemoPeople::getCreateTime, startTime, endTime);
        return wrapper;
    }

    /**
     * 分页列表
     *
     * @param vo
     * @return
     */
    @Override
    public IPage<DemoPeople> getPage(DemoPeoplePageVo vo) {
        LambdaQueryWrapper<DemoPeople> wrapper = getWrapper(vo);
        Page<DemoPeople> page = demoPeopleMapper.selectPage(vo.getPage(true), wrapper);
        return page;
    }

    /**
     * 导出
     *
     * @param vo
     * @return
     */
    @Override
    public List<DemoPeopleExportDto> exportData(DemoPeoplePageVo vo) {
        LambdaQueryWrapper<DemoPeople> wrapper = getWrapper(vo);
        List<DemoPeople> list = demoPeopleMapper.selectList(wrapper);
        List<DemoPeopleExportDto> realList = BeanUtil.copyToList(list, DemoPeopleExportDto.class);
        return realList;
    }

    /**
     * 导入
     *
     * @param list
     */
    @Override
    public Integer importData(List<DemoPeopleImportVo> list) {
        List<DemoPeopleAddVo> listReal = new ArrayList<>();
        for (DemoPeopleImportVo vo : list) {
            DemoPeopleAddVo userAddVo = BeanUtil.copyProperties(vo, DemoPeopleAddVo.class);
            listReal.add(userAddVo);
        }
        if (!listReal.isEmpty()) {
            return addInfoBatch(listReal);
        }
        return 0;
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Override
    public DemoPeople getInfo(String id) {
        return demoPeopleMapper.selectById(id);
    }

    /**
     * 新增
     *
     * @param vo
     */
    @Override
    public DemoPeople addInfo(DemoPeopleAddVo vo) {
        Boolean exist = checkKeyExist("name", vo.getName(), null);
        if (exist) {
            throw new MyException("姓名已存在");
        }
        DemoPeople po = new DemoPeople();
        po.setRemark(vo.getRemark());
        po.setName(vo.getName());
        po.setAge(vo.getAge());
        po.setSex(vo.getSex());
        po.setExpireTime(vo.getExpireTime());
        po.setStatus(vo.getStatus());
        if (demoPeopleMapper.insert(po) < 1) {
            throw new MyException("新增失败");
        }
        return po;
    }

    /**
     * 批量新增
     *
     * @param vos
     * @return
     */
    @Override
    public Integer addInfoBatch(List<DemoPeopleAddVo> vos) {
        List<DemoPeople> realList = new ArrayList<>();
        List<String> fields = vos.stream().map(DemoPeopleAddVo::getName).collect(Collectors.toList());
        LambdaQueryWrapper<DemoPeople> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(DemoPeople::getName, fields);
        List<DemoPeople> existList = demoPeopleMapper.selectList(wrapper);
        List<String> existFields = existList.stream().map(DemoPeople::getName).collect(Collectors.toList());
        for (DemoPeopleAddVo vo : vos) {
            if (!existFields.contains(vo.getName())) {
                DemoPeople po = new DemoPeople();
                po.setRemark(vo.getRemark());
                po.setName(vo.getName());
                po.setAge(vo.getAge());
                po.setSex(vo.getSex());
                po.setExpireTime(vo.getExpireTime());
                po.setStatus(vo.getStatus());
                realList.add(po);
            }
        }
        saveBatch(realList);
        return realList.size();
    }

    /**
     * 修改
     *
     * @param vo
     */
    @Override
    public void editInfo(DemoPeopleUpdVo vo) {
        if (checkKeyExist("name", vo.getName(), vo.getId())) {
            throw new MyException("姓名已存在");
        }
        LambdaUpdateWrapper<DemoPeople> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DemoPeople::getId, vo.getId());
        updateWrapper.set(DemoPeople::getRemark, vo.getRemark());
        updateWrapper.set(DemoPeople::getName, vo.getName());
        updateWrapper.set(DemoPeople::getAge, vo.getAge());
        updateWrapper.set(DemoPeople::getSex, vo.getSex());
        updateWrapper.set(DemoPeople::getExpireTime, vo.getExpireTime());
        updateWrapper.set(DemoPeople::getStatus, vo.getStatus());
        int rows = demoPeopleMapper.update(new DemoPeople(), updateWrapper);
        if (rows < 1) {
            throw new MyException("修改失败");
        }
    }

    /**
     * 删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<String> ids) {
        int i = demoPeopleMapper.deleteBatchIds(ids);
        if (i < 1) {
            throw new MyException("删除失败");
        }
    }

    /**
     * 全部删除
     */
    @Override
    public void delAll() {
        demoPeopleMapper.delete(new LambdaQueryWrapper<>());
    }

    /**
     * 修改状态
     *
     * @param vo
     */
    @Override
    public void changeStatus(ComStatusUpdVo vo) {
        LambdaUpdateWrapper<DemoPeople> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DemoPeople::getId, vo.getId());
        updateWrapper.set(DemoPeople::getStatus, vo.getStatus());
        int rows = demoPeopleMapper.update(new DemoPeople(), updateWrapper);
        if (rows < 1) {
            throw new MyException("修改失败");
        }
    }

    /**
     * 呼叫测试
     *
     * @param id
     */
    @Override
    public void testCall(String id) {

    }

    /**
     * 复制人员信息
     *
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> copyInfo(DemoPeopleCopyInfoVo vo) {
        if (ObjectUtil.isEmpty(vo.getFormat())) {
            vo.setFormat("【信息】");
        }
        List<Map<String, String>> copyList = new ArrayList<>();
        String copyStr = "";
        for (String id : vo.getIds()) {
            Map<String, String> map = new HashMap<>();
            DemoPeople po = demoPeopleMapper.selectById(id);
            String info = "姓名：" + ("1".equals(vo.getHideName()) ? MyUtil.masked(0, po.getName()) : po.getName()) + "，年龄：" + po.getAge() + "，性别：" + po.getSex() + "，有效期：" + po.getExpireTime();
            map.put("id", po.getId());
            map.put("name", po.getName());
            map.put("age", po.getAge());
            map.put("sex", po.getSex());
            map.put("info", info);
            copyList.add(map);
        }
        for (Map<String, String> map : copyList) {
            //【姓名】、【年龄】、【性别】、【信息】
            String line = vo.getFormat();
            line = line.replaceAll("【姓名】", map.get("name"));
            line = line.replaceAll("【年龄】", map.get("age"));
            line = line.replaceAll("【性别】", map.get("sex"));
            line = line.replaceAll("【信息】", map.get("info"));
            copyStr = copyStr + line + "\n";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("copyList", copyList);
        map.put("copyStr", copyStr);
        return map;
    }
}
