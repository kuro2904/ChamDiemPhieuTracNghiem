package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import custom.CustomTableModel;
import nu.pattern.OpenCV;

public class ImageAnalics {
	
	public static void initOpenCV() {
		OpenCV.loadLocally();
	}
	
	public List<Map<Integer,String>> answersList(CustomTableModel tableModel){
		List<Map<Integer,String>> aswers = new ArrayList<Map<Integer,String>>();
		for(int i = 0; i< tableModel.getRowCount(); i++) {
			for(int j = 0; j < tableModel.getColumnCount(); j++) {
				
			}
		}
		
		return aswers;
	}
	
}
