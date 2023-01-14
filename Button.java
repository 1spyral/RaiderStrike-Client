import java.awt.*;
public abstract class Button extends Rectangle {
    private Mouse mouse;

    protected boolean active;

    protected String text;
    protected Color color;
    protected Color hoverColor;
    protected Color textColor;
    protected int fontSize;

    Button(Mouse mouse) {
        this.mouse = mouse;

        this.active = false;
        
        this.text = "";
        this.color = Color.WHITE;
        this.hoverColor = Color.WHITE;
        this.textColor = Color.BLACK;
        this.fontSize = 20;
    }
    public boolean isActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
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
        g.setColor(this.textColor);
        Text.drawCentered(g, this.fontSize, this.text, this);
    }
    public boolean click(Mouse.Click click) {
        if (this.active && this.contains(click)) {
            return this.run();
        }
        return false;
    }
    public abstract boolean run();
}
