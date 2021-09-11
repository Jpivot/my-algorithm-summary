package com.aronson.utils.jsonprocess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Sherlock
 */
public class ReadFileToJsonObject {
    private final static String PATH_NAME = "E:\\prod_account_info.json";

    public static void main(String[] args) throws IOException {
        // 带where prod_account_info.json
        File file = new File(PATH_NAME);

        // 把整个文件都读成一个字符串
        String string = FileUtils.readFileToString(file, "UTF-8");
        JSONObject jsonObject = JSON.parseObject(string);
        JSONObject parameter = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("reader").getJSONObject("parameter");
        String where = parameter.getString("where");
        String querySql = parameter.getJSONArray("connection").getJSONObject(0).getJSONArray("querySql").getString(0);
        String querySqlWhere = "";
        if (querySql.contains(" where ")) {
            querySqlWhere = querySql.replaceAll(".* where ", "").replaceAll(";", "");
        } else {
            querySqlWhere = where;
        }
        System.out.println("where:" + where);
        System.out.println("querySql:" + querySql);
        System.out.println("querySqlWhere:" + querySqlWhere);
    }
}
