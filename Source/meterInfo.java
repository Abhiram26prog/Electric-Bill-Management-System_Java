package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterInfo extends JFrame implements ActionListener {
    Choice meterLocCho, meterTypeCho, phaseCodeCho, billTypeCho;
    JButton submit;
    String meterNumber;
    meterInfo(String meterNumber){
        super("Meter Information");

        this.meterNumber = meterNumber;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(175, 200, 222));
        add(panel);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(112,15,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,25));
        heading.setBackground(new Color(24, 2, 31));
        panel.add(heading);

        JLabel meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,80,100,20);
        meterNum.setFont(new Font("serif",Font.PLAIN,16));
        meterNum.setForeground(new Color(1,27,51));
        panel.add(meterNum);

        JLabel meterNumText = new JLabel(meterNumber);
        meterNumText.setBounds(180,80,100,20);
        meterNumText.setFont(new Font("serif",Font.PLAIN,16));
        panel.add(meterNumText);

        JLabel meterLoc = new JLabel("Meter Location");
        meterLoc.setBounds(50,120,100,20);
        meterLoc.setFont(new Font("serif",Font.PLAIN,16));
        meterLoc.setForeground(new Color(1,27,51));
        panel.add(meterLoc);

        meterLocCho = new Choice();
        meterLocCho.add("Outdoor");
        meterLocCho.add("Indoor");
        meterLocCho.setBounds(180,120,150,20);
        panel.add(meterLocCho);

        JLabel meterType = new JLabel("Meter Type");
        meterType.setBounds(50,160,100,20);
        meterType.setFont(new Font("serif",Font.PLAIN,16));
        meterType.setForeground(new Color(1,27,51));
        panel.add(meterType);

        meterTypeCho = new Choice();
        meterTypeCho.add("Electric Meter");
        meterTypeCho.add("Solar Meter");
        meterTypeCho.add("Smart Meter");
        meterTypeCho.setBounds(180,160,150,20);
        panel.add(meterTypeCho);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,200,100,20);
        phaseCode.setFont(new Font("serif",Font.PLAIN,16));
        phaseCode.setForeground(new Color(1,27,51));
        panel.add(phaseCode);

        phaseCodeCho = new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(180,200,150,20);
        panel.add(phaseCodeCho);

        JLabel billType = new JLabel("Bill Type");
        billType.setBounds(50,240,100,20);
        billType.setFont(new Font("serif",Font.PLAIN,16));
        billType.setForeground(new Color(1,27,51));
        panel.add(billType);

        billTypeCho = new Choice();
        billTypeCho.add("Normal");
        billTypeCho.add("Industrial");
        billTypeCho.setBounds(180,240,150,20);
        panel.add(billTypeCho);

        JLabel days = new JLabel("30 Days Billing Time...");
        days.setBounds(50,280,300,20);
        days.setFont(new Font("serif",Font.PLAIN,16));
        days.setForeground(new Color(1,27,51));
        panel.add(days);

        JLabel note = new JLabel("Note: ");
        note.setBounds(50,320,100,20);
        note.setFont(new Font("serif",Font.PLAIN,16));
        note.setForeground(new Color(1,27,51));
        panel.add(note);

        JLabel note1 = new JLabel("By Default Bill is Calculated for 30 Days Only.!");
        note1.setBounds(50,340,300,20);
        note1.setFont(new Font("serif",Font.PLAIN,15));
        note1.setForeground(new Color(1,27,51));
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(150,380,100,25);
        submit.setForeground(new Color(10, 12, 43));
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        add(imageLabel, "East");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
           String smeternum = meterNumber;
           String smeterloc = meterLocCho.getSelectedItem();
           String smetertype = meterTypeCho.getSelectedItem();
           String sphasecode = phaseCodeCho.getSelectedItem();
           String sbilltype = billTypeCho.getSelectedItem();
           String sday = "30";

           String queryMeterDt = "insert into meter_info values('"+smeternum+"','"+smeterloc+"','"+smetertype+"','"+sphasecode+"', '"+sbilltype+"','"+sday+"')";
           try{
               database c = new database();
               c.statement.executeUpdate(queryMeterDt);

               JOptionPane.showMessageDialog(null, "Meter Information Submitted Successfully");
           }catch (Exception E){
               E.printStackTrace();
           }
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new meterInfo("");
    }
}
