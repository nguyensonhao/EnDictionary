package ObjectInfos;

import Exceptions.TypeException;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class Meaning {
    private int wId;
    private int tId;
    private String mText;

    /**
     * Constructor 
     * @param wId
     * @param tId
     * @param mText
     * @throws TypeException
     */
    public Meaning(int wId, int tId, String mText) throws TypeException {
        this.setwId(wId);
        this.settId(tId);
        this.setmText(mText);
    }

    /**
     * Get wId for meaning
     * @return
     */
    public int getwId() {
        return wId;
    }

    /**
     * set wId for Meaning
     * @param wId
     * @throws TypeException
     */
    public void setwId(int wId) throws TypeException {
        if(wId < 0){
            throw new TypeException("Word ID must be gearter than 0");
        }else{
             this.wId = wId;
        }      
    }

    /**
     * Get tId for meaning
     * @return
     */
    public int gettId() {
        
        return tId;
    }

    /**
     * set Id for meaning
     * @param tId
     * @throws TypeException
     */
    public void settId(int tId) throws TypeException {
        if (tId < 0) {
            throw new TypeException("Type ID must be greater than 0");
        } else {
            this.tId = tId;
        }
    }

    /**
     * get mText for meaning
     * @return
     */
    public String getmText() {
        return mText;
    }

    /**
     * Set mText for meaning
     * @param mText
     * @throws TypeException
     */
    public void setmText(String mText) throws TypeException {
        if(mText.equals("")){
            throw new TypeException("Meaning can't be empty ");
        }else{
            this.mText = mText;
        }
    }

    @Override
    public String toString() {
        return "Meaning{" + "wId=" + wId + ", tId=" + tId + ", mText=" + mText + '}';
    }
    
}
