package com.psm.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChangeDirectory {


	private ChangeDirectory(){

	}

	public static void copy(File src, File dest) throws IOException {


    	InputStream is = null;
    	OutputStream os = null;

    	try {

    		is = new FileInputStream(src);
    		os = new FileOutputStream(dest);
    		// buffer size 1K
    		byte[] buf = new byte[1024];
    		int bytesRead;

    		while ((bytesRead = is.read(buf)) > 0)

    		{
    			os.write(buf, 0, bytesRead);
    			System.out.println("RUN");

    		}// end of while

    		} finally {

    			if(is!=null && os != null)
    			{
    				is.close();
	    			os.close();
    			}


    			}



    }// end of file compy method


}
