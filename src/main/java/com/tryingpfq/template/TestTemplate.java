package com.tryingpfq.template;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.scene.layout.BorderImage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public class TestTemplate {

    private static final String FILE_NAME = "template";

    public static final Class clazz = Test.class;

    @org.junit.Test
    public void doTest(){
        String path = getBasePath();
        prepEnviroment();
        doGenerate(path);
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

    class Test{
        private int id;

        private String ads;
    }
}
