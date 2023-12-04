package electricity.billing.system;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {
    Choice meterNumCho, monthCho;
    JLabel nameText,addressText;
    TextField unitText;
    JButton submit, cancel;
    calculate_bill(){
        super("Calculate Bill");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        heading.setBounds(70,10,300,20);
        panel.add(heading);

        JLabel meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,80,100,20);
        panel.add(meterNum);

        meterNumCho = new Choice();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer");
            while(resultSet.next()){
                meterNumCho.add(resultSet.getString("meterno"));
            }
        }catch(Exception E){
            E.printStackTrace();
        }
        meterNumCho.setBounds(180,80,100,20);
        panel.add(meterNumCho);

        JLabel namee = new JLabel("Name");
        namee.setBounds(50,120,100,20);
        panel.add(namee);

        nameText = new JLabel();
        nameText.setBounds(180,120,150,20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addressText = new JLabel();
        addressText.setBounds(180,160,150,20);
        panel.add(addressText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer where meterno = '"+meterNumCho.getSelectedItem()+"' ");
            while(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel unitConsumed = new JLabel("Unit Consumed");
        unitConsumed.setBounds(50,200,100,20);
        panel.add(unitConsumed);

        unitText = new TextField();
        unitText.setBounds(180,200,150,20);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,240,150,22);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(60,300,100,25);
        submit.setBackground(Color.LIGHT_GRAY);
        submit.setForeground(Color.black);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,300,100,25);
        cancel.setBackground(Color.LIGHT_GRAY);;
        cancel.setForeground(Color.red);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = img.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(image);
        JLabel imageB = new JLabel(img2);
        add(imageB, "East");

        setSize(650,400);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            String smeterNo = meterNumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();

            int totalBill = 0;
            int units = Integer.parseInt(sunit);
            String queryTax = "select * from tax";
            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(queryTax);
                while (resultSet.next()){
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swachh_bharat_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            }catch (Exception E){
                E.printStackTrace();
            }

            String query_total_bill = "insert into bill values('"+smeterNo+"','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";

            try{
                database c = new database();
                c.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);

            }catch (Exception E){
                E.printStackTrace();
            }

        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}
