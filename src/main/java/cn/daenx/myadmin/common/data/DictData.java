package cn.daenx.myadmin.common.data;

import cn.daenx.myadmin.common.constant.DictClassConstant;
import cn.daenx.myadmin.common.vo.DictDetailVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DictData {
    private Map<String, List<DictDetailVo>> data;

    public List<DictDetailVo> getData(String dictCode) {
        return this.data.get(dictCode);
    }

    public DictData() {
        data = new java.util.HashMap<>();
        CommonYesNo();
        CommonStatus();
        CommonLock();
        CommonNeed();
        DemoPeopleSex();
    }

    public void CommonYesNo() {
        //通用是否：0=否，1=是
        String dictCode = "dict_common_yes_no";
        List<DictDetailVo> list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "否", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "是", "1", DictClassConstant.PRIMARY, "1"));
        this.data.put(dictCode, list);
    }
    public void CommonStatus() {
        //状态，0=正常，1=禁用
        String dictCode = "dict_common_status";
        List<DictDetailVo> list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "正常", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "禁用", "1", DictClassConstant.DANGER, "1"));
        this.data.put(dictCode, list);
    }
    public void CommonLock() {
        //开关，0=开启，1=关闭
        String dictCode = "dict_common_lock";
        List<DictDetailVo> list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "开启", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "关闭", "1", DictClassConstant.DANGER, "1"));
        this.data.put(dictCode, list);
    }

    public void CommonNeed() {
        //需要， 0=需要, 1=不需要
        String dictCode = "dict_common_need";
        List<DictDetailVo> list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "需要", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "不需要", "1", DictClassConstant.DANGER, "1"));
        this.data.put(dictCode, list);
    }
    public void DemoPeopleSex() {
        //性别：1=男，0=女
        String dictCode = "dict_demo_people_sex";
        List<DictDetailVo> list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "女", "0", DictClassConstant.PRIMARY, "1"));
        list.add(new DictDetailVo(dictCode, "男", "1", DictClassConstant.SUCCESS, "1"));
        this.data.put(dictCode, list);
    }



}
