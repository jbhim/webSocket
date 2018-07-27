package com.amcjt.webserver;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint("/onlineServer")
public class Online {

    private static Vector<Online> users = new Vector<>();
    //用户与服务端建立webscoket链接会话对象
    private Session session;

    @OnOpen
    public void Open(Session session) {
        this.session = session;
        users.add(this);
        System.out.println("进入直播间");
    }

    @OnClose
    public void Close() {
        users.remove(this);
        System.out.println("退出直播间");
    }

    @OnMessage
    public void Message(String msg) throws IOException {
        System.out.println(msg);
        for (Online user : users) {
            user.session.getBasicRemote().sendText(msg);
        }
    }


    @OnError
    public void Error(Throwable throwable) {
        System.out.println(throwable.toString());
    }
}
