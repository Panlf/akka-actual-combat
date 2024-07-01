package com.plf.akka.mailbox.priority;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Breeze
 * @date 2024/6/30
 */
public class MsgPriorityMailBoxSystem {

    public static void main(String[] args) {
        ActorSystem.create("config-sys", ConfigFactory.load("appsys"));
        ActorSystem actorSystem = ActorSystem.create("msg-priority-mail-box-sys");
        ActorRef actorRef = actorSystem
                .actorOf(Props.create(MsgPriorityActor.class)
                        .withMailbox("msgprio-mailbox"),"msg-actor");

        Object[] messages = {"王五","李四","张三","小二"};
        for(Object msg:messages){
            actorRef.tell(msg,ActorRef.noSender());

            /**
             * 张三
             * 李四
             * 王五
             * 小二
             *
             * 当我们给MsgPriorityActor发送消息后，它会通过自定义的MsgPriorityMailBox
             * 对消息进行优先级的排序
             */
        }
    }
}
