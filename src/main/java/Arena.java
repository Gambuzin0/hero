import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Arena {
    private int width,height;
    private Hero hero = new Hero(20,10);

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void moveHero(Position position){
        if(canHeroMove(position)){
            hero.setPosition(position);
        }
    }

    public boolean canHeroMove(Position position){
        if(position.getY() >= height || position.getY() < 0 || position.getX() >= width || position.getX() < 0)
            return false;
        return true;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#00008B"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
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
