import java.awt.Font;
import java.io.File;

// Class for loading in the font
public class FontLoader {

    // font file
    public static final String FONT_PATH = "Special_Elite/SpecialElite-Regular.ttf";

    // load the fonts in
    private static Font FONT = LOAD_FONT();
    private static Font LOAD_FONT(){
        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH));
            return font;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    };

    // different font sizes
    public static Font SMALL_FONT = FontLoader.FONT.deriveFont((float)12.5);
    public static Font LARGE_FONT = FontLoader.FONT.deriveFont((float)25.0);
    public static Font EXTRA_LARGE_FONT = FontLoader.FONT.deriveFont((float)100.0);

    private FontLoader(){}
}
