package xinyiyun.chenfei.com.movecar.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import xinyiyun.chenfei.com.movecar.category.VehicleInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VEHICLE_INFO".
*/
public class VehicleInfoDao extends AbstractDao<VehicleInfo, Long> {

    public static final String TABLENAME = "VEHICLE_INFO";

    /**
     * Properties of entity VehicleInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property VehId = new Property(1, Integer.class, "VehId", false, "VEH_ID");
        public final static Property Divers = new Property(2, String.class, "divers", false, "DIVERS");
        public final static Property Deviceid = new Property(3, String.class, "Deviceid", false, "DEVICEID");
        public final static Property CameraTotal = new Property(4, int.class, "cameraTotal", false, "CAMERA_TOTAL");
        public final static Property Cph = new Property(5, String.class, "Cph", false, "CPH");
        public final static Property IpAddress = new Property(6, String.class, "IpAddress", false, "IP_ADDRESS");
        public final static Property Platecolor = new Property(7, String.class, "platecolor", false, "PLATECOLOR");
        public final static Property LineId = new Property(8, String.class, "LineId", false, "LINE_ID");
        public final static Property TeamNo = new Property(9, String.class, "TeamNo", false, "TEAM_NO");
        public final static Property Client = new Property(10, int.class, "client", false, "CLIENT");
        public final static Property Khqc = new Property(11, String.class, "khqc", false, "KHQC");
        public final static Property CompanyLong = new Property(12, String.class, "CompanyLong", false, "COMPANY_LONG");
        public final static Property Userid = new Property(13, String.class, "userid", false, "USERID");
        public final static Property Plat = new Property(14, int.class, "plat", false, "PLAT");
        public final static Property IsSelected = new Property(15, int.class, "IsSelected", false, "IS_SELECTED");
        public final static Property IsShoucang = new Property(16, int.class, "IsShoucang", false, "IS_SHOUCANG");
    }


    public VehicleInfoDao(DaoConfig config) {
        super(config);
    }
    
    public VehicleInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VEHICLE_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"VEH_ID\" INTEGER," + // 1: VehId
                "\"DIVERS\" TEXT," + // 2: divers
                "\"DEVICEID\" TEXT," + // 3: Deviceid
                "\"CAMERA_TOTAL\" INTEGER NOT NULL ," + // 4: cameraTotal
                "\"CPH\" TEXT," + // 5: Cph
                "\"IP_ADDRESS\" TEXT," + // 6: IpAddress
                "\"PLATECOLOR\" TEXT," + // 7: platecolor
                "\"LINE_ID\" TEXT," + // 8: LineId
                "\"TEAM_NO\" TEXT," + // 9: TeamNo
                "\"CLIENT\" INTEGER NOT NULL ," + // 10: client
                "\"KHQC\" TEXT," + // 11: khqc
                "\"COMPANY_LONG\" TEXT," + // 12: CompanyLong
                "\"USERID\" TEXT," + // 13: userid
                "\"PLAT\" INTEGER NOT NULL ," + // 14: plat
                "\"IS_SELECTED\" INTEGER NOT NULL ," + // 15: IsSelected
                "\"IS_SHOUCANG\" INTEGER NOT NULL );"); // 16: IsShoucang
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VEHICLE_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VehicleInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer VehId = entity.getVehId();
        if (VehId != null) {
            stmt.bindLong(2, VehId);
        }
 
        String divers = entity.getDivers();
        if (divers != null) {
            stmt.bindString(3, divers);
        }
 
        String Deviceid = entity.getDeviceid();
        if (Deviceid != null) {
            stmt.bindString(4, Deviceid);
        }
        stmt.bindLong(5, entity.getCameraTotal());
 
        String Cph = entity.getCph();
        if (Cph != null) {
            stmt.bindString(6, Cph);
        }
 
        String IpAddress = entity.getIpAddress();
        if (IpAddress != null) {
            stmt.bindString(7, IpAddress);
        }
 
        String platecolor = entity.getPlatecolor();
        if (platecolor != null) {
            stmt.bindString(8, platecolor);
        }
 
        String LineId = entity.getLineId();
        if (LineId != null) {
            stmt.bindString(9, LineId);
        }
 
        String TeamNo = entity.getTeamNo();
        if (TeamNo != null) {
            stmt.bindString(10, TeamNo);
        }
        stmt.bindLong(11, entity.getClient());
 
        String khqc = entity.getKhqc();
        if (khqc != null) {
            stmt.bindString(12, khqc);
        }
 
        String CompanyLong = entity.getCompanyLong();
        if (CompanyLong != null) {
            stmt.bindString(13, CompanyLong);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(14, userid);
        }
        stmt.bindLong(15, entity.getPlat());
        stmt.bindLong(16, entity.getIsSelected());
        stmt.bindLong(17, entity.getIsShoucang());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VehicleInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer VehId = entity.getVehId();
        if (VehId != null) {
            stmt.bindLong(2, VehId);
        }
 
        String divers = entity.getDivers();
        if (divers != null) {
            stmt.bindString(3, divers);
        }
 
        String Deviceid = entity.getDeviceid();
        if (Deviceid != null) {
            stmt.bindString(4, Deviceid);
        }
        stmt.bindLong(5, entity.getCameraTotal());
 
        String Cph = entity.getCph();
        if (Cph != null) {
            stmt.bindString(6, Cph);
        }
 
        String IpAddress = entity.getIpAddress();
        if (IpAddress != null) {
            stmt.bindString(7, IpAddress);
        }
 
        String platecolor = entity.getPlatecolor();
        if (platecolor != null) {
            stmt.bindString(8, platecolor);
        }
 
        String LineId = entity.getLineId();
        if (LineId != null) {
            stmt.bindString(9, LineId);
        }
 
        String TeamNo = entity.getTeamNo();
        if (TeamNo != null) {
            stmt.bindString(10, TeamNo);
        }
        stmt.bindLong(11, entity.getClient());
 
        String khqc = entity.getKhqc();
        if (khqc != null) {
            stmt.bindString(12, khqc);
        }
 
        String CompanyLong = entity.getCompanyLong();
        if (CompanyLong != null) {
            stmt.bindString(13, CompanyLong);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(14, userid);
        }
        stmt.bindLong(15, entity.getPlat());
        stmt.bindLong(16, entity.getIsSelected());
        stmt.bindLong(17, entity.getIsShoucang());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VehicleInfo readEntity(Cursor cursor, int offset) {
        VehicleInfo entity = new VehicleInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // VehId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // divers
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Deviceid
            cursor.getInt(offset + 4), // cameraTotal
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Cph
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // IpAddress
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // platecolor
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // LineId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // TeamNo
            cursor.getInt(offset + 10), // client
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // khqc
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // CompanyLong
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // userid
            cursor.getInt(offset + 14), // plat
            cursor.getInt(offset + 15), // IsSelected
            cursor.getInt(offset + 16) // IsShoucang
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VehicleInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setVehId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDivers(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeviceid(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCameraTotal(cursor.getInt(offset + 4));
        entity.setCph(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIpAddress(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPlatecolor(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLineId(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTeamNo(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setClient(cursor.getInt(offset + 10));
        entity.setKhqc(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCompanyLong(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUserid(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setPlat(cursor.getInt(offset + 14));
        entity.setIsSelected(cursor.getInt(offset + 15));
        entity.setIsShoucang(cursor.getInt(offset + 16));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VehicleInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VehicleInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VehicleInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
