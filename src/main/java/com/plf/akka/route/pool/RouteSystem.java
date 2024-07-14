package com.plf.akka.route.pool;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.plf.akka.route.RouterTaskActor;

/**
 * @author Lancer
 * @date 2024-07-03
 */
public class RouteSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("route-sys");
        ActorRef actorRef = actorSystem.actorOf(Props.create(MasterRouterActor.class), "poolTaskActor");
        actorRef.tell("helloA",ActorRef.noSender());
        actorRef.tell("helloB",ActorRef.noSender());
        actorRef.tell("helloC",ActorRef.noSender());
    }
}
