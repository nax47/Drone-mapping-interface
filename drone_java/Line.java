
public class Line
{
    private int start_x,start_y,end_x,end_y;
    public boolean isPic, isAlt = false;
    public static boolean isLow = true;

    public Line(){
        this.start_x = 0;
        this.start_y = 0;
    }

    public Line(int start_x, int start_y){
        this.start_x = start_x;
        this.start_y = start_y;
    }

    public void setEndX(int end_x){
        this.end_x = end_x;
    }

    public void setEndY(int end_y){
        this.end_y = end_y;
    }

    public int getStartX(){
        return start_x;
    }

    public int getStartY(){
        return start_y;
    }

    public int getEndX(){
        return end_x;
    }

    public int getEndY(){
        return end_y;
    }

}