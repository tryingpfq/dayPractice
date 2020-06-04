package com.tryingpfq.spi.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author tryingpfq
 * @date 2020/4/17
 **/
@ExcelName(fileName = "",sheetName = "sheet")
public class RowModelDemo extends BaseRowModel {

    @ExcelProperty(value = {"索引"}, index = 0)
    private int id;

    @ExcelProperty(value = {"名称"}, index = 1)
    private String name;

    @ExcelProperty(value = {"时间"}, index = 2)
   // @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class RowBuilder{
        private int id;

        private String name;

        private String time;

        public RowBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public RowBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RowBuilder setTime(String time) {
            this.time = time;
            return this;
        }

        public RowModelDemo builder(){
            RowModelDemo demo = new RowModelDemo();
            demo.setId(id);
            demo.setName(name);
            demo.setTime(time);
            return demo;
        }
    }
}
