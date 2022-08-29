package com.bsg.upm.query;

import java.util.List;

public class ImageTemplate {

	
    private static final long serialVersionUID = 1L;
    
    
    private String configFile;

    
    private List<ImageConf> keysets;


	public String getConfigFile() {
		return configFile;
	}
	

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}


	public List<ImageConf> getKeysets() {
		return keysets;
	}
	


	public void setKeysets(List<ImageConf> keysets) {
		this.keysets = keysets;
	}


	@Override
	public String toString() {
		return "ImageTemplate [configFile=" + configFile + ", keysets=" + keysets + "]";
	}
	

	
}
