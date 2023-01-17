import java.awt.*;

public class Text {
    private Text() {}

    // Draw text from x centered at y
    public static void draw(Graphics g, int fontSize, String text, double x, double y) {
        g.setFont(FontLoader.getFont(fontSize));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(text, (int)x, (int)(y - fm.getHeight() / 2 + fm.getAscent()));
    }
    // Draw text centered at (x, y)
    public static void drawCentered(Graphics g, int fontSize, String text, double x, double y) {
        g.setFont(FontLoader.getFont(fontSize));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(text, (int)(x - fm.stringWidth(text) / 2), (int)(y - fm.getHeight() / 2 + fm.getAscent()));
    }
    // Draw text centered at the provided rectangle
    public static void drawCentered(Graphics g, int fontSize, String text, double x, double y, int width, int height) {
        drawCentered(g, fontSize, text, x + width / 2, y + height / 2);
    }
    // Draw text centered at the provided rectangle
    public static void drawCentered(Graphics g, int fontSize, String text, Rectangle rect) {
        drawCentered(g, fontSize, text, rect.getCenterX(), rect.getCenterY());
    }
}
