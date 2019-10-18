package com.tryingpfq.template.easyexcel;

import com.alibaba.excel.metadata.Sheet;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class EasyExceUtil {

    @Test
    public void read(){
        String path = System.getProperty("user.dir") + "/template/test.xlsx";
        System.out.println(path);
        Sheet sheet = new Sheet(1, 1);
        List<Object> objects = ExcelUtil.readLineLessThan1000Row(path, sheet);
        System.out.println(objects.toString());
    }
}
