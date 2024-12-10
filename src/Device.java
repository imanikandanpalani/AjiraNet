public class Device {
    String name;
    String type;
    int strength;

    public Device(String name, String type){
        this.name = name;
        this.type = type;   // COMPUTER , REPEATER --> can be enum
        this.strength = type.equals("COMPUTER") ? 5 : -1;   // Default strength for computers
    }

    public void setStrength(int strength) {
        if (type.equals("REPEATER")) {
            throw new IllegalArgumentException("cannot set strength for REPEATER.");
        }
        if (strength < 0){
            throw new IllegalArgumentException("strength cannot be negative.");
        }
        this.strength = strength;
    }
}
