package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MainFastJSON {

    public static class Type {
        public List<String> enums = new ArrayList<>();
        public String typeBuilderName = "";
        public String type = "";
    }

    public static class Metadata {
        public String serviceType;
        public Map<String, Type> types = new HashMap<>();
    }

    public static void main(String[] args) {
        String jsonStr="{\n" +
                "    \"serviceType\":\"dubbo\",\n" +
                "    \"types\":[\n" +
                "        {\n" +
                "            \"enums\":[\n" +
                "            ],\n" +
                "            \"typeBuilderName\":\"DefaultTypeBuilder\",\n" +
                "            \"type\":\"int\",\n" +
                "            \"items\":[\n" +
                "            ],\n" +
                "            \"properties\":{\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println(JSONObject.parseObject(jsonStr));
        Metadata m = JSON.parseObject(JSONObject.parseObject(jsonStr).toJSONString(), Metadata.class);
        System.out.println("types size:" + m.types.size());
        System.out.println(JSON.toJSONString(m));
    }
}