package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchMonthCho;

    JTable table; //create a table using JTable.

    JButton search, close, print;
    deposit_details() {
        super("Deposit Details");

        getContentPane().setBackground(new Color(150,192,229));


        JLabel searchMeter = new JLabel("Search by Meter Number ");
        searchMeter.setBounds(20,20,180,20);
        searchMeter.setFont(new Font("Times New Roman",Font.BOLD,16));
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(210,20,150,20);
        add(searchMeterCho);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            while (resultSet.next()) {
                searchMeterCho.add(resultSet.getString("meterno"));
            }

        } catch (Exception E) {
            E.printStackTrace();
        }

        JLabel searchMonth = new JLabel("Search Month");
        searchMonth.setBounds(400,20,100,20);
        searchMonth.setFont(new Font("Times New Roman",Font.BOLD,16));
        add(searchMonth);

        searchMonthCho = new Choice();
        searchMonthCho.setBounds(530,20,150,20);
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        add(searchMonthCho);

        /*try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            //gets result from the given query which connects the db
            while (resultSet.next()) {
                searchMonthCho.add(resultSet.getString("month")); // result from the column 'name'
            }

        } catch (Exception E) {
            E.printStackTrace();
        }*/

        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception E) {
            E.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.white);
        scrollPane.setBounds(5,100,700,350);
        add(scrollPane);

        search = new JButton("Search");
        search.setBounds(20,60,100,25);
        search.setBackground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        close = new JButton("Close");
        close.setBounds(570,60,100,25);
        close.setBackground(Color.WHITE);
        close.addActionListener(this);
        add(close);

        print = new JButton("Print");
        print.setBounds(150,60,100,25);
        print.setBackground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        setSize(730,500);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            String query_search = "select * from bill where meterno='"+searchMeterCho.getSelectedItem()+"' and month='"+searchMonthCho.getSelectedItem()+"'";
            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try{
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit_details();
    }
}
