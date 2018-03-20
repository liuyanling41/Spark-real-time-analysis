package persistence;

import org.springframework.stereotype.Repository;
import pojo.bo.WebLogBO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyanling on 2018/3/17
 */
@Repository
public class WebLogDao extends BaseDaoImpl {
    public List<WebLogBO> webcount() {
        String statement = getStatement("webcount");
        return super.getSqlSessionTemplate().selectList(statement);
    }

    public Long websum() {
        String statement = getStatement("websum");
        return super.getSqlSessionTemplate().selectOne(statement);
    }

    public Long peoplesum() {
        String statement = getStatement("peoplesum");
        return super.getSqlSessionTemplate().selectOne(statement);
    }
}
