package persistence;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dao层的基础增删改查方法类
 * Created by LYL on 2017/10/25.
 */
public class BaseDaoImpl<T,P>{
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public String getStatement(String sqlId) {
        //字符串拼接尽量使用 StringBuffer 的append方法来拼接。而直接使用”+”来连接String类型。会增加内存和CPU的开销。
        // String字符串拼接的原理如下 String str1 = “a”; String str2 = “b”; str1 = str1 + str2; 内存上，他先会开辟出一个新的内存空间，存放str3 = str1+str2，然后再把str3的引用交给str1.
        // 如果使用StringBuffer呢？则是在str1后面“接”上的，完成过程只后str1,str2俩个对象。
        String name=this.getClass().getName();
        StringBuffer sb=new StringBuffer();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
    }
    public void insert(T var1) {

    }

    public int update(Map<String, Object> updatingFields, P param) throws Exception {
        if(param != null && updatingFields != null && !updatingFields.isEmpty()) {
            HashMap map = new HashMap();
            map.put("upd_nest_qry_param", param);
            map.put("upd_nest_entity", updatingFields);
            int result = this.sqlSessionTemplate.update(this.getStatement("updateBy"), map);
            return result;
        } else {
            throw new Exception("error");
        }
    }

    public int deleteBy(P var1) {
        return 0;
    }

    public List<T> listBy(P var1) {
        return null;
    }

    public T getBy(P var1) {
        return null;
    }

}
