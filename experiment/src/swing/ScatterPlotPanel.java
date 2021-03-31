package swing;

import pojo.ProfitWeight;

import javax.swing.*;
import java.awt.*;

public class ScatterPlotPanel extends JPanel {

    private ProfitWeight data=new ProfitWeight();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width=this.getWidth()-10;//获得当前宽度
        int height=this.getHeight()-10;//获得当前高度

        this.setBackground(Color.WHITE);

        Graphics2D g2d= (Graphics2D) g;

        g2d.setFont(new Font("宋体",Font.BOLD,16));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(5,10,5,height);
        g2d.drawLine(5,height,width,height);
        g2d.drawLine(5,10,10,15);
        g2d.drawLine(0,15,5,10);
        g2d.drawLine(width,height,width-5,height-5);
        g2d.drawLine(width-5,height+5,width,height);

        g2d.drawString("weight",width-50,height-10);
        g2d.drawString("profit",5,10);
        g2d.drawString("0",3,height+10);

        //绘制所有的坐标点
        if(data.getProfit()==null){
            return;
        }
        g2d.setColor(Color.red);
        String[] profitSpot=this.getData().getProfit().split(",");
        String[] weightSpot=this.getData().getWeight().split(",");
        for (int i=0;i<profitSpot.length;i++)
        {
            int x=Integer.valueOf(weightSpot[i])+10;
            int y=height-Integer.valueOf(profitSpot[i])-10;
            g2d.fillOval(x,y,4,4);
        }
    }

    public ProfitWeight getData() {
        return data;
    }

    public void setData(ProfitWeight data) {
        this.data = data;
    }
}
