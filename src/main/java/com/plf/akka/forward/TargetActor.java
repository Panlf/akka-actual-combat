package com.plf.akka.forward;

import akka.actor.UntypedAbstractActor;

public class TargetActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("TargetActor receive : " +message+", sender :"+ sender());
    }
}


