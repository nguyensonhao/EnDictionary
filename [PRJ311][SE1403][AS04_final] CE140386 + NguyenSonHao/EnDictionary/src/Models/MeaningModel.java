package Models;

import Exceptions.TypeException;
import ObjectInfos.Meaning;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class MeaningModel {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private String sql = null;
    private ArrayList<Meaning> meanings = null;
    private ArrayList<Meaning>result = null;

    /**
     * Connection Creates arrayList Meaning
     *
     * @param conn
     */
    public MeaningModel(Connection conn) {
        this.conn = conn;
        pst = null;
        rs = null;
        sql = null;
        meanings = new ArrayList<Meaning>();
    }

    /**
     * Load data from data base
     *
     * @throws SQLException
     * @throws TypeException
     */
    public void load() throws SQLException, TypeException {
        meanings = new ArrayList<Meaning>();
        sql = "SELECT * FROM `meaning` WHERE 1";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();

        int wId;
        int tId;
        String mText;

        while (rs.next()) {
            wId = rs.getInt("wId");
            tId = rs.getInt("tId");
            mText = rs.getString("mText");
            meanings.add(new Meaning(wId, tId, mText));
        }
    }

    /**
     * Insert to data base
     *
     * @param wId
     * @param tId
     * @param mText
     * @return
     */
    public String insert(int wId, int tId, String mText) {
        try {
            sql = "INSERT INTO `meaning`(`wId`, `tId`, `mText`) VALUES (?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, wId);
            pst.setInt(2, tId);
            pst.setString(3, mText);
            pst.execute();
            
            this.load();// load new database form server
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Delete from Data base
     *
     * @param wId
     * @return
     */
    public String delete(int wId, int tId) {
        try {
            sql = "DELETE FROM `meaning` WHERE `wId`=? and `tId`=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, wId);
            pst.setInt(2, tId);
            pst.execute();
            
             this.load();// load new database form server
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Update data base
     * @param wId
     * @param new_wId
     * @return
     */
    public String update(int wId, int tId, int new_tId, String New_mText) {
        try {
            sql = "UPDATE `meaning` SET `tId`= ?, `mText`= ? WHERE `wId` = ? and `tId`=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, new_tId);
            pst.setString(2, New_mText);
            pst.setInt(3, wId);
            pst.setInt(4, tId);
            pst.execute();
            
             this.load();// load new database form server
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    /**
     * Search meaning by ID
     * @param wId
     * @return 
     */
        public Meaning searchById(int wId) {
        for (Meaning meaning : meanings) {
            if (meaning.getwId() == wId) {
                return meaning;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        int no = 0;
        for (Meaning meaning : meanings) {
            ++no;
            str += "#" + no + ". " + meaning.gettId() + ". " + meaning.getmText() + "\n";
        }
        return str;
    }
    /**
     * Size of word meaning
     * @return 
     */
    public int getSize() {
        return meanings.size();
    }

    /**
     * add all meaning 
     * @param wId
     * @return 
     */
    public ArrayList<Meaning> getMeanings(int wId) {
        result = new ArrayList<>();

        for (Meaning meaning : meanings) {
            if (meaning.getwId() == wId) {
                result.add(meaning);
            }
        }
        return result;
    }
}
