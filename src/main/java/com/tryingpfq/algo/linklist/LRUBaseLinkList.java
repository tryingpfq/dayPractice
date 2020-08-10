package com.tryingpfq.algo.linklist;

/**
 * 基于链表实现LRU算法
 *
 * @author tryingpfq
 * @date 2020/7/23
 **/
public class LRUBaseLinkList<T> {

    private final static Integer DEFALUT_CAPACITY = 10;

    private LRUNode<T> head;

    private Integer length;


    private Integer capacity;

    public LRUBaseLinkList() {
        this.head = new LRUNode<>();
        this.capacity = DEFALUT_CAPACITY;
        this.capacity = 0;
    }

    public LRUBaseLinkList(Integer capacity) {
        this.head = new LRUNode<>();
        this.capacity = DEFALUT_CAPACITY;
        this.capacity = capacity;
    }

    public void add(T data) {
        LRUNode preNode = findPreNode(data);
        if (preNode == null) {
            if (length >= this.capacity) {
                deleteEndElement();
            }
            insertInHead(data);
        }else{
            deleteEndElement();
            insertInHead(data);
        }
    }



    private void deleteElemOptim(LRUNode preNode) {
        LRUNode temp =preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    public void insertInHead(T data) {
        LRUNode next = head.getNext();
        head.setNext(new LRUNode(data, next));
        length++;
    }

    public void deleteEndElement() {
        LRUNode node = head;
        if (node.getNext() == null) {
            return;
        }
        LRUNode prev = null;
        while (node.getNext() != null) {
            prev = node;
            node = node.getNext();
        }
        assert prev != null;
        prev.setNext(null);
        length--;
    }

    public LRUNode findPreNode(T data) {
        LRUNode node = head;
        while (node.getNext() != null) {
            if (node.getElement() == data) {
                return node;
            }
        }
        return null;
    }

    public class LRUNode<T> {
        private T element;

        private LRUNode next;

        public LRUNode(T element) {
            this.element = element;
        }

        public LRUNode(T element, LRUNode next) {
            this.element = element;
            this.next = next;
        }

        public LRUNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public LRUNode getNext() {
            return next;
        }

        public void setNext(LRUNode next) {
            this.next = next;
        }
    }

}
