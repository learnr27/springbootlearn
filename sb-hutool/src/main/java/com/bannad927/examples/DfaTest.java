package com.bannad927.examples;

import cn.hutool.dfa.WordTree;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.List;

/**
 * 由来
 * 在我最早入职的一家公司，主要负责内容方面的业务，对我来说大部分的工作是对内容的清洗和规整。当然，清洗过程免不了的就是按照关键词过滤，你懂的。需求如下：
 * <p>
 * 后台人员添加N个关键字，然后对主站所有的内容进行清洗，含有这些关键字的所有内容都置为无效。
 * <p>
 * 思路
 * 拿到此需求，我最早的方案比较粗暴：针对关键字建立一个HashSet，然后遍历整个数据库，针对每篇文章遍历这个Set，查找是否contains关键字……好吧我承认这不是一个好方法，随着关键字的增多和数据的增多，这个过程消耗的时间成指数型增长！
 * <p>
 * 于是我找到度娘，发现一个算法：DFA。
 * <p>
 * DFA介绍
 * DFA全称为：Deterministic Finite Automaton,即确定有穷自动机。因为本人算法学的不好，有兴趣的可以看这篇博客: 基于DFA敏感词查询的算法简析
 * <p>
 * 解释起来原理其实也不难，就是用所有关键字构造一棵树，然后用正文遍历这棵树，遍历到叶子节点即表示文章中存在这个关键字。
 * <p>
 * 我们暂且忽略构建关键词树的时间，每次查找正文只需要O(n)复杂度就可以搞定。
 * <p>
 * 针对DFA算法以及网上的一些实现，Hutool做了整理和改进，最终形成现在的Hutool-dfa模块。
 *
 * @author chengbb
 * @date 2020.6.18
 */
@Slf4j
public class DfaTest {

    public static void main(String[] args) {
        //1. 构建关键词树
        WordTree tree = new WordTree();
        tree.addWord("大");
        tree.addWord("大土豆");
        tree.addWord("土豆");
        tree.addWord("刚出锅");
        tree.addWord("出锅");

        //2. 查找关键词
        String text = "我有一颗大土豆，刚出锅的";

        //情况一：标准匹配，匹配到最短关键词，并跳过已经匹配的关键词
        // 匹配到【大】，就不再继续匹配了，因此【大土豆】不匹配
        // 匹配到【刚出锅】，就跳过这三个字了，因此【出锅】不匹配（由于刚首先被匹配，因此长的被匹配，最短匹配只针对第一个字相同选最短）
        List<String> matchAll = tree.matchAll(text, -1, false, false);
        Assert.assertEquals(matchAll.toString(), "[大, 土豆, 刚出锅]");

        //情况二：匹配到最短关键词，不跳过已经匹配的关键词
        // 【大】被匹配，最短匹配原则【大土豆】被跳过，【土豆继续被匹配】
        // 【刚出锅】被匹配，由于不跳过已经匹配的词，【出锅】被匹配
        matchAll = tree.matchAll(text, -1, true, false);
        Assert.assertEquals(matchAll.toString(), "[大, 土豆, 刚出锅, 出锅]");

        //情况三：匹配到最长关键词，跳过已经匹配的关键词
        // 匹配到【大】，由于到最长匹配，因此【大土豆】接着被匹配
        // 由于【大土豆】被匹配，【土豆】被跳过，由于【刚出锅】被匹配，【出锅】被跳过
        matchAll = tree.matchAll(text, -1, false, true);
        Assert.assertEquals(matchAll.toString(), "[大, 大土豆, 刚出锅]");

        //情况四：匹配到最长关键词，不跳过已经匹配的关键词（最全关键词）
        // 匹配到【大】，由于到最长匹配，因此【大土豆】接着被匹配，由于不跳过已经匹配的关键词，土豆继续被匹配
        // 【刚出锅】被匹配，由于不跳过已经匹配的词，【出锅】被匹配
        matchAll = tree.matchAll(text, -1, true, true);
        Assert.assertEquals(matchAll.toString(), "[大, 大土豆, 土豆, 刚出锅, 出锅]");

    }
}
