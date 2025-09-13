import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class NotePad {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Notepad");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        JMenuItem about = new JMenuItem("About");

        frame.add(scrollPane);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    
        open.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error opening file: " + ex.getMessage());
                }
            }
        });

        save.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(writer);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
                }
            }
        });

        exit.addActionListener(e -> System.exit(0));

        // Edit menu actions
        cut.addActionListener(e -> textArea.cut());
        copy.addActionListener(e -> textArea.copy());
        paste.addActionListener(e -> textArea.paste());

        // Help menu actions
        about.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Name:O.S.Jayathung\n ID:s16807", "About", JOptionPane.INFORMATION_MESSAGE)
        );



        frame.setJMenuBar(menuBar);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

