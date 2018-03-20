package service;

import facade.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.WebLogDao;
import pojo.bo.WebLogBO;

import java.util.List;

/**
 * Created by liuyanling on 2018/3/17
 */
@Service
public class WebLogServiceImpl implements WebLogService {
    @Autowired
    private WebLogDao webLogDao;

    public List<WebLogBO> webcount() {
        List<WebLogBO> webLogBO = webLogDao.webcount();
        return webLogBO;
    }

    public Long websum() {
        Long websum = webLogDao.websum();
        return websum;
    }

    public Long peoplesum() {
        Long peoplesum = webLogDao.peoplesum();
        return peoplesum;
    }
}
