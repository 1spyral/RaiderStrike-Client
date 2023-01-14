import java.awt.*;
public abstract class Button extends Rectangle {
    private Mouse mouse;

    protected boolean active;

    protected String text;
    protected Color color;
    protected Color hoverColor;
    protected int fontSize;

    Button(Mouse mouse, int x, int y, int width, int height, String text, Color color, int fontSize) {
        this(mouse, x, y, width, height, text, color, color, fontSize);
    }
    Button(Mouse mouse, int x, int y, int width, int height, String text, Color color, Color hoverColor, int fontSize) {
        super(x, y, width, height);
        this.active = true;
        
        this.mouse = mouse;
        this.text = text;
        this.color = color;
        this.hoverColor = hoverColor;
        this.fontSize = fontSize;
    }
    public boolean isActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void draw(Graphics g) {
        if (!this.active) {
            return;
        }
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        if (this.contains(this.mouse)) {
            g.setColor(this.hoverColor);
        }
        else {
            g.setColor(this.color);
        }
        g.fillRect(this.x, this.y, this.width, this.height);
        Text.drawCentered(g, this.fontSize, this.text, this);
    }
    public void click(Mouse.Click click) {
        if (this.active && this.contains(click)) {
            this.run();
        }
    }
    public abstract void run();
}
