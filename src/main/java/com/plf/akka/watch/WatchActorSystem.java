package com.plf.akka.watch;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 *
 * @author Lancer
 * @date 2024-06-01
 */
public class WatchActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("watch-actor-sys");

        ActorRef actorRef =actorSystem.actorOf(Props.create(WatchActor.class),"watch-actor");

        actorRef.tell("Hello",ActorRef.noSender());

        actorRef.tell("stopChild",ActorRef.noSender());
    }
}
