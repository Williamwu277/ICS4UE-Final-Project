import java.util.HashMap;
import java.awt.Color;

// class to store all the constant values
public class Const {
    
    // screen width and height
    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;
    // pause time between looping
    public static final int PAUSE = 15;
    // game map height and width in terms of blocks
    public static final int MAP_WIDTH = 24;
    public static final int MAP_HEIGHT = 36;
    // how large each block and tower is
    public static final int BLOCK_SIZE = 20;
    public static final int TOWER_SIZE = 60;
    // health bar statistics
    public static final int HEALTH_BAR_HEIGHT = 7;
    public static final int BASE_HEALTH_BAR_HEIGHT = 30;
    public static final int BASE_HEALTH = 2500;
    // movement statistics
    public static final int MOVEMENT_STEP_SIZE = 2;
    // projectile and bomb statistics
    public static final int PROJECTILE_STEP_COOLDOWN = 3;
    public static final int BOMB_EXPLOSION_DURATION = 10;
    // gold statistics
    public static final int BASE_GOLD_PRODUCTION_DELAY = 5;
    public static final int DWELLING_GOLD_PRODUCTION_DELAY = 10;
    public static final int BASE_GOLD = 1000;
    // communication between sockets
    public static final String BOX_CODE = "1";
    public static final String STRING_CODE = "2";
    public static final String SOCKET_NEXT_LINE = "-";
    // button statistics
    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 50;
    // change in y between each line of outputted strings
    public static final int STRING_HEIGHT = 15;
    // Color palette
    public static final HashMap<String, Color> COLORS = new HashMap<>(){{
        put("LIGHTGRAY", Color.LIGHT_GRAY);
        put("PINK", Color.PINK);
        put("BLACK", Color.BLACK);
        put("YELLOW", Color.YELLOW);
        put("GREEN", Color.GREEN);
        put("RED", Color.RED);
        put("GRAY", Color.GRAY);
        put("BLUE", Color.BLUE);
        put("LIGHTGREEN", new Color(193, 225, 193));
        put("LIGHTGREEN2", new Color(218, 247, 166));
        put("GREEN2", new Color(152, 251, 152));
        put("GREEN3", new Color(144, 238, 144));
        put("DARKGRAY", new Color(152, 153, 156));
    }};
    // cost for each unit
    public static final HashMap<String, Integer> COSTS = new HashMap<>(){{
        put("FootSoldier", 40);
        put("Archer", 50);
        put("Tanker", 150);
        put("Knight", 150);
        put("Healer", 75);
        put("SiegeEngine", 500);
        put("Dwelling", 150);
        put("ArcherTower", 250);
        put("Wall", 200);
        put("CannonTower", 300);
        put("BombTower", 400);
        put("BallistaTower", 500);
    }};
    // Description for each unit within the menu bar
    public static final HashMap<String, String> UNIT_DESCRIPTION = new HashMap<>(){{
        put("FootSoldier", "Melee soldier with low health, " + Const.SOCKET_NEXT_LINE + 
        "low damage, and medium speed;" + Const.SOCKET_NEXT_LINE +
        "However, extremely cheap to " + Const.SOCKET_NEXT_LINE +
        "purchase." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("FootSoldier") + Const.SOCKET_NEXT_LINE +
        "Size: 1x1");
        put("Archer", "Ranged soldier with low health, " + Const.SOCKET_NEXT_LINE + 
        "low damage, and medium speed;" + Const.SOCKET_NEXT_LINE +
        "However, extremely cheap to " + Const.SOCKET_NEXT_LINE +
        "purchase." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("Archer") + Const.SOCKET_NEXT_LINE +
        "Size: 1x1");
        put("Tanker", "Melee soldier with high health, " + Const.SOCKET_NEXT_LINE + 
        "medium damage, but low speed;" + Const.SOCKET_NEXT_LINE +
        "Used for tanking towers" + Const.SOCKET_NEXT_LINE +
        "and supporting other units." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("Tanker") + Const.SOCKET_NEXT_LINE +
        "Size: 2x2");
        put("Knight", "Melee soldier with medium health, " + Const.SOCKET_NEXT_LINE + 
        "medium damage, and high speed;" + Const.SOCKET_NEXT_LINE +
        "Can gain bonus damage" + Const.SOCKET_NEXT_LINE +
        "from travelling far distances." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("Knight") + Const.SOCKET_NEXT_LINE +
        "Size: 1x2");
        put("Healer", "Soldier with low health, and" + Const.SOCKET_NEXT_LINE + 
        "medium speed. Is able to heal" + Const.SOCKET_NEXT_LINE +
        "other units and passively" + Const.SOCKET_NEXT_LINE +
        "all units heals in a radius." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("Healer") + Const.SOCKET_NEXT_LINE +
        "Size: 1x1");
        put("SiegeEngine", "Tool to bring soldiers up to" + Const.SOCKET_NEXT_LINE + 
        "the front lines. High health," + Const.SOCKET_NEXT_LINE +
        "high damage, but low speed" + Const.SOCKET_NEXT_LINE +
        "Spawns attack party on death." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("SiegeEngine") + Const.SOCKET_NEXT_LINE +
        "Size: 3x3");
        put("Dwelling", "Building to increase your gold" + Const.SOCKET_NEXT_LINE + 
        "production. Has low health" + Const.SOCKET_NEXT_LINE +
        "and provides no defense measures." + Const.SOCKET_NEXT_LINE +
        "Has an average gold production." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("Dwelling") + Const.SOCKET_NEXT_LINE +
        "Size: 2x2");
        put("ArcherTower", "Defense tower with fast attack" + Const.SOCKET_NEXT_LINE + 
        "speeds but low damage. Has" + Const.SOCKET_NEXT_LINE +
        "moderate health and very" + Const.SOCKET_NEXT_LINE +
        "high range for a cheap cost." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("ArcherTower") + Const.SOCKET_NEXT_LINE +
        "Size: 2x2");
        put("Wall", "Stalls enemies for a long" + Const.SOCKET_NEXT_LINE + 
        "duration. Has a small area" + Const.SOCKET_NEXT_LINE +
        "but a high amount of health" + Const.SOCKET_NEXT_LINE +
        "for a cheap cost." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("Wall") + Const.SOCKET_NEXT_LINE +
        "Size: 3x1");
        put("CannonTower", "Tower with low range," + Const.SOCKET_NEXT_LINE + 
        "high damage, medium area, but" + Const.SOCKET_NEXT_LINE +
        "also slow reload speed." + Const.SOCKET_NEXT_LINE +
        "Strong on normal units." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("CannonTower") + Const.SOCKET_NEXT_LINE +
        "Size: 3x3");
        put("BombTower", "Tower with high damage" + Const.SOCKET_NEXT_LINE + 
        ", low range, and low" + Const.SOCKET_NEXT_LINE +
        "reload speed; however causes" + Const.SOCKET_NEXT_LINE +
        "area damage to enemies." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("BombTower") + Const.SOCKET_NEXT_LINE +
        "Size: 3x3");
        put("BallistaTower", "Tower with extremely high" + Const.SOCKET_NEXT_LINE + 
        "damage but also extremely" + Const.SOCKET_NEXT_LINE +
        "slow reload speed. For " + Const.SOCKET_NEXT_LINE +
        "taking down tankier units." + Const.SOCKET_NEXT_LINE + Const.SOCKET_NEXT_LINE +
        "Cost: " + Const.COSTS.get("BallistaTower") + Const.SOCKET_NEXT_LINE +
        "Size: 3x3");
    }};

    // private constructor
    private Const(){}

}