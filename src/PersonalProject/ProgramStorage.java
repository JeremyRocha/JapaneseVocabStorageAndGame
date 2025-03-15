package PersonalProject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**Class for storing data*/
public class ProgramStorage {
    private static final String FILE_PATH = "data/vocab_entries.json";

    /**Method for saving the entry list to a JSON file */
    public static void entrySaving(List<VocabEntry> entries){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(FILE_PATH), entries);
        }
        catch (Exception e){
            System.err.println("Error while saving vocab entries");
        }
    }

    /**Method for loading entries from the JSON file */
    public static List<VocabEntry> entryLoading(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(FILE_PATH);
            if(!file.exists()){
                return new ArrayList<>();
            }
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, VocabEntry.class));
        }
        catch (Exception e){
            System.err.println("Error while loading vocab entries");
            return new ArrayList<>();
        }
    }
}
