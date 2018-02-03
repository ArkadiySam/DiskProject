import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Самойлов Аркадий on 02.02.2018.
 */
public class Main {
    static List<User>users;
    static List disks;
    static User currentUser=new User();
    static SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
    static Session session=sessionFactory.openSession();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //foo();
        JFrame window = new JFrame("Disk Manager");
        JPanel panel = new JPanel();
        final JLabel label=new JLabel("Enter your login");
        final JTextArea textArea=new JTextArea();
        final JTextField textField=new JTextField();
        final JButton signInButton=new JButton("Authorization");
        final JButton viewAllButton=new JButton("View all users");
        final JButton viewUserDisk=new JButton("View user disk");
        final JButton addDisk=new JButton("Add new disk");

        textField.setBounds(10,10,150,40);
        signInButton.setBounds(170,10,150,40);
        viewAllButton.setBounds(170,100,150,40);
        viewUserDisk.setBounds(170,150,150,40);
        addDisk.setBounds(170,200,150,40);
        textArea.setBounds(10,100,130,100);
        label.setBounds(170,60,100,20);
        panel.setLayout(null);
        viewUserDisk.setEnabled(false);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputLogin=textField.getText();
                if(inputLogin.length()>3&&isAuthorization(inputLogin)) {
                    currentUser.setLogin(inputLogin);
                    label.setText("You are input");
                    viewUserDisk.setEnabled(true);
                }
                else
                    label.setText("login incorrect");

            }
        });

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getUsers();
                textArea.setText(users.toString());
            }
        });

        viewUserDisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    getUserDisk();
                    textArea.setText(disks.toString());

            }
        });

        addDisk.addActionListener(new ActionListener() {
            String filmTitle=textField.getText();
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewDisk(filmTitle);
            }
        });

        panel.add(addDisk);
        panel.add(viewUserDisk);
        panel.add(label);
        panel.add(textField);
        panel.add(signInButton);
        panel.add(textArea);
        panel.add(viewAllButton);


        window.setSize(400,600);
        window.getContentPane().add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    static void foo(){/*
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();*/
//        sessionFactory.close();
        //List<User>users=null;
        try {
            session.beginTransaction();
            Criteria criteria=session.createCriteria(User.class);
            criteria.add(Restrictions.eq("id",2L));
            users=criteria.list();


            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }

        for(Iterator iterator=users.iterator();iterator.hasNext();){
            User user=(User) iterator.next();
            System.out.println(user.toString());
        }
    }

    static boolean isAuthorization(String s){
        User user=null;
        try {
            session.beginTransaction();
            Criteria criteria=session.createCriteria(User.class);
            criteria.add(Restrictions.eq("login",s));
            user=(User)criteria.uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        if(user==null)
            return false;

        currentUser.setId(user.getId());
        return true;
    }

    static  void addNewDisk(String s){
        try {
            session.beginTransaction();
            Disk disk=new Disk();
            disk.setUser(currentUser);
            disk.setTitle(s);
            session.save(disk);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    static void getUsers(){
        //
            users=session.createCriteria(User.class).list();
    }

    static void getUserDisk(){
            //проверка на инъекцию
            disks=session.createQuery("from Disk where user_id="+currentUser.getId()).list();
//            disks=session.createCriteria(Disk.class).add(Restrictions.eq("user_id",currentUser.getId())).list();
    }
}
