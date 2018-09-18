package cambeeler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dev on 8/12/2015.
 */
public class Location implements Serializable
{
    private         long                    serialVersionUID    =   1L;
    private final   int                     locationID;
    private final   String                  description;
    private final   Map<String, Integer>    exits;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
        this.exits = new LinkedHashMap<String, Integer>();
        this.exits.put("Q", 0);
    }

    public Location(int locationID, String description, Map<String, Integer> exits)
    {
        this.locationID = locationID;
        this.description = description;
        this.exits = exits;
    }

    @Override
    public
    boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }
    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<String, Integer>(exits);
    }

    @Override
    public
    String toString()
    {
        String builder = "Node ID = " + locationID + ", Node Desc = " + description.trim() + "\n";

        builder += "\t";
        int incr = 0;

        for(String s : exits.keySet())
        {
            if(incr++ == 0)
            {
                builder += s + " :: " + exits.get(s).intValue()  ;
            } else
                {builder += ",\n\t" + s + " :: " + exits.get(s).intValue() ;}

        }

        return builder;
    }
}
