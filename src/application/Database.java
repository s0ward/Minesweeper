package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class Database {

	private static final String FILENAME = "src\\application\\Scores.txt";
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static void write(String s, boolean overWrite,boolean formatDate) {
		
		Date date = new Date();
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(FILENAME, overWrite);
			bw = new BufferedWriter(fw);
			if(formatDate)
			bw.write(s+" sec on "+sdf.format(date)+"\n");
			else
				bw.write(s);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	public static Vector<String> read(){
		Vector<String> lines = new Vector<String>(50);
		
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
				//System.out.println();
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return lines;
	}
	public static String ranking(Vector<String> all){
		double min=Double.MAX_VALUE; 
		String s = "";
		
		for(String curr: all){
			if(Double.parseDouble(curr.split(" ")[0]) < min){
				min = Double.parseDouble(curr.split(" ")[0]);
				s = curr;
			}
		}
		return s;
		
	}
}

