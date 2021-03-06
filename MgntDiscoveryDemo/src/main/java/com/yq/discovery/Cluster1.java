package com.yq.discovery;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Simple to Introduction
 * className: Cluster1
 *
 * @author EricYang
 * @version 2019/3/20 14:01
 */
public class Cluster1 {
    private static final String ACTOR_SYSTEM_NAME = "ClusterDemo";
    private static final String AKKA_CONF_FILE_NAME = "akka-system";
    public static void main(String[] args) {

        Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME)
                .withFallback(ConfigFactory.parseString("akka.cluster.roles=[rule1]"))
                .withFallback(ConfigFactory.load());

        // Create an Akka system
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);
        ClusterBootstrap clusterBootstrap = ClusterBootstrap.get(system);

        // Akka Management hosts the HTTP routes used by bootstrap
        AkkaManagement.get(system).start();

        // Starting the bootstrap process needs to be done explicitly
        ClusterBootstrap.get(system).start();




    }

}
