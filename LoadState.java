import java.util.*;
import java.awt.*;

public class LoadState extends State {
    int id;
    Player[] players;

    Map map;
    Loader loader;

    LoadState(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        super(keyboard, mouse, messenger);
    }
    public void setup(Object[] args) {
        super.setup();
        this.id = (int)args[0];
        this.players = (Player[])args[1];
        this.map = null;
        this.loader = null;
    }
    public void update() {
        while (!this.messenger.isEmpty()) {
            String messageText = this.messenger.poll();
            String[] message = messageText.split(" ");
            String command = message[0];
            String[] args = Arrays.copyOfRange(message, 1, message.length);
            switch (command) {
                case "AGENT":
                    this.agent(args);
                    break;
                case "NAME":
                    this.name(args);
                    break;
                case "MAP":
                    this.map(args);
                case "START":
                    this.start(args);
                    break;
                default:
                    this.loader.add(messageText);
                    break;
            }
        }
    }
    public void draw(Graphics g) {
        // TODO: draw
    }
    public void close() {
        super.close();
    }
    /* Server-Client commands */
    private void agent(String[] args) {
        this.players[Integer.valueOf(args[0])].setAgent(Agent.valueOf(args[1]));
    }
    private void name(String[] args) {
        String fullName = "";
        for (String word: Arrays.copyOfRange(args, 1, args.length)) {
            fullName = fullName + " " + word;
        }
        fullName = fullName.trim();
        this.players[Integer.valueOf(args[0])].setName(fullName);
    }
    private void map(String[] args) {
        this.map = new Map();
        this.loader = new Loader();
        new Thread(this.loader).start();
    }
    private void start(String[] args) {
        this.close();
    }

    private class Loader extends LinkedList<String> implements Runnable {
        public void run() {
            map.setName(this.nextLine());
            int roomCount = Integer.valueOf(this.nextLine());
            map.setRoomCount(roomCount);
            int defenderSpawn = Integer.valueOf(this.nextLine());
            int attackerSpawn = Integer.valueOf(this.nextLine());
            HashSet<Integer> bombRooms = new HashSet<Integer>();
            for (String roomId: this.nextLine().split(" ")) {
                bombRooms.add(Integer.valueOf(roomId));
            }
            for (int roomId = 0; roomId < roomCount; roomId++) {
                Room room;
                if (roomId == defenderSpawn || roomId == attackerSpawn) {
                    room = new SpawnRoom();
                } else if (bombRooms.contains(roomId)) {
                    room = new BombRoom();
                } else {
                    room = new Room();
                }
                map.addRoom(room);
                String[] rgb = this.nextLine().split(" ");
                room.setColor(new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2])));
                rgb = this.nextLine().split(" ");
                room.setObstacleColor(new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2])));
                rgb = this.nextLine().split(" ");
                room.setPenetrableObstacleColor(new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2])));
                room.setWidth(Integer.valueOf(this.nextLine()));
                room.setHeight(Integer.valueOf(this.nextLine()));
                int obstacleCount = Integer.valueOf(this.nextLine());
                room.setObstacleCount(obstacleCount);
                for (int obstacleId = 0; obstacleId < obstacleCount; obstacleId++) {
                    String[] args = this.nextLine().split(" ");
                    room.addObstacle(new Obstacle(Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]), args[4].equals("1")));
                }
                int doorCount = Integer.valueOf(this.nextLine());
                room.setDoorCount(doorCount);
                for (int doorId = 0; doorId < doorCount; doorId++) {
                    String[] args = this.nextLine().split(" ");
                    room.addDoor(new Door(Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]), Integer.valueOf(args[5])));
                }
                if (roomId == defenderSpawn || roomId == attackerSpawn) {
                    String[] spawns = this.nextLine().split(" ");
                    ((SpawnRoom)room).setSpawnX(new int[] {Integer.valueOf(spawns[0]), Integer.valueOf(spawns[2]), Integer.valueOf(spawns[4])});
                    ((SpawnRoom)room).setSpawnY(new int[] {Integer.valueOf(spawns[1]), Integer.valueOf(spawns[3]), Integer.valueOf(spawns[5])});
                }
            }
            messenger.print("LOADED");
        }
        private String nextLine() {
            while (!this.isEmpty()) {};
            return this.poll();
        }
    }
}
