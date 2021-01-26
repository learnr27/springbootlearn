package com.bannad927.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * Broker
 *
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setPopulateJMSXUserID(true);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();


    }
}
