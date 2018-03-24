package service;

import org.springframework.web.socket.server.standard.SpringConfigurator;
import com.alibaba.fastjson.JSON;
import facade.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.bo.WebLogBO;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket与前台后台对接实现全双工通信
 * Created by liuyanling on 2018/3/17
 */
@ServerEndpoint(value = "/websocket", configurator = SpringConfigurator.class)
public class WebSocket {
    @Autowired
    private WebLogService webLogService;

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        String[] titleNames = new String[10];
        Long[] titleCounts = new Long[10];
        Long[] titleSum = new Long[1];
        while (true) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<WebLogBO> list = webLogService.webcount();
            System.out.print(list);
            for (int i = 0; i < list.size(); i++) {
                titleNames[i] = list.get(i).getTitleName();
                titleCounts[i] = list.get(i).getWebcount();
            }
            titleSum[0] = webLogService.websum();
            map.put("titleName", titleNames);
            map.put("titleCount", titleCounts);
            map.put("titleSum", titleSum);
            System.out.print(map);
            session.getBasicRemote().sendText(JSON.toJSONString(map));
            Thread.sleep(1000);
            map.clear();
        }
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}
