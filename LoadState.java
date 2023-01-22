import java.util.*;
import java.io.*;
import java.awt.*;

public class LoadState extends State {
    int id;
    Player[] players;

    Map map;
    Loader loader;

    String text;

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
    public void message(String messageText) {
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
                break;
            case "START":
                this.start(args);
                break;
        }
    }
    public void type(char key) {

    }
    public void click(Mouse.Click click) {

    }
    public void draw(Graphics g) {
        if (this.map != null) {
            Text.draw(g, 20, map.getName(), 100, 100);
            if (this.map.getRooms() != null) {
                Text.draw(g, 20, this.text, 100, 200);
            }
        } else {
            Text.draw(g, 20, "Awaiting server", 100, 100);
        }
    }
    public void close() {
        super.close();
        this.setNextArgs(new Object[] {
            this.id,
            this.players,
            this.map
        });
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
        this.loader = new Loader(args[0]);
        new Thread(this.loader).start();
    }
    private void start(String[] args) {
        this.close();
    }

    private class Loader implements Runnable {
        BufferedReader input;
        Loader(String mapName) {
            try {
                this.input = new BufferedReader(new FileReader("assets/Maps/" + mapName + "Map.txt"));
            } catch (Exception e) {
                System.out.println("Error accessing map file");
            }
        }
        public void run() {
            // Read the map name
            map.setName(this.nextLine());
            // Read the room count
            int roomCount = Integer.valueOf(this.nextLine());
            map.setRoomCount(roomCount);
            text = "Loading rooms";
            // Read the spawn rooms
            int defenderSpawn = Integer.valueOf(this.nextLine());
            int attackerSpawn = Integer.valueOf(this.nextLine());
            // Read the plant sites
            HashSet<Integer> bombRooms = new HashSet<Integer>();
            for (String roomId: this.nextLine().split(" ")) {
                bombRooms.add(Integer.valueOf(roomId));
            }
            // Load each room
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
            try {
                while (this.input.ready()) {};
                return this.input.readLine();
            } catch (Exception e) {
                System.out.println("Error reading map file");
            }
            return null;
        }
    }
}
