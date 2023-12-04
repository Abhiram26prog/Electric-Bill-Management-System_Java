package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.RescaleOp;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {
    String meter;
    Choice monthCho;
    JButton pay, back;
    pay_bill(String meter){
        this.meter = meter;
        setSize(900,600);
        setLocation(400,200);
        setLayout(null);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        heading.setBounds(120,10,400,30);
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(35,80,150,20);
        add(meterNumber);

        JLabel meterNumText = new JLabel("");
        meterNumText.setBounds(300,80,200,20);
        add(meterNumText);

        JLabel name = new JLabel("Name");
        name.setBounds(35,130,150,20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(300,130,200,20);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35,180,150,20);
        add(month);

        monthCho = new Choice();
        monthCho.setBounds(300,180,200,20);
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
        add(monthCho);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,280,150,20);
        add(unit);

        JLabel unitText = new JLabel("");
        unitText.setBounds(300,280,200,20);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(35,330,150,20);
        add(totalBill);

        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(300,330,200,20);
        add(totalBillText);

        JLabel status = new JLabel("Status");
        status.setBounds(35,380,150,20);
        add(status);

        JLabel statusText = new JLabel("");
        statusText.setBounds(300,380,200,20);
        statusText.setForeground(Color.red);
        add(statusText);

        try{
            database c= new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer where meterno = '"+meter+"'");

            while (resultSet.next()){
                meterNumText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        monthCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from bill where meterno = '"+meter+"' and month='"+monthCho.getSelectedItem()+"'");
                    while(resultSet.next()){
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setForeground(Color.white);
        pay.setBackground(Color.black);
        pay.addActionListener(this);
        pay.setBounds(100,460,150,25);
        add(pay);

        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(280,460,150,25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==pay){
            try{
                database c= new database();
                c.statement.executeUpdate("update bill set status = 'Paid' where meterno = '"+meter+"' and month='"+monthCho.getSelectedItem()+"'");

            }catch (Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
