package com.tryingpfq.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.JSONToken;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.io.*;
import java.util.Map;

public class TestSortJson {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/template/153.txt";


        File file = new File(path);

        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            String line = null;
           while((line = reader.readLine()) != null){
               stringBuffer.append(line);
           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonmsg = stringBuffer.toString();

        String tmp = StringEscapeUtils.unescapeJavaScript(jsonmsg);
        Map<String,Map<String,String>> firstMap = JSON.parseObject(tmp,Map.class);

        Map<String,Map<String,String>> map = Maps.newTreeMap();
        for (Map.Entry<String, Map<String, String>> entry : firstMap.entrySet()) {
            Map<String,String> treeMap = Maps.newTreeMap();
            for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                treeMap.put(entry1.getKey(), entry1.getValue());
            }
            map.put(entry.getKey(), treeMap);
        }

        String result = JSON.toJSONString(map);

        try (FileWriter fileWriter = new FileWriter("input153.txt")){
            fileWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
