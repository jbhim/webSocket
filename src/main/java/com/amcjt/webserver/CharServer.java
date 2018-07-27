package com.amcjt.webserver;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint(value = "/charServer")
public class CharServer {
    //每次聊天的都会new CharaServer();
    //存放聊天集合对象
    private static Vector<CharServer> users = new Vector<>();
    //用户与服务端建立webscoket链接会话对象
    private Session session;

    /*
    * @OnOpen 连接
    * @OnClose 关闭
    * @OnMessage 发送信息
    * @OnError
     */
    @OnOpen
    public void buildConnent(Session session) {
        this.session = session;
        users.add(this);
        System.out.println(session + "用户上线了");
    }

    @OnClose
    public void disConnect() {
        users.remove(this);
        System.out.println("用户下线了");
    }

    @OnMessage
    public void getMessage(String msg) {
        sendMessage(msg);
        System.out.println(msg);
    }
    @OnError
    public void onError(Throwable throwable){
        System.out.println(throwable.toString());
    }


    private static void sendMessage(String msg){
        for (CharServer user : users) {
            try {
                user.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
