package com.plf.akka.tool.future;

import akka.actor.UntypedAbstractActor;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class FutureActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        Thread.sleep(4000);
        getSender().tell("reply...reply",getSelf());
    }
}
