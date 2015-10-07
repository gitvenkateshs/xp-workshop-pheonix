import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Headend {

	private boolean authStatus = false;
	private int[] unique = {1,2,3};
	private String[] name = {"mtv","natgeo","tv9"};
	private String[] url = {"http://10.142.216.116:8080/Channels/MTV.mp4","http://10.142.216.116:8080/Channels/NATGEO.mp4","http://10.142.216.116:8080/Channels/NEWS.mp4"};
	private String[] desc = {"This is a music channel","This is natgeo","News"};
	private String[] key = {"key1","key2","key3"};
	private FileOutputStream fos = null; 
	private BufferedWriter bw;
	private JSONArray channels;

	public void getChannelData() {
		channels = new JSONArray();
		
		for (int i = 0; i < unique.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("CHANNELNO", unique[i]);
			obj.put("CHANNELNAME", name[i]);
			obj.put("TIME", "9:15AM");
			obj.put("PROGRAMNAME", name[i]);
			obj.put("PROGDESCRIPTION",desc[i]);
			obj.put("URL", url[i]);
			obj.put("SUBSCRIPTIONKEY", key[i]);
			channels.add(obj);
		}



		StringWriter out = new StringWriter();
		try {
			channels.writeJSONString(out);
			File file = new File("C:\\FTPUrl\\tv_info.json");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			String jsonText = out.toString();
			bw.write(jsonText);
			bw.close();
			System.out.println(jsonText);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}

		
		//return jsonText;

	}

	public boolean authenticate(String key) {

		return authStatus;
	}
}
