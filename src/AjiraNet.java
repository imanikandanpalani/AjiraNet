import java.util.Scanner;

public class AjiraNet {
    public static void main(String[] args) {
        Network network = new Network();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine().trim();
            try {
                String[] params = command.split(" ");
                switch (params[0]) {
                    case "ADD":
                        handleAddCommand(network, params);
                        break;
                    case "CONNECT":
                        handleConnectCommand(network, params);
                        break;
                    case "INFO_ROUTE":
                        handleInfoRouteCommand(network, params);
                        break;
                    case "SET_DEVICE_STRENGTH":
                        handleSetDeviceStrengthCommand(network, params);
                        break;
                    case "SEND":
                        handleSendCommand(network, params);
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private static void handleAddCommand(Network network, String[] params) {
        if (params.length < 3) {
            throw new IllegalArgumentException("Error: Invalid command syntax.");
        }

        if (params[1].equals(DeviceType.BRIDGE.getValue())) {
            if (params.length != 4) {
                throw new IllegalArgumentException("Error: Invalid command syntax for BRIDGE. Use 'ADD BRIDGE <NAME> <BRIDGE_TYPE>'.");
            }
            network.addBridge(params[2], params[3]);
        } else {
            if (params.length != 3) {
                throw new IllegalArgumentException("Error: Invalid command syntax for DEVICE. Use 'ADD <DEVICE_TYPE> <NAME>'.");
            }
            network.addDevice(params[2], params[1]);
        }
        System.out.println("Successfully added " + params[2]);
    }

    private static void handleConnectCommand(Network network, String[] params) {
        if (params.length != 3) throw new IllegalArgumentException("Error: Invalid command syntax");
        network.connectDevice(params[1], params[2]);
        System.out.println("Successfully connected.");
    }

    private static void handleInfoRouteCommand(Network network, String[] params) {
        if (params.length != 3) throw new IllegalArgumentException("Error: Invalid command syntax");
        System.out.println(network.findRoute(params[1], params[2]));
    }

    private static void handleSetDeviceStrengthCommand(Network network, String[] params) {
        if (params.length != 3) throw new IllegalArgumentException("Error: Invalid command syntax");
        network.setDeviceStrength(params[1], Integer.parseInt(params[2]));
        System.out.println("Successfully defined strength.");
    }

    private static void handleSendCommand(Network network, String[] params) {
        if (params.length != 4) throw new IllegalArgumentException("Error: Invalid command syntax");
        System.out.println(network.send(params[1], params[2], params[3]));
    }
}