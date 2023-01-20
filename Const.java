import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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
    public static final String BOX_CODE = "1";
    public static final String STRING_CODE = "2";
    public static final String SOCKET_NEXT_LINE = "-";
    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 50;
    // remove later when you finally actually have a font
    public static final int STRING_HEIGHT = 15;
    public static final String FONT_PATH = "Special_Elite/SpecialElite-Regular.ttf";
    private static Font FONT = LOAD_FONT();
    private static Font LOAD_FONT(){
        try{
            Font ft = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH));
            System.out.println("OUT");
            return ft;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    };
    public static Font SMALL_FONT = Const.FONT.deriveFont((float)12.5);
    public static Font LARGE_FONT = Const.FONT.deriveFont((float)25.0);
    public static Font EXTRA_LARGE_FONT = Const.FONT.deriveFont((float)100.0);
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
    public static final HashMap<String, Integer> COSTS = new HashMap<>(){{
        put("FootSoldier", 25);
        put("Archer", 40);
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
    public static final HashMap<String, BufferedImage> IMAGES = new HashMap<>();
    private Const(){}

    public static void load(){
        try{
            Const.IMAGES.put("FootSoldier", ImageIO.read(new File("Images/FootSoldier.png")));
            Const.IMAGES.put("Archer", ImageIO.read(new File("Images/Archer.png")));
            Const.IMAGES.put("Tanker", ImageIO.read(new File("Images/Tanker.png")));
            Const.IMAGES.put("Knight", ImageIO.read(new File("Images/Knight.png")));
            Const.IMAGES.put("Healer", ImageIO.read(new File("Images/Healer.png")));
            Const.IMAGES.put("SiegeEngine", ImageIO.read(new File("Images/SiegeEngine.png")));
            Const.IMAGES.put("Dwelling", ImageIO.read(new File("Images/Dwelling.png")));
            Const.IMAGES.put("ArcherTower", ImageIO.read(new File("Images/ArcherTower.png")));
            Const.IMAGES.put("Wall", ImageIO.read(new File("Images/Wall.png")));
            Const.IMAGES.put("CannonTower", ImageIO.read(new File("Images/CannonTower.png")));
            Const.IMAGES.put("BombTower", ImageIO.read(new File("Images/BombTower.png")));
            Const.IMAGES.put("BallistaTower", ImageIO.read(new File("Images/BallistaTower.png")));
            Const.IMAGES.put("Base", ImageIO.read(new File("Images/Base.png")));
            Const.IMAGES.put("Explosion", ImageIO.read(new File("Images/Explosion.png")));
            Const.IMAGES.put("InputBox", ImageIO.read(new File("Images/InputBox.png")));
            Const.IMAGES.put("MenuButton", ImageIO.read(new File("Images/MenuButton.png")));
            Const.IMAGES.put("StartButton", ImageIO.read(new File("Images/StartButton.png")));
            Const.IMAGES.put("Title", ImageIO.read(new File("Images/NoNameV2.png")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}