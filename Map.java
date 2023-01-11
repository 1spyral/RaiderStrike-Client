public class Map {
    private String name;
    private Room[] rooms;
    
    Map(String name, int roomCount) {
        this.name = name;
        this.rooms = new Room[roomCount];
    }
    public String getName() {
        return this.name;
    }
    public Room[] getRooms() {
        return this.rooms;
    }
    public void addRoom(Room room) {
        for (int i = 0; i < this.rooms.length; i++) {
            if (this.rooms[i] != null) {
                this.rooms[i] = room;
                break;
            }
        }
    }
}
