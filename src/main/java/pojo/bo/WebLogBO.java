package pojo.bo;

import java.util.Date;

/**
 * Created by LYL on 2017/10/24.
 */
public class WebLogBO {

    private String titleName;
    private Long webcount;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Long getWebcount() {
        return webcount;
    }

    public void setWebcount(Long webcount) {
        this.webcount = webcount;
    }

    @Override
    public String toString() {
        return "WebLogBO{" +
                "titleName='" + titleName + '\'' +
                ", count='" + webcount + '\'' +
                '}';
    }
}


