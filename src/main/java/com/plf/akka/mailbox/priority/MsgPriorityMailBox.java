package com.plf.akka.mailbox.priority;

import akka.actor.ActorSystem;
import akka.dispatch.PriorityGenerator;
import akka.dispatch.UnboundedStablePriorityMailbox;
import com.typesafe.config.Config;

/**
 * @author Breeze
 * @date 2024/6/30
 */
public class MsgPriorityMailBox extends UnboundedStablePriorityMailbox {

    public MsgPriorityMailBox(ActorSystem.Settings settings, Config config) {
        /**
         * 数值越小，优先级越高
         */
        super(new PriorityGenerator() {
            @Override
            public int gen(Object message) {
                if(message.equals("张三")){
                    return 0;
                } else if(message.equals("李四")) {
                    return 1;
                } else if(message.equals("王五")) {
                    return 2;
                } else {
                    return 3;
                }
            }
        });
    }
}
