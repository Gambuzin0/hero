import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster  extends Element{

    private Position position;

    public Monster(int x, int y) {
        position = new Position(x, y);
    }

    public Position move() {
        Random random = new Random();
        int num = random.nextInt(4);
        Position pos = position;
        switch (num) {
            case 0:
                pos = new Position(position.getX() + 1, position.getY());
                break;
            case 1:
                pos = new Position(position.getX() - 1, position.getY());
                break;
            case 2:
                pos = new Position(position.getX(), position.getY() + 1);
                break;
            case 3:
                pos = new Position(position.getX(), position.getY() - 1);
                break;
        }
        return pos;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");

    }


}
