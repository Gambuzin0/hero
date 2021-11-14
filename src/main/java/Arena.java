import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width,height;
    private Hero hero = new Hero(20,10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) +
                    1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) +
                    1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    public void moveHero(Position position){
        if(canHeroMove(position)){
            hero.setPosition(position);
        }
        retrieveCoins();
        verifyMonsterCollisions();
    }

    public void verifyMonsterCollisions(){
        for(Monster monster : monsters)
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Unfortunately you died!");
                System.exit(0);
            }
    }

    public void retrieveCoins(){
        for(Coin coin : coins){
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
        }

    }

    public boolean canHeroMove(Position position){
        if(position.getY() >= height-1 || position.getY() < 1 || position.getX() >= width -1 || position.getX() < 1)
            return false;
        for(Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }
        return true;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#00008B"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);

        for (Coin coin : coins)
            coin.draw(graphics);

        for (Monster monster : monsters)
            monster.draw(graphics);

        hero.draw(graphics);
    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            Position prev_pos = monster.getPosition();
            Position pos = monster.move();
            if(pos.getY() >= height - 1 || pos.getY() < 1 || pos.getX() >= width -1 || pos.getX() < 1)
                pos = prev_pos;
            monster.setPosition(pos);
        }
    }

    public void processKey(KeyStroke key)  {
        moveMonsters();
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
        }
    }
}
