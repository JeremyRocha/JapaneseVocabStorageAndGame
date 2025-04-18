package PersonalProject;


import java.util.List;
import java.util.Scanner;

/*
- Jeremy Rocha
- Japanese Vocab Storage/Game
- Started: Feb 3rd 2025
- Updated: Feb 7th 2025
 */

/**The main class for running most of the program*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //Creates a scanner to get input
        List<VocabEntry> loadedVocab = ProgramStorage.entryLoading(); //Loads the list of entries into loadedVocab
        EntryHolder entryHolder = new EntryHolder(); //Creates a new instance of object entryHolder
        for(VocabEntry entry : loadedVocab) { //For loop to add entries from loadedVocab into entryHolder
            entryHolder.addEntry(entry); //Add an entry into entryHolder
        }
        boolean prompt = false; //Boolean for menu prompt

        while (!prompt) {
            menu(); //Calls menu method
            String choice = sc.nextLine(); //String to hold the users choice
            switch (choice) { //Switch to deal with multiple choices(cases)
                case "1":
                    addEntry(sc, entryHolder); //Calls addEntry method with parameters scanner and entryHolder
                    break; //Breaks from switch
                case "2":
                    editEntry(sc, entryHolder); //Call editEntry with parameters for scanner and entryHolder
                    break;
                case "3":
                    viewTable(entryHolder); //Calls viewTable method with parameters entry holder
                    break;
                case "4":
                    prompt = true; //Set prompt to true
                    System.out.println("Goodbye!"); //Prints goodbye message
                    break;
                default:
                    System.out.println("Invalid input please try again!"); //Invalid input print
            }
            System.out.println();
        }
        sc.close();
    }

    /**Method for holding menu print*/
    private static void menu() {
        System.out.println("Welcome to your personal project!"); //Prints in console
        System.out.println("1. Add Entry"); //Prints in console
        System.out.println("2. Edit Entry"); //Prints in console
        System.out.println("3. View Table"); //Prints in console
        System.out.println("4. Exit"); //Prints in console
        System.out.println("Enter your choice: "); //Prints in console
    }
    private static void addEntry(Scanner sc, EntryHolder entryHolder) {
        System.out.println("Enter the romanji for the entry you would like to add: "); //Prints in console
        String roman = sc.nextLine(); //String to hold roman
        System.out.println("Enter the kana for the entry: "); //Prints in console
        String kana = sc.nextLine(); //String for Kana
        System.out.println("Enter the kanji for the entry: ");//Prints in console
        String kanji = sc.nextLine(); //String for kanji
        System.out.println("Enter the english meaning for the entry: "); //Prints in console
        String englishMeaning = sc.nextLine(); //String for english
        System.out.println("Enter any notes for the entry: "); //Prints in console
        String notes = sc.nextLine(); //String for note
        VocabEntry entry = new VocabEntry(roman, kana, kanji, englishMeaning, notes); //Adds the entry by passing the variable to constructor
        entryHolder.addEntry(entry); //Adds the entry to stored entries
        ProgramStorage.entrySaving(entryHolder.getEntries()); //Saves the entries to json file
    }
    private static void editEntry(Scanner sc, EntryHolder entryHolder) {
        boolean editing = true; //Boolean  to control follow
        while(editing) {
            System.out.println("Enter search term(romanji, kana, kanji, english for the entry): "); //Prints in console
            String search = sc.nextLine(); //String to hold search

            entryHolder.displayEntries(search); //Pass the search to the display entry method
            System.out.println("Enter the number of the entry you would like to edit: "); //Prints in console

            int selection = sc.nextInt() - 1; //Store int for selection
            sc.nextLine(); //clears for next line
            VocabEntry selectedEntry = entryHolder.selectEntry(selection); //Pulls selected entry from method

            if (selectedEntry != null) {
                System.out.println("Editing Entry: " + selectedEntry.getRomanji() + "|"
                        + selectedEntry.getKana() + "|" + selectedEntry.getKanji() + "|"
                        + selectedEntry.getEnglish() + "|" + selectedEntry.getNotes()); //Prints out the entry
                System.out.println("Enter the new value for romanji or leave blank to keep it: "); //Prints out message
                String updater = sc.nextLine(); //String to hold updated part
                if (updater.isEmpty()) {
                    System.out.println("You kept: " + selectedEntry.getRomanji()); //Prints out original entry
                } else {
                    entryHolder.updateEntry(selectedEntry, updater, selectedEntry.getKana(), selectedEntry.getKanji(), selectedEntry.getEnglish(), selectedEntry.getNotes()); //Pass update string to method
                }
                System.out.println("Enter the new value for kana or leave blank to keep it: "); //Prints out message
                updater = sc.nextLine(); //String to hold update
                if (updater.isEmpty()) {
                    System.out.println("You kept: " + selectedEntry.getKana()); //Prints out original entry
                }
                else{
                    entryHolder.updateEntry(selectedEntry, selectedEntry.getRomanji(), updater, selectedEntry.getKanji(), selectedEntry.getEnglish(), selectedEntry.getNotes());//Pass update string to method
                }
                updater = sc.nextLine(); //String to hold update
                if (updater.isEmpty()) {
                    System.out.println("You kept: " + selectedEntry.getKanji()); //Prints out original entry
                }
                else{
                    entryHolder.updateEntry(selectedEntry, selectedEntry.getRomanji(), selectedEntry.getKana(), updater, selectedEntry.getEnglish(), selectedEntry.getNotes()); //Pass update string to method
                }
                updater = sc.nextLine(); //String to hold update
                if (updater.isEmpty()) {
                    System.out.println("You kept: " + selectedEntry.getEnglish()); //Prints out original entry
                }
                else{
                    entryHolder.updateEntry(selectedEntry, selectedEntry.getRomanji(), selectedEntry.getKana(), selectedEntry.getKanji(), updater, selectedEntry.getNotes()); //Pass update string to method
                }
                updater = sc.nextLine(); //String to hold update
                if (updater.isEmpty()) {
                    System.out.println("You kept: " + selectedEntry.getNotes()); //Prints out original entry
                }
                else{
                    entryHolder.updateEntry(selectedEntry, selectedEntry.getRomanji(), selectedEntry.getKana(), selectedEntry.getKanji(), selectedEntry.getEnglish(), updater); //Pass update string to method
                }
                System.out.println("Your update Entry has been successfully updated!");
                System.out.println(selectedEntry);
                System.out.println("Press enter to continue...");

                updater = sc.nextLine(); //Get user input to continue. Used update so I didn't have to make a new variable
                if(updater.isEmpty()) {
                    editing = false; //Set editing to false to exit loop
                    ProgramStorage.entrySaving(entryHolder.getEntries()); //Save updated entry to json
                }
            } else {
                System.out.println("Entry not found!");
            }
        }
    }
    private static void viewTable(EntryHolder entryHolder) {
        List<VocabEntry> entries = entryHolder.getEntries();

        if (entries.isEmpty()) {
            System.out.println("No entries available.");
            return;
        }

        System.out.println("Displaying all vocab entries:\n");

        // Define fixed column widths
        int romanjiWidth = 30;
        int kanaWidth = 30;
        int kanjiWidth = 30;
        int englishWidth = 45;
        int notesWidth = 100; // Adjust the width of the Notes column to your needs

        // Print a header
        System.out.printf("%-" + romanjiWidth + "s %-"
                + kanaWidth + "s %-"
                + kanjiWidth + "s %-"
                + englishWidth + "s %-"
                + notesWidth + "s\n", "Romanji", "Kana", "Kanji", "English", "Notes");

        // Print a separator line that goes to the end of the Notes column
        System.out.println("-".repeat(romanjiWidth + kanaWidth + kanjiWidth + englishWidth + notesWidth));

        // Print each entry
        for (VocabEntry entry : entries) {
            // Pad strings to ensure each column has a fixed width
            String romanji = padToWidth(entry.getRomanji(), romanjiWidth);
            String kana = padToWidth(entry.getKana(), kanaWidth);
            String kanji = padToWidth(entry.getKanji(), kanjiWidth);
            String english = padToWidth(entry.getEnglish(), englishWidth);
            String notes = padToWidth(entry.getNotes(), notesWidth);

            // Print the padded entry
            System.out.printf("%s %s %s %s %s\n", romanji, kana, kanji, english, notes);
        }
    }

    // Helper method to pad a string to a specific width
    private static String padToWidth(String str, int width) {
        if (str.length() >= width) {
            return str.substring(0, width); // Truncate if the string is longer than the specified width
        }
        // Pad the string with spaces to the specified width
        return String.format("%-" + width + "s", str);
    }
}