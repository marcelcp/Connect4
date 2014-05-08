/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4gewinnt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

/**
 *
 * @author Daniel Stadelmann <daniel.stadelmann@stud.hslu.ch>
 */
public class Spielfeld extends JFrame {

    // Spielerinformationen
    private String nickname;

    // Spielfeldkomponenten
    private int numOfSpalten;
    private int numOfZeilen;

    private JSplitPane splitPane;
    private JPanel networkPanel;
    private JPanel gameBoardPanel;
    private JPanel buttonPanel;
    private JPanel diskPanel;
    private InsertDiskListener insertDisk;

    // Komponenten die benötigt werden um Netzwerkspiel zu starten
    private JButton hostButton;
    private JButton connectButton;
    private JList<String> availableOpponents; // String??

    public Spielfeld(String nickname, int zeilen, int spalten) { //Parameter Spielfeldgrösse evt. von StartGUI erhalten -> variable Grösse
        super("ConnectFour");

        this.nickname = nickname;
        numOfZeilen = zeilen;
        numOfSpalten = spalten;

        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        drawMainFrame();
        setVisible(true);
    }

    public void drawMainFrame() {
        //networkPanel erzeugen
        networkPanel = new JPanel();
        
        //buttonPanel erzeugen, welches im Gameboard oberhalb jeder Spalte einen Button enthält
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.red);//zum Testen
        //diskPanel erzeugen welches das eigentliche Spielfeld mit den Spielsteinen enthält
        diskPanel = new JPanel();
        
        //gameBoardPanel erzeugen
        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BorderLayout());
        gameBoardPanel.setBackground(Color.WHITE);//nur zum Testen wie gross das eigentliche gameBoardPane ist
        gameBoardPanel.add(buttonPanel, BorderLayout.NORTH);
        gameBoardPanel.add(diskPanel, BorderLayout.CENTER);

        // Hauptframe unterteilen in Netzwerkkomponente auf der linken Seite und Spielbrett auf der rechten Seite
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, networkPanel, gameBoardPanel);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(1);

        //Listener erzeugen für insertCoin
        insertDisk = new InsertDiskListener();

        //ColumnButtons erzeugen
        for (int i = 1; i <= numOfSpalten; i++) {
            JButton temp = new JButton(Integer.toString(i));
            //.add(temp);
            temp.addActionListener(insertDisk);
        }
        Container contentPane = this.getContentPane();
        contentPane.add(splitPane);
    }
}
