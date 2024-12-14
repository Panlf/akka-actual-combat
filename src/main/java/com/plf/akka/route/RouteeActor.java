package com.plf.akka.route;

import akka.actor.UntypedAbstractActor;

/**
 * @author Lancer
 * @date 2024-07-03
 */
public class RouteeActor extends UntypedAbstractActor  {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf()+"--->"+message);
    }
}
