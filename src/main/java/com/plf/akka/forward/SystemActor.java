package com.plf.akka.forward;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class SystemActor {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("actor-forward-sys");

        ActorRef actorRef =actorSystem.actorOf(Props.create(ForwardActor.class),"actor-forward");

        actorRef.tell("forward message",ActorRef.noSender());
    }
}
