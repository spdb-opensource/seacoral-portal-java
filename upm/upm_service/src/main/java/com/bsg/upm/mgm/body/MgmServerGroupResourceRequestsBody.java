package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年7月11日
 */
public class MgmServerGroupResourceRequestsBody implements Serializable {

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
		return "MgmServerGroupResourceRequestsBody [spec=" + spec + "]";
	}

	public static class SpecBody {
		@JSONField(name = "database")
		private DatabaseBody database;
		
		@JSONField(name = "proxy")
		private DatabaseBody proxy;
		
		@JSONField(name = "cmha")
		private DatabaseBody cmha;

		public DatabaseBody getDatabase() {
			return database;
		}

		public void setDatabase(DatabaseBody database) {
			this.database = database;
		}

		public DatabaseBody getProxy() {
			return proxy;
		}

		public void setProxy(DatabaseBody proxy) {
			this.proxy = proxy;
		}

		public DatabaseBody getCmha() {
			return cmha;
		}

		public void setCmha(DatabaseBody cmha) {
			this.cmha = cmha;
		}

		@Override
		public String toString() {
			return "SpecBody [database=" + database + ", proxy=" + proxy + ", cmha=" + cmha + "]";
		}

	}

	public static class DatabaseBody {

		@JSONField(name = "services")
		private ServicesBody services;

		public ServicesBody getServices() {
			return services;
		}

		public void setServices(ServicesBody services) {
			this.services = services;
		}

		@Override
		public String toString() {
			return "DatabaseBody [services=" + services + "]";
		}

	}

	public static class ServicesBody {
		@JSONField(name = "units")
		private UnitsBody units;

		public UnitsBody getUnits() {
			return units;
		}

		public void setUnits(UnitsBody units) {
			this.units = units;
		}

		@Override
		public String toString() {
			return "ServicesBody [units=" + units + "]";
		}

	}

	public static class UnitsBody {
		@JSONField(name = "resources")
		private ResourcesBody resources;

		public ResourcesBody getResources() {
			return resources;
		}

		public void setResources(ResourcesBody resources) {
			this.resources = resources;
		}

		@Override
		public String toString() {
			return "UnitsBody [resources=" + resources + "]";
		}

	}

	public static class ResourcesBody {
		@JSONField(name = "requests")
		private RequestsBody requests;

		public RequestsBody getRequests() {
			return requests;
		}

		public void setRequests(RequestsBody requests) {
			this.requests = requests;
		}

		@Override
		public String toString() {
			return "ResourcesBody [requests=" + requests + "]";
		}

	}

	public static class RequestsBody {
		@JSONField(name = "cpu")
		private Integer cpu;

		@JSONField(name = "memory")
		private Long memory;

		@JSONField(name = "net_bandwidth")
		private Integer netBandwidth;

		@JSONField(name = "storage")
		private StorageBody storage;

		public Integer getCpu() {
			return cpu;
		}

		public void setCpu(Integer cpu) {
			this.cpu = cpu;
		}

		public Long getMemory() {
			return memory;
		}

		public void setMemory(Long memory) {
			this.memory = memory;
		}

		public Integer getNetBandwidth() {
			return netBandwidth;
		}

		public void setNetBandwidth(Integer netBandwidth) {
			this.netBandwidth = netBandwidth;
		}

		public StorageBody getStorage() {
			return storage;
		}
		

		public void setStorage(StorageBody storage) {
			this.storage = storage;
		}

		@Override
		public String toString() {
			return "RequestsBody [cpu=" + cpu + ", memory=" + memory + ", netBandwidth=" + netBandwidth + ", storage="
					+ storage + "]";
		}
		

	}

	public static class StorageBody {
		@JSONField(name = "volumes")
		private VolumesBody[] volumes;

		public VolumesBody[] getVolumes() {
			return volumes;
		}

		public void setVolumes(VolumesBody[] volumes) {
			this.volumes = volumes;
		}

		@Override
		public String toString() {
			return "StorageBody [volumes=" + Arrays.toString(volumes) + "]";
		}

	}

	public static class VolumesBody {
		@JSONField(name = "type")
		private String type;

		@JSONField(name = "capacity")
		private Long capacity;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Long getCapacity() {
			return capacity;
		}

		public void setCapacity(Long capacity) {
			this.capacity = capacity;
		}

		@Override
		public String toString() {
			return "VolumesBody [type=" + type + ", capacity=" + capacity + "]";
		}

	}
}
