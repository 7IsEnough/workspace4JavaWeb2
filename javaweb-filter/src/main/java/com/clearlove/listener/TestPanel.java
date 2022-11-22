package com.clearlove.listener;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author promise
 * @date 2022/11/22 - 22:53
 */
public class TestPanel {

  public static void main(String[] args) {
    // 新建一个窗体
    Frame frame = new Frame("Viper前程似锦");
    // 面板
    Panel panel = new Panel(null);
    // 设置窗体的布局
    frame.setLayout(null);

    frame.setBounds(300,300,500,500);
    // 设置背景颜色
    frame.setBackground(Color.BLUE);

    panel.setBounds(50,50,300,300);
    panel.setBackground(Color.GREEN);

    frame.add(panel);

    frame.setVisible(true);


    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
      }
    });

    // 监听事件，监听关闭事件
    frame.addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent e) {
        System.out.println("打开");
      }

      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("关闭ing");
        System.exit(0);
      }

      @Override
      public void windowClosed(WindowEvent e) {
        System.out.println("关闭end");
      }

      @Override
      public void windowIconified(WindowEvent e) {

      }

      @Override
      public void windowDeiconified(WindowEvent e) {

      }

      @Override
      public void windowActivated(WindowEvent e) {
        System.out.println("激活");
      }

      @Override
      public void windowDeactivated(WindowEvent e) {
        System.out.println("未激活");
      }
    });
  }

}
