package com.tryingpfq.algo.link;

/**
 * 链表实现LRU缓存淘汰算法
 * <p> 这个T是指一个泛型呀 也就是节点要存储数据的类型 比如整形 String 总之是对象就OK
 *  不知道js是怎样的<p/>
 * @author tryingpfq
 * @date 2020/5/9
 **/
public class LRULinked<T> {
    /**
     * 默认容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    private SNode<T> head;

    private Integer length;

    private Integer capacity;

    public LRULinked(){
        this.head = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        //初始长度
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        } else {
            if (length >= this.capacity) {
                //删除尾结点
                deleteElemAtEnd();
            }
            intsertElemAtBegin(data);
        }
    }

    /**
     * 删除preNode结点下一个元素
     *
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     */
    private void intsertElemAtBegin(T data) {
        SNode next = head.getNext();
        head.setNext(new SNode(data, next));
        length++;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = head;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = head;
        // 空链表直接返回
        if (ptr.getNext() == null) {
            return;
        }

        // 倒数第二个结点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    public class SNode<T> {

        private T element;

        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }
}
