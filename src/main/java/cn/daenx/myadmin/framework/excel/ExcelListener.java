package cn.daenx.myadmin.framework.excel;

import com.alibaba.excel.read.listener.ReadListener;

/**
 * Excel 导入监听
 *
 * @author DaenMax
 */
public interface ExcelListener<T> extends ReadListener<T> {

    ExcelResult<T> getExcelResult();

}
