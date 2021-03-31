package swing;

import pojo.ProfitWeight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ShowDataPanel extends JPanel
{

    private ArrayList<ProfitWeight> list=new ArrayList<>();
    private JComboBox<Integer> comboBox=new JComboBox<>();
    private JButton refresh=new JButton("请刷新并选择组数：");
    private JTextArea textArea=new JTextArea("当前没有数据",30,20);

    public ShowDataPanel()
    {
        BorderLayout layout=new BorderLayout();
        this.setLayout(layout);

        this.setBackground(Color.WHITE);

        textArea.setEditable(false);
        textArea.setFont(new Font("宋体",Font.BOLD,14));
        textArea.setLineWrap(true);//激活自动换行功能
        textArea.setWrapStyleWord(true); //激活断行不断字功能

        JScrollPane panel=new JScrollPane(textArea);

        //上面是输入的组数和确定按钮
        JPanel top=new JPanel();
        GridLayout grid=new GridLayout();
        top.setLayout(grid);
        top.add(refresh);
        top.add(comboBox);
        this.add(top,BorderLayout.NORTH);

        //下方是显示的数据
        this.add(panel,BorderLayout.CENTER);

        refresh.addActionListener((ActionEvent e)->{
                selectGroup();
            });

        comboBox.addActionListener((ActionEvent e)-> {
            showData();
        });
    }

    public void setList(ArrayList<ProfitWeight> list) {
        this.list = list;
    }

    private void selectGroup()
    {
        comboBox.removeAllItems();
        if(list==null)
        {
            return;
        }
        for(int i=0;i<list.size();i++)
        {
            comboBox.addItem(i+1);
        }
    }

    private void showData()
    {
        int count=comboBox.getSelectedIndex();
        if(count<0) {
            JOptionPane.showMessageDialog(this, "您当前未选择文件");
            return;
        }
        ProfitWeight data=list.get(count);
        textArea.setText("背包容量：\n"+data.getCubage()+
                "\n\n数量：\n"+data.getDimension()+
                "\n\n权重：\n"+data.getWeight()+
                "\n\n利润：\n"+data.getProfit());
    }

    public ProfitWeight getProfitWeight()
    {
        return this.list.get(comboBox.getSelectedIndex());
    }
}