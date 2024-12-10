public enum BridgeType {
    UPPER("UPPER"),
    LOWER("LOWER");

    private final String value;

    BridgeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BridgeType fromString(String value) {
        for (BridgeType type : BridgeType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid bridge type. Must be 'UPPER' or 'LOWER'.");
    }
}
