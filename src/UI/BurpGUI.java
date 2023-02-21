package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BurpGUI {
    private List<String> list = new ArrayList<>();

    // Swing 根结点
    public JPanel root;
    private JComboBox fillText;
    private JComboBox dataBlock;
    private JTextArea textArea;
    private JButton descryptobtn;
    private JButton cryptobtn;
    private JLabel fill;
    private JLabel data;
    private JLabel PASSWORD;
    private JLabel IV;
    private JTextField passwdField;
    private JTextArea textArea1;
    public JPanel sec;
    private JTextField ivField;


    // 单例模式
    private volatile static BurpGUI burpGUI;

    public static BurpGUI getburpGUI() {
        if (burpGUI == null) {
            synchronized (BurpGUI.class) {
                if (burpGUI == null) {
                    burpGUI = new BurpGUI();
                }
            }
        }
        return burpGUI;
    }

    // 构造函数
    private BurpGUI() {
        this.setCryptobtn();
        this.setfillTextValue();
        this.setdataBlock();
        this.setDescryptobtn();
        this.textArea.setLineWrap(true);
        this.textArea1.setLineWrap(true);
    }

    // 加解密按钮监听事件
    private void setCryptobtn(){
        cryptobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = textArea.getText();
                String algorithmstr =  fillText.getSelectedItem().toString();
                int datablock =  Integer.parseInt(dataBlock.getSelectedItem().toString());
                String Key = passwdField.getText();
                try {
                    textArea1.setText("");
                    textArea1.append(AESUtil.oldAesEncrypt(content,algorithmstr,datablock,Key));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    // 解密
    private void setDescryptobtn(){
        descryptobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String algorithmstr =  fillText.getSelectedItem().toString();
                int datablock =  Integer.parseInt(dataBlock.getSelectedItem().toString());
                String Key = passwdField.getText();

                try {
                    String content = textArea.getText();
                    textArea1.setText("");
                    textArea1.append(AESUtil.aesDecrypt(content,algorithmstr,datablock,Key));
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }catch (Exception exc){
                    exc.printStackTrace();
                }

            }
        });
    }
    // 下拉菜单监听事件
    private void setfillTextValue(){
        fillText.addItem("请选择");
        fillText.addItem("AES/ECB/PKCS5Padding");
        fillText.addItem("AES/ECB/PKCS7Padding");
        fillText.addItem("zeropadding");
        fillText.addItem("iso10126");
        fillText.addItem("ansix923");
        fillText.addItem("no padding");
        fillText.setSelectedIndex(0);
        fillText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    list.add(fillText.getSelectedItem().toString());
                }
            }
        });
    }

    private void setdataBlock(){
        dataBlock.addItem("请选择");
        dataBlock.addItem("128");
        dataBlock.addItem("192");
        dataBlock.addItem("256");
        dataBlock.setSelectedIndex(0);
        dataBlock.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    list.add(dataBlock.getSelectedItem().toString());
                }
            }
        });
    }
}
