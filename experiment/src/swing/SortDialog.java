package swing;

import pojo.ProfitWeight;
import pojo.ProfitWeightRatio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class SortDialog extends JDialog {

    private JButton returnButton=new JButton("返回");
    private DefaultTableModel tableModel=new DefaultTableModel();
    private JTable table=new JTable(tableModel);
    private ArrayList<ProfitWeightRatio> indexs= new ArrayList<>();

    public SortDialog(String title,ProfitWeight profitWeight)
    {
        super();
        this.setTitle(title);
        this.setSize(1500,800);

        JPanel root=new JPanel();
        this.setContentPane(root);

        BorderLayout layout=new BorderLayout();
        this.setLayout(layout);

        this.add(returnButton,BorderLayout.NORTH);
        JScrollPane scrollPane=new JScrollPane(table);
        root.add(scrollPane,BorderLayout.CENTER);

        //处理数据
        String[] profitValue=profitWeight.getProfit().split(",");
        String[] weightValue=profitWeight.getWeight().split(",");
        //获取并计算权重比
        for (int i=2;i<profitValue.length;i+=3)
        {
            double profit=Double.parseDouble(profitValue[i]);
            double weight=Double.parseDouble(weightValue[i]);
            double ratio=profit/weight;
            indexs.add(new ProfitWeightRatio(profitValue[i],weightValue[i],ratio,i));
        }
        //排序
        Collections.sort(indexs,(ProfitWeightRatio o1, ProfitWeightRatio o2) -> {
            if(o1.getRatio()> o2.getRatio()) return -1;
            else if(o1.getRatio()== o2.getRatio()) return 0;
            else return 1;
        });

        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setRowHeight(20);
        table.setFont(new Font("宋体",Font.BOLD,18));
        JTableHeader tableHeader=this.table.getTableHeader();
        tableHeader.setFont(new Font("宋体",Font.BOLD,18));

        tableModel.addColumn("组号");
        tableModel.addColumn("价值重量比");
        tableModel.addColumn("价值");
        tableModel.addColumn("重量");
        tableModel.addColumn("剩余项1");
        tableModel.addColumn("剩余项2");

        //构造向量，向模板中添加数据
        for (int i = 0; i < indexs.size(); i++) {
            int indexValue=indexs.get(i).getIndex();
            Vector<Object> vector=new Vector<>();
            vector.add((i+1));
            vector.add(indexs.get(i).getRatio());
            vector.add(indexs.get(i).getProfit());
            vector.add(indexs.get(i).getWeight());
            vector.add("价值:"+profitValue[indexValue-2]+"重量:"+profitValue[indexValue-2]);
            vector.add("价值:"+profitValue[indexValue-1]+"重量:"+weightValue[indexValue-1]);
            tableModel.addRow(vector);
        }
        returnButton.addActionListener((ActionEvent event)->{
            this.setVisible(false);
        });
    }

    public void showDialog()
    {
        this.setVisible(true);
    }
}
