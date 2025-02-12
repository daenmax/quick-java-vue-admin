package cn.daenx.myadmin.framework.excel;

import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.Data;
@Data
public class ReadRetVo<T> {
    private ReadSheet sheet;

    private DefaultExcelListener<T> listener;
}
