package com.plf.akka.mailbox.control;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Breeze
 * @date 2024/7/1
 */
public class ControlMsgSystem {
    public static void main(String[] args) {
        ActorSystem.create("config-sys", ConfigFactory.load("appsys"));
        ActorSystem actorSystem = ActorSystem.create("control-msg-sys");
        ActorRef actorRef = actorSystem.actorOf(Props.create(ControlAwareActor.class)
                .withMailbox("control-aware-mailbox"), "controlAware");

        Object[] messages = {"Java","C#",new ControlMsg("ServerPage"),"PHP"};
        for(Object msg:messages){
            actorRef.tell(msg,ActorRef.noSender());
        }
    }

    /**
     * 结果
     * ServerPage
     * Java
     * C#
     * PHP
     *
     * 结果说明：实现了ControlMessage接口后的消息，在ControlAwareMailbox类型的邮箱中
     * 拥有最高优先级。
     */
}
