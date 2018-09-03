package xinyiyun.chenfei.com.movecar.category;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import ittiger.indexlist.entity.BaseEntity;

/**
 * Created by Administrator on 2018/2/23.
 */
@Entity
public class VehicleInfo implements BaseEntity {


    /**
     * $type : MoveBus.KD_GetvehicleInfo, MoveBus, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
     * divers :
     * Deviceid : 13300042464
     * cameraTotal : 4
     * VehId : 233
     * Cph : 渝AG2673
     * IpAddress : 013300042464
     * platecolor : 黄色
     * LineId : 荣昌-重庆
     * TeamNo : 高速车队
     * client : 27
     * khqc : 长运荣昌分公司
     * CompanyLong : 长运荣昌分公司
     * userid : 111
     * plat : 0
     */

    @Id(autoincrement = true)
    private Long id;
    private Integer VehId;
    private String divers;
    private String Deviceid;
    private int cameraTotal;
    private String Cph;
    private String IpAddress;
    private String platecolor;
    private String LineId;
    private String TeamNo;
    private int client;
    private String khqc;
    private String CompanyLong;
    private String userid;
    private int plat;
    private int IsSelected;
    private int IsShoucang;



    @Generated(hash = 88115273)
    public VehicleInfo(Long id, Integer VehId, String divers, String Deviceid, int cameraTotal,
            String Cph, String IpAddress, String platecolor, String LineId, String TeamNo, int client,
            String khqc, String CompanyLong, String userid, int plat, int IsSelected, int IsShoucang) {
        this.id = id;
        this.VehId = VehId;
        this.divers = divers;
        this.Deviceid = Deviceid;
        this.cameraTotal = cameraTotal;
        this.Cph = Cph;
        this.IpAddress = IpAddress;
        this.platecolor = platecolor;
        this.LineId = LineId;
        this.TeamNo = TeamNo;
        this.client = client;
        this.khqc = khqc;
        this.CompanyLong = CompanyLong;
        this.userid = userid;
        this.plat = plat;
        this.IsSelected = IsSelected;
        this.IsShoucang = IsShoucang;
    }
    @Generated(hash = 280466907)
    public VehicleInfo() {
    }


  
    public String getDivers() {
        return this.divers;
    }
    public void setDivers(String divers) {
        this.divers = divers;
    }
    public String getDeviceid() {
        return this.Deviceid;
    }
    public void setDeviceid(String Deviceid) {
        this.Deviceid = Deviceid;
    }
    public int getCameraTotal() {
        return this.cameraTotal;
    }
    public void setCameraTotal(int cameraTotal) {
        this.cameraTotal = cameraTotal;
    }
    public String getCph() {
        return this.Cph;
    }
    public void setCph(String Cph) {
        this.Cph = Cph;
    }
    public String getIpAddress() {
        return this.IpAddress;
    }
    public void setIpAddress(String IpAddress) {
        this.IpAddress = IpAddress;
    }
    public String getPlatecolor() {
        return this.platecolor;
    }
    public void setPlatecolor(String platecolor) {
        this.platecolor = platecolor;
    }
    public String getLineId() {
        return this.LineId;
    }
    public void setLineId(String LineId) {
        this.LineId = LineId;
    }
    public String getTeamNo() {
        return this.TeamNo;
    }
    public void setTeamNo(String TeamNo) {
        this.TeamNo = TeamNo;
    }
    public int getClient() {
        return this.client;
    }
    public void setClient(int client) {
        this.client = client;
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
    public int getPlat() {
        return this.plat;
    }
    public void setPlat(int plat) {
        this.plat = plat;
    }
    public int getIsSelected() {
        return this.IsSelected;
    }
    public void setIsSelected(int IsSelected) {
        this.IsSelected = IsSelected;
    }
    public int getIsShoucang() {
        return this.IsShoucang;
    }
    public void setIsShoucang(int IsShoucang) {
        this.IsShoucang = IsShoucang;
    }


    @Override
    public String getIndexField() {
        int length = getCph().length();
        if (length>1){
            return Cph.substring(length-1,length);
        }
        return "";
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getVehId() {
        return this.VehId;
    }
    public void setVehId(Integer VehId) {
        this.VehId = VehId;
    }
  
}
