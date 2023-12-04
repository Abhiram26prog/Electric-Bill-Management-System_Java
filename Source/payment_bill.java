package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment_bill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    payment_bill(String meter){
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://paytm.com/online-payments");
            j.setBounds(400,160,800,600);
        }catch (Exception E){
            E.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html> Error! Error! Error! Error! Error! </html>");
        }

        JScrollPane jsp = new JScrollPane(j);
        add(jsp);

        back = new JButton("Back");
        back.setBounds(640,20,100,25);
        back.addActionListener(this);
        j.add(back);

        setSize(800,300);
        setLocation(400,200);
        //setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new pay_bill(meter);
    }

    public static void main(String[] args) {
        new payment_bill("");
    }
}
