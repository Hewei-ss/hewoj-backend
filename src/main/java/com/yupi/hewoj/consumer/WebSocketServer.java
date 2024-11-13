package com.yupi.hewoj.consumer;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yupi.hewoj.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author websocket服务
 */
@ServerEndpoint(value = "/websocket/{userid}")
@Component
public class WebSocketServer {


    public static UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 记录当前在线连接数
     */
    public static final Map<Long, Session> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    public void setMessageMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userid") Long userid) {
        sessionMap.put(userid, session);
        //   System.out.println(sessionMap.toString());
        log.info("有新用户加入，userid={}, 当前在线人数为：{}", userid, sessionMap.size());
//        JSONObject result = new JSONObject();
//        JSONArray array = new JSONArray();
//        result.set("users", array);
//        for (Object key : sessionMap.keySet()) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.set("username", key);
//            // {"username", "zhang", "username": "admin"}
//            array.add(jsonObject);
//        }
////        {"users": [{"username": "zhang"},{ "username": "admin"}]}
//        sendAllMessage(JSONUtil.toJsonStr(result));  // 后台发送消息给所有的客户端
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //  log.info("服务端收到用户userid={}的消息:{}", myid, message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}
