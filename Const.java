import java.util.*;
import java.awt.*;

public class Const {
    
    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;
    public static final int PAUSE = 15;
    public static final int MAP_WIDTH = 24;
    public static final int MAP_HEIGHT = 36;
    public static final int BLOCK_SIZE = 20;
    public static final int TOWER_SIZE = 60;
    public static final int HEALTH_BAR_HEIGHT = 7;
    public static final int BASE_HEALTH_BAR_HEIGHT = 30;
    public static final int BASE_HEALTH = 2500;
    public static final int MOVEMENT_STEP_SIZE = 2;
    public static final int PROJECTILE_STEP_COOLDOWN = 3;
    public static final int BOMB_EXPLOSION_DURATION = 10;
    public static final int BASE_GOLD_PRODUCTION_DELAY = 5;
    public static final int DWELLING_GOLD_PRODUCTION_DELAY = 10;
    public static final int BASE_GOLD = 1000;
    public static final HashMap<String, Color> COLORS = new HashMap<>(){{
        put("LIGHTGRAY", Color.LIGHT_GRAY);
        put("PINK", Color.PINK);
        put("BLACK", Color.BLACK);
        put("YELLOW", Color.YELLOW);
        put("GREEN", Color.GREEN);
        put("RED", Color.RED);
        put("GRAY", Color.GRAY);
        put("BLUE", Color.BLUE);
    }};
    public static final HashMap<String, Integer> COSTS = new HashMap<>(){{
        put("FootSoldier", 25);
        put("Archer", 40);
        put("Tanker", 150);
        put("Knight", 150);
        put("Healer", 75);
        put("SiegeEngine", 500);
        put("Dwelling", 150);
        put("ArcherTower", 300);
        put("Wall", 250);
        put("CannonTower", 350);
        put("BombTower", 400);
        put("BallistaTower", 500);
    }};
    public static final int BOX_CODE = 1;
    public static final int STRING_CODE = 2;

    private Const(){}

}