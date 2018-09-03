package xinyiyun.chenfei.com.movecar.category;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2018/2/23.
 */
@Entity
public class TeamInfo {


    /**
     * $type : MoveBus.KD_GetTeamNoInfo, MoveBus, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
     * TeamNo : 大联盟刘浩
     * companyid : 1016
     */
    private String TeamNo;
    private int companyid;
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






    @Generated(hash = 843867051)
    public TeamInfo(String TeamNo, int companyid, String Des, Integer Total, Integer State) {
        this.TeamNo = TeamNo;
        this.companyid = companyid;
        this.Des = Des;
        this.Total = Total;
        this.State = State;
    }

    @Generated(hash = 42623532)
    public TeamInfo() {
    }


  



    public String getTeamNo() {
        return TeamNo;
    }

    public void setTeamNo(String TeamNo) {
        this.TeamNo = TeamNo;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getDes() {
        return this.Des;
    }

    public void setDes(String Des) {
        this.Des = Des;
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
}
