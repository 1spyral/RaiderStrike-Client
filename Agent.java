public enum Agent {
    ZERO(0, 1, 0),
    MUSTANG(1, 2, 0),
    HELLCAT(2, 3, 0),
    WILDCAT(3, 4, 0),
    MIRAGE(4, 5, 0);

//-------------------------------------------------------------------------------------
    private final int agentId;
    private final int basicAbility; // ability Id
    private final int ultimateAbility; // ability Id

    Agent(int agentId, int basicAbility, int ultimateAbility) {
        this.agentId = agentId;
        this.basicAbility = basicAbility;
        this.ultimateAbility = ultimateAbility;
    }

    public int getAgentId(){
        return this.agentId;
    }
    public int getBasicAbility(){
        return this.basicAbility;
    }
    public int getUltimateAbility(){
        return this.ultimateAbility;
    }
}