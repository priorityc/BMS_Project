

    import java.util.Objects;

    /**
     * Record details for each Report. Reports have a different types, 
     * users are advised to specify types with other fields.
     *
     * @author (Petya Dimitrova)
     * @version version 1, 31/03/24)
     */
    public class Report implements Comparable<Report>
    {
    
        // number id of report
        private int numberID;
        //who did the report
        private String name;
        //date of the report;
        private String date;
        //location of the report-city;
        private String location;
        //duration of the observation in seconds
        private int duration;
        //magnitude of the meteor observed
        private int magnitude;
        //potential fall
        private boolean isPotentialFall;
        /**
         * Create a record of a report 
         * * @param spotter The ID of the report
         * @param name of reporter
         * Question 2 b. i
         * @param count How many were seen (>= 0).
         * @param area The ID of the area in which they were seen.
         * @param period The reporting period.
         */
        public Report(int numberID, String name, String date, String location, int duration, int magnitude, boolean isPotentialFall)
        {
            this.numberID = numberID;
            this.name = name;
            this.date = date;
            this.location = location;
            this.duration = duration;
            this.magnitude = magnitude;
            this.isPotentialFall = isPotentialFall;
        }
    
        /**
         * Return the name of the observer.
         * Question 2 b. i
         * @return the observer name type.
         */
        public String getName()
        {
            return name;
        }
    
        /**
         * Return the date it was spotted.
         * Question 2 b. i
         * @return The animal type.
         */
        public String getDate()
        {
            return date;
        }
    
        /**
         * Return the ID of the report.
         * Question 2 b. i
         * @return The report's ID.
         */
        public int getnumberID()
        {
            return numberID;
        }
    
        /**
         * Return the magnitude of the meteor.
         *  Question 2 b. i
         * @return the magnitude value.
         */
        public int getMagnitude()
        {
            return magnitude;
        }
        
        /**
         * Update the magnitude of the meteor.
         *  Question 2 b. i
         * @return the magnitude value.
         */
        public int setMagnitude(int anMagnitude)
        {
            magnitude = anMagnitude;
            return magnitude;
        }
        
         /**
         * Get potential fall info.
         *  Question 2 b. i
         * @return potential fall info.
         */
        public boolean getPotentialFall()
        {
            
            return isPotentialFall;
        }
        
        /**
         * Set potential fall info.
         *  Question 2 b. i
         * @return potential fall info.
         */
        public boolean setPotentialFall(boolean isFall)
        {
            isPotentialFall = isFall;
            return isPotentialFall;
        }
        
        
        /**
         * Update the magnitude of the meteor.
         *  Question 2 b. i
         * @return the magnitude value.
         */
        public int getDuration()
        {
            
            return duration;
        }
        
    
        /**
         * Return a string containing details of the report, the numberID of the report,
         * who is the observer, where they were seen, how long is the observation,
         * what magnitude is reported.
         * @return A string giving details of the sighting.
         */
        public String getDetails()
        {
            return date +
            ", ID = " + numberID +
    
            ", name = " + name +
            ", location = " + location +
            ", duration = " +  duration +
            ", magnitude = " +  magnitude +
            ", isPotentialFall = " +  isPotentialFall;
        }
        /**
         * Return string representation of the report
         * Question 2 b. i
    
         * @return a string representation
         */
        public String toString()
        {
            String returnString = getnumberID() + "," + getName() + ","
                + getDate() + "," + location + "," + duration + "," + getMagnitude();
    
            return returnString;
        } 
    
        
    
        /**
         * Equals method for this object.
         * @return true if objects compared have the same magnitude, date, and ID
         *  Question 2 b. ii
         * 
         */
        public boolean equals(Object otherReport)
        {
            if(otherReport != null && otherReport instanceof Report)
            {
                Report r = (Report) otherReport;
                return getMagnitude() == (r.getMagnitude()) && getDate().equals(r.getDate()) && getnumberID()==(r.getnumberID());
            }
            return false;
        }
    
        /**
         * Hash code methodfor this report.
         * @returns the same hash code if two objects has content equality.
         *  Question 2 b. ii
         * have the same name and ID
         */
        public int hashCode()
        {
            return Objects.hash(getMagnitude(), getDate(), getnumberID());
        }
    
        /**
         * Method that compare this report and other report and sort it
         * Reports are compared by their ID
         * Question 2 b. ii
         * 
         * @param the report to compare to other report
         * @return negative integer if object that is compared is before other obj
         * otherwize returns positive integer and returns 0 if both reports are equal
         */
        public int compareTo(Report otherReport) 
        {
            int result = getDate().compareTo(otherReport.getDate());
            int resultTwo = magnitude-otherReport.magnitude;
            if (result == 0 && resultTwo == 0) {//if they have same date and same magnitude
    
                result = getnumberID()-otherReport.getnumberID();//sort them by ID
            }
            return result;
        }
    
    }
    

