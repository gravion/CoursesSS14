package u06;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Options extends JFrame implements ActionListener {
    
    MainFrame mainFrame;
    JPanel contentFrame;
    JButton button;
    
    JTextField[] tfs;
    
    public Options(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(160,520);
        this.setLocation((int)mainFrame.getLocation().getX() + mainFrame.getWidth(),
                            (int)mainFrame.getLocation().getY());
        contentFrame = (JPanel)this.getContentPane();
        contentFrame.setLayout(new GridLayout(8,2));
        //Display the window.

        setUp();
        
        this.setVisible(true);
    }

    private void setUp(){
        String[] list = {"a","b","c","d","e","f"};
        tfs = new JTextField[list.length];
        for(int i = 0; i < list.length; i++){
            JLabel l = new JLabel(list[i] + ":");
            tfs[i] = new JTextField();
            contentFrame.add(l);
            contentFrame.add(tfs[i]);
        }
        button = new JButton("paint");
        button.addActionListener(this);
        contentFrame.add(new JPanel());
        contentFrame.add(button);
        
        
        
        tfs[0].setText("0.125");
        tfs[1].setText("0");
        tfs[2].setText("0.25");
        tfs[3].setText("0");
        tfs[4].setText("0");
        tfs[5].setText("-10");
        mainFrame.paintEllipsoid(Double.parseDouble(tfs[0].getText()), Double.parseDouble(tfs[1].getText()),
                Double.parseDouble(tfs[2].getText()), Double.parseDouble(tfs[3].getText()),
                Double.parseDouble(tfs[4].getText()), Double.parseDouble(tfs[5].getText()));
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        mainFrame.paintEllipsoid(Double.parseDouble(tfs[0].getText()), Double.parseDouble(tfs[1].getText()),
                Double.parseDouble(tfs[2].getText()), Double.parseDouble(tfs[3].getText()),
                Double.parseDouble(tfs[4].getText()), Double.parseDouble(tfs[5].getText()));
    }
}
