package controller;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:基础控制类
 *
 * @author liuyanling
 */
@Controller
public class BaseController {

    protected String retcode = "0";//返回代码
    protected String retmsg = "";//返回信息提示
    protected final String SUCCESS = "SUCCESS";

    //JSON字符串的对象
    protected Map<String, Object> dataMap = new HashMap<String, Object>();


    protected final Map<String, Object> dataput(Map<String, Object> datamap) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        if (null == retcode) {
            retcode = "";
        }
        if (null == retmsg) {
            retmsg = "";
        }
        data.put("retcode", retcode);
        data.put("retmsg", retmsg);
        data.put("data", datamap);
        result.put("result", data);


        return result;
    }


}
