package com.yuechedu.FlyBirdgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 需求:绘制动态的地面
 *    1. 绘制地面---获得地面的图片资源
 *    2.地面要想动起来--改变x坐标 不断改变
 *      定义一个方法来控制地面的横坐标
 * 
 * @author mzmy
 *
 */
public class Ground {
	//定义变量
	BufferedImage groundimage=null;
	//定义参数
	int width=0;//图片宽度
	int height=0;//图片的高度
	int x=0;//横坐标
	int y=0;//纵坐标
	
	//构造方法获得地面的图片资源
	public Ground(int y) throws Exception{
		groundimage=ImageIO.read(this.getClass().getResource("ground.png"));
	  //获得参数
		width=groundimage.getWidth();
		height=groundimage.getHeight();
		x=0;
		this.y=y;
	}
	
	//绘制图片
	public void paint(Graphics g) {
		//绘制背景
		g.drawImage(groundimage, x, y, null);
		//绘制地面
		
	}
	//定义一个方法控制地面的很坐标
	public  void step(){
		//坐标改变
		x--;
		//满足条件重新开始
		if(x<-(width-330)){
			x=0;
		}
	}
	
	

}
