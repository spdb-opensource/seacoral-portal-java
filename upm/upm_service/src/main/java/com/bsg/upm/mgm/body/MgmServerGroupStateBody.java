package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年7月11日
 */
public class MgmServerGroupStateBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "state")
	private String state;
	
	@JSONField(name = "spec")
	private Spec spec;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Spec getSpec() {
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "MgmServerGroupStateBody [state=" + state + ", spec=" + spec + "]";
	}
	
	public static class Spec{
		
		@JSONField(name = "database")
		private Statebase database;
		
		@JSONField(name = "cmha")
		private Statebase cmha;
		
		@JSONField(name = "proxy")
		private Statebase proxy;

		public Statebase getDatabase() {
			return database;
		}

		public void setDatabase(Statebase database) {
			this.database = database;
		}

		public Statebase getCmha() {
			return cmha;
		}

		public void setCmha(Statebase cmha) {
			this.cmha = cmha;
		}

		public Statebase getProxy() {
			return proxy;
		}

		public void setProxy(Statebase proxy) {
			this.proxy = proxy;
		}

		@Override
		public String toString() {
			return "Spec [database=" + database + ", cmha=" + cmha + ", proxy=" + proxy + "]";
		}
		
	}
	
	public static class Statebase{
		@JSONField(name = "services")
		private Services services;

		public Services getServices() {
			return services;
		}

		public void setServices(Services services) {
			this.services = services;
		}

		@Override
		public String toString() {
			return "Statebase [services=" + services + "]";
		}
		
	}
	public static class Services{
		@JSONField(name = "state")
		private String state;

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "Services [state=" + state + "]";
		}
		
	}

}
