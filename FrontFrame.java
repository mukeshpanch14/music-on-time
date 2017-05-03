import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;


class FrontFrame extends JFrame {
	JTextField tt;
	
	JLabel sel_Time;
	JTextField hour_field;
	JTextField min_field;
	JComboBox<String>time_cycle_dropdown;
	JLabel colon_Time;
	JLabel period_Time;
	JTextField period_hour;
	JTextField period_min;
	JLabel st_hour;
	JLabel st_min;
	JFileChooser fc;
	private Button button_browse;
	private Button start_sleep;
	private Button start_shut;

	int period_min_int=0;
	int period_hour_int=0;

	int hour_time=0;
	int min_time=0;

	String str_am_pm="AM";

	ArrayList<String> Song_list=new ArrayList<String>();

	FrontFrame(){
		
		Font font=new Font(getTitle(), Font.BOLD,16);
		
		tt = new JTextField();		
		tt.setBounds(20,30,280,20);
		add(tt);
		
		//tt.setFont(font2);
		@SuppressWarnings("unsafe")

		
		
		Button button_browse=new Button("Browse");
		button_browse.setBounds(310, 30, 50, 20);		
			
		add(button_browse);

		sel_Time=new JLabel("Select Time:");
		sel_Time.setSize(sel_Time.getPreferredSize());
		sel_Time.setLocation(20,60);
		add(sel_Time);
		//repaint();

		hour_field=new JTextField();
		hour_field.setBounds(100,60,25,20);
		add(hour_field);

		colon_Time=new JLabel(":");
		colon_Time.setSize(colon_Time.getPreferredSize());
		colon_Time.setLocation(126,60);
		add(colon_Time);

		min_field=new JTextField();
		min_field.setBounds(131,60,25,20);
		add(min_field);

		String time_cycle[]={"AM","PM"};
		JComboBox<String> time_cycle_dropdown=new JComboBox<String>(time_cycle);
		time_cycle_dropdown.setBounds(160,60,45,20);
		add(time_cycle_dropdown);


		period_Time=new JLabel("Time Period to Play:");
		period_Time.setSize(period_Time.getPreferredSize());
		period_Time.setLocation(20,85);
		add(period_Time);

		period_hour=new JTextField();
		period_hour.setBounds(135,85,25,20);
		add(period_hour);

		st_hour=new JLabel("Hour");
		st_hour.setSize(st_hour.getPreferredSize());
		st_hour.setLocation(163,85);
		add(st_hour);

		period_min=new JTextField();
		period_min.setBounds(193,85,25,20);
		add(period_min);

		st_min=new JLabel("Min");
		st_min.setSize(st_hour.getPreferredSize());
		st_min.setLocation(220,85);
		add(st_min);

		Button start_sleep=new Button("Start & Sleep");
		start_sleep.setBounds(20, 120, 80, 20);		
		add(start_sleep);
		
		
		Button start_shut=new Button("Start & Shut Down");
		start_shut.setBounds(260, 120, 110, 20);		
				
		add(start_shut);

		HandlerClass handler=new HandlerClass();

		button_browse.addActionListener(handler);	
		start_sleep.addActionListener(handler);
		start_shut.addActionListener(handler);
		
		time_cycle_dropdown.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent event){
					if(event.getStateChange()==ItemEvent.SELECTED){						
						str_am_pm=(String)time_cycle_dropdown.getSelectedItem();						
					}

				}
			}
		);

		setTitle("LazyPlayer");
		setSize(400,300);
		setLocation(500,200);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
	}

	private class HandlerClass implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
		//tt.setText("Test");

		System.out.println(e.getActionCommand());


		if(e.getActionCommand()=="Browse"){
			class FileChooserDemo extends JFrame implements ActionListener{
				FileChooserDemo(){
					Container c=getContentPane();
					this.setVisible(true);
					this.setSize(500,400);

					fc=new JFileChooser();
					fc.setMultiSelectionEnabled(true);
					fc.addActionListener(this);
					fc.setLayout(new FlowLayout());
					c.add(fc);
				}

				public void actionPerformed(ActionEvent ae){
					File[] f=fc.getSelectedFiles();
					
					int leng=f.length;
					String path[]=new String[leng];

					String allfile="";
					for(int i=0;i<leng;i++){
						path[i]=f[i].getAbsolutePath();
						path[i]=path[i].replace("\\","\\\\");
						allfile=allfile+"-"+path[i];
						Song_list.add(path[i]);

					}

					tt.setText(allfile);
					//new Timer(path);

					System.out.println(allfile);

					this.setVisible(false);
				}
			}
			new FileChooserDemo();	
		}
		
			String str_period_hour=period_hour.getText();

			String str_period_min=period_min.getText();
			String str_hour_time=hour_field.getText();
			String str_min_time=min_field.getText();

			
			



		if(e.getActionCommand()=="Start & Shut Down"){
			
			try{
				/*period_hour_int=(int)Double.parseDouble(str_period_hour);
				period_min_int=(int)Double.parseDouble(str_period_min);
				hour_time=(int)Double.parseDouble(str_hour_time);
				min_time=(int)Double.parseDouble(str_min_time);*/

				period_hour_int=changeInt(str_period_hour.trim());				
				period_min_int=changeInt(str_period_min.trim());
				hour_time=changeInt(str_hour_time.trim());
				min_time=changeInt(str_min_time.trim());

				/*period_hour_int=NumberUtils.toInt(str_period_hour,0);
				period_min_int=NumberUtils.toInt(str_period_min,0);
				hour_time=NumberUtils.toInt(str_hour_time,0);
				min_time=NumberUtils.toInt(str_min_time,0);*/



				time_check();

			}catch(NumberFormatException ne){
				System.out.println("not a number");
			}




		}

		if(e.getActionCommand()=="Start & Sleep"){

		}

		
	}
}


	public void time_check(){
		while(true){
			

			Calendar now=Calendar.getInstance();
			int hour=now.get(Calendar.HOUR_OF_DAY);
			int minute=now.get(Calendar.MINUTE);

			if(hour==hour_time){
				if(minute==min_time){
					try{
					new Music(Song_list).PlayMusic();
					break;
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}

			
			try{
				Thread.sleep(10000);
			}catch(Exception e){
				e.printStackTrace();
			}


		}
	}

	public int changeInt(String str){
		int i;
		int n=0;
		for(i=0;i<str.length();i++){
			n*=10;
			n+=str.charAt(i)-48;
		}
		return n;
	}
		
	
	
	public static void main(String[] args){
		FrontFrame fc=new FrontFrame();
		//new Timer().disp();
		

	}
}