package com.plf.akka.supervisor;

import akka.actor.UntypedAbstractActor;

import java.util.Optional;

/**
 * @author Lancer
 * @date 2024-06-07
 */
public class WorkerActor extends UntypedAbstractActor {
    //状态数据
    private int stateCount = 1;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        System.out.println("worker actor preStart");
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("worker actor postStop");
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        System.out.println("worker actor preRestart begin "+ this.stateCount);
        super.preRestart(reason, message);
        System.out.println("worker actor preRestart end "+ this.stateCount);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        System.out.println("worker actor postRestart begin "+ this.stateCount);
        super.postRestart(reason);
        System.out.println("worker actor postRestart end "+ this.stateCount);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        //模拟计算
        this.stateCount++;
        if(message instanceof Exception){
            throw (Exception) message;
        } else if ("getValue".equals(message)){
            //返回当前数据
            getSender().tell(stateCount,getSelf());
        } else {
            unhandled(message);
        }
    }
}
