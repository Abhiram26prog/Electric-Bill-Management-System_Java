package electricity.billing.system;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JLabel custName, meterNumText, heading, meterNum, address, city, state, email, phone;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;
    JButton next, cancel;
    newCustomer(){
        super("New Customer");

        JPanel panel = new JPanel(); //layout like panel
        panel.setLayout(null);
        panel.setBackground(new Color(200, 136, 219));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(100,15,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,25));
        heading.setBackground(new Color(24, 2, 31));
        panel.add(heading);

        custName = new JLabel("Customer Name");
        custName.setBounds(50,80,100,20);
        custName.setFont(new Font("serif",Font.PLAIN,16));
        custName.setForeground(new Color(24, 2, 31));
        panel.add(custName);

        nameText = new TextField();
        nameText.setBounds(180,80,150,20);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,120,100,20);
        meterNum.setFont(new Font("serif",Font.PLAIN,16));
        meterNum.setForeground(new Color(24, 2, 31));
        panel.add(meterNum);

        meterNumText = new JLabel("");
        meterNumText.setBounds(180,120,150,20);
        panel.add(meterNumText);

        //random Meter number generate

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterNumText.setText(""+ Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        address.setFont(new Font("serif",Font.PLAIN,16));
        address.setForeground(new Color(24, 2, 31));
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180,160,150,20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(50,200,100,20);
        city.setFont(new Font("serif",Font.PLAIN,16));
        city.setForeground(new Color(24, 2, 31));
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180,200,150,20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50,240,100,20);
        state.setFont(new Font("serif",Font.PLAIN,16));
        state.setForeground(new Color(24, 2, 31));
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180,240,150,20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(50,280,100,20);
        email.setFont(new Font("serif",Font.PLAIN,16));
        email.setForeground(new Color(24, 2, 31));
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180,280,150,20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setBounds(50,320,100,20);
        phone.setFont(new Font("serif",Font.PLAIN,16));
        phone.setForeground(new Color(24, 2, 31));
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180,320,150,20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(80,390,100,25);
        next.setBackground(new Color(24, 2, 31));
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,390,100,25);
        cancel.setBackground(new Color(24, 2, 31));;
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(300,280,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        add(imageLabel, "West");


        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == next){
            String sname = nameText.getText();
            String smeter = meterNumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            String queryCust = "insert into newCustomer values('"+sname+"', '"+smeter+"', '"+saddress+"', '"+scity+"', '"+sstate+"', '"+semail+"', '"+sphone+"')";
            String querySignup = "insert into Signup values('"+smeter+"', '', '"+sname+"', '', '')";

            try{
                database c = new database();
                c.statement.executeUpdate(queryCust);
                c.statement.executeUpdate(querySignup);

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                new meterInfo(smeter);
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
