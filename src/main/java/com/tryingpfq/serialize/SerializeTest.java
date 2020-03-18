package com.tryingpfq.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2020/3/17
 **/
public class SerializeTest {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
//        String fastJson = "{\"id\":1,\"map\":{1111:\"abc\"},\"name\":\"aaa\"}";
//        String gsonStr = "{\"id\":1,\"firstName\":\"aaa\",\"map\":{\"1111\":\"abc\"}}";
//
//        Info info = JSON.parseObject(fastJson, Info.class);
//        System.err.println(info.toString());
//        info = gson.fromJson(gsonStr, Info.class);
//        System.err.println(info.toString());
//        gson.toJson()

        Map<String,String> map = new HashMap<>();
        map.put("abc", "abc");
        //System.err.println(gson.toJson(map.entrySet()));
        String s = JSON.toJSONString(map.entrySet());
        System.err.println(s);
    }


}
