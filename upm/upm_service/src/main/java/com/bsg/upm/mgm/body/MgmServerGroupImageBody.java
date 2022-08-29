package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年7月11日
 */
public class MgmServerGroupImageBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "spec")
	private SpecBody spec;

	public SpecBody getSpec() {
		return spec;
	}

	public void setSpec(SpecBody spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "MgmServerGroupImageBody [spec=" + spec + "]";
	}

	public static class SpecBody {
		@JSONField(name = "database")
		private DatabaseBody database;
		
		@JSONField(name = "cmha")
		private DatabaseBody cmha;
		
		@JSONField(name = "proxy")
		private DatabaseBody proxy;

		public DatabaseBody getCmha() {
			return cmha;
		}

		public void setCmha(DatabaseBody cmha) {
			this.cmha = cmha;
		}

		public DatabaseBody getProxy() {
			return proxy;
		}

		public void setProxy(DatabaseBody proxy) {
			this.proxy = proxy;
		}

		public DatabaseBody getDatabase() {
			return database;
		}

		public void setDatabase(DatabaseBody database) {
			this.database = database;
		}

		@Override
		public String toString() {
			return "SpecBody [database=" + database + ", cmha=" + cmha + ", proxy=" + proxy + "]";
		}

	}

	public static class DatabaseBody {
		@JSONField(name = "image")
		private ImageBody image;

		public ImageBody getImage() {
			return image;
		}

		public void setImage(ImageBody image) {
			this.image = image;
		}

		@Override
		public String toString() {
			return "DatabaseBody [image=" + image + "]";
		}

	}

	public static class ImageBody {
		@JSONField(name = "id")
		private String id;
		
		@JSONField(name = "type")
		private String type;

		@JSONField(name = "major")
		private Integer major;

		@JSONField(name = "minor")
		private Integer minor;

		@JSONField(name = "patch")
		private Integer patch;

		@JSONField(name = "build")
		private Integer build;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getMajor() {
			return major;
		}

		public void setMajor(Integer major) {
			this.major = major;
		}

		public Integer getMinor() {
			return minor;
		}

		public String getId() {
			return id;
		}
		

		public void setId(String id) {
			this.id = id;
		}
		

		public void setMinor(Integer minor) {
			this.minor = minor;
		}

		public Integer getPatch() {
			return patch;
		}

		public void setPatch(Integer patch) {
			this.patch = patch;
		}

		public Integer getBuild() {
			return build;
		}

		public void setBuild(Integer build) {
			this.build = build;
		}

		@Override
		public String toString() {
			return "ImageBody [id=" + id + ", type=" + type + ", major=" + major + ", minor=" + minor + ", patch="
					+ patch + ", build=" + build + "]";
		}

	}

}
