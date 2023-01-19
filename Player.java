public class Player {
    protected String name;
    protected int team;
    protected Agent agent;
    protected boolean ready;
    
    protected boolean alive;
    protected int room;
    protected GunModel gun;
    protected int x;
    protected int y;
    
    Player() {
        this.team = -1;
        this.ready = false;

        this.alive = false;
        this.room = -1;
        this.x = -1;
        this.y = -1;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTeam(int team) {
        this.team = team;
    }
    public Agent getAgent() {
        return this.agent;
    }
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public boolean isReady() {
        return this.ready;
    }
    public void lock() {
        this.ready = true;
    }
    public boolean isAlive() {
        return this.alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public int getRoom() {
        return this.room;
    }
    public void setRoom(int room) {
        this.room = room;
    }
    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
