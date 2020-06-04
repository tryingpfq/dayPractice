package com.tryingpfq.spi.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2020/4/17
 **/
public class EasyExcelUtils {

    private static void export(List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz) {
        ExcelName excelName = clazz.getAnnotation(ExcelName.class);
        String fileName = clazz.getSimpleName();
        String sheetName = "";
        if (excelName != null) {
            if(!StringUtils.isEmpty(excelName.fileName())){
                fileName = excelName.fileName();
            }
            if (!StringUtils.isEmpty(excelName.sheetName())) {
                sheetName = excelName.sheetName();
            }
        }
        fileName += ".xlsx";
        EasyExcel.write(fileName).sheet(sheetName).doWrite(data);

    }

    public static void main(String[] args) {
        List<RowModelDemo> data = new ArrayList<>();
        RowModelDemo demo1 = new RowModelDemo.RowBuilder().setId(1).setName("aa").setTime("2020").builder();
        RowModelDemo demo2 = new RowModelDemo.RowBuilder().setId(2).setName("bb").setTime("2021").builder();
        data.add(demo1);
        data.add(demo2);

        EasyExcelUtils.export(data, RowModelDemo.class);
    }
}
