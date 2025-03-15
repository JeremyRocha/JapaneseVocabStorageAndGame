package PersonalProject;

import com.fasterxml.jackson.annotation.JsonProperty;

/**Class for holding the variable that make up one entry */
public class VocabEntry {
    private String romanji, kana, kanji, english, notes;

    /**Constructor to initialise the value of the object when it is created*/
    public VocabEntry(@JsonProperty("romanji") String romanji,@JsonProperty("kana") String kana,@JsonProperty("kanji") String kanji,@JsonProperty("english") String english,@JsonProperty("notes") String notes) {
        this.romanji = romanji;
        this.kana = kana;
        this.kanji = kanji;
        this.english = english;
        this.notes = notes;
    }
    /**Getter to retrieve the value of notes */
    public String getNotes() {
        return notes;
    }
   /**Setter for setting the value of notes */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**Getter to retrieve the value of Kanji */
    public String getKanji() {
        return kanji;
    }
    /**Setter for setting the value of Kanji */
    public void setKanji(String kanji) {
        this.kanji = kanji;
    }
    /**Getter to retrieve the value of English */
    public String getEnglish() {
        return english;
    }
    /**Setter for setting the value of english */
    public void setEnglish(String english) {
        this.english = english;
    }
    /**Getter to retrieve the value of Kana */
    public String getKana() {
        return kana;
    }
    /**Setter for setting the value of Kana*/
    public void setKana(String kana) {
        this.kana = kana;
    }
    /**Getter to retrieve the value of Romanji */
    public String getRomanji() {
        return romanji;
    }
    /**Setter for setting the value of romanji  */
    public void setRomanji(String romanji) {
        this.romanji = romanji;
    }

    @Override
    public String toString() {
        return "The Updated Entry is: | " +
                "Romanji = " + romanji + " | " +
                "Kana = " + kana + " | " +
                "Kanji = " + kanji + " | " +
                "English = " + english + " | " +
                "Notes = " + notes + " | ";
    }
}
