import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.io.IOException;

// class to load in the images in the game
public class ImageLoader {

    // load in the images to a hashmap
    public static final HashMap<String, BufferedImage> IMAGES = new HashMap<>(){{
        try{
            put("FootSoldier", ImageIO.read(new File("Images/FootSoldier.png")));
            put("Archer", ImageIO.read(new File("Images/Archer.png")));
            put("Tanker", ImageIO.read(new File("Images/Tanker.png")));
            put("Knight", ImageIO.read(new File("Images/Knight.png")));
            put("Healer", ImageIO.read(new File("Images/Healer.png")));
            put("SiegeEngine", ImageIO.read(new File("Images/SiegeEngine.png")));
            put("Dwelling", ImageIO.read(new File("Images/Dwelling.png")));
            put("ArcherTower", ImageIO.read(new File("Images/ArcherTower.png")));
            put("Wall", ImageIO.read(new File("Images/Wall.png")));
            put("CannonTower", ImageIO.read(new File("Images/CannonTower.png")));
            put("BombTower", ImageIO.read(new File("Images/BombTower.png")));
            put("BallistaTower", ImageIO.read(new File("Images/BallistaTower.png")));
            put("Base", ImageIO.read(new File("Images/Base.png")));
            put("Explosion", ImageIO.read(new File("Images/Explosion.png")));
            put("InputBox", ImageIO.read(new File("Images/InputBox.png")));
            put("MenuButton", ImageIO.read(new File("Images/MenuButton.png")));
            put("StartButton", ImageIO.read(new File("Images/StartButton.png")));
            put("Title", ImageIO.read(new File("Images/NoNameV2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }};

    // not meant to be instantiated
    private ImageLoader(){}

}
