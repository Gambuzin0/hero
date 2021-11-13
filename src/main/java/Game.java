import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class Game {
    private Screen screen;
    private Arena arena = new Arena(40,20);

    private void draw() throws IOException {
        try{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        while(true){
        draw();
        KeyStroke key = screen.readInput();
        if(key.getKeyType() == EOF)
            break;
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            screen.close();
        else
            arena.processKey(key);
        }

    }

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
