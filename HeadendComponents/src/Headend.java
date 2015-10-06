import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;

import org.json.simple.JSONObject;

public class Headend {

	private boolean authStatus = false;
	private int[] unique = {1,2,3};
	private String[] name = {"mtv","natgeo","tv9"};
	private String[] url = {"","",""};
	private String[] desc = {"This is a music channel","This is natgeo","News"};
	private FileOutputStream fos = null; 
	private BufferedWriter bw;

	Headend() {
		

	}

	public void getChannelData() {
		

		JSONObject objOuter = new JSONObject();
		for (int i = 0; i < unique.length; i++) {
			JSONObject objInner = new JSONObject();
			objInner.put("unique", unique[i]);
			objInner.put("name", name[i]);
			objInner.put("url", url[i]);
			objInner.put("desc", desc[i]);
			objOuter.put(""+i, objInner);
		}



		StringWriter out = new StringWriter();
		try {
			objOuter.writeJSONString(out);
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
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//return jsonText;

	}

	public boolean authenticate(String key) {

		return authStatus;
	}
}
