package com.yuechedu.FlyBirdgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 需求:编写滚动的柱子
 *    1.获得柱子的图片资源
 *    2.绘制图片
 *    3，让图片动起来
 *  
 * 
 * @author mzmy
 *
 */
public class Column {
	//定义变量
	BufferedImage  colimage=null;
	
	//定义参数
	int width=0;//宽度
    int height=0;//高度
    int x=0;//横坐标
    int y=0;//纵坐标
	//构造方法获得柱子的图片资源
	public Column(int x) throws Exception{
		//加载
		colimage=ImageIO.read(this.getClass().getResource("column.png"));
		//获得宽度和高度
		width=colimage.getWidth();
		height=colimage.getHeight();
		this.x=x;
//		y=210;
		//随机获得柱子的纵坐标
		y=140+new Random().nextInt(140);
	}
    
	//绘制图片
	public void paint(Graphics g) {
		//绘制背景
		g.drawImage(colimage, x, y-height/2, null);
	}
	//让柱子动起来的方法
	public void step(){
		//坐标变化
		x--;
		if(x<-width){
			x=325;
			y=140+new Random().nextInt(140);
		}
	}
	
	
}
