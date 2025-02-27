package cn.daenx.myadmin.framework.common.data;

import cn.daenx.myadmin.framework.common.constant.DictClassConstant;
import cn.daenx.myadmin.framework.common.vo.DictDetailVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DictData {
    private static Map<String, List<DictDetailVo>> data;

    /**
     * 根据字典编码查询字典详细信息
     *
     * @param dictCode
     * @return
     */
    public static List<DictDetailVo> getDetailListByCode(String dictCode) {
        return data.get(dictCode);
    }

    /**
     * 根据字典编码和值查询字典指定详细信息
     *
     * @param dictCode
     * @param value
     * @return
     */
    public static DictDetailVo getDetailByCodeAndValue(String dictCode, String value) {
        List<DictDetailVo> list = data.get(dictCode);
        for (DictDetailVo item : list) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据字典编码和标签查询字典指定详细信息
     *
     * @param dictCode
     * @param label
     * @return
     */
    public DictDetailVo getDetailByCodeAndLabel(String dictCode, String label) {
        List<DictDetailVo> list = data.get(dictCode);
        for (DictDetailVo item : list) {
            if (item.getLabel().equals(label)) {
                return item;
            }
        }
        return null;
    }

    public DictData() {
        data = new java.util.HashMap<>();
        String dictCode;
        List<DictDetailVo> list;

        //通用是否：0=否，1=是
        dictCode = "dict_common_yes_no";
        list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "否", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "是", "1", DictClassConstant.PRIMARY, "1"));
        data.put(dictCode, list);

        //状态，0=正常，1=禁用
        dictCode = "dict_common_status";
        list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "正常", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "禁用", "1", DictClassConstant.DANGER, "1"));
        data.put(dictCode, list);

        //开关，0=开启，1=关闭
        dictCode = "dict_common_lock";
        list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "开启", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "关闭", "1", DictClassConstant.DANGER, "1"));
        data.put(dictCode, list);

        //需要， 0=需要, 1=不需要
        dictCode = "dict_common_need";
        list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "需要", "0", DictClassConstant.SUCCESS, "1"));
        list.add(new DictDetailVo(dictCode, "不需要", "1", DictClassConstant.DANGER, "1"));
        data.put(dictCode, list);

        //性别：1=男，0=女
        dictCode = "dict_demo_people_sex";
        list = new ArrayList<>();
        list.add(new DictDetailVo(dictCode, "女", "0", DictClassConstant.PRIMARY, "1"));
        list.add(new DictDetailVo(dictCode, "男", "1", DictClassConstant.SUCCESS, "1"));
        data.put(dictCode, list);
    }


}
