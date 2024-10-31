public class Room {
    int roomNumber;
    String type;
    boolean isAvailable;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - " + (isAvailable ? "Available" : "Booked");
    }
}

