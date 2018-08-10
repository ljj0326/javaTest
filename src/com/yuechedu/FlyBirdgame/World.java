package com.yuechedu.FlyBirdgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 需求:游戏的主要操作
 *    1.获得窗体界面--设置参数
 *    2.加载背景图片--绘制到jpanel上
 *       2.1重写Jpanel的Paint方法用户绘制图片数据
 *    3.将JPanel对象添加到窗体上
 *    
 *    
 * 
 * 
 * 
 * @author mzmy
 *
 */
public class World extends JPanel {

	//定义变量
	BufferedImage bgimage=null;
	//定义地面对象
	Ground ground=null;
	//定义柱子对象
	Column col1=null;
	Column col2=null;
	//定义小鸟对象
	Bird bird=null;
	//定义变量存数分数
	int score=100;
	//获得资源信息mspaint
	public World() throws Exception{
		//获得背景图片资源
		bgimage=ImageIO.read(this.getClass().getResource("bg.png"));
	  
		//调用初始化方法
		initData();
	}
	
	//创建一个初始化的方法
	public void initData() throws Exception{
		ground=new Ground(400);
		//获得柱子对象
		col1=new Column(320+100);
		col2=new Column(320+100+180);
		//初始化小鸟对象
		bird=new Bird();
	}
	
	//绘制图片
	/**
	 * Graphics 画笔
	 */
	@Override
	public void paint(Graphics g) {
		//绘制背景
		g.drawImage(bgimage, 0, 0, null);
		
		//绘制柱子
		col1.paint(g);
		col2.paint(g);
		//绘制小鸟
		bird.paint(g);
		//绘制地面
		ground.paint(g);
		/**
		 * 绘制分数
		 */
		//设置分数显示的字体样式 字体   粗细 大小
		Font font=new Font(Font.MONOSPACED,Font.BOLD,30);
	   //设置画笔字体样式
		g.setFont(font);
		//设置字体颜色
		g.setColor(Color.WHITE);
		//绘制文字
		g.drawString("得分:"+score, 10, 40);
	}
	
	//定义一个控制动作的方法
	public void action() throws Exception{
		//添加点击事件
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bird.flyFun();
			}
		});
		
		while(true){
		    //地面滚动
			ground.step();
			//柱子滚动
			col1.step();
			col2.step();
			//小鸟飞动
			bird.step();
			
			//重新绘制页面
			this.repaint();
			//休息一会
			Thread.sleep(1000/60);
		}
	}
     

	public static void main(String[] args) throws Exception {
		// 创建一个窗体对象
		JFrame jf=new JFrame("FlyBird");
		//创建jpanel对象
		World w=new World();
		//将面板添加到窗体
		jf.add(w);
		//设置窗体样式
		jf.setBounds(100, 100, 320,480);//设置大小和位置
		//设置默认的界面关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		//显示窗体
		jf.setVisible(true);
		//调用控制执行的方法
		w.action();
	

	}

}
