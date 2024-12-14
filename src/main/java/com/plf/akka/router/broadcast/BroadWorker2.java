package com.plf.akka.router.broadcast;

import akka.actor.UntypedAbstractActor;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class BroadWorker2 extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf()+"--->"+message);
    }
}
