package org.chzz.mvp.model.bean;

import org.chzz.mvp.base.BaseEntity;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/3/26
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/3/26--14:23
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class UserLoginEntity extends BaseEntity {

    /**
     * hospitalId : 1
     * id : 2518
     * cookie : A2FE45DB49EB34FD7E3F2BBFC3EDB35A
     * managerTypeCode : 3
     * managerTypeValue : 实习生
     * hospitalName : 深圳市人民医院
     */

    private MsgEntity msg;

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public static class MsgEntity {
        private int hospitalId;
        private int id;
        private String cookie;
        private int managerTypeCode;
        private String managerTypeValue;
        private String hospitalName;

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public int getManagerTypeCode() {
            return managerTypeCode;
        }

        public void setManagerTypeCode(int managerTypeCode) {
            this.managerTypeCode = managerTypeCode;
        }

        public void setManagerTypeValue(String managerTypeValue) {
            this.managerTypeValue = managerTypeValue;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public int getId() {
            return id;
        }

        public String getCookie() {
            return cookie;
        }



        public String getManagerTypeValue() {
            return managerTypeValue;
        }

        public String getHospitalName() {
            return hospitalName;
        }
    }
}
