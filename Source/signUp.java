package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class signUp extends JFrame implements ActionListener{
    Choice loginAsCho;
    TextField empId, meterText, usname, pswd, namee;
    JButton createBtn,backBtn;
    signUp(){
        super("Sign-up Page");

        getContentPane().setBackground(new Color(180, 216, 219));

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginAsCho = new Choice();
        loginAsCho.setBounds(170,50,120,20);
        loginAsCho.add("Admin");
        loginAsCho.add("User");
        add(loginAsCho);

        JLabel employerId = new JLabel("Employer ID");
        employerId.setBounds(30,85,125,20);
        employerId.setVisible(true);
        add(employerId);

        empId = new TextField();
        empId.setBounds(170,85,120,20);
        empId.setVisible(true);
        add(empId);

        JLabel meterNum = new JLabel("Meter Number");
        meterNum.setBounds(30,85,125,20);
        meterNum.setVisible(false);
        add(meterNum);

        meterText = new TextField();
        meterText.setBounds(170,85,120,20);
        meterText.setVisible(false);
        add(meterText);

        JLabel userName = new JLabel("Username");
        userName.setBounds(30,120,125,20);
        userName.setVisible(true);
        add(userName);

        usname = new TextField();
        usname.setBounds(170,120,120,20);
        usname.setVisible(true);
        add(usname);

        JLabel name = new JLabel("Name");
        name.setBounds(30,155,125,20);
        name.setVisible(true);
        add(name);

        namee = new TextField("");
        namee.setBounds(170,155,120,20);
        namee.setVisible(true);
        add(namee);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from newCustomer where meterno = '"+meterText.getText()+"'");
                    if(resultSet.next()){
                        namee.setText(resultSet.getString("name"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30,190,125,20);
        password.setVisible(true);
        add(password);

        pswd = new TextField();
        pswd.setBounds(170,190,120,20);
        pswd.setVisible(true);
        add(pswd);

        //this works when the button is clicked.
        loginAsCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAsCho.getSelectedItem();
                if (user.equals("User")){
                   employerId.setVisible(false);
                   namee.setEditable(false);
                   empId.setVisible(false);
                   meterNum.setVisible(true);
                   meterText.setVisible(true);
                }else{
                    employerId.setVisible(true);
                    empId.setVisible(true);
                    meterNum.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        createBtn = new JButton("Create");
        createBtn.setBounds(40,245,100,25);
        createBtn.setBackground(new Color(46, 102, 61));
        createBtn.setForeground(Color.WHITE);
        createBtn.addActionListener(this); //to perform the action is create to actionPerformed we use this
        add(createBtn);
        
        backBtn = new JButton("Back");
        backBtn.setBounds(175,245,100,25);
        backBtn.setBackground(new Color(222, 101, 53));
        backBtn.setForeground(Color.BLACK);
        backBtn.addActionListener(this); //to perform the action is back we need this keyword
        add(backBtn);

        ImageIcon boyPic = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image bp2 = boyPic.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT); //scale the image
        ImageIcon fBoyPic = new ImageIcon(bp2);
        JLabel signBoy = new JLabel(fBoyPic);
        signBoy.setBounds(300,10,310,260);
        add(signBoy);

        setSize(600,350);
        setLocation(500,250);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== createBtn){
            // creates strings to get updated to the query and those strings have the values of the global variables
            String sloginAs = loginAsCho.getSelectedItem();
            String suser = usname.getText();
            String sname = namee.getText();
            String spswd = pswd.getText();
            String smeter = meterText.getText();
            try{
                database c = new database();
                String query = null;
                if(loginAsCho.equals("Admin")) {
                    query = "insert into Signup value('" + smeter + "','" + suser + "','" + sname + "','" + spswd + "','" + sloginAs + "')";
                }else {
                    query = "update Signup set username='"+suser+"', password='"+spswd+"', usertype='"+sloginAs+"' where meterno='"+smeter+"' ";
                }
                //this will added to the table as a query and run the sql db.

                c.statement.executeUpdate(query); //the query gets updated from the values.

                JOptionPane.showMessageDialog(null, "Account Created :)");
                setVisible(false);
                new Login();

            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else if(e.getSource()== backBtn){
            setVisible(false);
            new Login(); // jump to Login.java page
        }
    }

    public static void main(String[] args) {
        new signUp();
    }
}
