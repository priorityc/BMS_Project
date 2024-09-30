import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.io.FileWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
/**
 * Monitor reports BMS(British meteor society).Monitor Reports of Meteors 
 * to classify them 
 * of different type such as Fire Ball, Bolide or Super Bolid. 
 * Also determine which reports have a potential impact with Earth and store their ID in -(meteorites) for monitoring
 * Question 3 a
 * @author (Petya Dimitrova)
 * @version (Version 1 31/03/24)
 */
public class BMS
{
    //Question 3 a.
    // stores reports to clasify them of different types
    HashMap<Report, ArrayList<String>>reports;
    //when meteor have chance to survive the Earth atmosphere is called meterite 
    //meteorites stores the ID of all reports that have potential to inpact Earth
    private ArrayList<Integer> meteorites;

    /**
     * Constructor for objects of class BMS
     * Question 3 a.
     */
    public BMS()
    {
        // initialise instance variables
        this.reports = new HashMap();
        meteorites =new ArrayList<>();
    }

    /**
     * Add Report to the map
     * Question 3 a. i.
     * @param. new report to be added
     */
    public void addReport(int number, String name, String date, String location, int duration, int magnitude, boolean isPotentialFall, String value)
    {

        Report newRep = new Report(number, name, date, location,  duration, magnitude, isPotentialFall);
        reports.put(newRep, new ArrayList<>());
        reports.get(newRep).add(value);

    }

    /**
     * Clear all enetries of the map
     * Question 3 a ii.

     */
    public void clear()
    {
        reports.clear();
    }

    /**
     * Populate with reports of different type
     * Question 3 a iii.
     */
    public void populate()
    {
        reports.clear();
        //exemple entries for testing
        Report reportOne = new Report(213, "Petya", "21/02/24", "Grays", 9, -9, false);
        Report reportTwo = new Report(113, "John", "12/12/23", "Colchester", 5, -8, false);
        Report reportThree = new Report(123, "Mark", "01/01/24", "Manchester", 3, -4, false);
        Report reportFour = new Report(128, "Mario", "01/01/24", "Manchester", 3, -3, false);
        Report reportFive = new Report(129, "Sam", "01/01/24", "Manchester", 3, -3, false);
        Report reportSix = new Report(130, "Wiliam", "01/01/24", "Manchester", 10, -18, false);

        //populate with keys and empty list as value
        reports.put(reportOne, new ArrayList<>());
        reports.put(reportTwo, new ArrayList<>());
        reports.put(reportThree, new ArrayList<>());
        reports.put(reportFour, new ArrayList<>());
        reports.put(reportFive, new ArrayList<>());
        reports.put(reportSix, new ArrayList<>());

        //selects keys to add the values
        reports.get(reportOne).add("superbolide");
        reports.get(reportTwo).add("bolide");
        reports.get(reportThree).add("fireball");
        reports.get(reportFour).add("fireball");
        reports.get(reportFive).add("fireball");
        reports.get(reportSix).add("fireball");

    }  

    /**
     * Check if the specific entry is in the map and remove it
     * Question 3 a iv.
     * @param of type fields of the object to search for in the data and remove it
     * @return true if is found or false if not.
     */
    public boolean removeEntry(int number, String name, String date, String location, int duration, int magnitude, boolean isPotentialFall)
    {
        Report nameReport = new Report(number, name, date, location, duration, magnitude, isPotentialFall);

        if (reports.containsKey(nameReport)) {//if found
            reports.remove(nameReport);//removes an entry
            return true;
        }
        return false;
    } 

    /**
     * Update field of report acording to provided magnitude value 
     * if magnitude is betwean -8 and -10 potential fall is set to true

     * Question 3 a v.
     * @param. of type Integer the specified value of magnitude
     */
    public void updateField(boolean isPotentialFall)
    {
        for (Report key :reports.keySet()) {

            if (key.getMagnitude() <= -8 && key.getMagnitude() >= -10) {

                key.setPotentialFall(isPotentialFall);

            }else {
                key.setPotentialFall(false);
            }
        }
    }

    /**
     * Get subset of reports according to Report value
     * if the Report value is of different type(fireball, bolide, super bolide) and location is the same
     * Question 3 a vi.

     * @param type of the report such as fireball, bolide or superbolide
     * @returns list of reports with value fireball;
     */
    public HashSet clasifyFireBalls(String anType)

    {

        HashSet<Report> set = new HashSet<Report>();

        for (Report key : reports.keySet())//get the keys
        {
            for (String value : reports.get(key))//get the value
            {
                if (value.equals(anType)) {

                    set.add(key);

                }

            }
        }return set;//returns fireball/bolide/superbolide set
    }

    /**
     * Returns average number of all types of meteorites 
     * 
     * a v.
     * @require param.
     */
    public double averageNumberOfAllObservations()
    {
        HashSet<Report> fSet = new HashSet<>();
        HashSet<Report> bSet = new HashSet<>();
        HashSet<Report> sSet = new HashSet<>();
        
        fSet = clasifyFireBalls("fireball");
        bSet = clasifyFireBalls("bolide");
        sSet = clasifyFireBalls("superbolide");


        double total = (fSet.size())+(bSet.size())+(sSet.size());//4+1+1

        double average = Math.round(total/4);

        return average; 
    }


    /**
     * Print all reports from reports
     * 
     * a viii.
     * @require param.
     */
    public void displayAllReports()
    {
        for (Report report : reports.keySet()) {
            for (String type : reports.get(report)) {
                
                System.out.println("Report : " + report + ", with potential fall: " + report.getPotentialFall() + ", of type: " + type);

            } 

        }

    }

    /**
     * Method that creates a file and write data for each value of the report - in a line takes an argument representing the name of the CSV (Comma-Separated Value)
     * file to be written to disk and returns true or false depending on whether
     * there was an exception when writing to the file.
     * Question 3 b.
     * @param. filename the file to write
     * @return true if writing is sucessifull and false otherwise
     */
    public boolean writeCSVFile (String filename) 
    {
        boolean sucess = false;

        //tell the filewriter which file to write
        try(BufferedWriter writer =new BufferedWriter(new FileWriter(filename))) {
            for (Report key : reports.keySet()) { 
                for (String value : reports.get(key)) { 
                    // put key and value separated by a colon 
                    writer.write(key + ","
                        +  value.toString()); 

                    // new line 
                    writer.newLine(); 

                }
            }
            sucess=true;
        }
        catch (IOException e) {
            System.err.println("There was a problem writing to " + filename);
        }

        return sucess;
    }    

    
    public void readFile(String filename)
    {
        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = reader.readLine();
            while(line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        }
        catch(FileNotFoundException e) {
            System.err.println("Unable to open " + filename);
        }
        catch(IOException e) {
            System.err.println("A problem was encountered reading " + filename);
        }
    }
    
    /**
     * Stores all report`s ID that are potential Earth fall and returns their number
     * 
     * a viii.
     * @require param.
     */
    public int storePotentialFallRepotID()
    {
        for (Report report : reports.keySet()) {
            if (report.getPotentialFall() == true) {
            meteorites.add(report.getnumberID()); 
                

            } 

        }
        return meteorites.size();
    }
    
}

