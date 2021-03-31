package swing;

import pojo.ProfitWeight;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ScatterPlotDialog extends JDialog {

    private JButton closeButton=new JButton("关闭散点图的显示");
    private ScatterPlotPanel paintPanel=new ScatterPlotPanel();

    public ScatterPlotDialog(String title,ProfitWeight data)
    {
        super();

        this.setTitle(title);
        this.setSize(1500,800);

        //设置跟容器
        JPanel root=new JPanel();
        this.setContentPane(root);

        //设置卡片布局
        BorderLayout layout=new BorderLayout();
        root.setLayout(layout);

        //设置边框
        Border padding=BorderFactory.createEmptyBorder(5,5,5,5);
        root.setBorder(padding);

        //传递数据
        paintPanel.setData(data);

        //设置上方的按钮
        root.add(closeButton,BorderLayout.NORTH);
        root.add(paintPanel,BorderLayout.CENTER);

        closeButton.addActionListener((ActionEvent event)->{
            this.setVisible(false);
        });
    }

    public void showDialog()
    {
        this.setVisible(true);
    }
}
