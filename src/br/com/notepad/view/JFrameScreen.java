package br.com.notepad.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo Fernandes
 */
public class JFrameScreen extends JFrame implements ActionListener {

    private TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_BOTH);
    private MenuBar menuBar = new MenuBar();
    private Menu file = new Menu(); //create menu
    private MenuItem openFile = new MenuItem("Abrir Arquivo"); //create submenus
    private MenuItem saveFile = new MenuItem("Salvar Arquivo"); //create submenus
    private MenuItem close = new MenuItem("Sair");//create submenus

    public JFrameScreen() {

        this.setSize(500, 300); //Screen size
        this.setTitle("Bloco de notas"); // Title screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Initialize operation default

        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12)); // Font size

        this.getContentPane().setLayout(new BorderLayout()); // create conteiner
        this.getContentPane().add(textArea); // Add texArea in contentPane

        //First Menu
        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.file);
        this.file.setLabel("Arquivo"); //Name title

        //Open file
        this.openFile.addActionListener(this);
        this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
        this.file.add(this.openFile);

        //Save file
        this.saveFile.addActionListener(this);
        this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
        this.file.add(this.saveFile);

        //Close aplication
        this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
        this.close.addActionListener(this);
        this.file.add(this.close);
    }

    //Implements actionPerformed
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.close) {
            this.dispose();
        } else if (e.getSource() == this.openFile) {
            JFileChooser open = new JFileChooser();

            int optin = open.showOpenDialog(this);
            if (optin == JFileChooser.APPROVE_OPTION) {
                this.textArea.setText("");
                try {
                    Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext()) {
                        this.textArea.append(scan.nextLine() + "\n");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else if (e.getSource() == this.saveFile) {
            JFileChooser save = new JFileChooser();
            int optin = save.showSaveDialog(this);
            if (optin == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                    out.write(this.textArea.getText());
                    out.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
