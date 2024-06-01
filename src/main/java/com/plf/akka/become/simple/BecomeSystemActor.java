package com.plf.akka.become.simple;

import akka.actor.*;

/**
 * @author panlf
 * @date 2022/12/6
 */
public class BecomeSystemActor {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("becomeSystem");
        ActorRef actorRef = system.actorOf(Props.create(BecomeActor.class),"becomeActor");
        actorRef.tell("hello",ActorRef.noSender());
        actorRef.tell("hi",ActorRef.noSender());
        actorRef.tell("akka",ActorRef.noSender());

        //停掉一个Actor
        actorRef.tell(Kill.getInstance(),ActorRef.noSender());
        //actorRef.tell(PoisonPill.getInstance(),ActorRef.noSender());
        //system.stop(actorRef);

        actorRef.tell("你还能收到信息吗？",ActorRef.noSender());

        /**
         * Actor在停止时都会遵循一套比较可靠的流程
         * 1）当停止Actor时，正在处理的消息会在完全停止之前处理完毕，后续消息将不再进行处理，邮箱（用来保存Actor的消息）将被挂起
         * 2）给所有子级Actor发送终止命令，当子级都停掉后，再停掉自己，停止完毕后会调用postStop方法，在这里可以清理或释放资源
         * 3）向生命周期监控者（DeathWatch）发送Terminated消息，以便监控者做相应处理
         */
    }
}
