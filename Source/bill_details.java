package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.sql.ResultSet;

public class bill_details extends JFrame {
    String meter;
    bill_details(String meter){
        super("Bill Details");


        setLocation(400,200);
        setSize(700,650);
        setLayout(null);
        setBackground(Color.white);

        JTable table = new JTable();

        try{
            database c = new database();
            String query_bill = " select * from bill where meterno = '"+meter+"' ";
            ResultSet resultSet = c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new bill_details("");
    }
}
