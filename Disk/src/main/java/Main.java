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
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        foo();
        /*JFrame window = new JFrame("Caption");
        JPanel panel = new JPanel();

        final JTextArea textArea=new JTextArea(2,2);
        JButton giveAllEntry=new JButton("Показать всех");
        giveAllEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append(users.get(0).toString());
            }
        });





        panel.add(giveAllEntry);
        panel.add(textArea);


        window.setSize(400,400);
        window.getContentPane().add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);*/

    }
    static void foo(){
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
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
}
