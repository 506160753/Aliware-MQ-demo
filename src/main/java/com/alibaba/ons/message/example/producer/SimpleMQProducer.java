/**
 * Copyright (C) 2010-2016 Alibaba Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.ons.message.example.producer;

import com.aliyun.openservices.ons.api.*;

import java.util.Date;
import java.util.Properties;

/**
 * @author jixiang.jjx
 */
public class SimpleMQProducer {
    /**
     * 启动测试之前请替换如下 XXX 为您的配置
     */
    private static final String TOPIC = "XXX";
    private static final String PRODUCER_ID = "XXXX";
    private static final String ACCESS_KEY = "XXX";
    private static final String SECRET_KEY = "XXX";
    private static final String TAG = "mq_test_tag";

    public static void main(String[] args) {
        Properties producerProperties = new Properties();
        producerProperties.setProperty(PropertyKeyConst.ProducerId, PRODUCER_ID);
        producerProperties.setProperty(PropertyKeyConst.AccessKey, ACCESS_KEY);
        producerProperties.setProperty(PropertyKeyConst.SecretKey, SECRET_KEY);
        producerProperties.setProperty(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        Producer producer = ONSFactory.createProducer(producerProperties);
        producer.start();
        System.out.println("Producer Started");

        for (int i = 0; i < 10; i++) {
            Message message = new Message(TOPIC, TAG, "mq send transaction message test".getBytes());
            SendResult sendResult = producer.send(message);
            if (sendResult != null) {
                System.out.println(new Date() + " Send mq message success! Topic is:" + TOPIC + "msgId is: " + sendResult.getMessageId());
            }
        }
    }
}
