/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Michael Moretti
 * @version    29 October 2018
 */
public class LogAnalyzer
{
    // Where to calculate the access counts.
    private int[] hourCounts;
    private int[] dailyCounts;// Where to calculate the hourly access counts.
    private int[] monthlyCounts;// Where to calculate the hourly access counts.
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the
        // access counts.
        hourCounts = new int[24];
        dailyCounts = new int[32];
        monthlyCounts = new int[13];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Create an object to analyze hourly web accesses.
     * @Params String with the Log filename
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly, daily, and monthly
        // access counts.
        hourCounts = new int[24];
        dailyCounts = new int[32];
        monthlyCounts = new int[13];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    public int numberofAccesses()
    {
        int total = 0;
        //Add the value in each element of hourCounts
        //to total.
        for(int hour = 0; hour < hourCounts.length; hour++) 
        {
            total = total + hourCounts[hour];
        }
           return total;
    }
    
     public int busiestHour()
     {
        int numOfAccessesAtBusiest = 0;
        int busiest = 0;
        int index = 0;
        
        while (index < hourCounts.length -1)
        {
            if (numOfAccessesAtBusiest < hourCounts[index])
            {
                busiest = index;
                numOfAccessesAtBusiest = hourCounts[index];
                index++;
            }
            else
            {
                index++;
            }
        }
        return busiest;
    }
    
    public int quietestHour()
    {
        int numOfAccessesAtQuietest = 9999;
        int quietest = 0;
        int index = 0;
        
        while (index < hourCounts.length -1)
        {
            if (numOfAccessesAtQuietest > hourCounts[index])
            {
                quietest = index;
                numOfAccessesAtQuietest = hourCounts[index];
                index++;
            }
            else
            {
                index++;
            }
        }
        return quietest;
    }
    
    public int twoHourBusiest()
    {
            int numOfAccessesAtBusiest = 0;
            int index = 0;
            int busiest = 0;
                        
            while(index < hourCounts.length - 1)
            {
                if (numOfAccessesAtBusiest < hourCounts[index] + hourCounts[index + 1])
                {
                    busiest = index;
                    numOfAccessesAtBusiest = hourCounts[index] + hourCounts[index + 1];
                    index++;
                }
                else
                {
                    index++;
                }
            }
            return busiest;
    }
    
    public void analyzeDailyData()
    {
        while(reader.hasNext())
        {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dailyCounts[day]++;
        }
    }
    
    public void analyzeMonthlyData()
    {
        while(reader.hasNext())
        {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthlyCounts[month]++;
        }
    }
    
    public int quietestDay()
    {
        int quietest = 1;
        for(int i = 1; i < dailyCounts.length; i++)
        {
            if(dailyCounts[i] < dailyCounts[quietest])
                quietest = i;
        }
        return quietest;
    }
    
    public int busiestDay()
    {
        int busiest = 1;
        for(int i = 1; i < dailyCounts.length; i++)
        {
            System.out.println(dailyCounts[i]);
            if(dailyCounts[i] > dailyCounts[busiest])
                busiest = i;
        }
        return busiest;
    }
    
    public int avgAccessesPerMonth()
    {
        int numMonths = 0;
        int totalAccesses = 0;
        for(int i = 1; i <= 12; i++)
        {
            totalAccesses += monthlyCounts[i];
            if(monthlyCounts[i] > 0)
                numMonths++;
        }
        return totalAccesses/numMonths;
    }
    
    public int totalAccessPerMonth()
    {
        int numMonths = 0;
        
        for (int i = 1; i<= 12; i++)
        {
            //something here;
        }
        return numMonths;
    }
    
    public int quietestMonth()
    {
        int quietest = 1;
        for(int i = 1; i <= 12; i++)
        {
            if(monthlyCounts[i] < monthlyCounts[quietest])
                quietest = i;
        }
        return quietest;
    }
    
    public int busiestMonth()
    {
        int busiest = 1;
        for(int i = 1; i <= 12; i++)
        {
            if(monthlyCounts[i] > monthlyCounts[busiest])
                busiest = i;
        }
        return busiest;
    }
   
}

