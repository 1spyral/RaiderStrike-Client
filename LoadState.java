import java.util.*;
import java.awt.*;

public class LoadState extends State {
    int id;
    Player[] players;

    Map map;

    LoadState(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        super(keyboard, mouse, messenger);
    }
    public void setup(Object[] args) {
        super.setup();
        this.id = (int)args[0];
        this.players = (Player[])args[1];
    }
    public void update() {

    }
    public void draw(Graphics g) {

    }
    public void close() {
        super.close();
    }
    private class Loader extends LinkedList<String> implements Runnable {
        public void run() {
            map.setName(this.nextLine());
            int roomCount = Integer.valueOf(this.nextLine());
            map.setRoomCount(roomCount);
            int defenderSpawn = Integer.valueOf(this.nextLine());
            int attackerSpawn = Integer.valueOf(this.nextLine());
            int[] bombRooms = Arrays.stream(this.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int roomId = 0; roomId < roomCount; roomId++) {
                Room room = new Room();
                map.addRoom(room);
                String[] rgb = this.nextLine().split(" ");
                room.setColor(new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2])));
            }
        }
        private String nextLine() {
            while (!this.isEmpty()) {};
            return this.poll();
        }
    }
}
