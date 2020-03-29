package ObjectInfos;

import Exceptions.TypeException;

/**
 *
 * @author Admin
 */
public final class Type{

    private int tId;
    private String tText;

    /**
     * Constructor 
     * @param tId
     * @param tText
     * @throws TypeException
     */
    public Type(int tId, String tText) throws TypeException {
        this.settId(tId);
        this.settText(tText);
    }

    /**
     * Get tId Type
     * @return
     */
    public int gettId() {
        return tId;
    }

    /**
     * Set tId for Type
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
     * Get tText for Type 
     * @return
     */
    public String gettText() {
        return tText;
    }

    /**
     * Set tText for Type
     * @param tText
     * @throws TypeException
     */
    public void settText(String tText) throws TypeException {
        if (tText.equals("")) {
            throw new TypeException("Type name can't be empty");
        } else {
            this.tText = tText;
        }
    }

    @Override
    public String toString() {
        return "Type{" + "tId=" + tId + ", tText=" + tText + '}';
    }
    
}
