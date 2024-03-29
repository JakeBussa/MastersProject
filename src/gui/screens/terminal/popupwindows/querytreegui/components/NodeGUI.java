package gui.screens.terminal.popupwindows.querytreegui.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class NodeGUI {

    private final String text;
    private double x, y;
    private final double width, height;
    private final double textSize;
    private Color color;
    private final boolean isPipelined;

    public NodeGUI(String text, double x, double y, boolean isPipelined) {

        this.text = text;
        this.x = x;
        this.y = y;
        this.isPipelined = isPipelined;
        this.color = Color.rgb(90, 90, 90);

        this.textSize = 35;

        // used to get the actual pixel width and height of the text
        Text temp = new Text(text);
        temp.setFont(new Font(textSize));

        this.width = temp.getLayoutBounds().getWidth();
        this.height = temp.getLayoutBounds().getHeight();
    }

    public String getText() {
        return text;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isPipelined() {
        return isPipelined;
    }

    public boolean contains(double mouseX, double mouseY) {
        Rectangle rectangle = new Rectangle(x - 2 - 10 - width / 2, y - 2 - height + 10 - 5, width + 4 + 20,
                height + 4 + 10);
        return rectangle.contains(mouseX, mouseY);
    }

    public void render(GraphicsContext gc) {

        // background border
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(x - 2 - 10 - width / 2, y - 2 - height + 10 - 5, width + 4 + 20, height + 4 + 10,
                20, 20);

        // background
        gc.setFill(color);
        gc.fillRoundRect(x - 10 - width / 2, y - height + 10 - 5, width + 20, height + 10, 20,
                20);

        // text
        gc.setFill(Color.WHITE);
        Font font = new Font(text, textSize);
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);
        // "y - other garbage" is to accommodate our compound selections, which are chunky
        gc.fillText(text, x, y - (height > 50 ? height - 46.552734375 : 0));
    }
}