import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame
{
    public SnakeFrame(){

        add(new SnakeFrame());
        setSize(1370,710);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SnakeFrame();
    }

}
