package facade;

import pojo.bo.WebLogBO;
import java.util.List;

/**
 * Created by liuyanling on 2018/3/17
 */

public interface WebLogService {
    /**
     * 取搜索量前10位的话题
     */
    List<WebLogBO> webcount();

    /**
     * 取搜索话题总数
     */
    Long websum();

    /**
     * 取搜索用户总数
     */
    Long peoplesum();
}
