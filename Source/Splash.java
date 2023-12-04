package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/Splash.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(500,800, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        setSize(500, 800);
        setLocation(600, 50);
        setVisible(true);

        try{
            Thread.sleep(2000);
            setVisible(false);

            new Login();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}
