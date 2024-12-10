import java.util.*;

public class Network {
    /*
     * Commands:
     * 1. ADD
     * 2. CONNECT
     * 3. INFO_ROUTE
     * 4. SET_DEVICE_STRENGTH<#STRENGTH>
     *
     * Properties:
     * 1. devicesMap
     * 2. connectionsMap
     *
     * */

    private Map<String, Device> devicesMap = new HashMap<>();
    private Map<String, List<String>> connectionsMap = new HashMap<>();

    // 1. ADD
    public void addDevice(String name, String type) {
        // type check
        if (!type.equals("COMPUTER") && !type.equals("REPEATER")){
            throw new IllegalArgumentException("Invalid device type.");
        }

        // name check
        if (devicesMap.containsKey(name)){
            throw new IllegalArgumentException("Device name already exists.");
        }

        devicesMap.put(name, new Device(name, type));
        connectionsMap.put(name, new ArrayList<>());
    }

    // 2. CONNECT
    public void connectDevice(String deviceName1, String deviceName2) {
        // Device node check
        if(!devicesMap.containsKey(deviceName1) || !devicesMap.containsKey(deviceName2)){
            throw new IllegalArgumentException("Node not found.");
        }

        // Same device connect check
        if (deviceName1.equals(deviceName2)) {
            throw new IllegalArgumentException("Cannot connect to itself.");
        }

        // Already connected check
        if (connectionsMap.get(deviceName1).contains(deviceName2)){
            throw new IllegalArgumentException("Devices are already connected.");
        }

        connectionsMap.get(deviceName1).add(deviceName2);
        connectionsMap.get(deviceName2).add(deviceName1);
    }

    /*
     * 1. Enum handlation
     * 2. Repeater strength handlation
     * 3. initial strength handlation
     * 4. code cleaning
     * 5. code should extensible
     * 6. work on the extention
     *
     * */

    // 3. INFO_ROUTE
    public String findRoute(String source, String destination){

        // search logic
        Queue<Route> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // init search
        int initialStrength = devicesMap.get(source).strength;
        queue.add(new Route(source, initialStrength, new ArrayList<>(List.of(source))));
        visited.add(source);    // to keep a track on visited devices

        while (!queue.isEmpty()){
            Route currentRoute = queue.poll();
            String lastNode = currentRoute.path.get(currentRoute.path.size()-1);
            int remainingStrength = currentRoute.remainingStrength;

            // destination check
            if (lastNode.equals(destination)){
                return String.join(" -> ", currentRoute.path);
            }

            //  continue searching
            for (String nearByNode : connectionsMap.get(lastNode)){
                if (!visited.contains(nearByNode)){
                    int newStrength = remainingStrength - 1;
                    if (devicesMap.get(nearByNode).type.equals("REPEATER")){
                        newStrength *= 2;
                    }
                    if(newStrength > 0) {
                        visited.add(nearByNode);
                        List<String> newPath = new ArrayList<>(currentRoute.path);   // creating a new path
                        newPath.add(nearByNode);    //adding  nearByNode to newPath
                        queue.add(new Route(nearByNode, newStrength, newPath));
                    }
                }
            }

        }


        return "Error : Route not found!";
    }

    // 4. SET_DEVICE_STRENGTH<#STRENGTH>
    public void setDeviceStrength(String deviceName, int strength){
        // Node check
        if (!devicesMap.containsKey(deviceName)){
            throw new IllegalArgumentException("Node not found.");
        }

        devicesMap.get(deviceName).setStrength(strength);
    }

}
