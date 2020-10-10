import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class SimpleGUI extends JFrame
{
	private JButton button = new JButton("����������");
    private JTextField input = new JTextField("������", 5);

    public SimpleGUI () 
    {
        super("������� ���������");
        this.setBounds(0, 0, 300, 75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2, 2, 2));
        container.add(input);

       
        button.addActionListener(new ButtonEventListener ());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener 
    {
        public void actionPerformed (ActionEvent e) 
        {
            if (input.getText().length() > 0)
            	JOptionPane.showMessageDialog(null, input.getText(), "����������", JOptionPane.PLAIN_MESSAGE);
        }
    }

}
