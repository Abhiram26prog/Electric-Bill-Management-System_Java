package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchNameCho;

    JTable table; //create a table using JTable.
    JButton search, close, print;
    customer_details(){
        super("Customer Details");
        getContentPane().setBackground(new Color(150, 192, 229));


        JLabel searchMeter = new JLabel("Search by Meter Number ");
        searchMeter.setBounds(20,20,180,20);
        searchMeter.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(210,20,150,20);
        add(searchMeterCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer");
            while(resultSet.next()){
                searchMeterCho.add(resultSet.getString("meterno"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchName = new JLabel("Search Name ");
        searchName.setBounds(400,20,100,20);
        searchName.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(searchName);

        searchNameCho = new Choice();
        searchNameCho.setBounds(530,20,150,20);
        add(searchNameCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer");
            //gets result from the given query which connects the db
            while(resultSet.next()){
                searchNameCho.add(resultSet.getString("name")); // result from the column 'name'
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newCustomer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
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
            String query_search = "select * from newCustomer where meterno='"+searchMeterCho.getSelectedItem()+"' and name='"+searchNameCho.getSelectedItem()+"'";
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
        new customer_details();
    }
}
