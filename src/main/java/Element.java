public class Element {
    Position position;

    public Element(int x, int y) { position = new Position(x,y); }

    public Element() {
        position = new Position(0,0);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    public void draw(){

    }
}
