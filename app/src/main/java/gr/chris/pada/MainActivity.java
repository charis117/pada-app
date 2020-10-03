package gr.chris.pada;

import android.app.*;
import android.os.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
					final StringBuilder fTex=new StringBuilder();
					for(Element ei:els) {
						final String timeString=ei.getElementsByTag("time").html();
						final String timeSpampString=ei.getElementsByTag("time").attr("datetime");
				        String title=ei.getElementsByTag("itemprop").get(0).html();
				        fTex.append(timeString);
				        fTex.append(":");
				        fTex.append(title);
				        fTex.append("\n");
				        int b=5;

					}

					runOnUiThread(new Runnable(){
						public void run(){
							TextView tv=(TextView)findViewById(R.id.mainTextView);
							tv.setText(fTex.toString());
						}
					});
				}catch(IOException e){}



			}

		});
	}
}
