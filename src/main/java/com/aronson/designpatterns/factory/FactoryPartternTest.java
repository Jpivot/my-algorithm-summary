package com.aronson.designpatterns.factory;

/**
 * @author T_zhangchaowu_kzx
 * 工厂模式测试类
 */
public class FactoryPartternTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Good good = FactoryProduce.produceGoods(LifeGood.class);
        good.produce();

        good = FactoryProduce.produceGoods(HouseGood.class);
        good.produce();
    }
}
