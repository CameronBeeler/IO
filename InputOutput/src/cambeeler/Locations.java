package cambeeler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public
class Locations
implements Map<Integer, Location>
{
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private static String FILENAME = "Locations.txt", DIRECTIONMAP = "Directions.txt";

    public static
    void main(String[] args)
    throws IOException
    {

//        try(    FileWriter fw =  new FileWriter(FILENAME);
//                FileWriter dir =  new FileWriter(DIRECTIONMAP);
//           )
//        {
//            for (Location l : locations.values())
//            {
//                fw.write(l.getLocationID() + "," + (l.getDescription().trim()) + "\n");
//                for(String direction: l.getExits().keySet()) {
//                    dir.write(l.getLocationID() + "," + direction + "," + l.getExits().get(direction) + "\n" );
//                }
//            }
//        }

//        PRINT OUT ALL LOCATIONS & EXITS
        for(Location l:locations.values())
        {
            System.out.println(l);
        }

    }


//        FileReader fr = null;
//        String tempVal;
//        try
//        {
//            fr = new FileReader(FILENAME);
//
//            tempVal = fr.read();
//            for ()
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }

//    SIMPLE BUFFERED READER CODE REVIEW
    static

    {
//        READ THE NODE & DESCRIPTION DATA
//        Scanner buffRead = null;
//        try(Scanner buffRead = new Scanner(new BufferedReader(new FileReader(FILENAME)));)
        try(BufferedReader br = new BufferedReader(new FileReader(FILENAME));)
        {
//            Scanner buffRead = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            String sTemp, input;
            Integer iTemp;
            while((input = br.readLine()) != null)
            {
                String inputData[] = input.split(",");
                iTemp = Integer.parseInt(inputData[0]);
                sTemp = inputData[1];

//                iTemp = buffRead.nextInt();
//                buffRead.skip(buffRead.delimiter());
//                buffRead.useDelimiter("\n");
//                sTemp = buffRead.next();
                locations.put(iTemp,new Location(iTemp, sTemp) );
//                buffRead.nextLine();
                iTemp=-1;
                sTemp=null;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed to open / create a file handle");
        }

//        READ THE LOCATION-DIRECTION-EXIT DATA
//        Scanner mapBuffRead = null;
//        try(Scanner mapBuffRead = new Scanner(new BufferedReader(new FileReader(DIRECTIONMAP))))
        try(BufferedReader brExit = new BufferedReader(new FileReader(DIRECTIONMAP)))
        {
//            mapBuffRead = new Scanner(new BufferedReader(new FileReader(DIRECTIONMAP)));
//            mapBuffRead.useDelimiter(",");
            String direction, inputExit;
            Integer inode, newNode;
            while((inputExit = brExit.readLine()) != null)
            {
                String inputExitData[] = inputExit.split(",");
                inode = Integer.parseInt(inputExitData[0]);
                direction = inputExitData[1];
                newNode = Integer.parseInt(inputExitData[2]);

//                mapBuffRead.useDelimiter(",");
//                inode = mapBuffRead.nextInt();
//                mapBuffRead.skip(mapBuffRead.delimiter());
//                direction = mapBuffRead.next();
//                mapBuffRead.skip(mapBuffRead.delimiter());
//                mapBuffRead.useDelimiter("\n");
//                newNode = mapBuffRead.nextInt();
//                mapBuffRead.nextLine();
                if(direction.equalsIgnoreCase("Q"))continue;
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


//        SIMPLE FILEREADER CODE REVIEW
//    static
//    {
//
//        Scanner read = null;
//        String[] input = null;
//        int node = -1, nodeDestination = -1;
//        String nodeDescription = null;
//        String nodeDirection = null;
//
//        try
//        {
////            first, get the locations, then get the directions
//
//            read = new Scanner(new FileReader(FILENAME));
//            read.useDelimiter(",");
//            while(read.hasNext())
//            {
//                node = read.nextInt();
//                read.skip(read.delimiter());
//                nodeDescription = read.nextLine();
//                locations.put(node, new Location(node, nodeDescription));
//                node=-1;
//                nodeDescription=null;
//            }
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            System.out.println();
//            input=null;
//            read.close();
//        }
//
//        try
//        {
//            read = new Scanner(new FileReader(DIRECTIONMAP));
//            read.useDelimiter(",");
//
//            while(read.hasNext())
//            {// node, direction, nodedestination
//
//                input = read.nextLine().split(",");
//
//                node = read.nextInt();
//                read.skip(read.delimiter());
//                nodeDirection = read.next();
//                read.skip(read.delimiter());
//                System.out.println("node " + node + "Node Dir " + nodeDirection);
//                nodeDestination = read.nextInt();
//
//                locations.get(node).addExit(nodeDirection, nodeDestination);
////                System.out.println("Node#: " + node + ", Dir: " + nodeDirection + ", Dest Node: " + nodeDestination);
//
//                node=-1;
//                nodeDirection=null;
//                nodeDestination = -1;
//
//            }
//            for(Integer i:locations.keySet())
//            {
//                System.out.println(locations.get(i).toString());
//
//            }
//
//
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            read.close();
//        }
//
//    }
 /*       locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

*/

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
