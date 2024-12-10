public enum DeviceType {
    COMPUTER("COMPUTER"),
    REPEATER("REPEATER"),
    BRIDGE("BRIDGE");

    private final String value;

    DeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeviceType fromString(String value) {
        for (DeviceType type : DeviceType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid device type.");
    }
}
