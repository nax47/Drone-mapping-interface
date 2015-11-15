import java.util.ArrayList;
import java.io.*;

public class Manage
{
    public static void main(String[] args) throws IOException{
        FrontEndDisplay display = new FrontEndDisplay();
        ArrayList<Line> line;
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Nakul/node/commands.txt"));
        PrintWriter commands = new PrintWriter(bw);

        while(true){
            display.waitprint();
            display.drawLines();
            if(display.takepic){
                display.setPicLine();
            }
            if(display.change_alt){
                display.setAlt();
            }
            if(display.complete){
                line = display.getLineList();
                for(int i = 0; i<line.size(); i++){
                    if(line.get(i).isPic){
                        commands.println("TP");
                    }
                    else if(line.get(i).isAlt){
                        if(line.get(i).isLow){
                            commands.println("UP");
                            line.get(i).isLow = false;
                        }
                        else{
                            commands.println("DOWN");
                            line.get(i).isLow = true;
                        }
                    }
                    else{
                        commands.println("X"+(line.get(i).getEndX()-line.get(i).getStartX())
                            +" Y"+(line.get(i).getEndY()-line.get(i).getStartY()));
                    }
                }
                commands.close();
                bw.close();
                return;
            }
        }
    }
}