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

public class snakw implements Runnable{
        JTextField text;
        JTextArea texta;
        JButton but;
        Socket serverSocket ;
        ServerSocket socket;

        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        snakw() {







                        Frame frame=new Frame("Bala");

                        text =new JTextField("bala");
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
                        socket=new ServerSocket(9999);
                        serverSocket=socket.accept();



                        dataInputStream=new DataInputStream(serverSocket.getInputStream());
                        dataOutputStream=new DataOutputStream(serverSocket.getOutputStream());
                          but.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                          String masg=text.getText();
                                          texta.append("person 1:"+masg+"\n");
                                          text.setText("");
                                          try {


                                                  dataOutputStream.writeUTF(masg);
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



                frame.setSize(500,500);
                frame.setVisible(true);

        }
        public static void main(String[]args)  {
                new snakw();



        }  public void run()  {

                while (true) {
                        try {


                                String str = (String) dataInputStream.readUTF();
                                texta.setText("person 2:"+str+"\n");
                                text.setText("");
                        }
                        catch (Exception o){

                        }
                }
        }


}