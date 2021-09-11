package com.aronson.utils.dynamicgeneratefield;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class DynamicGenerateFieldTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Daisy");
        System.out.println("User：" + JSON.toJSONString(user));
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("age", 18);

        Object obj = ReflectUtil.getObject(user, propertiesMap);
        System.out.println("动态为User添加age之后，User：" + JSON.toJSONString(obj));

        propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("interest", "basketball");
        obj = ReflectUtil.getObject(obj, propertiesMap);
        System.out.println("动态为User添加interest之后，User：" + JSON.toJSONString(obj));

    }
}
