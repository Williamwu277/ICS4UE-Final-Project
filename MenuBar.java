import java.awt.*;

public class MenuBar {

    static final int MENU_WIDTH = 250;
    private static final int BUTTON_SIZE = 60;
    private static final int BUTTONS_PER_ROW = 3;
    private static final int UNIT_BUTTON_Y = 580;
    private static final int TOWER_BUTTON_Y = 410;
    private static final int GOLD_Y = 25;

    private InputListener input;
    private GameMap gameMap;
    private int x;
    private int y;
    private int width;
    private int height;

    private Button footSoldierButton;
    private Button archerButton;
    private Button tankerButton;
    private Button knightButton;
    private Button healerButton;
    private Button siegeEngineButton;

    private Button dwellingButton;
    private Button archerTowerButton;
    private Button wallButton;
    private Button cannonTowerButton;
    private Button bombTowerButton;
    private Button ballistaTowerButton;

    private Unit purchase;
    private Base playerOneBase;
    private Base playerTwoBase;

    private int currentTeam = 1;
    private Button changeTeam;

    public MenuBar(InputListener input, GameMap gameMap, int x, int y, int width, int height, Base playerOneBase, Base playerTwoBase){
        this.input = input;
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playerOneBase = playerOneBase;
        this.playerTwoBase = playerTwoBase;

        int xGap = (MENU_WIDTH - BUTTONS_PER_ROW * BUTTON_SIZE) / (BUTTONS_PER_ROW + 1);

        this.footSoldierButton = new Button(this.input, this.x+xGap, UNIT_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE);
        this.archerButton = new Button(this.input, this.x+2*xGap+BUTTON_SIZE, UNIT_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE);
        this.tankerButton = new Button(this.input, this.x+3*xGap+BUTTON_SIZE*2, UNIT_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE);
        this.knightButton = new Button(this.input, this.x+xGap, UNIT_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE);
        this.healerButton = new Button(this.input, this.x+2*xGap+BUTTON_SIZE, UNIT_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE);
        this.siegeEngineButton = new Button(this.input, this.x+3*xGap+BUTTON_SIZE*2, UNIT_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE);

        this.dwellingButton = new Button(this.input, this.x+xGap, TOWER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE);
        this.archerTowerButton = new Button(this.input, this.x+2*xGap+BUTTON_SIZE, TOWER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE);
        this.wallButton = new Button(this.input, this.x+3*xGap+BUTTON_SIZE*2, TOWER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE);
        this.cannonTowerButton = new Button(this.input, this.x+xGap, TOWER_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE);
        this.bombTowerButton = new Button(this.input, this.x+2*xGap+BUTTON_SIZE, TOWER_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE);
        this.ballistaTowerButton = new Button(this.input, this.x+3*xGap+BUTTON_SIZE*2, TOWER_BUTTON_Y+BUTTON_SIZE+xGap, BUTTON_SIZE, BUTTON_SIZE);

        this.changeTeam = new Button(this.input, this.x + 120, this.y + 120, 60, 60);
    }

    public void update(){
        if(this.changeTeam.isClicked()){
            if(this.currentTeam == 1){
                this.currentTeam = -1;
            }else{
                this.currentTeam = 1;
            }
        }
        Unit newPurchase = null;
        if(this.footSoldierButton.isClicked()){
            newPurchase = new FootSoldier(currentTeam, 0, 0);
        }else if(this.archerButton.isClicked()){
            newPurchase = new Archer(currentTeam, 0, 0);
        }else if(this.tankerButton.isClicked()){
            newPurchase = new Tanker(currentTeam, 0, 0);
        }else if(this.knightButton.isClicked()){
            newPurchase = new Knight(currentTeam, 0, 0);
        }else if(this.healerButton.isClicked()){
            newPurchase = new Healer(currentTeam, 0, 0);
        }else if(this.siegeEngineButton.isClicked()){
            newPurchase = new SiegeEngine(currentTeam, 0, 0);
        }else if(this.dwellingButton.isClicked()){
            newPurchase = new Dwelling(currentTeam, 0, 0);
        }else if(this.archerTowerButton.isClicked()){
            newPurchase = new ArcherTower(currentTeam, 0, 0);
        }else if(this.wallButton.isClicked()){
            newPurchase = new Wall(currentTeam, 0, 0);
        }else if(this.cannonTowerButton.isClicked()){
            newPurchase = new CannonTower(currentTeam, 0, 0);
        }else if(this.bombTowerButton.isClicked()){
            newPurchase = new BombTower(currentTeam, 0, 0);
        }else if(this.ballistaTowerButton.isClicked()){
            newPurchase = new BallistaTower(currentTeam, 0, 0);
        }
        if(newPurchase != null && this.purchase != null && newPurchase.getClass() == this.purchase.getClass()){
            this.purchase = null;
            this.gameMap.setPurchasePending(false);
        }else if(newPurchase != null){
            this.purchase = newPurchase;
        }
        if(this.purchase != null){
            this.gameMap.setPurchasePending(true);
        }
        boolean mouseXPositionLegal = this.gameMap.getX() <= this.input.getX() && this.input.getX() <= this.gameMap.getX() + this.gameMap.getWidth() * Const.BLOCK_SIZE;
        boolean mouseYPositionLegal = this.gameMap.getY() <= this.input.getY() && this.input.getY() <= this.gameMap.getY() + this.gameMap.getHeight() * Const.BLOCK_SIZE;
        if(this.input.isClicked() && this.purchase != null && mouseXPositionLegal && mouseYPositionLegal){
            this.purchase.setX((this.input.getX()-this.gameMap.getX()) / Const.BLOCK_SIZE * Const.BLOCK_SIZE + this.gameMap.getX());
            this.purchase.setY((this.input.getY()-this.gameMap.getY()) / Const.BLOCK_SIZE * Const.BLOCK_SIZE + this.gameMap.getY());
            this.gameMap.addUnit(this.purchase);
            try{
                this.purchase = this.purchase.getClass().getConstructor(int.class, int.class, int.class).newInstance(currentTeam, 0, 0);
            } catch(Exception E){
                E.printStackTrace();
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y, this.width, this.height);
        this.footSoldierButton.draw(g);
        this.archerButton.draw(g);
        this.tankerButton.draw(g);
        this.knightButton.draw(g);
        this.healerButton.draw(g);
        this.siegeEngineButton.draw(g);
        this.dwellingButton.draw(g);
        this.archerTowerButton.draw(g);
        this.wallButton.draw(g);
        this.cannonTowerButton.draw(g);
        this.bombTowerButton.draw(g);
        this.ballistaTowerButton.draw(g);
        this.changeTeam.draw(g);
        // draw gold amounts
        String playerOneGold = Integer.toString(this.playerOneBase.getGold());
        String playerTwoGold = Integer.toString(this.playerTwoBase.getGold());
        int widthOne = g.getFontMetrics().stringWidth(playerOneGold);
        int widthTwo = g.getFontMetrics().stringWidth(playerTwoGold);
        g.drawString(playerOneGold, this.x + MENU_WIDTH / 2 - widthOne / 2, GOLD_Y);
        g.drawString(playerTwoGold, this.x + MENU_WIDTH / 2 - widthTwo / 2, GOLD_Y+25);
    }
    
}
