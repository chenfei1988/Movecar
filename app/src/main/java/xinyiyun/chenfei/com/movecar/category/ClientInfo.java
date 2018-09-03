package xinyiyun.chenfei.com.movecar.category;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2018/2/23.
 */

@Entity
public class ClientInfo {

    /**
     * $type : MoveBus.KD_GetClientInfo, MoveBus, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
     * companyid : 1272
     * khqc : 出租车
     * CompanyLong : 出租车
     * userid : 111
     */
    private Integer companyid;
    private String khqc;
    private String CompanyLong;
    private String userid;
    /**
     * 列表描述
     */
    private String Des;
    /**
     * 显示数量
     */
    private Integer Total = 0;
    /**
     * 选中状态
     * 注：0(全部选中)，1(全部不选中)，2(部分选中)
     */
    private Integer State = -1;





    @Generated(hash = 1235582029)
    public ClientInfo(Integer companyid, String khqc, String CompanyLong, String userid, String Des,
            Integer Total, Integer State) {
        this.companyid = companyid;
        this.khqc = khqc;
        this.CompanyLong = CompanyLong;
        this.userid = userid;
        this.Des = Des;
        this.Total = Total;
        this.State = State;
    }
    @Generated(hash = 1999466069)
    public ClientInfo() {
    }




   
    public Integer getCompanyid() {
        return this.companyid;
    }
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
    public String getKhqc() {
        return this.khqc;
    }
    public void setKhqc(String khqc) {
        this.khqc = khqc;
    }
    public String getCompanyLong() {
        return this.CompanyLong;
    }
    public void setCompanyLong(String CompanyLong) {
        this.CompanyLong = CompanyLong;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public Integer getTotal() {
        return this.Total;
    }
    public void setTotal(Integer Total) {
        this.Total = Total;
    }
    public Integer getState() {
        return this.State;
    }
    public void setState(Integer State) {
        this.State = State;
    }
    public String getDes() {
        return this.Des;
    }
    public void setDes(String Des) {
        this.Des = Des;
    }
}
