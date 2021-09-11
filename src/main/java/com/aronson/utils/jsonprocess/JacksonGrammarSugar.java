package com.aronson.utils.jsonprocess;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class JacksonGrammarSugar {
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        // 属性为null的不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 序列化枚举以ordinal()来输出，默认false
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
        // 忽略无法转换的对象，防止抛异常
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化忽略未知属性，防止抛JsonMappingException异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public static void main(String[] args) throws JsonProcessingException, IOException {
//        Order order = new Order();
//        order.setOrderId("20200917171325236001222");
//        order.setStatus(0);
//        Goods goods1 = new Goods();
//        goods1.setGoodsName("空调");
//        goods1.setGoodsNum(2);
//        goods1.setGoodsAmount(new BigDecimal("1998.86"));
//        Goods goods2 = new Goods();
//        goods2.setGoodsName("冰箱");
//        goods2.setGoodsNum(2);
//        goods2.setGoodsAmount(new BigDecimal("2569.86"));
//        List<Goods> list = new ArrayList<Goods>();
//        list.add(goods1);
//        list.add(goods2);
//        order.setGoodsList(list);
//        // 将对象序列化成字符串
//        String s = mapper.writeValueAsString(order);
//        System.out.println(s);
//        // 获取json中的节点
//        JsonNode tree = mapper.readTree(s);
//        String orderId = tree.get("orderId").asText();
//        System.out.println(orderId);
//        // jsonnode取list中下标为0的数据项
//        JsonNode b = tree.get("goodsList").get(0);
//        Iterator<JsonNode> iterator = tree.get("goodsList").iterator();
//        while (iterator.hasNext()){
//            Goods goods = mapper.treeToValue(iterator.next(),Goods.class);
//            System.out.println("=========读取json中列表元素："+goods);
//        }
//
//        // jsonnode转对象
//        Goods goods = mapper.treeToValue(b, Goods.class);
//        System.out.println(goods.toString());
//        // 对象转jsonnode
////        JsonNode c = mapper.convertValue(goods, JsonNode.class);
////        System.out.println(c.toString());
////        System.out.println(c.get("goodsName").asText());
//        // 字符串反序列化成对象
//        order = mapper.readValue(s, Order.class);
//        System.out.println(order.toString());
//        // 字符串反序列化带泛型对象
//        JsonNode listNode = tree.get("goodsList");
//        List<Goods> goodsList = mapper.readValue(listNode.toString(), new TypeReference<List<Goods>>() {});
//        System.out.println(goodsList.get(1).toString());

        String ss = "{\"retCode\":\"0000\",\"retMsg\":\"success\",\"data\":{\"total\":7,\"guid\":\"05a541a1333641529c1ea625f72b9c17\",\"list\":[{\"questionId\":5922,\"channel\":\"APEX_SS\",\"questionName\":\"无可推送问题\",\"questionAnswer\":\"无可推送问题\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"D类(渠道调查信息)\"},{\"questionId\":630,\"channel\":\"APEX_SS\",\"questionName\":\"您是什么时候入职的？\",\"questionAnswer\":\"根据回答情况自行判断\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"A类(工作调查信息)\"},{\"questionId\":2184,\"channel\":\"APEX_SS\",\"questionName\":\"司法考试时间是什么时候\",\"questionAnswer\":\"9月\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"A类(风险调查信息)\"},{\"questionId\":6832,\"channel\":\"APEX_SS\",\"questionName\":\"热映的电影有哪些？\",\"questionAnswer\":\"根据回答情况自行判断\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"B类(工作调查信息)\"},{\"questionId\":588,\"channel\":\"APEX_SS\",\"questionName\":\"无可推送问题\",\"questionAnswer\":\"无可推送问题\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"B类(工作调查信息)\"},{\"questionId\":7637,\"channel\":\"APEX_SS\",\"questionName\":\"我所谓了吧\",\"questionAnswer\":\"很好啊那样\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"A类(渠道调查信息)\"},{\"questionId\":4025,\"channel\":\"APEX_SS\",\"questionName\":\"无可推送问题\",\"questionAnswer\":\"无可推送问题\",\"answerType\":\"W\",\"verifyMethod\":\"S\",\"questionType\":\"B类(风险调查信息)\"}]}}";

        JsonNode jsonNode = JsonUtils.getBasicMapper().readTree(ss);

        JsonNode jsonNode1 = jsonNode.get("data");
        for(int i = 0;i<jsonNode1.size();i++){
            // 这里会出现空指针异常
            System.out.println(jsonNode1.get(i).toString());
        }


//        // 迭代方式
        Iterator<JsonNode> iterator = jsonNode1.iterator();
        while (iterator.hasNext()){
            JsonNode jsonNode2 = iterator.next();
//            QuestionBean questionBean = JsonUtils.getBasicMapper().treeToValue(jsonNode2,QuestionBean.class);
            System.out.println(jsonNode2.toString());
        }
    }


}


@Data
class Order {
    private String orderId;
    private Integer status;
    private List<Goods> goodsList;
}

@Data
class Goods {
    private String goodsName;
    private Integer goodsNum;
    private BigDecimal goodsAmount;
}

@Data
class QuestionBean {
    private String traceid;
    private String questionId;
    private String channel;
    private String questionName;
    private String questionAnswer;
    private String answerType;
    private String verifyMethod;
    private String questionType;
    private String questionKind;
    private String riskType;
}
