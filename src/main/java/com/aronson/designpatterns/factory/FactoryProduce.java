package com.aronson.designpatterns.factory;

public class FactoryProduce {
    private FactoryProduce(){

    }

    /**
     * 工厂模式产生实例
     * @param goodClass
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Good produceGoods(Class<? extends Good> goodClass) throws IllegalAccessException, InstantiationException {
        return goodClass.newInstance();
    }

}
