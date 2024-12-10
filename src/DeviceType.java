public enum DeviceType {
    COMPUTER("COMPUTER"),
    REPEATER("REPEATER");

    String value;

    DeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}