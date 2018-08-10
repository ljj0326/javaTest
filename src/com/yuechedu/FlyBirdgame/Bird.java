package com.yuechedu.FlyBirdgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *需求：编写小鸟相关的操作
 *  1.加载小鸟的相关图片资源
 *  2.小鸟飞起来
 *  3.设置小鸟的运动状态 上抛运动  自由落体运动
 *  4.小鸟撞柱子
 * @author mzmy
 *
 */
public class Bird {
	//定义数组
	BufferedImage[] birdimages=new BufferedImage[3];
	//每一次显示的图片
	BufferedImage birdimage=null;
	//获得相关的参数
	int width=0;
	int height=0;
	int x=0;
	int y=0;
	//定义变量记录此时是小鸟那一个状态
	int index=0;
	//定义小鸟运动相关的数据
	final int g=4;//重力加速度
	final double t=0.25;//上抛时间段
	final double v0=20;//上抛初速度
	//定义变量
	double s=0;
	double speed=0;
	//角度
	double angle=0;
	

	//构造方法获得小鸟的图片
	public Bird() throws Exception{
		birdimages[0]=ImageIO.read(this.getClass().getResource("0.png"));
		birdimages[1]=ImageIO.read(this.getClass().getResource("1.png"));
		birdimages[2]=ImageIO.read(this.getClass().getResource("2.png"));
		//设置第一次显示的小鸟状态
		birdimage=birdimages[0];
		//获得小鸟的一些参数
		width=birdimage.getWidth();
		height=birdimage.getHeight();
		x=100;
	    y=210;
		
	}
	//山寨绘制的方法
	public void paint(Graphics g) {
		//获得2D画笔
		Graphics2D g2=(Graphics2D)g;
		g2.rotate(angle,x,y);
		//绘制小鸟
		g.drawImage(birdimage,x-width/2,y-height/2, null);
		g2.rotate(-angle,x,y);
	}
	
	/**
	 * 分析：
	 *   上抛
	 *     初速度
	 *     上抛之后的速度
	 *     上抛的距离
	 *     上抛的时间
	 *   自由落体的运动
	 *     重力加速度
	 *     
	 *   上抛的距离=初速度*上抛的时间-1/2 *加速度*上抛的时间的平方
	 *   上抛后的速度
	 *     v=初速度-重力加速度*上抛时间；
	 *     
	 *  需要的数据
	 *    重力加速度 g=4;
	 *    初始速度 v0=20;
	 *    变化的时间 t=0.25;
	 *    
	 *    求出来的数据
	 *      每一次上抛的距离
	 *      上抛后的速度
	 *      上抛距离内可以变化的角度
	 * 
	 */
	//小鸟运动
	public void step(){
		//第一步获得当前的速度
		double v1=speed;
		//获得变化后速度
		speed=v1-g*t;
		//变化的高度
		s=v1*t-0.5*g*t*t;
		//获得小鸟此时y坐标
		y=y-(int)s;
		//获得角度
		angle=-Math.atan(s/8);
		
		
		index++;
		//变化显示的小鸟
		birdimage=birdimages[index];
		//判断
		if(index==2){
			index=0;
		}
	}
	
	//方法控制小鸟飞
	public void flyFun(){
		AudioPlayWave aud=new AudioPlayWave("src/com/yuechedu/FlyBirdgame/fei.wav");
		aud.start();
		//改变小鸟上抛的初始速度
		speed=v0;
	}

}
