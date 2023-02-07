package com.sh.obtg.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class OotdFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
	File newFile = null;
		
		do {
			//20230103_124533123_12345.txt 이렇게 만들거야 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("00000"); //0은 해당 자리수가 없어도 0으로 채워주는 format문자 
			
			String filename = oldFile.getName(); //파일명가져오기 
			
			String ext = ""; // 파일명에서 확장자  필요해서 가져옴
			int dot = filename.lastIndexOf(".");
			
			if( dot>-1 ) {
				ext = filename.substring(dot);
			}
			String newFilename = sdf.format(new Date()) + df.format( Math.random()*100000 ) + ext;
			newFile = new File( oldFile.getParent(), newFilename);
			
		}while(!createNewFile(newFile));

		System.out.println( newFile);
		return newFile;
	}
	
	
	//DefaultRe~ copy해온거
	/* f가 존지해지 않으면 파일 생성 후 true 반환 
	 * f가 존재하면 파일 생성하지 않고 false반환 
	 * 
	 */
	 private boolean createNewFile(File f) {
		    try {
		      return f.createNewFile();
		    }
		    catch (IOException ignored) {
		      return false;
		    }
	 }

}
