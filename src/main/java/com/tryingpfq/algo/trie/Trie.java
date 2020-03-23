package com.tryingpfq.algo.trie;

/**
 *
 * @Author tryingpfq
 * @Date 2020/3/23
 */
public class Trie {

    private TrieNode root = new TrieNode('/');

    private void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.childNode[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.childNode[index] = newNode;
            }
            p = p.childNode[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.childNode[index] == null) {
                return false;
            }
            p = p.childNode[index];
        }
        if (!p.isEndingChar) {//前缀 并不能完全匹配
            return false;
        }
        return true;
    }

    /**
     * 每一个节点信息，并保存子节点的指针信息
     * 子节点数据角标是 字符串 - 'a'（ASICC) 这样就可以使得 字符a位置从0开始
     */
    public class TrieNode {
        char data;
        TrieNode[] childNode = new TrieNode[26];

        public TrieNode(char data) {
            this.data = data;
        }

        public boolean isEndingChar = false;
    }
}
