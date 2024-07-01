package com.plf.akka.mailbox.priority;

import akka.actor.UntypedAbstractActor;

/**
 * @author Breeze
 * @date 2024/6/30
 */
public class MsgPriorityActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(message);
    }
}
