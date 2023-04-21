package com.shstu.huffman;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FileMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Byte, String> huffmanCodes = new HashMap<>();
    private int countZero = 0;
    private byte[] bytes;

    public FileMessage() {
    }

    public Map<Byte, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void setHuffmanCodes(Map<Byte, String> huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }

    public int getCountZero() {
        return countZero;
    }

    public void setCountZero(int countZero) {
        this.countZero = countZero;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
