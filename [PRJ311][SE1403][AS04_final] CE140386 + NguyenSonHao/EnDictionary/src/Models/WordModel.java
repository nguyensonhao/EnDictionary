package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ObjectInfos.Word;
import ObjectInfos.Meaning;
import ObjectInfos.Type;
import java.util.Random;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class WordModel {

    private Connection conn = null; // connection
    private PreparedStatement pst = null;// 
    private ResultSet rs = null;
    private String sql = "";
    private ArrayList<Word> words = null;// Creates arraylist
    private ArrayList<Word> result = null;// creates arraylist for seach...
    private ArrayList<String> dictionary = null;
    private ArrayList<Word> test = null;
    private static Random rand;
    private TypeModel tm = null;
    private MeaningModel mm = null;
    
    /**
     * Connect data base word model
     *
     * @param conn
     */
    public WordModel(Connection conn, TypeModel tm, MeaningModel mm){
        
        rand = new Random();
        this.conn = conn;
        this.tm = tm;
        this.mm = mm;
        
        words = new ArrayList<Word>();
        result = new ArrayList<Word>();
        test = new ArrayList<Word>();
        pst = null;
        rs = null;
        sql = "";
    }

    /**
     * Load data word in data base
     *
     * @throws SQLException
     */
    public void load() throws SQLException {
        sql = "SELECT * FROM `word` ORDER BY `wText` ASC";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();

        int wId;
        String wText;
        words = new ArrayList<Word>();

        while (rs.next()) {
            wId = rs.getInt("wId");
            wText = rs.getString("wText");
            words.add(new Word(wId, wText));
        }
    }

    /**
     * Insert data word to database
     *
     * @param wText
     * @return
     */
    public String insert(String wText) {
        try {
            sql = "INSERT INTO `word`(`wText`) VALUES (?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, wText);
            pst.execute();
            
            this.load();
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Delete data word
     *
     * @param wId
     * @return
     */
    public String delete(int wId) {
        try {
            sql = "DELETE FROM `word` WHERE `wId`=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, wId);
            pst.execute();
            this.load();

            sql = "alter table `word` modify `wid` int not null AUTO_INCREMENT, AUTO_INCREMENT = " + wId;
            pst = conn.prepareStatement(sql);
            pst.execute();

            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Update data word
     *
     * @param wId
     * @param new_wId
     * @return
     */
    public String update(int wId, String new_wId) {
        try {
            sql = "UPDATE `word` SET `wText`=? WHERE `wId`=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, new_wId);
            pst.setInt(2, wId);
            pst.execute();
            
            this.load();
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public boolean isWordExist(int wId) {
        return searchById(wId) != null;
    }

    /**
     * Search word by ID
     * @param wId
     * @return 
     */
    public Word searchById(int wId) {
        for (Word word : words) {
            if (word.getwId() == wId) {
                return word;
            }
        }
        return null;
    }

    /**
     * Search by full word
     * @param wText
     * @return 
     */
    public Word searchByFullWord(String wText) {
        for (Word word : words) {
            if (word.getwText().equals(wText)) {
                return word;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        int no = 0;
        for (Word word : words) {
            ++no;
            str += "#" + no + ". " + word.getwText() + "\n";
        }
        return str;
    }
    /**
     * Size of word
     * @return 
     */
    public int getSize() {
        return words.size();
    }
    /**
     * Search word
     * @param userInput
     * @return 
     */
    public ArrayList<Word> search(String userInput) {
        result.clear();
        
        String wText = "";
        userInput = userInput.toLowerCase();
        for(Word word : words){
            wText = word.getwText().toLowerCase();
            if(wText.startsWith(userInput)){
                result.add(word);
            }
        }
        return result;
    }
    /**
     * Show word
     * @param wId
     * @return 
     */
    public String showWord(int wId){
        String strHTML = "";
        Word word = searchById(wId);
        ArrayList<Meaning> meanings = mm.getMeanings(wId);
        //show the word
        strHTML += "<h1><font color='#ff0000'>" + word + "</font></h1>";
        
        //Show meanging
        int tId;
        Type type;
        String tText;
        //String mean;
        for (Meaning meaning : meanings) {
            //show typee
            tId = meaning.gettId();
            type = tm.searchById(tId);
            tText = type.gettText();
            strHTML += "<b><i><font color='#0000ff'>["+ tText+"]</font></i></b><br/>";
            //Show mean
            strHTML += "<blockquote>#" + meaning.getmText() +"</blockquote><br/>";
        }
        return strHTML;
    }
    /**
     * Show dictionary and first character of word
     * @return 
     */
    public  ArrayList<String> showDictionary(){
        String oldWord = " ";
        String newWord = "";
        
        dictionary = new ArrayList<String>();
        
        for(Word word : words ){
            newWord = word.getwText();
            
            //---c--
            
            if(oldWord.charAt(0)!=newWord.charAt(0)){
                dictionary.add("---" + newWord.charAt(0) + "---");
            }
            dictionary.add(newWord);
            oldWord = newWord;
        }
        return dictionary;
    }
    
    /**
     * Show meaning not show word
     * @param wId
     * @return 
     */
    public String showMeaningOnly(int wId){
         String strHTML = "";
        Word word = searchById(wId);
        ArrayList<Meaning> meanings = mm.getMeanings(wId);
        int tId;
        Type type;
        String tText;
        //String mean;
        for (Meaning meaning : meanings) {
            //show typee
            tId = meaning.gettId();
            type = tm.searchById(tId);
            tText = type.gettText();
            strHTML += "<b><i><font color='#0000ff'>["+ tText+"]</font></i></b>";
            //Show mean
            strHTML += "<blockquote>#" + meaning.getmText() +"</blockquote><br/>";

        }
        return strHTML;
    }
    /**
     * Random id
     * @param m
     * @param n
     * @return 
     */
    public static int random(int m, int n){
        return m + rand.nextInt(n - m + 1);
    }
    
    /**
     * Check isShuffle in word  model
     * @param wNumber
     * @param isShuffle
     * @return 
     */
    public ArrayList<Word> generateTest(int wNumber, boolean isShuffle) {
        test.clear();
        int[] indexies = new int[words.size()];
        for (int i = 0; i < words.size(); i++) {
            indexies[i] = i;
        }
        if (isShuffle) {
            int j, tmp;
            for (int i = 0; i < words.size(); i++) {
                j = random(0, words.size() - 1);
                //Swap i, j
                tmp = indexies[i];
                indexies[i] = indexies[j];
                indexies[j] = tmp;
            }
        }
        
        Word word;
        int index;
        for(int i = 0; i< wNumber;i++){
            index = indexies[i];
            word = words.get(index);
            test.add(word);
        }
        
        return test;
    }
    /**
     * Check word correct
     * @param wId
     * @param userInput
     * @return 
     */
    public boolean isWordCorrect(int wId, String userInput){
        Word word = searchById(wId);
        return  word.isCorrect(userInput);
    }
}
