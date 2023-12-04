package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.jar.JarFile;

public class view_information extends JFrame implements ActionListener {
    String view;
    JButton cancel;
    view_information(String view){
        this.view = view;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(400,200,850,650);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,10,500,40);
        heading.setFont(new Font("Serif",Font.BOLD,25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(70,80,100,20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(200,80,150,20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(70,140,100,20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(200,140,150,20);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(200,200,150,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(200,260,150,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(600,80,150,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600,140,200,20);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600,200,200,20);
        add(phoneText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer where meterno = '"+view+"'");
            if(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meterno"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_num"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.black);
        cancel.setBackground(new Color(245,189,189));
        cancel.setBounds(220,350,120,30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLbl = new JLabel(i3);
        imgLbl.setBounds(100,320,600,300);
        add(imgLbl);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_information("");
    }
}
