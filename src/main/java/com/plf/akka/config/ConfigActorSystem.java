package com.plf.akka.config;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;

/**
 * @author Lancer
 * @date 2024-06-08
 */
public class ConfigActorSystem {
    public static void main(String[] args) {
         ActorSystem.create("config-sys", ConfigFactory.load("appsys"));
    }
}
