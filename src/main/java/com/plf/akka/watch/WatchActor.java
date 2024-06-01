package com.plf.akka.watch;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;

/**
 * @author Lancer
 * @date 2024-06-01
 */
public class WatchActor extends UntypedAbstractActor {

    ActorRef child = null;

    @Override
    public void preStart() throws Exception {
        //创建子级Actor
        child = getContext().actorOf(Props.create(WorkerActor.class),"workerActor");
        //监控child
        getContext().watch(child);

        //解除监控
        //getContext().unwatch(child);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        // 为了监控到子级Actor的停止操作，我们需要调用getContext().watch方法对其进行监听，当Actor真正停掉后，监控者就可以接收到Terminated
        // 消息。
        if(message instanceof String) {
            if(((String) message).equalsIgnoreCase("stopChild")){
                getContext().stop(child);
            }else {
                //做一个消息转发
                child.forward(message,getContext());
            }
        } else if(message instanceof Terminated){
            Terminated terminated= (Terminated) message;
            System.out.println("监控到"+terminated.getActor()+"停止了");
        } else {
           unhandled(message);
        }
    }
}
