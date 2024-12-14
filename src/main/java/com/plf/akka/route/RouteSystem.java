package com.plf.akka.route;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Lancer
 * @date 2024-07-03
 */
public class RouteSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("route-sys");
        ActorRef actorRef = actorSystem.actorOf(Props.create(RouterTaskActor.class), "routerTaskActor");
        actorRef.tell("helloA",ActorRef.noSender());
        actorRef.tell("helloB",ActorRef.noSender());
        actorRef.tell("helloC",ActorRef.noSender());
    }
}
