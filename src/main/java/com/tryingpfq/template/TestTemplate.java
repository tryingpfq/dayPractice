package com.tryingpfq.template;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestTemplate {

    private static final String FILE_NAME = "template";

    private static final String EXCEL_XLSX = ".xlsx";

    public static final Class clazz = Test.class;

    @org.junit.Test
    public void doVmTest(){
        String path = getBasePath();
        prepEnviroment();
        doGenerate(path);
    }

    @org.junit.Test
    public void doExcelTest(){
        Map<Integer,  List<Object>> dataMap = getWriteData(clazz);
        doWriteExelData(dataMap);
    }

    private static void doWriteExelData(Map<Integer, List<Object>> data) {
        ExeFileDes exeFileDesAnno = (ExeFileDes) clazz.getAnnotation(ExeFileDes.class);

        String exelName = exeFileDesAnno.des();
        String path = System.getProperties().getProperty("user.dir") + "/" + FILE_NAME + "/" + exelName + EXCEL_XLSX;

        try(OutputStream outputStream = new FileOutputStream(path)){
            //创建一个工作蒲
            Workbook workbook = new HSSFWorkbook();
            //创建一个sheet页
            HSSFSheet sheet = (HSSFSheet) workbook.createSheet(clazz.getSimpleName());

            HSSFCreationHelper creationHelper = (HSSFCreationHelper) workbook.getCreationHelper();

            HSSFFont font = (HSSFFont) workbook.createFont();

            font.setFontHeightInPoints((short) 24);
            font.setFontName("courier New");
            font.setStrikeout(true);

            for (Map.Entry<Integer, List<Object>> entry : data.entrySet()) {
                HSSFRow row = sheet.createRow(entry.getKey());
                int index = 0;
                for (Object o : entry.getValue()) {
                    HSSFCell cell = row.createCell(index++);
                    cell.setCellValue(o.toString());
                }
            }

            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Map<Integer, List<Object>> getWriteData(Class clazz){
        Map<Integer,  List<Object>> data = Maps.newHashMapWithExpectedSize(2);

        List<Object> fileDes = Lists.newLinkedList();
        List<Object> fieldDef = Lists.newLinkedList();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExeFileDes.class)) {
                ExeFileDes des = field.getAnnotation(ExeFileDes.class);
                fileDes.add(des.des());
                fieldDef.add(field.getName());
            }
        }
        data.put(0, fileDes);
        data.put(1, fieldDef);
        return data;
    }


    public static String getBasePath(){
        String path = System.getProperty("user.dir");
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
//            Properties properties = new Properties();
//            properties.load(bufferedReader);
//            String secondPath = properties.getProperty("config");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return path;
    }

    private static void prepEnviroment() {
        Properties prop = new Properties();
        prop.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        prop.setProperty(Velocity.INPUT_ENCODING,"UTF-8");
        prop.setProperty(Velocity.OUTPUT_ENCODING,"UTF-8");
        Velocity.init(prop);
    }

    private static void doGenerate(String path) {
        Map<String,Object> params = Maps.newHashMap();
        LinkedList<Object> list = Lists.newLinkedList();
        for (Field field : clazz.getDeclaredFields()) {
            list.add(field);
        }
        params.put("list", list);

        String generathPath = path + "/" + FILE_NAME + "/" + clazz.getSimpleName() + ".mvl";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(generathPath))) {
            //将数据加载到模板引擎上下文
            VelocityContext context = new VelocityContext(params);
            Template template = Velocity.getTemplate("vm/test.vm");
            if (template == null) {
                throw new RuntimeException("vm/test.vm is null");
            }
            template.merge(context,writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static boolean fieldCheck(Field field) {
        return !Modifier.isFinal(field.getModifiers()) &&
                !Modifier.isStatic(field.getModifiers());
    }

    @ExeFileDes(des = "test")
    class Test{

        @ExeFileDes(des = "索引")
        private int id;

        @ExeFileDes(des = "地址")
        private String address;
    }
}
