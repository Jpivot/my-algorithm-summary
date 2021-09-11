package com.aronson.divideconquer;

import java.util.LinkedList;
import java.util.List;

/**
 * 对应leetcode 241题
 * 分治算法：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247488970&idx=1&sn=d4eb6a371f1706d76e370be18b27afb4&chksm=9bd7ebc2aca062d4e9d62bb363a1e386cc5b42224e63c505f902275c48f03fd8f8289b717fb2&scene=21#wechat_redirect
 */
public class DiffWaysToComputeTest {
    public static void main(String[] args) {
        String input = "2*3-4*5";
        System.out.println(diffWaysToCompute(input).toString());

    }

    static List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 扫描算式 input 中的运算符
            if (c == '-' || c == '*' || c == '+') {
                /****** 分 ******/
                // 以运算符为中心，分割成两个字符串，分别递归计算
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                /****** 治 ******/
                // 通过子问题的结果，合成原问题的结果
                for (int a : left) {
                    for (int b : right) {
                        if (c == '+') {
                            res.add(a + b);
                        } else if (c == '-') {
                            res.add(a - b);
                        } else {
                            res.add(a * b);
                        }
                    }
                }
            }
        }
        // base case，这里是重点，
        // 如果 res 为空，说明算式是一个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
}
