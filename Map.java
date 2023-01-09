public class Map {
    String name;
    Room[] rooms;
    
    Map(String name, int roomCount) {
        this.name = name;
        this.rooms = new Room[roomCount];
    }
    public String getName() {
        return this.name;
    }
    
}
