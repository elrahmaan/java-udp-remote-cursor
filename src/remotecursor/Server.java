/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecursor;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author USER
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            byte[] buffer = new byte[65];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            DatagramSocket ds = new DatagramSocket(1111);
            
            while(true){
                ds.receive(dp);
                byte[] data = dp.getData();
                String pesan = new String(data, 0, data.length);
                System.out.println("Koordinat cursor: " + pesan);
                
                //Titik koordinat mouse
                String[] posisi = pesan.split("\\|");
                int x = Integer.parseInt(posisi[0]);
                int y = Integer.parseInt(posisi[1]);
                String click = posisi[2];
                Robot rb = new Robot();
//                rb.mouseMove(x, y);
                
                if(click.contains("click")){
//                    rb.mousePress(InputEvent.BUTTON1_MASK);
//                    rb.mouseRelease(InputEvent.BUTTON1_MASK);
                    System.out.println("Position : x = " + x + "| y = " + y + "| clicked");
                }
            }
        } catch (Exception e) {
        }
    }
    
}
