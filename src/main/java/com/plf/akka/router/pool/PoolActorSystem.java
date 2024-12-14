package com.plf.akka.router.pool;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.plf.akka.router.starter.RouterTaskActor;
import com.typesafe.config.ConfigFactory;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class PoolActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("pool-sys", ConfigFactory.load("appsys"));
        ActorRef actorRef =actorSystem.actorOf(Props.create(MasterRouterActor.class),"master-router-actor");
        actorRef.tell("HelloA",ActorRef.noSender());
    }
}
