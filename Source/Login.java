package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText, psdText;
    Choice loginAsC;
    JButton login,signup,cancel;
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel username = new JLabel("Username");
        username.setBounds(300,35,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,35,100,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300,70,100,20);
        add(password);

        psdText = new JTextField();
        psdText.setBounds(400,70,100,20);
        add(psdText);

        JLabel loginn = new JLabel("Log-in as");
        loginn.setBounds(300,105,100,20);
        add(loginn);

        loginAsC = new Choice();
        loginAsC.add("Admin");
        loginAsC.add("User");
        loginAsC.setBounds(400,105,100,20);
        add(loginAsC);

        login = new JButton("Login");
        login.setBounds(310,150,70,20);
        login.setBackground(new Color(242, 161, 56));
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(410,150,80,20);
        cancel.setBackground(new Color(242, 161, 56));
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        signup = new JButton("Sign-Up");
        signup.setBounds(360,185,80,20);
        signup.setBackground(new Color(242, 161, 56));
        signup.setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);

        ImageIcon profile1 = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profile2 = profile1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon fprofile1 = new ImageIcon(profile2);
        JLabel profileLabel = new JLabel(fprofile1);
        profileLabel.setBounds(50,20,200,200);
        add(profileLabel);

        setSize(600, 300);
        setLocation(500,250);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== login){
            String susername = userText.getText();
            String spassword = psdText.getText();
            String suser = loginAsC.getSelectedItem();

            try{
                database c = new database();
                String qry = "select * from Signup where username='"+susername+"' and password = '"+spassword+"' and usertype = '"+suser+"'"; //qry to check username & pswd in Signup table

                ResultSet resultSet = c.statement.executeQuery(qry);

                if (resultSet.next()){
                    String meter = resultSet.getString("meterno");
                    setVisible(false);
                    new main_class(suser,meter);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }else if(e.getSource()== signup){
            setVisible(false);
            new signUp();
        } else if (e.getSource()== cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
