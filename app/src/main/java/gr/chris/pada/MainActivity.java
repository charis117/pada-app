package gr.chris.pada;

import android.app.*;
import android.os.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import java.io.IOException;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Thread th=new Thread(new Runnable(){
			
			public void run(){
				try{
					Document wb=Jsoup.connect("https://eee.uniwa.gr/el/anakinoseis/anakoinoseis-grammateias").get();
					System.out.println(wb.title());
					Elements els=wb.getElementsByTag("article");

					final String timeString=els.get(0).getElementsByTag("time").html();
					final String timeSpampString=els.get(0).getElementsByTag("time").attr("datetime");
					System.out.println(timeSpampString);
					runOnUiThread(new Runnable(){
						public void run(){
							TextView tv=(TextView)findViewById(R.id.mainTextView);
							tv.setText(timeString);
							Toast.makeText(getApplicationContext(),timeSpampString,Toast.LENGTH_SHORT).show();
						}
					});
				}catch(IOException e){}

				
		
		}
		
		});
    }
}
