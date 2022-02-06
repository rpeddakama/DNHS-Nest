
class Invitation {
    private String hostName;
    private String address;

    public Invitation(String address) {
        this.address = address;

        hostName = "Host";
    }

    public String getHost() {
        return hostName;
    }

    public void setAddress(String _addr) {
        this.address = _addr;
    }

    public String getInvite(String guest) {
        return String.format(
                "Dear %s, please attend my event at %s. See you then, %s.",
                guest, address, hostName);
    }
}