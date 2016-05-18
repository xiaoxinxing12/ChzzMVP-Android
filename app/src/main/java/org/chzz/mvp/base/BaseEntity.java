package org.chzz.mvp.base;

import java.io.Serializable;
import java.util.Map;

import rx.Observable;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/3/21
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/21--16:56
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class BaseEntity implements Serializable {

    /**
     * code : 0
     * desc : success
     * msg : {"hospitalId":1,"id":2518,"cookie":"A2FE45DB49EB34FD7E3F2BBFC3EDB35A","managerTypeCode":"3","managerTypeValue":"实习生","hospitalName":"深圳市人民医院"}
     */

    private int code;
    private String desc;


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    interface IListBean {
        Observable getPageAt(int page);

        void setParam(Map<String, String> param);
    }

    class BaseBean implements Serializable {
        public long id;
        public String objectId;
        public Map<String, String> param;
    }

   public    abstract class ListBean extends BaseBean implements IListBean {
        @Override
        public void setParam(Map<String, String> param) {
            this.param = param;
        }
    }
}
