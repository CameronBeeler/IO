package cambeeler;

import java.io.*;
import java.util.*;

public
class Locations
implements Map<Integer, Location>
{
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static String FILENAME = "Locations.txt", DIRECTIONMAP = "Directions.txt";

    public static
    void main(String[] args)
    throws IOException
    {

        try(BufferedWriter bw = new BufferedWriter( new FileWriter(FILENAME));
            BufferedWriter bwe =  new BufferedWriter( new FileWriter(DIRECTIONMAP))
        )
        {
            for (Location l : locations.values())
            {
                bw.write(l.getLocationID() + "," + (l.getDescription().trim()) + "\n");
                for(String direction: l.getExits().keySet()) {
                    bwe.write(l.getLocationID() + "," + direction + "," + l.getExits().get(direction) + "\n" );
                }
            }
        }

//        PRINT OUT ALL LOCATIONS & EXITS
        for(Location l:locations.values())
        {
            System.out.println(l);
        }

    }


//    SIMPLE BUFFERED READER CODE REVIEW
    static

    {
        try(BufferedReader br = new BufferedReader(new FileReader(FILENAME));)
        {
            String sTemp, input;
            Integer iTemp;
            while((input = br.readLine()) != null)
            {
                String inputData[] = input.split(",");
                iTemp = Integer.parseInt(inputData[0]);
                sTemp = inputData[1];
                locations.put(iTemp,new Location(iTemp, sTemp) );
                iTemp=-1;
                sTemp=null;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed to open / create a file handle");
        }

        try(BufferedReader brExit = new BufferedReader(new FileReader(DIRECTIONMAP)))
        {
            String direction, inputExit;
            Integer inode, newNode;
            while((inputExit = brExit.readLine()) != null)
            {
                String inputExitData[] = inputExit.split(",");
                inode = Integer.parseInt(inputExitData[0]);
                direction = inputExitData[1];
                newNode = Integer.parseInt(inputExitData[2]);
                locations.get(inode).addExit(direction, newNode);
                inode=-1;
                newNode = -1;
                direction=null;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed to open / create a file handle");
        }
    }

// DELEGATE the implemented methods to the HashMap object "locations".
    @Override
    public
    int size()
    {
        return locations.size();
    }

    @Override
    public
    boolean isEmpty()
    {
        return locations.isEmpty();
    }

    @Override
    public
    boolean containsKey(Object key)
    {
        return locations.containsKey(key);
    }

    @Override
    public
    boolean containsValue(Object value)
    {
        return locations.containsValue(value);
    }

    @Override
    public
    Location get(Object key)
    {
        return locations.get(key);
    }

    @Override
    public
    Location put(Integer key, Location value)
    {
        return locations.put(key,value);
    }

    @Override
    public
    Location remove(Object key)
    {
        return locations.remove(key);
    }

    @Override
    public
    void putAll(Map<? extends Integer, ? extends Location> m)
    {
//        locations.putAll(m);
    }

    @Override
    public
    void clear()
    {
        locations.clear();
    }

    @Override
    public
    Set<Integer> keySet()
    {
        return locations.keySet();
    }

    @Override
    public
    Collection<Location> values()
    {
        return locations.values();
    }

    @Override
    public
    Set<Entry<Integer, Location>> entrySet()
    {
        return locations.entrySet();
    }

}
