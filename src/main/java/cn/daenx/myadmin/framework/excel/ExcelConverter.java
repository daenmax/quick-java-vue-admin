package cn.daenx.myadmin.framework.excel;

import cn.daenx.myadmin.framework.common.data.DictData;
import cn.daenx.myadmin.framework.common.utils.MyUtil;
import cn.daenx.myadmin.framework.common.vo.DictDetailVo;
import cn.daenx.myadmin.framework.dictMasked.annotation.Dict;
import cn.daenx.myadmin.framework.dictMasked.annotation.DictDetail;
import cn.daenx.myadmin.framework.dictMasked.annotation.Masked;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * easyexcel导出导入字典和脱敏转换
 */
@Slf4j
public class ExcelConverter implements Converter<Object> {


    @Override
    public Class<?> supportJavaTypeKey() {
        return Converter.super.supportJavaTypeKey();
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return Converter.super.supportExcelTypeKey();
    }

    /**
     * 导入时
     *
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public Object convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Dict dict = contentProperty.getField().getAnnotation(Dict.class);
        if (dict != null) {
            String label = cellData.getStringValue();
            String value = transDictToValue(dict, label);
            if (StringUtils.isNotBlank(value)) {
                return Convert.convert(contentProperty.getField().getType(), value);
            }
        }
        return Converter.super.convertToJavaData(cellData, contentProperty, globalConfiguration);
    }

    /**
     * 导出时
     *
     * @param value
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public WriteCellData<?> convertToExcelData(Object value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (ObjectUtil.isNull(value)) {
            return new WriteCellData<>("");
        }
        String valueStr = Convert.toStr(value);
        Dict dict = contentProperty.getField().getAnnotation(Dict.class);
        if (dict != null) {
            valueStr = transDictToLabel(dict, valueStr);
        }
        Masked masked = contentProperty.getField().getAnnotation(Masked.class);
        if (masked != null) {
            valueStr = MyUtil.masked(masked.type().getType(), valueStr);
        }
        if (StringUtils.isNotBlank(valueStr)) {
            return new WriteCellData<>(valueStr);
        }
        return Converter.super.convertToExcelData(value, contentProperty, globalConfiguration);
    }

    /**
     * value转换到label
     *
     * @param annotation
     * @param value
     * @return
     */
    private String transDictToLabel(Dict annotation, String value) {
        if (StringUtils.isNotBlank(annotation.dictCode())) {
            //根据系统字典翻译
            List<DictDetailVo> list = DictData.getDetailListByCode(annotation.dictCode());
            for (DictDetailVo detailVo : list) {
                if (detailVo.getValue().equals(value)) {
                    return detailVo.getLabel();
                }
            }
        } else {
            //根据自定义字典翻译
            DictDetail[] custom = annotation.custom();
            for (DictDetail dictDetail : custom) {
                if (dictDetail.value().equals(value)) {
                    return dictDetail.label();
                }
            }
        }
        return "";
    }

    /**
     * label转换到value
     *
     * @param annotation
     * @param label
     * @return
     */
    private String transDictToValue(Dict annotation, String label) {
        if (StringUtils.isNotBlank(annotation.dictCode())) {
            //根据系统字典翻译
            List<DictDetailVo> list = DictData.getDetailListByCode(annotation.dictCode());
            for (DictDetailVo detailVo : list) {
                if (detailVo.getLabel().equals(label)) {
                    return detailVo.getValue();
                }
            }
        } else {
            //根据自定义字典翻译
            DictDetail[] custom = annotation.custom();
            for (DictDetail dictDetail : custom) {
                if (dictDetail.label().equals(label)) {
                    return dictDetail.value();
                }
            }
        }
        return "";
    }

}
