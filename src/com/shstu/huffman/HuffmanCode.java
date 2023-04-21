package com.shstu.huffman;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    private static codeNode root;
    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();
    private static int countZero = 0;

    public static void main(String[] args) throws IOException {
//        String content = "i like like like freedom do you like freedom";
//        byte[] huffmanCodesBytes = zip(content.getBytes());
//        System.out.println(Arrays.toString(huffmanCodesBytes));
//        byte[] decode = decode(huffmanCodesBytes, huffmanCodes);
//        System.out.println(new String(decode));

        /**
         * 下面出问题了，待解决。。。
         * 已解决。。。
         */
//        String srcFile = "d:\\computer.bmp";
//        String desFile = "d:\\computer.zip";
//        zipFile(srcFile, desFile);
//
//        String srcFile2 = "d:\\computer.zip";
//        String desFile2 = "d:\\computer18.bmp";
//        unZipFile(srcFile2, desFile2);


    }

    public static void unZipFile(String srcFile, String desFile) {
        ObjectInputStream ois = null;
        FileOutputStream os = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(srcFile));
            FileMessage fileMessage = (FileMessage) ois.readObject();
            byte[] huffmanCodesBytes = fileMessage.getBytes();
            Map<Byte, String> huffmanCodes = fileMessage.getHuffmanCodes();
            countZero = fileMessage.getCountZero();

            byte[] bytes = decode(huffmanCodesBytes, huffmanCodes);
            os = new FileOutputStream(desFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void zipFile(String srcFile, String desFile) {
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            byte[] huffmanCodesBytes = zip(bytes);

            FileMessage fileMessage = new FileMessage();
            fileMessage.setCountZero(countZero);
            fileMessage.setBytes(huffmanCodesBytes);
            fileMessage.setHuffmanCodes(huffmanCodes);

            oos = new ObjectOutputStream(new FileOutputStream(desFile));
            oos.writeObject(fileMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] decode(byte[] huffmanCodesBytes, Map<Byte, String> huffmanCodes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanCodesBytes.length; i++) {
            boolean flag = (i == huffmanCodesBytes.length - 1);
            builder.append(byteToBitString(!flag, huffmanCodesBytes[i]));
        }
        //System.out.println(builder + " " +(builder.length() % 8));

        Map<String, Byte> map = new HashMap<>();
        Set<Byte> keySet = huffmanCodes.keySet();
        for (Byte aByte : keySet) {
            map.put(huffmanCodes.get(aByte), aByte);
        }

        ArrayList<Byte> byteList = new ArrayList<>();
        int i = 0;
        int count;
        boolean flag;
        Byte b;
        String key;
        while (i < builder.length()) {
            count = 1;
            flag = true;
            b = null;
            while (flag) {
                key = builder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            byteList.add(b);
            i += count;
        }

        byte[] bytes = new byte[byteList.size()];
        for (int j = 0; j < byteList.size(); j++) {
            bytes[j] = byteList.get(j);
        }
        return bytes;
    }

    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String binaryString = Integer.toBinaryString(temp);
        if (flag) {
            return binaryString.substring(binaryString.length() - 8);
        }
        if (temp == 0) {
            countZero--;
        }
        for (int i = 0; i < countZero; i++) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    public static byte[] zip(byte[] bytes) {
        List<codeNode> nodes = nodeList(bytes);
        //System.out.println(Arrays.toString(bytes));
        root = huffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(root);
        return zip(bytes, huffmanCodes);
    }

    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        //System.out.println(builder + " " +(builder.length() % 8));
        int len = (builder.length() + 7) / 8;
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        String temp = "";
        for (int i = 0; i < builder.length(); i += 8) {
            if (i + 8 > builder.length()) {
                temp = builder.substring(i);
                if (temp.length() > 1) {
                    for (int j = 0; j < temp.length(); j++) {
                        if (temp.charAt(j) == '0') {
                            countZero++;
                        } else {
                            break;
                        }
                    }
                }
                huffmanCodesBytes[index++] = (byte) Integer.parseInt(builder.substring(i), 2);
            } else {
                huffmanCodesBytes[index++] = (byte) Integer.parseInt(builder.substring(i, i + 8), 2);
            }
        }
        return huffmanCodesBytes;
    }

    public static Map<Byte, String> getCodes(codeNode root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    public static void getCodes(codeNode node, String code, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node.data == null) {
            getCodes(node.left, "0", stringBuilder2);
            getCodes(node.right, "1", stringBuilder2);
        } else {
            huffmanCodes.put(node.data, stringBuilder2.toString());
        }
    }

    public static void preOrder() {
        if (root == null) {
            return;
        }
        root.preOrder();
    }

    public static codeNode huffmanTree(List<codeNode> nodes) {

        while (nodes.size() > 1) {
            Collections.sort(nodes);

            codeNode leftNode = nodes.get(0);
            codeNode rightNode = nodes.get(1);
            codeNode parentNode = new codeNode(null, leftNode.weight + rightNode.weight);

            parentNode.left = leftNode;
            parentNode.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    public static List<codeNode> nodeList(byte[] contentBytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte contentByte : contentBytes) {
            map.merge(contentByte, 1, Integer::sum);
        }

        List<codeNode> nodes = new ArrayList<>();
        Set<Byte> keySet = map.keySet();
        for (Byte next : keySet) {
            nodes.add(new codeNode(next, map.get(next)));
        }

        return nodes;
    }
}

class codeNode implements Comparable<codeNode> {
    public Byte data;
    public int weight;
    public codeNode left;
    public codeNode right;

    public codeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "codeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(codeNode o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
