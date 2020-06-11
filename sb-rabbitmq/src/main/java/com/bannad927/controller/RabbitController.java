package com.bannad927.controller;

import com.bannad927.server.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author chengbb
 * @date 2020.6.10
 */
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Resource
    private SimpleSender simpleSender;

    /**
     * 简单模式
     */
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public void simpleTest() {
        for (int i = 0; i < 10; i++) {
            try {
                simpleSender.send();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Resource
    private WorkSender workSender;

    /**
     * 工作模式
     */
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    @ResponseBody
    public void workTest() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Resource
    private FanoutSender fanoutSender;

    /**
     * 发布/订阅模式
     *
     * @return
     */
    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    @ResponseBody
    public void fanoutTest() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Resource
    private DirectSender directSender;

    /**
     * 路由模式
     *
     * @return
     */
    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseBody
    public void directTest() {
        for (int i = 0; i < 10; i++) {
            directSender.send(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Resource
    private TopicSender topicSender;

    /**
     * 通配符模式
     *
     * @return
     */
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public void topicTest() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
