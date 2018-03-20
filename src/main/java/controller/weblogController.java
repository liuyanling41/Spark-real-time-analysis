package controller;

import facade.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.bo.WebLogBO;

import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyanling on 2018/3/17
 */
@Controller
@RequestMapping(value = "/weblogController")
public class weblogController extends BaseController{
    @Autowired
    private WebLogService webLogService;

    @RequestMapping(value = "/webcount")
    @ResponseBody
    public Map<String,Object> webcount() {
        Map<String, Object> map = new HashMap();
        Long peoplesum = webLogService.peoplesum();
        Long namesum = webLogService.websum();
        map.put("peoplesum", peoplesum);
        map.put("namesum", namesum);
        return dataput(map);
    }
}
