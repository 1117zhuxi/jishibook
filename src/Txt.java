

import javax.swing.*;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Txt extends JFrame implements ActionListener{
    private StringBuilder ss=new StringBuilder();

    private JMenuBar jm1=new JMenuBar();                      //创建菜单栏即对应菜单项
    private JMenu jme1=new JMenu("文件(F)");
    private JMenuItem jm3=new JMenuItem("新建(N)                 ");
    private JMenuItem jm4=new JMenuItem("新窗口(W)      ");
    private JMenuItem jm5=new JMenuItem("打开(O)...          ");
    private JMenuItem jm6=new JMenuItem("保存(S)            ");
    private JMenuItem jm7=new JMenuItem("另存为(A)       ");
    private JMenuItem jm8=new JMenuItem("退出(X)");

    private JMenu jme2=new JMenu("格式(O)");
    private JMenuItem jm9=new JMenuItem("自动换行(W)");

    private JMenu jme3=new JMenu("颜色(C)");
    private JMenuItem jm10=new JMenuItem("字体颜色(C)");

    private JMenu jme4=new JMenu("帮助(H)");
    private JMenuItem jm11=new JMenuItem("查看帮助(H)");

    private JMenu jme5 = new JMenu("功能(E)");

    private JMenuItem jm12 = new JMenuItem("查找(F)");
    private JMenuItem jm13 = new JMenuItem("替换(R)");

    private DefaultColorSelectionModel modle=new DefaultColorSelectionModel();   //默认颜色选择模板
    private  JColorChooser jcc=new JColorChooser(modle);
    private JFileChooser jfc=new JFileChooser(new File(""));


    private JTextArea jt=new JTextArea(10,30);
    private JScrollPane jsp=new JScrollPane(jt);
    private Font font=new Font("楷体",0,22);

    Txt()
    {
        super("记事本");   //设置框架标题

        add(jm1,BorderLayout.NORTH);
        add(jsp,BorderLayout.CENTER);

        jt.setLayout(null);
        jt.setFont(font);

        jm1.add(jme1);jm1.add(jme2);jm1.add(jme3);jm1.add(jme5);jm1.add(jme4);         //将对应菜单选项加入对应菜单项

        jme1.add(jm3);jme1.add(jm4);jme1.add(jm5);jme1.add(jm6);
        jme1.add(jm7);jme1.addSeparator();
        jme1.addSeparator();jme1.add(jm8);

        jme2.add(jm9);
        jme3.add(jm10);
        jme4.add(jm11);
        jme5.add(jm12);
        jme5.add(jm13);

        jme1.setMnemonic('F');   //mnemonic n/助记符 adj.记忆的  设置助记符，按alt+字符 打开对应菜单项
        jme2.setMnemonic('O');
        jme3.setMnemonic('C');
        jme4.setMnemonic('H');
        jme5.setMnemonic('E');

        jm3.setMnemonic('N');
        jm4.setMnemonic('W');
        jm5.setMnemonic('O');
        jm6.setMnemonic('S');
        jm7.setMnemonic('A');
        jm8.setMnemonic('X');
        jm9.setMnemonic('W');
        jm10.setMnemonic('C');
        jm12.setMnemonic('F');
        jm13.setMnemonic('R');

        //设置快捷键，按对应快捷键进行相应操作
        jm3.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK)));
        jm4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        jm5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        jm6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        jm7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        jm8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        jm10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        jm11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        jm12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
        jm13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));

        jm3.addActionListener(new ActionListener() {               //采用匿名内部类的形式书写事件处理
            @Override
            public void actionPerformed(ActionEvent e) {
                jt.setText("");
                setTitle("无标题-记事本");
            }
        });
        jm4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Txt t1=new Txt();
                t1.setTitle("无标题-记事本");
            }
        });
        jm8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jm11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Txt.this,"共同成长！！","帮助",JOptionPane.PLAIN_MESSAGE);
            }
        });
        jm12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jt.getText();
//                String str = JOptionPane.showInputDialog(null, "请输入查询内容：",null);
                JDialog jd = new JDialog((Frame) null,"查找");
                JButton bt1 = new JButton("查找下一个");
                JTextArea jTextArea = new JTextArea(1,10);
                JLabel jLabel = new JLabel("请输入要查找的内容：");
                jd.add(jLabel,BorderLayout.NORTH);
                jd.add(jTextArea);
                jd.add(bt1,BorderLayout.SOUTH);
                bt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = jTextArea.getText();
                        int start = text.indexOf(str);
                        if (start!=-1){
                            jt.select(start,start+str.length());
                        }
                    }
                });
                jd.setPreferredSize(new Dimension(300,150));
                jd.setVisible(true);
                jd.pack();
            }
        });
        jm13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jt.getText();
                final int[] start = {-1};
                final String[] str = {""};
//                String str = JOptionPane.showInputDialog(null, "请输入查询内容：",null);
                JDialog jd = new JDialog((Frame) null,"替换");
                JButton bt1 = new JButton("查找下一个");
                JButton bt2 = new JButton("替换");
                JPanel jPanel = new JPanel();
                jPanel.add(bt1);
                jPanel.add(bt2);
                JPanel jPanel1 = new JPanel();
                JPanel jPanel2 = new JPanel();
                JTextArea jTextArea1 = new JTextArea(1,10);
                JTextArea jTextArea2 = new JTextArea(1,10);
                JLabel jLabel1 = new JLabel("请输入要查找的内容：");
                JLabel jLabel2 = new JLabel("请输入要替换的内容：");
                jPanel1.add(jLabel1);
                jPanel1.add(jTextArea1);
                jPanel2.add(jLabel2);
                jPanel2.add(jTextArea2);
                jd.add(jPanel1,BorderLayout.NORTH);
                jd.add(jPanel2);
                jd.add(jPanel,BorderLayout.SOUTH);
                bt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        str[0] = jTextArea1.getText();
                        start[0] = text.indexOf(str[0]);
                        if (start[0] !=-1){
                            jt.select(start[0], start[0] + str[0].length());
                        }
                    }
                });
                bt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text1 = jTextArea2.getText();
                        jt.replaceRange(text1, start[0], start[0] + str[0].length());
                    }
                });

                jd.setPreferredSize(new Dimension(500,150));
                jd.setVisible(true);
                jd.pack();
            }
        });

        jm5.addActionListener(this);     //对菜单项添加监听
        jm7.addActionListener(this);
        jm8.addActionListener(this);
        jm6.addActionListener(this);
        jm9.addActionListener(this);
        jm10.addActionListener(this);

        setBounds(700,250,700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {      //主方法
        Txt t=new Txt();
    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getActionCommand().equals("打开(O)...          "))           //打开文件
        {
            int open=jfc.showOpenDialog(this);
            if(open!=JFileChooser.APPROVE_OPTION)
            {}
            else
            {
                try{
                    File f=jfc.getSelectedFile();
                    ss.append(f.getAbsolutePath());
                    FileReader fr=new FileReader(f);

                    BufferedReader br=new BufferedReader(fr);
                    StringBuilder info=new StringBuilder();
                    String str;
                    while((str=br.readLine())!=null) {info.append(str+"\r\n");}
                    jt.setText(String.valueOf(info));
                    br.close();
                }catch (IOException ioe){}
            }
        }
        else if(e.getActionCommand().equals("另存为(A)       "))             //存储文件
        {
            int save=jfc.showSaveDialog(this);
            if(save==JFileChooser.APPROVE_OPTION)
            {
                try{
                    File f2=jfc.getSelectedFile();
                    FileWriter fw=new FileWriter(f2);                 //第二种措施
                    String str=jt.getText();
                    fw.write(str);
                    fw.close();

                }catch (IOException io){System.out.println(io.getMessage());}
            }
        }
        else if(e.getActionCommand().equals("保存(S)            "))          //保存文件
        {
            if(this.getTitle()=="无标题-记事本") {
                int save=jfc.showSaveDialog(this);
                if(save==JFileChooser.APPROVE_OPTION)
                    try {
                        File f2 = jfc.getSelectedFile();
                        FileWriter fw = new FileWriter(f2);                 //第二种措施
                        String str = jt.getText();
                        fw.write(str);
                        fw.close();
                    }catch (IOException i){}
            }
            else {
                try {

                    String str = jt.getText();
                    FileWriter ra=new FileWriter(String.valueOf(ss));
                    ra.write(str);
                    ra.close();
                }catch (IOException i){System.out.println(i.getMessage());}
            } }
        else if(e.getActionCommand().equals("自动换行(W)"))             //设置文本是否自动换行
        {
            boolean b=!(jt.getLineWrap());
            jt.setLineWrap(b);
        }
        else if(e.getActionCommand().equals("字体颜色(C)"))               //改变字体颜色
        {
            Color color=jcc.getColor();
            Color c=JColorChooser.showDialog(this,"颜色选择",color);
            jt.setForeground(c);
        } }}
