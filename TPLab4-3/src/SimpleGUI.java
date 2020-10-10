import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class SimpleGUI extends JFrame
{
	private JButton button = new JButton("Скрыть поле");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Введите число:");

    public SimpleGUI () 
    {
        super("Простая программа");
        this.setBounds(0, 0, 300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 2, 2, 2));
        container.add(label);
        input.setBackground(Color.red);
        container.add(input);

       
        button.addActionListener(new ButtonEventListener ());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener 
    {
        public void actionPerformed (ActionEvent e) 
        {
            if (input.isShowing())
            	input.setVisible(false);
            else
            	input.setVisible(true);
        }
    }

}
