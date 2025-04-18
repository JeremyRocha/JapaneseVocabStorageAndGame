package PersonalProject;

import java.util.ArrayList;
import java.util.List;
/**Class for managing/viewing Entries */
public class EntryHolder {
    private final List<VocabEntry> entries;
    /** Constructor Manager
     * Creates an empty array to hold objects(individual entry) from VocabEntry */
    public EntryHolder() {
        entries = new ArrayList<VocabEntry>();
    }
    /**Method for adding an entry to the entries list
     * @param entry Call check entry method to see if entry is not a duplicate
     * @return Adds the entry(object) from VocabEntry if no duplicate is found*/
    public boolean addEntry(VocabEntry entry) {
        if(checkEntry(entry)) { //Check the entry against conditions in checkEntry
            System.err.println("Duplicate entry found!"); //Prints if duplicate is found
            return false;
        }
        entries.add(entry); //Adds the individual entry to the entry list
        System.out.println("Entry added successfully!"); //Prints if entry was added correctly
        return true; //Return true
    }
    /**Method to check if the entry already exist
     * @return true if a duplicate is found or false if no duplicate is found */
    public boolean checkEntry(VocabEntry entry) {
        for (VocabEntry existingEntry : entries) { // For loop to cycle existing entries
            if(existingEntry.getRomanji().equalsIgnoreCase(entry.getRomanji()) &&
            existingEntry.getKana().equalsIgnoreCase(entry.getKana()) &&
            existingEntry.getKanji().equalsIgnoreCase(entry.getKanji()) &&
            existingEntry.getEnglish().equalsIgnoreCase(entry.getEnglish())) {
                //If statement for conditions to determine a duplicate entry
                return true;
            }
        }
        return false;
    }

    /**Method for display entries
     * @param search Look for entries that match the value of search */
    public void displayEntries(String search) {
        boolean found = false;

        for (VocabEntry entry : entries) { //fg
            if (entry.getKana().toLowerCase().contains(search.toLowerCase())
                    || entry.getKanji().toLowerCase().contains(search.toLowerCase())
                    || entry.getRomanji().toLowerCase().contains(search.toLowerCase())
                    || entry.getEnglish().toLowerCase().contains(search.toLowerCase())) {
                System.out.println(entry.getRomanji() + " | " + entry.getKana() + " | " + entry.getKanji() + " | " + entry.getEnglish() + " | " + entry.getNotes());
                found = true;
            }
        }
        if(!found) {
            System.err.println("No entries found for search: " + search);
        }
    }

    /**Method for selecting the entry to edit
     * @param index is the number inputted by the user */
    public VocabEntry selectEntry(int index) {
        if(index >= 0 && index < entries.size()) {
            return entries.get(index);
        }
        return null; //return null
    }
    /**Method for updating an entry
     * Sets the value based on the input received */
    public void updateEntry(VocabEntry entry, String romanji, String kana, String kanji, String english, String notes) {
        entry.setRomanji(romanji);
        entry.setKana(kana);
        entry.setKanji(kanji);
        entry.setEnglish(english);
        entry.setNotes(notes);
    }
    /**Getter to get the list of entries
     * @return List of VocabEntry Object(the individual entries)*/
    public List<VocabEntry> getEntries() {
        return entries;
    }

}
