import java.util.Scanner;

public class AjiraNet {
    private static Network network = new Network();

    /*
     * Commands:
     * 1. ADD
     * 2. CONNECT
     * 3. INFO_ROUTE
     * 4. SET_DEVICE_STRENGTH<#STRENGTH>
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            String command = scanner.nextLine().trim();

            try{
                String[] params = command.split(" ");
//                if (params.length != 3) {
//                    throw new IllegalArgumentException("Error : Invalid command syntax");
//                }
                switch (params[0]){
                    case "ADD":
                        // ex : ADD COMPUTER A1
                        if (params.length != 3) throw new IllegalArgumentException("Error : Invalid command syntax");
                        network.addDevice(params[2], params[1].toUpperCase());
                        System.out.println("Successfully added " + params[2]);
                        break;
                    case "CONNECT":
                        // ex : CONNECT A1 A2
                        if (params.length != 3) throw new IllegalArgumentException("Error : Invalid command syntax");
                        network.connectDevice(params[1], params[2]);
                        System.out.println("Successfully connected.");
                        break;
                    case "INFO_ROUTE":
                        // ex : INFO_ROUTE A1 A4
                        if (params.length != 3) throw new IllegalArgumentException("Error : Invalid command syntax");
                        System.out.println(network.findRoute(params[1], params[2]));
                        break;
                    case "SET_DEVICE_STRENGTH":
                        // ex : SET_DEVICE_STRENGTH A1 3
                        if (params.length != 3) throw new IllegalArgumentException("Error : Invalid command syntax");
                        network.setDeviceStrength(params[1], Integer.parseInt(params[2]));
                        System.out.println("Successfully defined strength.");
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid command.");
                }
            } catch (Exception e){
                System.out.println("Error occurred : " + e.getMessage());
            }
        }
    }
}
