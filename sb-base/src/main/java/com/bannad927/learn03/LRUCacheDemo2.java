package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 手写LRU算法
 *
 * @author: chengbinbin
 * @date: 2021.1.22
 **/
@Slf4j
public class LRUCacheDemo2 {


    //构造一个node节点 作为数据载体
    class Node<K, V> {
        K key;
        V value;

        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }


    //2构建一个虚拟双向链表，里面安放node节点
    class DoubleLikeList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        //2.1构造方法
        public DoubleLikeList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        //2.2添加到头
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        //2.3删除节点
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        //2.4获得最后一个节点
        public Node getLast() {
            return tail.prev;
        }
    }

    private int cacheSize;

    Map<Integer, Node<Integer, Integer>> map;
    DoubleLikeList<Integer, Integer> doubleLikeList;

    public LRUCacheDemo2(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        doubleLikeList = new DoubleLikeList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLikeList.removeNode(node);
        doubleLikeList.addHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //update
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);
            doubleLikeList.removeNode(node);
            doubleLikeList.addHead(node);
        } else {
            if (map.size() == cacheSize) {
                //坑位满了
                Node<Integer, Integer> lastNode = doubleLikeList.getLast();
                map.remove(lastNode.key);
                doubleLikeList.removeNode(lastNode);
            }
            //才是新增
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLikeList.addHead(newNode);

        }

    }

    public static void main(String[] args) {
        LRUCacheDemo2 lruCacheDemo = new LRUCacheDemo2(3);
        lruCacheDemo.put(1, 11);
        lruCacheDemo.put(2, 22);
        lruCacheDemo.put(3, 33);
        log.info("keySet:{}", lruCacheDemo.map.keySet());
        lruCacheDemo.put(4, 44);
        log.info("keySet:{}", lruCacheDemo.map.keySet());
        lruCacheDemo.put(3, 33);
        log.info("keySet:{}", lruCacheDemo.map.keySet());
        lruCacheDemo.put(3, 33);
        log.info("keySet:{}", lruCacheDemo.map.keySet());
        lruCacheDemo.put(5, 44);
        log.info("keySet:{}", lruCacheDemo.map.keySet());
    }
}
