/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.*;
import javax.swing.*;


public class GUI {
    public GUI() {
JFrame frame = new JFrame();
JPanel panel = new JPanel();
panel.setBorder (BorderFactory.createEmptyBorder (30, 30, 10, 30));
panel.setLayout(new GridLayout(0, 1));
frame.add(panel, BorderLayout.CENTER);
frame.setDefaultCloseOperation (Frame.EXIT_ON_CLOSE);
frame.setTitle("Our GUI");
frame.pack();
frame.setVisible(true);
}
}
