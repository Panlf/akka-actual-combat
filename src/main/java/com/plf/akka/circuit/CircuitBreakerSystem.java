package com.plf.akka.circuit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Lancer
 * @date 2024-06-08
 */
public class CircuitBreakerSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("circuit-breaker-actor-sys");

       ActorRef actorRef =  actorSystem.actorOf(Props.create(CircuitBreakerActor.class),"circuit-breaker-actor");

       for(int i=0;i<5;i++) {
           actorRef.tell("syncActor", ActorRef.noSender());
       }
    }
}
