import java.lang.Runtime;
import java.lang.Process;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Music extends Timer{

	int len;
	private ArrayList<String> arr_song;
	Music(ArrayList<String> song_list){
		this.len=song_list.size();
		this.arr_song=song_list;
	}


	String fr="C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe";

	@SuppressWarnings("unchecked")
	
	public void PlayMusic() throws Exception{

	//		new FrontFrame();
	//		System.out.println(super.period_hour_int);


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
					Thread.sleep(len*4*60*1000);
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

			/*try{
				System.out.println("this method");
				Runtime runtime=Runtime.getRuntime();
				Process p=runtime.exec("shutdown -s -t 0");
				System.exit(0);
			}catch(Exception ex){
				ex.printStackTrace();
			}*/
		
	}

	/*public static void main(String[] args){
		Music m=new Music();
	}*/


}