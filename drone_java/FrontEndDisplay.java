import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class FrontEndDisplay
{
    private JFrame frame;
    private JPanel basePanel;
    private int size_x = 1200,size_y = 700;
    private int click_x = 0,click_y = 0,prev_x = 0,prev_y = 0;
    private ArrayList<Line> line = new ArrayList<Line>();
    public boolean complete, takepic, change_alt = false;

    public FrontEndDisplay(){
        frame = new JFrame("Drone Mapping Interface");
        basePanel = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.blue);
                for(int i = 0; i<line.size();i++){
                    if(line.get(i).getStartX()!=line.get(i).getEndX()){
                        g.drawLine(line.get(i).getStartX(),line.get(i).getStartY(),line.get(i).getEndX(),line.get(i).getEndY());
                    }
                }
            }
        };

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size_x, size_y);
        frame.setVisible(true);

        frame.setContentPane(createbasePanel(basePanel));
    }

    private JPanel createbasePanel(JPanel Panel){

        Panel.setLayout(null);
        Panel.setSize(size_x,size_y);
        Panel.setOpaque(true);
        Panel.setBackground(Color.white);

        JButton end_button = new JButton("Complete");
        end_button.setSize(100,25);
        end_button.setLocation(size_x-100,0);
        end_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    complete = true;
                }
            });
        Panel.add(end_button);

        JButton pic_button = new JButton("Take Pic");
        pic_button.setSize(100,25);
        pic_button.setLocation(size_x-100,25);
        pic_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    takepic = true;
                }
            });
        Panel.add(pic_button);
        
        JButton alt_button = new JButton("Change Alt");
        alt_button.setSize(100,25);
        alt_button.setLocation(size_x-100,50);
        alt_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    change_alt = true;
                }
            });
        Panel.add(alt_button);

        Panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    click_x = e.getX();
                    click_y = e.getY();
                }
            });  

        return Panel;
    }

    public void drawLines(){

        if(prev_x!=click_x && prev_y!=click_y){
            Line l = new Line(prev_x,prev_y);
            l.setEndX(click_x);
            l.setEndY(click_y);
            line.add(l);
            prev_x = click_x;
            prev_y = click_y; 
            basePanel.repaint();
        }

    }

    public void setPicLine(){
        Line l = new Line();
        l.isPic = true;
        line.add(l);

        takepic = false;
    }

    public void setAlt(){
        Line l = new Line();
        l.isAlt = true;
        line.add(l);

        change_alt = false;
    }

    public void waitprint(){
        System.out.println("...");
    }

    public ArrayList<Line> getLineList(){
        return line;
    }

}