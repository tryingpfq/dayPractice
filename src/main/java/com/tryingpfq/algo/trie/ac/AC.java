package com.tryingpfq.algo.trie.ac;

/**
 * @author tryingpfq
 * @date 2020/3/24
 **/
public class AC {
    private AcNode root = new AcNode('/');



    private void insert(char[] text) {
         AcNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.childNode[index] == null) {
                AcNode newNode = new AcNode(text[i]);
                p.childNode[index] = newNode;
            }
            p = p.childNode[index];
        }
        p.isEndChar = true;
    }

    public class AcNode{
        public char data;

        //假设只包含a-z
        public AcNode[] childNode = new AcNode[26];

        public boolean isEndChar = false;

        //isEndChar = true 记录长度
        public int length = -1;

        //失败指针
        public AcNode fail;

        public AcNode(char data) {
            this.data = data;
        }

    }
}
