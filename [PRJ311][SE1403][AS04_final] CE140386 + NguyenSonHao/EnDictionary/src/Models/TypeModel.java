package Models;

import Exceptions.TypeException;
import ObjectInfos.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class TypeModel {
   private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private String sql = "";
    private ArrayList<Type> types = null;// creates new arraylist
    private ArrayList<String> result = null;
    
    /**
     * Connection data base
     * @param conn
     */
    public TypeModel(Connection conn){
        this.conn = conn;
        pst =null;
        rs =null;
        sql = null;
        types = new ArrayList<Type>();
        result = new ArrayList<String>();
    }
    
    /**
     * Load data for Type model from data base
     * @throws SQLException
     * @throws TypeException
     */
    public void load() throws SQLException, TypeException{
        types = new ArrayList<Type>();
        sql ="SELECT * FROM `type` WHERE 1";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        int tId;
        String tText;
        
        while(rs.next()){
            tId = rs.getInt("tId");
            tText = rs.getString("tText");
            types.add(new Type(tId, tText));
        }
    }
    
    /**
     * Insert data for type 
     * @param tText
     * @return
     */
    public String insert(String tText){
        try {
            sql = "INSERT INTO `type`(`tText`) VALUES (?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tText);
            pst.execute();
            
            this.load();// load new database form server
            return "Success";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Delete data type
     * @param tId
     * @return
     */
    public String delete(int tId){
        try {
            sql = "DELETE FROM `type` WHERE `tId`=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, tId);
            pst.execute();
            
            sql = "alter table `type` modify `tId` int not null AUTO_INCREMENT, AUTO_INCREMENT = " + tId;
            pst = conn.prepareStatement(sql);
            pst.execute();
            
             this.load();// load new database form server
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    /**
     * Update data for type
     * @param tId
     * @param new_tText
     * @return
     */
    public String update(int tId, String new_tText){
        try {
            sql = "UPDATE `type` SET `tText`=? WHERE `tId`=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, new_tText);
            pst.setInt(2,tId);
            pst.execute();
            
            this.load();// load new database form server
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    } 
    /**
     * Search Type mode by Id
     * @param tId
     * @return 
     */
        public Type searchById(int tId) {
        for (Type type : types) {
            if (type.gettId() == tId) {
                return type;
            }
        }
        return null;
    }
        
     public Type searchByName(String tText) {
        for (Type type : types) {
            if (type.gettText().equals(tText)) {
                return type;
            }
        }
        return null;

    }

    @Override
    public String toString() {
        String str = "";
        int no =0;
        for(Type type : types){
            ++no;
            str += "#" + no + ". " + type.gettText() + "\n";
        }
        return str;
    } 
    /**
     * Size of type mode
     * @return 
     */
    public int getSize(){
        return types.size();
    }
    
    public ArrayList<String> getTypeList(){
        result.clear();
        for (Type type : types) {
            result.add(type.gettText());
        }
        return result;
    }
}
