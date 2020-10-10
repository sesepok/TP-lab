import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class SimpleGUI extends JFrame
{
	private JButton button = new JButton("Все готово");
    private JTextField input = new JTextField("Джон Сноу", 5);
    private JLabel label = new JLabel("Введите имя:");
    private JCheckBox checkbox = new JCheckBox("Согласны?");

    public SimpleGUI () 
    {
        super("Простая программа");
        this.setBounds(0, 0, 300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 2, 2, 2));
        container.add(label);
        container.add(input);

       
        //button.addActionListener(new ButtonEventListener ());
        container.add(button);
        container.add(checkbox);
    }

    /*class ButtonEventListener implements ActionListener 
    {
        public void actionPerformed (ActionEvent e) 
        {
            
        }
    }*/

}
