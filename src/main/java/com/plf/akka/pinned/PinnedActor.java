package com.plf.akka.pinned;

import akka.actor.UntypedAbstractActor;

/**
 * @author Lancer
 * @date 2024-06-09
 */
public class PinnedActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf() + " ---> " + Thread.currentThread().getName() +" --- " + message );
        Thread.sleep(5000);
    }
}
