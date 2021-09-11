package com.aronson.utils.mysqltohql;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sherlock
 */
public class ToHqlMain_tmp_table {

    private static Set<String> typeSet = new HashSet<String>();

    public static void main(String[] args) {

        try {

            // System.out.println(Long.MAX_VALUE);
            // System.out.println(Long.MAX_VALUE / 40000000);
            // Set<String> tableNames =
            // ToHql("E:\\abs_account_transaction_balance_info.sql",
            // "E:\\abs_account_transaction_balance_info.hql");
            Set<String> tableNames = ToHql("E:\\SQL\\act_proc_db.sql", "E:\\SQL\\tmp_" + "act_proc_db.hql");
            // Set<String> tableNames2 =
            // ToHql("F:\\sql\\auth_txn_db_dev_r4_1031.sql",
            // "F:\\sql\\auth_txn_db_dev_r4_1031.hql");

            // Set<String> tableNames =
            // ToHql("F:\\sql\\act_proc_db_dev_r3-3.sql",
            // "F:\\sql\\act_proc_db_dev_r3-3.hql");
            //
            // func(tableNames, "F:\\sql\\readme.txt");
            System.out.println(typeSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getTableName(String lineTemp) {
        String[] strings = lineTemp.split(" ");

        String tableName = null;
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            if (string.toUpperCase().equals("EXISTS")) {
                tableName = strings[i + 1];
            }
        }

        int len = tableName.length();
        if (tableName.charAt(len - 1) == ';') {
            tableName = tableName.substring(0, len - 1);
        }

        return tableName;
    }

    private static Set<String> ToHql(String sourceFile, String targetFile) throws Exception {

        File file = new File(targetFile);
        PrintStream printStream = new PrintStream(new FileOutputStream(file));

        String line = null;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        int drop = 0;
        int create = 0;
        Set<String> tableNames = new HashSet<String>();
        while ((line = reader.readLine()) != null) {

            String lineTemp = line.replace('`', ' ').trim().replaceAll("\\s{2,}|\t", " ").replaceAll(",\\s+", ",").replaceAll("\\s+=\\s+", "=");
            lineTemp = lineTemp.replace("CREATE TABLE ", "CREATE TABLE tmp_");

            if (lineTemp.toUpperCase().contains("DROP TABLE IF EXISTS")) {
                // StringBuilder stringBuilder = new StringBuilder(lineTemp);
                // stringBuilder.insert(4, " EXTERNAL");
                lineTemp = lineTemp.replace("DROP TABLE IF EXISTS ", "DROP TABLE IF EXISTS tmp_");
                stringBuffer.append(lineTemp).append("\r\n");
                String tableName = getTableName(lineTemp);
                if (!tableNames.contains(tableName)) {
                    tableNames.add(tableName);
                } else {
                    System.out.println("SQL建表语句重复：" + tableName);
                }
                drop++;

            } else if (lineTemp.toUpperCase().contains("CREATE TABLE")) {
                StringBuilder stringBuilder = new StringBuilder(lineTemp);
                stringBuilder.insert(6, " EXTERNAL");
                stringBuffer.append(stringBuilder).append("\r\n");
                create++;
            } else if (lineTemp.startsWith(")")) {

                // 删除前一条语句末尾的逗号
                if (stringBuffer.charAt(stringBuffer.length() - 3) == ',') {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 3);
                }
                stringBuffer.append(")\r\n");

                // 此时为表定义语句
                String tableComment = null;
                if (lineTemp.toUpperCase().contains("COMMENT")) {
                    // 此时存在tableComment
                    tableComment = getTableComment(lineTemp);

                    if (!StringUtils.isBlank(tableComment)) {
                        stringBuffer.append("COMMENT").append(tableComment).append("\r\n");
                    }
                }

                stringBuffer.append("PARTITIONED BY (biz_dt string, shard string)").append("\r\n");
                stringBuffer.append("ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\001'").append("\r\n");
                stringBuffer.append("LINES TERMINATED BY '\\n'").append("\r\n");
                stringBuffer.append("STORED AS ORC;").append("\r\n\r\n\r\n");

                printStream.append(stringBuffer.toString());
                stringBuffer = new StringBuffer();

            } else if (!lineTemp.startsWith("-") && !StringUtils.isBlank(lineTemp) && !lineTemp.toUpperCase().startsWith("PRIMARY KEY")
                    && !lineTemp.toUpperCase().startsWith("INDEX") && !lineTemp.toUpperCase().startsWith("UNIQUE")
                    && !lineTemp.toUpperCase().startsWith("KEY")) {
                // 此时为字段定义语句

                if (lineTemp.startsWith("PARTITIONS 9") || lineTemp.startsWith("(PARTITION") || lineTemp.startsWith("PARTITION")
                        || lineTemp.startsWith("PARTITION `") || lineTemp.startsWith(";")) {
                    stringBuffer.append("\r\n");
                } else {
                    String[] strings = lineTemp.split(" ");
                    String field = strings[0];
                    // System.out.println(lineTemp);
                    String type = handleType(strings[1], lineTemp);

                    stringBuffer.append(field).append(" ").append(type);
                    if (lineTemp.toUpperCase().contains("COMMENT")) {
                        String fieldComment = getFieldComment(lineTemp);
                        if (!StringUtils.isBlank(fieldComment)) {
                            if (fieldComment.length() < 20) {
                                stringBuffer.append(" COMMENT ").append(fieldComment).append(",\r\n");
                            } else {
                                stringBuffer.append(",\r\n");
                            }

                        } else {
                            stringBuffer.append(",\r\n");
                        }

                    } else {
                        stringBuffer.append(",\r\n");
                    }
                }
            }

        }

        System.out.println("drop = " + drop);
        System.out.println("create = " + create);
        System.out.println("actual count = " + tableNames.size());

        return tableNames;

    }

    private static void func(Set<String> tableNames, String file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;
        Set<String> existTableNames = new HashSet<String>();
        while ((line = reader.readLine()) != null) {
            if (!existTableNames.contains(line.trim())) {
                existTableNames.add(line.trim());
            }
        }

        for (String tableName : existTableNames) {
            if (tableNames.contains(tableName.toLowerCase())) {
                tableNames.remove(tableName.toLowerCase());
            } else if (tableNames.contains(tableName.toUpperCase())) {
                tableNames.remove(tableName.toUpperCase());
            }
        }

        System.out.println("HIVE建表语句出错：" + tableNames);
    }

    private static void handleEnd(String tableComment, StringBuffer stringBuffer) {
        // 删除前一条语句末尾的逗号
        if (stringBuffer.charAt(stringBuffer.length() - 3) == ',') {
            stringBuffer.deleteCharAt(stringBuffer.length() - 3);
        }
        stringBuffer.append(")\r\n");

        if (!StringUtils.isBlank(tableComment)) {
            stringBuffer.append("COMMENT ").append(tableComment).append("\r\n");
        }

        stringBuffer.append("ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\001'").append("\r\n");
        stringBuffer.append("LINES TERMINATED BY '\\n'").append("\r\n");
        stringBuffer.append("STORED AS ORC;").append("\r\n\r\n\r\n");
    }

    /**
     * 获取字段定义信息
     *
     * @param lineTemp
     * @return
     */
    private static String getFieldComment(String lineTemp) {
        // 字段定义语句
        StringBuilder fieldComment = new StringBuilder();

        String subLine = lineTemp.substring(lineTemp.toUpperCase().indexOf("COMMENT") + 7);
        int count = 0;
        for (int i = 0; i < subLine.length(); i++) {
            if (count == 2) {
                break;
            }

            char ch = subLine.charAt(i);
            if (ch == '\\') {
                i = i + 1;
                continue;
            }

            fieldComment = fieldComment.append(ch);
            if (ch == '\'') {
                count++;
            }
        }

        String fieldCommentTemp = fieldComment.toString().replaceAll("\\s+", "").replaceAll(";", " ");
        if (fieldCommentTemp.contains(":")) {
            StringBuilder stringBuilderTemp = new StringBuilder();
            stringBuilderTemp.append(fieldCommentTemp.substring(0, fieldCommentTemp.indexOf(":"))).append("'");
            return stringBuilderTemp.toString();
        } else if (fieldCommentTemp.contains("：")) {
            StringBuilder stringBuilderTemp = new StringBuilder();
            stringBuilderTemp.append(fieldCommentTemp.substring(0, fieldCommentTemp.indexOf("："))).append("'");
            return stringBuilderTemp.toString();
        }

        if (fieldCommentTemp.length() > 20) {
            return null;
        }

        return fieldCommentTemp;

    }

    private static String getTableComment(String lineTemp) {
        StringBuilder comment = new StringBuilder();

        String subLine = lineTemp.substring(lineTemp.toUpperCase().indexOf("COMMENT") + 7);
        int count = 0;
        for (int i = 0; i < subLine.length(); i++) {
            if (count == 2) {
                break;
            }

            char ch = subLine.charAt(i);
            if (ch != ' ' && ch != '=') {
                comment = comment.append(ch);
            }

            if (subLine.charAt(i) == '\'') {
                count++;
            }
        }

        return comment.toString();
    }

    /**
     * 判断是否为字段定义语句 是，则返回true
     *
     * @param lineTemp
     * @return
     */
    private static boolean isFieldDef(String lineTemp) {
        boolean b = true;
        String subLine = lineTemp.substring(lineTemp.toUpperCase().indexOf("COMMENT") + 7);
        for (int i = 0; i < subLine.length(); i++) {
            char ch = subLine.charAt(i);
            if (ch == '=') {
                b = false;
                break;
            } else if (ch == ' ') {
                continue;
            } else {
                break;
            }
        }

        return b;
    }

    private static String handleType(String type, String lineTemp) {
        String typeTemp = type.toLowerCase();

        String string = typeTemp;
        if (typeTemp.indexOf("(") > 0) {
            string = typeTemp.substring(0, typeTemp.indexOf("("));
        }
        if (typeTemp.contains("decimal") && !typeSet.contains(typeTemp)) {
            typeSet.add(typeTemp);
        }

        if (string.equals("char") || string.equals("varchar") || string.equals("text") || string.equals("mediumtext") || string.equals("time")) {
            return "string";
        } else if (string.equals("datetime")) {
            return "timestamp";
        } else if (string.equals("tinyint")) {
            return "bigint";
        } else if (string.equals("int")) {
            return "int";
        } else if (string.equals("smallint")) {
            return "bigint";
        } else if (string.equals("bigint")) {
            return "bigint";
        } else if (string.equals("decimal")) {
            return typeTemp;
            // return "decimal";
        } else if (string.equals("date")) {
            return "date";
        } else if (string.equals("timestamp")) {
            return "timestamp";
        } else if (string.equals("numeric")) {
            return "decimal";
        }

        return type;
    }

}
