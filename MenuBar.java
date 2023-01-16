import java.util.*;

public class MenuBar {

    static final int MENU_WIDTH = 250;
    private static final int BUTTON_SIZE = 60;
    private static final int BUTTONS_PER_ROW = 3;
    private static final int UNIT_BUTTON_Y = 580;
    private static final int TOWER_BUTTON_Y = 410;
    private static final int GOLD_Y = 25;

    private int x;
    private int y;
    private int width;
    private int height;
    private ArrayList<Button> buttons;

    private Unit purchase;
    private boolean purchasePending;
    private int currentTeam;
    private int playerGold;

    public MenuBar(int currentTeam, int x, int y, int width, int height){
        this.currentTeam = currentTeam;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.buttons = new ArrayList<>();
        int xGap = (MENU_WIDTH - BUTTONS_PER_ROW * BUTTON_SIZE) / (BUTTONS_PER_ROW + 1);

        this.buttons.add(new Button("FootSoldier", this.x+xGap, UNIT_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("Archer", this.x+2*xGap+BUTTON_SIZE, UNIT_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("Tanker", this.x+3*xGap+BUTTON_SIZE*2, UNIT_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("Knight", this.x+xGap, UNIT_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("Healer", this.x+2*xGap+BUTTON_SIZE, UNIT_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("SiegeEngine", this.x+3*xGap+BUTTON_SIZE*2, UNIT_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE));

        this.buttons.add(new Button("Dwelling", this.x+xGap, TOWER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("ArcherTower", this.x+2*xGap+BUTTON_SIZE, TOWER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("Wall", this.x+3*xGap+BUTTON_SIZE*2, TOWER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("CannonTower", this.x+xGap, TOWER_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("BombTower", this.x+2*xGap+BUTTON_SIZE, TOWER_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE));
        this.buttons.add(new Button("BallistaTower", this.x+3*xGap+BUTTON_SIZE*2, TOWER_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE));
    }

    public void update(GameMap gameMap, int mouseX, int mouseY, boolean mouseClicked){
        Unit newPurchase = null;
        for(Button nextButton: this.buttons){
            nextButton.update(mouseX, mouseY, mouseClicked);
            if(!nextButton.isClicked()){
                continue;
            }else if(nextButton.getId().equals("FootSoldier")){
                newPurchase = new FootSoldier(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("Archer")){
                newPurchase = new Archer(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("Tanker")){
                newPurchase = new Tanker(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("Knight")){
                newPurchase = new Knight(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("Healer")){
                newPurchase = new Healer(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("SiegeEngine")){
                newPurchase = new SiegeEngine(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("Dwelling")){
                newPurchase = new Dwelling(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("ArcherTower")){
                newPurchase = new ArcherTower(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("Wall")){
                newPurchase = new Wall(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("CannonTower")){
                newPurchase = new CannonTower(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("BombTower")){
                newPurchase = new BombTower(currentTeam, 0, 0);
            }else if(nextButton.getId().equals("BallistaTower")){
                newPurchase = new BallistaTower(currentTeam, 0, 0);
            }
        }
        if(newPurchase != null && this.purchase != null && newPurchase.getClass() == this.purchase.getClass()){
            this.purchase = null;
            this.purchasePending = false;
        }else if(newPurchase != null){
            this.purchase = newPurchase;
        }
        if(this.purchase != null){
            this.purchasePending = true;
        }
        boolean mouseXPositionLegal = gameMap.getX() <= mouseX && mouseX <= gameMap.getX() + gameMap.getWidth() * Const.BLOCK_SIZE;
        boolean mouseYPositionLegal = gameMap.getY() <= mouseY && mouseY <= gameMap.getY() + gameMap.getHeight() * Const.BLOCK_SIZE;
        int newX = (mouseX-gameMap.getX()) / Const.BLOCK_SIZE * Const.BLOCK_SIZE + gameMap.getX();
        int newY = (mouseY-gameMap.getY()) / Const.BLOCK_SIZE * Const.BLOCK_SIZE + gameMap.getY();
        if(mouseClicked && this.purchase != null && mouseXPositionLegal && mouseYPositionLegal && this.getBase(gameMap).getGold() >= Const.COSTS.get(this.purchase.getClass().getSimpleName()) && GameMap.onCorrectSide(this.currentTeam, newX, newY, this.purchase.getWidth(), this.purchase.getHeight())){
            this.purchase.setX(newX);
            this.purchase.setY(newY);
            boolean added = gameMap.addUnit(this.purchase);
            try{
                this.purchase = this.purchase.getClass().getConstructor(int.class, int.class, int.class).newInstance(currentTeam, 0, 0);
            } catch(Exception E){
                E.printStackTrace();
            }
            if(added){
                this.getBase(gameMap).setGold(this.getBase(gameMap).getGold() - Const.COSTS.get(this.purchase.getClass().getSimpleName()));
            }
        }
        this.playerGold = this.getBase(gameMap).getGold();
    }

    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " BLACK");
        for(Button nextButton: this.buttons){
            output.addAll(nextButton.getData());
        }
        // draw gold amounts
        String playerGold = "Gold: " + Integer.toString(this.playerGold);
        output.add(Const.STRING_CODE + " " + (this.x + MENU_WIDTH / 2) + " " + GOLD_Y + " PINK " + playerGold);
        return output;
    }


    public Base getBase(GameMap gameMap){
        if(this.currentTeam == 1) {
            return gameMap.getPlayerOneBase();
        }else{
            return gameMap.getPlayerTwoBase();
        }
    }

    public boolean getPurchasePending(){
        return this.purchasePending;
    }
    
}
