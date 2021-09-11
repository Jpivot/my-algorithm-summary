package com.aronson.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 51、52题
 */
public class SolveNQueensTest {

    public static void main(String[] args) {
        SolveNQueensTest solveNQueensTest = new SolveNQueensTest();
        List<List<String>> solve = solveNQueensTest.solveNQueens(12);
        System.out.println(solve.size());

    }

    /**
     * n皇后放置
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        List<StringBuilder> board = generateBoard(n, ".");
        backtrack(board, 0, res);
        return res;
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     *
     * @param board
     * @param row
     * @param res
     */
    void backtrack(List<StringBuilder> board, int row, List<List<String>> res) {
        // 触发结束条件
        if (row == board.size()) {
            res.add(stringBuilderToString(board));
            return;
        }

        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            // 如果在未达到底层的某一层就不满足条件，自然无法继续递归，也就无法触发结束条件
            // 这样起到了剪枝效果，不会全层递归
            if (!isValid(board, row, col))
                continue;
            // 做选择
            board.get(row).replace(col, col + 1, "Q");
            // 进入下一行决策
            backtrack(board, row + 1, res);
            // 撤销选择
            board.get(row).replace(col, col + 1, ".");
        }
    }

    /**
     * 是否可以在 board[row][col] 放置皇后？
     *
     * @param board
     * @param row
     * @param col
     * @return
     */
    boolean isValid(List<StringBuilder> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }

    /**
     * 转换方法
     *
     * @param stringBuilderList
     * @return
     */
    public List<String> stringBuilderToString(List<StringBuilder> stringBuilderList) {
        List<String> stringList = new ArrayList<>();
        stringBuilderList.forEach(stringBuilder -> {
            stringList.add(stringBuilder.toString());
        });
        return stringList;
    }

    /**
     * 初始化棋盘
     *
     * @param n
     * @param symbol
     * @return
     */
    public List<StringBuilder> generateBoard(int n, String symbol) {
        List<StringBuilder> stringList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                stringBuilder.append(symbol);
            }
            stringList.add(stringBuilder);
        }
        return stringList;
    }

}
