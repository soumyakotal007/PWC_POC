package com.pwc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.pwc.constants.PWCViewerConstant;

public class PWCViewerUtil {  
  public static Properties getPrintProperties(String propFileName) throws IOException
  {
	  Properties properties = new Properties();
	  InputStreamReader in = null;
	  try {
	       in = new InputStreamReader(new FileInputStream(propFileName), "UTF-8");
	       properties.load(in);
	  } finally {
	       if (null != in) {
	           try {
	               in.close();
	           } catch (IOException ex) {}
	       }
	  }
	  return properties;
  }
  
  public static String getProperty(String propKey) throws Exception
  {
	 return getPrintProperties(PWCViewerConstant.PWCVIEWER_PROP_FILE_LOCATION).getProperty(propKey);
  }
	 
}
