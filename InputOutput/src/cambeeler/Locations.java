package cambeeler;

import java.io.*;
import java.util.*;

public
class Locations
implements Map<Integer, Location>
{
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static String FILENAME = "Locations.txt", DIRECTIONMAP = "Directions.txt";
    private static String BINFILENAME = "Locations.dat";
    private static String OBJFILENAME = "Locations.obj";

    public static
    void main(String[] args)
    throws IOException
    {

        try
        {
//            writeBinary();
//            readBinary();
//            writeSerialObject();
//            readSerialObject();
//            writeTextFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

 //        PRINT OUT ALL LOCATIONS & EXITS
        for(Location l:locations.values())
        {
            System.out.println(l);
        }
//        try
//        {
////            writeBinary();
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//
//        }
//        catch(Exception f)
//        {
//            f.printStackTrace();
//        }

    }


//    SIMPLE BUFFERED READER CODE REVIEW
    static

    {
        try
        {
//            readBinary();
//
            readSerialObject();
//            writeSerialObject();


        }

//        try
//        {
//            readBinary();
////            writeBinary();
//        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();

        }
        catch(Exception f)
        {
            f.printStackTrace();
        }



    }

    public static
    void readTextFiles()
    throws Exception
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


    public static
    void writeTextFile()
    throws Exception
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


    }
    public static
    void writeSerialObject()
    throws Exception
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(OBJFILENAME))))
        {
            for(Location l: locations.values())
            {
                oos.writeObject(l);
//                System.out.println(l);
            }
        }
    }

    public static
    void readSerialObject()
    throws Exception
    {
        locations.clear();
        boolean eof = false;
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(OBJFILENAME))))
        {
            while (!eof)
            {
                try
                {
                    Location location = (Location) ois.readObject();
//                    System.out.println(location);
                    locations.put(location.getLocationID(), location);
                }
                catch (EOFException e)
                {
                    eof = true;
                }
            }
        }


    }


    public static
    void writeBinary()
    throws Exception
    {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(BINFILENAME))))
        {
            for (Location loc : locations.values())
            {
                dos.writeInt(loc.getLocationID());
                dos.writeUTF(loc.getDescription());
                System.out.println(loc.getLocationID() + " :: " + loc.getDescription());
                System.out.println("exits available = " + (loc.getExits().size() - 1));
                dos.writeInt((loc.getExits().size()-1));
                for (String dir : loc.getExits().keySet())
                {
                    if(!dir.equalsIgnoreCase("Q")) // Do not write the "Q" direction
                    {
                        dos.writeUTF(dir);
                        dos.writeInt(loc.getExits().get(dir));
                        System.out.println("\t\t" + dir + "::" + loc.getExits().get(dir));
                    }
                }

            }
        }
    }

    public static
    void readBinary()
    throws Exception
    {
        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(BINFILENAME))))
        {
            boolean eof = false;
            locations.clear();

            while(!eof)
            {
                try
                {
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    int locid = dis.readInt(); // home node #
                    String description = dis.readUTF(); // description of the home node
                    int numExits = dis.readInt(); // # of exits to read...
                    System.out.println(locid + " :: " + description);
                    System.out.println("Exits: " + numExits);
                    for(int i = 0 ; i< numExits ; i++)
                    {
                        String direction = dis.readUTF(); // NESWUD directions
                        int destination = dis.readInt(); // node to move to...
                        exits.put(direction, destination);
                        System.out.println("\t" + direction + " :: " + destination);
                    }
                    locations.put(locid, new Location(locid, description, exits));
                }
                catch (EOFException e)
                {
                    eof = true;
                }

            }
        }
        catch(IOException e)
        {
            System.out.println("IOException");
            e.printStackTrace();
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
