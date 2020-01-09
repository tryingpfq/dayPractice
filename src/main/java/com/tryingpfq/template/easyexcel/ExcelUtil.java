package com.tryingpfq.template.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class ExcelUtil {

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    public static List<Object> readLineLessThan1000Row(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }
        sheet = sheet == null ? initSheet : sheet;
        List<Object> result = null;
        try (InputStream inputStream = new FileInputStream(filePath)) {
            result = EasyExcelFactory.read(inputStream, sheet);
        } catch (Exception e) {

        }
        return result;
    }
}
