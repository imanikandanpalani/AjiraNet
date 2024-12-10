import java.util.*;

public class Network {
    private final Map<String, Device> devicesMap = new HashMap<>();
    private final Map<String, List<String>> connectionsMap = new HashMap<>();

    public boolean isDeviceExists(String name) {
        return devicesMap.containsKey(name);
    }

    public void addDevice(String name, String type) {
        if (isDeviceExists(name)) {
            throw new IllegalArgumentException("Device name already exists.");
        }
        Device device = new Device(name, type);
        devicesMap.put(name, device);
        connectionsMap.put(name, new ArrayList<>());
    }


    public void connectDevice(String deviceName1, String deviceName2) {
        validateDevice(deviceName1);
        validateDevice(deviceName2);

        if (deviceName1.equals(deviceName2)) {
            throw new IllegalArgumentException("Cannot connect a device to itself.");
        }

        if (connectionsMap.get(deviceName1).contains(deviceName2)) {
            throw new IllegalArgumentException("Devices are already connected.");
        }

        connectionsMap.get(deviceName1).add(deviceName2);
        connectionsMap.get(deviceName2).add(deviceName1);
    }

    public String findRoute(String source, String destination) {
        validateDevice(source);
        validateDevice(destination);

        if (devicesMap.get(source).type == DeviceType.REPEATER ||
                devicesMap.get(destination).type == DeviceType.REPEATER) {
            return "Error: Route cannot involve a repeater as source or destination.";
        }

        Queue<Route> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Route(source, devicesMap.get(source).strength, new ArrayList<>(List.of(source))));
        visited.add(source);

        while (!queue.isEmpty()) {
            Route current = queue.poll();
            String lastNode = current.path.get(current.path.size() - 1);

            if (lastNode.equals(destination)) {
                return String.join(" -> ", current.path);
            }

            for (String neighbor : connectionsMap.get(lastNode)) {
                if (!visited.contains(neighbor) && current.remainingStrength > 0) {
                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(current.path);
                    newPath.add(neighbor);
                    int newStrength = (devicesMap.get(neighbor).type == DeviceType.REPEATER)
                            ? current.remainingStrength * 2
                            : current.remainingStrength - 1;
                    queue.add(new Route(neighbor, newStrength, newPath));
                }
            }
        }
        return "Error: No route found between " + source + " and " + destination + ".";
    }

    public void setDeviceStrength(String deviceName, int strength) {
        validateDevice(deviceName);
        devicesMap.get(deviceName).setStrength(strength);
    }

    private void validateDevice(String name) {
        if (!isDeviceExists(name)) {
            throw new IllegalArgumentException("Node not found: " + name);
        }
    }
}
