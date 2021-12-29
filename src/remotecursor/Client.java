/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecursor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class Client extends Frame implements MouseListener {

    static JLabel label1, label2, label3, label4;
    static boolean clicked = false;

    Client() {

    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Kelompok 06 MI3F | Remote Cursor");
        f.getContentPane().setLayout(new FlowLayout());
        f.setSize(1000, 1000);
        f.getContentPane().setBackground(new Color(51, 51, 51));
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();
        p.setBackground(new Color(51, 51, 51));
        p.setLayout(new FlowLayout());

        label1 = new JLabel("[-]     ");
        label1.setFont(new Font("Consolas", Font.BOLD, 15));
        label1.setForeground(Color.white);

        label2 = new JLabel("[-] ");
        label2.setFont(new Font("Consolas", Font.BOLD, 15));
        label2.setForeground(Color.white);

        Client client = new Client();

        f.addMouseListener(client);

        p.add(label1);
        p.add(label2);

        f.add(p);

        f.show();

        try {
            PointerInfo pointI;
            Point point;
            int x, y;
            InetAddress ia = InetAddress.getByName("localhost");
            int port = 1111;
            String pesan = "";

            while (true) {
                pointI = MouseInfo.getPointerInfo();
                point = pointI.getLocation();
                x = (int) point.getX();
                y = (int) point.getY();
                if (clicked) {
                    pesan = "x:" + String.valueOf(x) + " | " + "y:" + String.valueOf(y) + "| clicked";
                    clicked = false;
                } else {
                    pesan = "x:" + String.valueOf(x) + " | " + "y:" + String.valueOf(y);
                }
                System.out.println(pesan);
                byte[] data = pesan.getBytes();
                DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
                DatagramSocket ds = new DatagramSocket();
                Thread.sleep(100);
                ds.send(dp);
                label2.setText("[Koordinat Cursor x : " + x + " | y : " + y + "]");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }

    @Override
    public void mousePressed(MouseEvent e) {
        label1.setText("[Koordinat Cursor (Click) x : " + e.getX() + " | y : " + e.getY() + "]     ");
        clicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
