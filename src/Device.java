public class Device {
    String name;
    DeviceType type;
    int strength;
    BridgeType bridgeType;

    public Device(String name, String type) {
        this.setName(name);
        this.setType(type);
        this.strength = this.type == DeviceType.COMPUTER ? 5 : -1; // Default strength for computers
    }

    public void setType(String type) {
        try {
            this.type = DeviceType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid device type.");
        }
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Device name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setStrength(int strength) {
        if (type == DeviceType.REPEATER || type == DeviceType.BRIDGE) {
            throw new IllegalArgumentException("Cannot set strength for REPEATER or BRIDGE.");
        }
        if (strength < 0) {
            throw new IllegalArgumentException("Strength cannot be negative.");
        }
        this.strength = strength;
    }

    public void setBridgeType(String bridgeType) {
        if (type != DeviceType.BRIDGE) {
            throw new IllegalArgumentException("Bridge type can only be set for BRIDGE devices.");
        }
        this.bridgeType = BridgeType.fromString(bridgeType);
    }

    public String transformMessage(String message) {
        if (type != DeviceType.BRIDGE || bridgeType == null) return message;

        switch (bridgeType) {
            case UPPER:
                return message.toUpperCase();
            case LOWER:
                return message.toLowerCase();
            default:
                return message; // Default transformation
        }
    }
}
