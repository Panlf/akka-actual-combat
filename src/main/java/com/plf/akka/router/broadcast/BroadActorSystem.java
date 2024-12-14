package com.plf.akka.router.broadcast;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.plf.akka.router.pool.MasterRouterActor;
import com.typesafe.config.ConfigFactory;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class BroadActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("broad-sys", ConfigFactory.load("appsys"));
        ActorRef actorRef =actorSystem.actorOf(Props.create(MasterBroadActor.class),"master-broad-actor");
        actorRef.tell("HelloA",ActorRef.noSender());
        actorRef.tell("HelloB",ActorRef.noSender());
    }
}
