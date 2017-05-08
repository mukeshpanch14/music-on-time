import java.lang.Runtime;
import java.lang.Process;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Music {
 
	int len;
	int p_hour;
	int p_min;
	private ArrayList<String> arr_song;
	Music(ArrayList<String> song_list,int period_hour_int,int period_min_int){
		this.len=song_list.size();
		this.arr_song=song_list;
		this.p_hour=period_hour_int;
		this.p_min=period_min_int;

	}

	

	

	String fr="C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe";

	@SuppressWarnings("unchecked")
	
	public void PlayMusic(int check_flag) throws Exception{

	//		new FrontFrame();
		//		System.out.println(super.period_hour_int);
		long song_time=Math.round((len-1)*4.5*60*1000);
		long period_time=(p_hour+(p_min/60))*3600*1000;
		long check=0;

		/*System.out.println(check_flag);*/
			if(song_time<period_time)
				check=song_time;
			else
				check=period_time;

			arr_song.add(0,fr);
			len++;
			String[] arg=new String[len];
			for(int i=0;i<len;i++){
				arg[i]=(String)arr_song.get(i);
			}


			/*arg[0]="C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe";
			//arg[10]="C:\\Windows\\System32\\shutdown.exe";
			for(int i=1;i<10;i++){
				arg[i]="N:\\Projects\\Personal\\MyMusicPlayer\\Song_Folder\\"+i+".mp3";
			}*/
		
			try{
			
				Process proc=new ProcessBuilder(arg).start();

				try{
					Thread.sleep(check);
				}catch(Exception ex){
					ex.printStackTrace();
				}

				proc.destroy();
				
				/*Runtime rt=Runtime.getRuntime();
				rt.exec("taskkill /F /IM wmplayer.exe");
				*/
				
								
			}

			catch(Exception ex){
				ex.printStackTrace();           
			}
			if(check_flag==1){
					try{
				
				Runtime runtime=Runtime.getRuntime();
				Process p=runtime.exec("shutdown -s -t 0");
				System.exit(0);
			}catch(Exception ex){
				ex.printStackTrace();
			}

			}
			else if(check_flag==2){
				try{
				
				Runtime runtime=Runtime.getRuntime();
				Process p=runtime.exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
				System.exit(0);
			}catch(Exception ex){
				ex.printStackTrace();
			}

			}
		
		
	}

	

}