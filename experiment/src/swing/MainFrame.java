package swing;

import jdk.nashorn.internal.scripts.JO;
import org.apache.commons.io.FileUtils;
import pojo.ProfitWeight;
import util.DynamicProgramming;
import util.InitAnalysis;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private ArrayList<ProfitWeight> list=new ArrayList<>();
    private JLabel showFilePath=new JLabel("D:\\liaotianwenjianjia\\MobileFile\\实验二 任务3data_set\\idkp1-10.txt");
    private JButton selectFile= new JButton("选择文件");
    private JButton getFileData=new JButton("显示数据");
    private JButton sort=new JButton("非递增排序");
    private JButton showScatterPlot=new JButton("散点图");
    private JButton dynamicProgramming=new JButton("动态规划算法求解");
    private JButton backtracking=new JButton("回溯算法求解");
    private JButton saveResult=new JButton("保存结果");
    private ShowDataPanel center=new ShowDataPanel();
    private Double time;
    private Integer optimalSolution;

    public MainFrame(String title)
    {
        super(title);

        //设置根容器
        JPanel root=new JPanel();
        this.setContentPane(root);

        //设置边框
        Border border=BorderFactory.createLineBorder(Color.CYAN,8);
        root.setBorder(border);

        //设置根布局为边框布局器
        BorderLayout borderLayout=new BorderLayout();
        root.setLayout(borderLayout);

        //整体中间放置面板
        root.add(center,BorderLayout.CENTER);

        //整体上面放置面板
        JPanel top=new JPanel();
        BorderLayout topBorderLayout=new BorderLayout();
        top.setLayout(topBorderLayout);
        root.add(top,BorderLayout.NORTH);

        //整体上部的按钮组
        JPanel topButtonGroup=new JPanel();
        GridLayout grid=new GridLayout();
        topButtonGroup.setLayout(grid);
        topButtonGroup.add(selectFile);
        topButtonGroup.add(getFileData);
        topButtonGroup.add(showScatterPlot);
        topButtonGroup.add(sort);
        topButtonGroup.add(dynamicProgramming);
        topButtonGroup.add(backtracking);
        topButtonGroup.add(saveResult);
        top.add(topButtonGroup,BorderLayout.NORTH);

        //整体上部中间的文本域
        JPanel topTextFieldPanel=new JPanel();
        BorderLayout topBorder=new BorderLayout();
        topBorder.addLayoutComponent(topTextFieldPanel,BorderLayout.CENTER);
        topTextFieldPanel.add(showFilePath);
        top.add(topTextFieldPanel,BorderLayout.CENTER);
        //选择文件处理
        selectFile.addActionListener((ActionEvent e)->{
            selectFileDeal();
        });
        //显示文件数据处理
        getFileData.addActionListener((ActionEvent e)->{
            showDataDeal();
        });
        //散点图的处理
        showScatterPlot.addActionListener((ActionEvent e)->{
            showScatterPlotDeal();
        });
        //非递增排序处理
        sort.addActionListener((ActionEvent e)->{
            sortDeal();
        });
        //动态规划算法处理
        dynamicProgramming.addActionListener((ActionEvent e)->{
            dynamicProgrammingDeal();
        });
        //回溯法算法处理
        backtracking.addActionListener((ActionEvent e)->{
            backtrackingDeal();
        });
        //保存结果至文件
        saveResult.addActionListener((ActionEvent e)->{
            saveResultDeal();
        });
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getOptimalSolution() {
        return optimalSolution;
    }

    public void setOptimalSolution(Integer optimalSolution) {
        this.optimalSolution = optimalSolution;
    }

    public ArrayList<ProfitWeight> getList() {
        return list;
    }

    public void setList(ArrayList<ProfitWeight> list) {
        this.list = list;
    }

    //选择文件处理
    private void selectFileDeal()
    {
        JOptionPane.showMessageDialog(this,"您必须选择正确的文件，否则无法解析文件内容");
        //弹出文件选择对话框
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("D:\\liaotianwenjianjia\\MobileFile\\实验二 任务3data_set\\idkp1-10.txt"));
        int result=chooser.showOpenDialog(this);//显示对话框
        if(result==JFileChooser.APPROVE_OPTION)
        {
            File file=chooser.getSelectedFile();
            showFilePath.setText(file.getAbsolutePath());
            InitAnalysis analysis=new InitAnalysis();
            try {
                analysis.getAllParameterByFile(file.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,"读入文件数据出错");
                return;
            }
            //显示数据
            this.setList(analysis.getList());
        }
    }
    //展示文件数据处理
    private void showDataDeal()
    {
        if(list.size()==0) {
            JOptionPane.showMessageDialog(this, "您当前未选择文件");
            return;
        }
        JOptionPane.showMessageDialog(this, "请点击下方\"请刷新并选择组数\"显示数据");
        center.setList(this.getList());
    }
    //散点图处理
    private void showScatterPlotDeal()
    {
        if(list.size()==0) {
            JOptionPane.showMessageDialog(this, "您当前未选择文件");
            return;
        }
        ScatterPlotDialog dialog=new ScatterPlotDialog("散点图",center.getProfitWeight());
        dialog.showDialog();
    }
    //非递增排序处理
    private void sortDeal()
    {
        if(list.size()==0) {
            JOptionPane.showMessageDialog(this, "您当前未选择文件");
            return;
        }
        //传递参数
        SortDialog dialog=new SortDialog("非递增排序",center.getProfitWeight());
        dialog.showDialog();
    }
    //动态规划算法处理
    private void dynamicProgrammingDeal()
    {
        if(list.size()==0) {
            JOptionPane.showMessageDialog(this, "您当前未选择文件");
            return;
        }
        DynamicProgramming dp=new DynamicProgramming();
        dp.setProfitWeight(center.getProfitWeight());
        this.setTime(dp.discountBackpack()/1000.0);
        this.setOptimalSolution(dp.getOptimalSolution());
        JOptionPane.showMessageDialog(this,
                "最优解:"+optimalSolution+",耗时："+time+"s",
                "动态规划算法求解最优解",
                JOptionPane.INFORMATION_MESSAGE);
    }
    //回溯法处理
    private void backtrackingDeal()
    {
        JOptionPane.showMessageDialog(this,
                "回溯法没有实现",
                "回溯法求解最优解",
                JOptionPane.INFORMATION_MESSAGE);
    }
    private void saveResultDeal()
    {
        if(list.size()==0) {
            JOptionPane.showMessageDialog(this, "您当前未选择文件");
            return;
        }
        if (this.getTime()==null||this.getOptimalSolution()==null)
            JOptionPane.showMessageDialog(this,"当前未进行算法处理","提示",JOptionPane.INFORMATION_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("D:\\liaotianwenjianjia\\MobileFile\\实验二 任务3data_set\\idkp1-10.txt"));
        int result=chooser.showSaveDialog(this);//显示对话框
        if (result==JFileChooser.APPROVE_OPTION)
        {
            File file=chooser.getSelectedFile();
            String text="耗时："+this.getTime()+",最优解："+this.getOptimalSolution();
            try {
                FileUtils.writeStringToFile(file,text,"UTF-8");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "写文件出现错误",
                        "错误",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
