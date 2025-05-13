import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class person2 implements Runnable{
    JTextField text;
    JTextArea texta;
    JButton but;
    Socket serverSocket ;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    person2() {







        Frame frame=new Frame("Murugan");

        text =new JTextField("murugan");
        text.setBounds(50,40,100,30);
        texta=new JTextArea("");
        texta.setBounds(20,90,300,300);
        but=new JButton("Send");
        but.setBounds(80,400,80,30);
        frame.setBackground(Color.ORANGE);
        frame.setLayout(null);
        frame.add(but);
        frame.add(texta);
        frame.add(text);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        try {
             serverSocket=new Socket("localhost",9999);



            dataInputStream=new DataInputStream(serverSocket.getInputStream());
            dataOutputStream=new DataOutputStream(serverSocket.getOutputStream());
            but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String msg=text.getText();
                    texta.append("person 2:"+msg+"\n");
                    text.setText("");

                    try {
                        dataOutputStream.writeUTF(msg);


                        dataOutputStream.flush();

                    }
                    catch (Exception a){

                    }

                }
            });

        }
        catch (Exception e){

        }
        Thread chat = new Thread(this);
        chat.setDaemon(true);
        chat.start();




        frame.setVisible(true);
        frame.setSize(500,500);

    }
    public static void main(String[]args)  {
        new person2();



    }  public void run()  {

        while (true) {
            try {


                String str = (String) dataInputStream.readUTF();
                texta.append("person 1 :"+str+"\n");
                text.setText("");
            }
            catch (Exception o){

            }
        }
    }


}