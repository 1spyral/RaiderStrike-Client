public class Map {
    private String name;
    private Room[] rooms;
    
    Map() {
        this.name = "";
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Room[] getRooms() {
        return this.rooms;
    }
    public void setRoomCount(int roomCount) {
        this.rooms = new Room[roomCount];
    }
    public void addRoom(Room room) {
        for (int i = 0; i < this.rooms.length; i++) {
            if (this.rooms[i] == null) {
                this.rooms[i] = room;
                System.out.println("dasdjlksjd");
                break;
            }
        }
    }
}
