package ObjectInfos;

import Exceptions.TypeException;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class Word implements Comparable<Word>{

    private int wId;
    private String wText;

    /**
     * Constructor
     * @param wId
     * @param wText
     */
    public Word(int wId, String wText) {
        this.wId = wId;
        this.wText = wText;
    }

    /**
     * get wId for word
     * @return
     */
    public int getwId() {
        return wId;
    }

    /**
     * Set wId for Word
     * @param wId
     * @throws TypeException
     */
    public void setwId(int wId) throws TypeException {
        if (wId < 0) {
            throw new TypeException("Word ID must be gearter than 0");
        } else {
            this.wId = wId;
        }
    }

    /**
     * Get wText for word
     * @return
     */
    public String getwText() {
        return wText;
    }

    /**
     * Set wText for word
     * @param wText
     * @throws TypeException
     */
    public void setwText(String wText) throws TypeException {
        if (wText.equals("")) {
            throw new TypeException("Word name can't be empty");
        } else {
            this.wText = wText;
        }
    }
    public int compareTo(Word o){
        final Word other = (Word) o;
        return this.wText.compareTo(o.getwText());
    }
    /**
     * Use for testing. This method will compare userInput with word's text
     * @param userIput
     * @return
     */
    public boolean isCorrect(String userIput) {
       return userIput.equals(this.wText);
    }

    @Override
    public String toString() {
        return  wText;
    }

}
