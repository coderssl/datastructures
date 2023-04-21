package com.shstu.sparsearray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建棋盘，二维数组[11][11]
        //0:无 1：黑 2：白
        int[][] chessArray = new int[11][11];
        chessArray[1][1] = 1;
        chessArray[2][2] = 2;
        //打印棋盘
        int nums = 0;
        for (int[] ints : chessArray) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    nums++;
                }
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        //创建稀疏数组
        int row = chessArray.length;
        int col = chessArray[0].length;
        int[][] sparseArray = new int[nums + 1][3];
        //初始化行/列/棋子数
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = nums;
        //遍历棋盘存储棋子位置
        int count = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (chessArray[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count++][2] = chessArray[i][j];
                }
            }
        }
        System.out.println("===========================");
        //打印稀疏数组
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        //创建文件
        String path = "src\\sparsearray.data";
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        //将稀疏数组存储到文件
//        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
//        for (int i = 0; i < sparseArray.length; i++) {
//            bw.write(sparseArray[i][0] + " " + sparseArray[i][1] + " " + sparseArray[i][2]);
//            bw.newLine();
//        }
//        bw.close();
        //读取文件获得稀疏数组
        String line = "";
        String[][] array = new String[1024][3];
        count = 0;
        BufferedReader br = new BufferedReader(new FileReader(path));
        //将文件中的数据存储到String数组中
        while ((line = br.readLine()) != null) {
            String[] s = line.split(" ");
            array[count][0] = s[0];
            array[count][1] = s[1];
            array[count++][2] = s[2];
        }
        br.close();
        //将稀疏数组从String数组中恢复
        int[][] sparseArr = new int[Integer.parseInt(array[0][2]) + 1][3];
        for (int i = 0; i < sparseArr.length; i++) {
            sparseArr[i][0] = Integer.parseInt(array[i][0]);
            sparseArr[i][1] = Integer.parseInt(array[i][1]);
            sparseArr[i][2] = Integer.parseInt(array[i][2]);
        }
        //打印稀疏数组
        System.out.println("===========================");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        //恢复棋盘
        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印棋盘
        System.out.println("===========================");
        for (int[] ints : chessArr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }
}