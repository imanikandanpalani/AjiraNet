public class Device {
    private static final int DEFAULT_COMPUTER_STRENGTH = 5;
    private static final int DEFAULT_REPEATER_STRENGTH = -1;

    private String name;
    private DeviceType type;
    private int strength;
    private BridgeType bridgeType;

    public Device(String name, String type) {
        setName(name);
        setType(type);
        this.strength = this.type == DeviceType.COMPUTER ? DEFAULT_COMPUTER_STRENGTH : DEFAULT_REPEATER_STRENGTH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Device name cannot be null or empty.");
        }
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(String type) {
        try {
            this.type = DeviceType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid device type.");
        }
    }

    public int getStrength() {
        return strength;
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

    public BridgeType getBridgeType() {
        return bridgeType;
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
                return message;
        }
    }
}