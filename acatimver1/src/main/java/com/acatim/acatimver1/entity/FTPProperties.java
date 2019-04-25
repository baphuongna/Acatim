package com.acatim.acatimver1.entity;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class FTPProperties {
	 private String server = "156.67.222.210";
	    private String username = "u179631086";
	    private String password = "lala123";
	    @Min(0)
	    @Max(65535)
	    private int port = 21;
	    private int keepAliveTimeout = 15;
	    private boolean autoStart;

	    @PostConstruct
	    public void init() {
	        if (port == 0) {
	            port = 21;
	        }
	    }

	    public String getServer() {
	        return server;
	    }

	    public void setServer(String server) {
	        this.server = server;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public int getPort() {
	        return port;
	    }

	    public void setPort(int port) {
	        this.port = port;
	    }

	    public int getKeepAliveTimeout() {
	        return keepAliveTimeout;
	    }

	    public void setKeepAliveTimeout(int keepAliveTimeout) {
	        this.keepAliveTimeout = keepAliveTimeout;
	    }

	    public boolean isAutoStart() {
	        return autoStart;
	    }

	    public void setAutoStart(boolean autoStart) {
	        this.autoStart = autoStart;
	    }
}
