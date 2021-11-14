import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width,height;
    private Hero hero = new Hero(20,10);
    private List<Wall> walls;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
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
        hero.draw(graphics);
    }

    public void processKey(KeyStroke key) throws IOException {
        switch(key.getKeyType()){
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
