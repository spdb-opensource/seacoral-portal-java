package com.bsg.upm.mgm.model;

import java.awt.List;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.dto.ServerGroupDTO.AreaDTO;
import com.bsg.upm.dto.ServerGroupDTO.OwnerDTO;
import com.bsg.upm.dto.ServerGroupDTO.SiteDTO;

public class MgmServerGroup extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private String id; // 服务应用唯一识别码

	@JSONField(name = "name")
	private String name; // 服务应用名称

	@JSONField(name = "desc")
	private String desc; // 服务应用描述

	@JSONField(name = "task")
	private Task task; // 最近任务

	@JSONField(name = "created")
	private Info created; // 创建信息

	@JSONField(name = "modified")
	private Info modified;

	@JSONField(name = "state")
	private String state; // 服务应用状态

	@JSONField(name = "status")
	private Status status; // 服务应用详细

	@JSONField(name = "spec")
	private Spec spec; // 服务应用需求描述
	
	@JSONField(name = "arch")
	private String arch; // 硬件架构
	

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Info getCreated() {
		return created;
	}

	public void setCreated(Info created) {
		this.created = created;
	}

	public Info getModified() {
		return modified;
	}

	public void setModified(Info modified) {
		this.modified = modified;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Spec getSpec() {
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "MgmServerGroup [id=" + id + ", name=" + name + ", desc=" + desc + ", task=" + task + ", created="
				+ created + ", modified=" + modified + ", state=" + state + ", status=" + status + ", spec=" + spec
				+ ", arch=" + arch + "]";
	}

	public static class Status {

		@JSONField(name = "database")
		private StatusDatabase database;
		
		//add by yeht
		@JSONField(name = "proxy")
		private StatusDatabase proxy;
		
		@JSONField(name = "cmha")
		private StatusDatabase cmha;
		
		public StatusDatabase getProxy() {
			return proxy;
		}

		public void setProxy(StatusDatabase proxy) {
			this.proxy = proxy;
		}

		public StatusDatabase getCmha() {
			return cmha;
		}

		public void setCmha(StatusDatabase cmha) {
			this.cmha = cmha;
		}


		public StatusDatabase getDatabase() {
			return database;
		}

		public void setDatabase(StatusDatabase database) {
			this.database = database;
		}

		@Override
		public String toString() {
			return "Status [database=" + database + ", proxy=" + proxy + ", cmha=" + cmha + "]";
		}

	}

	public static class Spec {
		@JSONField(name = "database")
		private SpecDatabase database;
		
		//add by yeht
		@JSONField(name = "proxy")
		private SpecDatabase proxy;
		
		@JSONField(name = "cmha")
		private SpecDatabase cmha;

		public SpecDatabase getDatabase() {
			return database;
		}

		public void setDatabase(SpecDatabase database) {
			this.database = database;
		}
		
		public SpecDatabase getProxy() {
			return proxy;
		}

		public void setProxy(SpecDatabase proxy) {
			this.proxy = proxy;
		}

		public SpecDatabase getCmha() {
			return cmha;
		}

		public void setCmha(SpecDatabase cmha) {
			this.cmha = cmha;
		}

		@Override
		public String toString() {
			return "Spec [database=" + database + ", proxy=" + proxy + ", cmha=" + cmha + "]";
		}

	}

	public static class StatusDatabase {
		@JSONField(name = "services")
		private StatusService[] services; // 服务应用数据库类服务

		public StatusService[] getServices() {
			return services;
		}

		public void setServices(StatusService[] services) {
			this.services = services;
		}

		@Override
		public String toString() {
			return "StatusDatabase [services=" + Arrays.toString(services) + "]";
		}

	}

	public static class SpecDatabase {
		@JSONField(name = "image")
		private Image image; // 数据库类服务资源

		@JSONField(name = "services")
		private SpecService services;

		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}

		public SpecService getServices() {
			return services;
		}

		public void setServices(SpecService services) {
			this.services = services;
		}

		@Override
		public String toString() {
			return "SpecDatabase [image=" + image + ", services=" + services + "]";
		}

	}

	public static class SpecService {
		@JSONField(name = "num")
		private String num;
		
		@JSONField(name = "state")
		private String state;

		@JSONField(name = "arch")
		private Arch arch;

		@JSONField(name = "ports")
		private Port[] ports;

		@JSONField(name = "conditions")
		private Conditions conditions;

		@JSONField(name = "units")
		private SpecUnit units;

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

		public Arch getArch() {
			return arch;
		}

		public void setArch(Arch arch) {
			this.arch = arch;
		}

		public Port[] getPorts() {
			return ports;
		}

		public void setPorts(Port[] ports) {
			this.ports = ports;
		}

		public Conditions getConditions() {
			return conditions;
		}

		public void setConditions(Conditions conditions) {
			this.conditions = conditions;
		}

		public SpecUnit getUnits() {
			return units;
		}

		public void setUnits(SpecUnit units) {
			this.units = units;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "SpecService [num=" + num + ", state=" + state + ", arch=" + arch + ", ports="
					+ Arrays.toString(ports) + ", conditions=" + conditions + ", units=" + units + "]";
		}

	}

	public static class SpecUnit {

		@JSONField(name = "resources")
		private SpecResources resources;
		
		//add by yeht
		@JSONField(name = "ip")
		private String ip;
		//容器漂移方式
		@JSONField(name = "ha")
		private Boolean ha;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public SpecResources getResources() {
			return resources;
		}

		public void setResources(SpecResources resources) {
			this.resources = resources;
		}

		public Boolean getHa() {
			return ha;
		}

		public void setHa(Boolean ha) {
			this.ha = ha;
		}

		@Override
		public String toString() {
			return "SpecUnit [resources=" + resources + ", ip=" + ip + ", ha=" + ha + "]";
		}

	}

	public static class Port {
		@JSONField(name = "name")
		private String name;

		@JSONField(name = "port")
		private String port;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		@Override
		public String toString() {
			return "Ports [name=" + name + ", port=" + port + "]";
		}

	}

	public static class Arch {

		@JSONField(name = "mode")
		private String mode;
		
		@JSONField(name = "name")
		private String name;
		
		@JSONField(name = "cntName")
		private String cntName;

		@JSONField(name = "replicas")
		private Integer replicas;

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public Integer getReplicas() {
			return replicas;
		}

		public void setReplicas(Integer replicas) {
			this.replicas = replicas;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCntName() {
			return cntName;
		}

		public void setCntName(String cntName) {
			this.cntName = cntName;
		}

		@Override
		public String toString() {
			return "Arch [mode=" + mode + ", name=" + name + ", cntName=" + cntName + ", replicas=" + replicas + "]";
		}

	}

	public static class StatusResources {

		@JSONField(name = "cpu")
		private Integer cpu;

		@JSONField(name = "memory")
		private BigInteger memory;

		@JSONField(name = "net_bandwidth")
		private Integer netBandwidth;

		@JSONField(name = "storage")
		private Storage storage;

		public Integer getCpu() {
			return cpu;
		}

		public void setCpu(Integer cpu) {
			this.cpu = cpu;
		}

		public BigInteger getMemory() {
			return memory;
		}

		public void setMemory(BigInteger memory) {
			this.memory = memory;
		}

		public Integer getNetBandwidth() {
			return netBandwidth;
		}

		public void setNetBandwidth(Integer netBandwidth) {
			this.netBandwidth = netBandwidth;
		}

		public Storage getStorage() {
			return storage;
		}

		public void setStorage(Storage storage) {
			this.storage = storage;
		}

		@Override
		public String toString() {
			return "StatusResources [cpu=" + cpu + ", memory=" + memory + ", netBandwidth=" + netBandwidth
					+ ", storage=" + storage + "]";
		}

	}

	public static class SpecResources {
		@JSONField(name = "requests")
		private Requests requests;

		public Requests getRequests() {
			return requests;
		}

		public void setRequests(Requests requests) {
			this.requests = requests;
		}

		@Override
		public String toString() {
			return "SpecResources [requests=" + requests + "]";
		}

	}

	public static class Conditions {
		@JSONField(name = "cluster")
		private ConditionsCandidatesIdFlag cluster;

		@JSONField(name = "host")
		private ConditionsCandidatesIdFlag host;

		@JSONField(name = "storage_remote")
		private ConditionsCandidatesIdFlag storageRemote;

		@JSONField(name = "network")
		private ConditionsCandidatesIdFlag network;

		public ConditionsCandidatesIdFlag getCluster() {
			return cluster;
		}

		public void setCluster(ConditionsCandidatesIdFlag cluster) {
			this.cluster = cluster;
		}

		public ConditionsCandidatesIdFlag getHost() {
			return host;
		}

		public void setHost(ConditionsCandidatesIdFlag host) {
			this.host = host;
		}

		public ConditionsCandidatesIdFlag getStorageRemote() {
			return storageRemote;
		}

		public void setStorageRemote(ConditionsCandidatesIdFlag storageRemote) {
			this.storageRemote = storageRemote;
		}

		public ConditionsCandidatesIdFlag getNetwork() {
			return network;
		}

		public void setNetwork(ConditionsCandidatesIdFlag network) {
			this.network = network;
		}

		@Override
		public String toString() {
			return "Conditions [cluster=" + cluster + ", host=" + host + ", storageRemote=" + storageRemote
					+ ", network=" + network + "]";
		}

	}

	public static class ConditionsCandidatesIdFlag {

		@JSONField(name = "candidates_id")
		private String[] candidatesId;

		@JSONField(name = "high_availability")
		private Boolean highAvailability;

		public String[] getCandidatesId() {
			return candidatesId;
		}

		public void setCandidatesId(String[] candidatesId) {
			this.candidatesId = candidatesId;
		}

		public Boolean getHighAvailability() {
			return highAvailability;
		}

		public void setHighAvailability(Boolean highAvailability) {
			this.highAvailability = highAvailability;
		}

		@Override
		public String toString() {
			return "ConditionsCandidatesIdFlag [candidatesId=" + Arrays.toString(candidatesId) + ", highAvailability="
					+ highAvailability + "]";
		}

	}

	public static class Requests {

		@JSONField(name = "cpu")
		private Integer cpu;

		@JSONField(name = "memory")
		private BigInteger memory;

		@JSONField(name = "net_bandwidth")
		private Integer netBandwidth;

		@JSONField(name = "storage")
		private Storage storage;

		public Integer getCpu() {
			return cpu;
		}

		public void setCpu(Integer cpu) {
			this.cpu = cpu;
		}

		public BigInteger getMemory() {
			return memory;
		}

		public void setMemory(BigInteger memory) {
			this.memory = memory;
		}

		public Integer getNetBandwidth() {
			return netBandwidth;
		}

		public void setNetBandwidth(Integer netBandwidth) {
			this.netBandwidth = netBandwidth;
		}

		public Storage getStorage() {
			return storage;
		}

		public void setStorage(Storage storage) {
			this.storage = storage;
		}

		@Override
		public String toString() {
			return "Requests [cpu=" + cpu + ", memory=" + memory + ", netBandwidth=" + netBandwidth + ", storage="
					+ storage + "]";
		}

	}

	public static class Storage {
		@JSONField(name = "type")
		private String type;
		
		@JSONField(name = "typeDisplay")
		private String typeDisplay;
		
		@JSONField(name = "code")
		private String code;
		
		@JSONField(name = "display")
		private String display;

		@JSONField(name = "performance")
		private String performance;

		@JSONField(name = "volumes")
		private Volume[] volumes;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getPerformance() {
			return performance;
		}

		public void setPerformance(String performance) {
			this.performance = performance;
		}

		public Volume[] getVolumes() {
			return volumes;
		}

		public void setVolumes(Volume[] volumes) {
			this.volumes = volumes;
		}

		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDisplay() {
			return display;
		}

		public void setDisplay(String display) {
			this.display = display;
		}

		public String getTypeDisplay() {
			return typeDisplay;
		}

		public void setTypeDisplay(String typeDisplay) {
			this.typeDisplay = typeDisplay;
		}

		@Override
		public String toString() {
			return "Storage [type=" + type + ", typeDisplay=" + typeDisplay + ", code=" + code + ", display=" + display
					+ ", performance=" + performance + ", volumes=" + Arrays.toString(volumes) + "]";
		}

	}

	public static class Volume {
		@JSONField(name = "type")
		private String type;
		
		@JSONField(name = "display")
		private String display;

		@JSONField(name = "capacity")
		private BigInteger capacity;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public BigInteger getCapacity() {
			return capacity;
		}

		public void setCapacity(BigInteger capacity) {
			this.capacity = capacity;
		}

		public String getDisplay() {
			return display;
		}

		public void setDisplay(String display) {
			this.display = display;
		}

		@Override
		public String toString() {
			return "Volume [type=" + type + ", display=" + display + ", capacity=" + capacity + "]";
		}

	}

	public static class StatusService {
		@JSONField(name = "name")
		private String name; // 服务应用数据库类服务名称
		
		@JSONField(name = "state")
		private String state; // 服务应用数据库状态

		@JSONField(name = "arch")
		private Arch arch;

		@JSONField(name = "ports")
		private Port[] ports;

		@JSONField(name = "units")
		private Unit[] units;
		
		@JSONField(name = "unitha")
		private Boolean unitha;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Unit[] getUnits() {
			return units;
		}

		public void setUnits(Unit[] units) {
			this.units = units;
		}

		public Arch getArch() {
			return arch;
		}

		public void setArch(Arch arch) {
			this.arch = arch;
		}

		public Port[] getPorts() {
			return ports;
		}

		public void setPorts(Port[] ports) {
			this.ports = ports;
		}

		public Boolean getUnitha() {
			return unitha;
		}

		public void setUnitha(Boolean unitha) {
			this.unitha = unitha;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "StatusService [name=" + name + ", state=" + state + ", arch=" + arch + ", ports="
					+ Arrays.toString(ports) + ", units=" + Arrays.toString(units) + ", unitha=" + unitha + "]";
		}
	}

	public static class Unit {
		@JSONField(name = "id")
		private String id;

		@JSONField(name = "image")
		private Image image;

		@JSONField(name = "ip")
		private String ip;

		@JSONField(name = "state")
		private String state;

		@JSONField(name = "pod_state")
		private String podState;

		@JSONField(name = "start_at")
		private String startAt;

		@JSONField(name = "node")
		private Node node;
		
		@JSONField(name = "replication")
		private Replication replication;

		@JSONField(name = "resources")
		private StatusResources resources; // 数据库类服务资源
		
		//数量
		@JSONField(name = "num")
		private Integer num;
		
		//容器漂移方式
		@JSONField(name = "ha")
		private Boolean ha;
		
		@JSONField(name = "task")
		private Task task; // 最近任务

		public Task getTask() {
			return task;
		}

		public void setTask(Task task) {
			this.task = task;
		}

		public Boolean getHa() {
			return ha;
		}

		public void setHa(Boolean ha) {
			this.ha = ha;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getPodState() {
			return podState;
		}

		public void setPodState(String podState) {
			this.podState = podState;
		}

		public String getStartAt() {
			return startAt;
		}

		public void setStartAt(String startAt) {
			this.startAt = startAt;
		}

		public Node getNode() {
			return node;
		}

		public void setNode(Node node) {
			this.node = node;
		}

		public StatusResources getResources() {
			return resources;
		}

		public void setResources(StatusResources resources) {
			this.resources = resources;
		}

		public Replication getReplication() {
			return replication;
		}

		public void setReplication(Replication replication) {
			this.replication = replication;
		}

		@Override
		public String toString() {
			return "Unit [id=" + id + ", image=" + image + ", ip=" + ip + ", state=" + state + ", podState=" + podState
					+ ", startAt=" + startAt + ", node=" + node + ", replication=" + replication + ", resources="
					+ resources + ", num=" + num + ", ha=" + ha + ", task=" + task + "]";
		}

	}

	public static class Replication {
		@JSONField(name = "role")
		private String role;

		@JSONField(name = "self_port")
		private Integer selfPort;

		@JSONField(name = "self_ip")
		private String selfIp;

		@JSONField(name = "master_ip")
		private String masterIp;

		@JSONField(name = "master_port")
		private Integer masterPort;

		@JSONField(name = "slave_io_running")
		private String slaveIoRunning;

		@JSONField(name = "slave_sql_running")
		private String slaveSqlRunning;

		@JSONField(name = "slave_io_state")
		private String slaveIoState;

		@JSONField(name = "slave_sql_running_state")
		private String slaveSqlRunningState;

		@JSONField(name = "seconds_behind_master")
		private Long secondsBehindMaster;

		@JSONField(name = "master_log_file")
		private String masterLogFile;

		@JSONField(name = "relay_master_log_file")
		private String relayMasterLogFile;

		@JSONField(name = "read_master_log_pos")
		private Long readMasterLogPos;

		@JSONField(name = "exec_master_log_pos")
		private Long execMasterLogPos;

		@JSONField(name = "relay_log_file")
		private String relayLogFile;

		@JSONField(name = "relay_log_pos")
		private Long relayLogPos;

		@JSONField(name = "last_io_error")
		private String lastIoError;

		@JSONField(name = "last_sql_error")
		private String lastSqlError;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public Integer getSelfPort() {
			return selfPort;
		}

		public void setSelfPort(Integer selfPort) {
			this.selfPort = selfPort;
		}

		public String getSelfIp() {
			return selfIp;
		}

		public void setSelfIp(String selfIp) {
			this.selfIp = selfIp;
		}

		public String getMasterIp() {
			return masterIp;
		}

		public void setMasterIp(String masterIp) {
			this.masterIp = masterIp;
		}

		public Integer getMasterPort() {
			return masterPort;
		}

		public void setMasterPort(Integer masterPort) {
			this.masterPort = masterPort;
		}

		public String getSlaveIoRunning() {
			return slaveIoRunning;
		}

		public void setSlaveIoRunning(String slaveIoRunning) {
			this.slaveIoRunning = slaveIoRunning;
		}

		public String getSlaveSqlRunning() {
			return slaveSqlRunning;
		}

		public void setSlaveSqlRunning(String slaveSqlRunning) {
			this.slaveSqlRunning = slaveSqlRunning;
		}

		public String getSlaveIoState() {
			return slaveIoState;
		}

		public void setSlaveIoState(String slaveIoState) {
			this.slaveIoState = slaveIoState;
		}

		public String getSlaveSqlRunningState() {
			return slaveSqlRunningState;
		}

		public void setSlaveSqlRunningState(String slaveSqlRunningState) {
			this.slaveSqlRunningState = slaveSqlRunningState;
		}

		public Long getSecondsBehindMaster() {
			return secondsBehindMaster;
		}

		public void setSecondsBehindMaster(Long secondsBehindMaster) {
			this.secondsBehindMaster = secondsBehindMaster;
		}

		public String getMasterLogFile() {
			return masterLogFile;
		}

		public void setMasterLogFile(String masterLogFile) {
			this.masterLogFile = masterLogFile;
		}

		public String getRelayMasterLogFile() {
			return relayMasterLogFile;
		}

		public void setRelayMasterLogFile(String relayMasterLogFile) {
			this.relayMasterLogFile = relayMasterLogFile;
		}

		public Long getReadMasterLogPos() {
			return readMasterLogPos;
		}

		public void setReadMasterLogPos(Long readMasterLogPos) {
			this.readMasterLogPos = readMasterLogPos;
		}

		public Long getExecMasterLogPos() {
			return execMasterLogPos;
		}

		public void setExecMasterLogPos(Long execMasterLogPos) {
			this.execMasterLogPos = execMasterLogPos;
		}

		public String getRelayLogFile() {
			return relayLogFile;
		}

		public void setRelayLogFile(String relayLogFile) {
			this.relayLogFile = relayLogFile;
		}

		public Long getRelayLogPos() {
			return relayLogPos;
		}

		public void setRelayLogPos(Long relayLogPos) {
			this.relayLogPos = relayLogPos;
		}

		public String getLastIoError() {
			return lastIoError;
		}

		public void setLastIoError(String lastIoError) {
			this.lastIoError = lastIoError;
		}

		public String getLastSqlError() {
			return lastSqlError;
		}

		public void setLastSqlError(String lastSqlError) {
			this.lastSqlError = lastSqlError;
		}

		@Override
		public String toString() {
			return "Replication [role=" + role + ", selfPort=" + selfPort + ", selfIp=" + selfIp + ", masterIp="
					+ masterIp + ", masterPort=" + masterPort + ", slaveIoRunning=" + slaveIoRunning
					+ ", slaveSqlRunning=" + slaveSqlRunning + ", slaveIoState=" + slaveIoState
					+ ", slaveSqlRunningState=" + slaveSqlRunningState + ", secondsBehindMaster=" + secondsBehindMaster
					+ ", masterLogFile=" + masterLogFile + ", relayMasterLogFile=" + relayMasterLogFile
					+ ", readMasterLogPos=" + readMasterLogPos + ", execMasterLogPos=" + execMasterLogPos
					+ ", relayLogFile=" + relayLogFile + ", relayLogPos=" + relayLogPos + ", lastIoError=" + lastIoError
					+ ", lastSqlError=" + lastSqlError + "]";
		}

	}

	public static class Node {
		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "host_ip")
		private String hostIp;

		@JSONField(name = "cluster")
		private Cluster cluster;
		
		@JSONField(name = "site")
		private Site site;

		public Site getSite() {
			return site;
		}

		public void setSite(Site site) {
			this.site = site;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHostIp() {
			return hostIp;
		}

		public void setHostIp(String hostIp) {
			this.hostIp = hostIp;
		}

		public Cluster getCluster() {
			return cluster;
		}

		public void setCluster(Cluster cluster) {
			this.cluster = cluster;
		}

		@Override
		public String toString() {
			return "Node [id=" + id + ", name=" + name + ", hostIp=" + hostIp + ", cluster=" + cluster + "]";
		}

	}

	public static class Site {

		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Site [id=" + id + ", name=" + name + "]";
		}

	}

	public static class Cluster {
		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Cluster [id=" + id + ", name=" + name + "]";
		}

	}

	public static class Image {

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
		
		@JSONField(name = "arch")
		private String arch;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

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

		public String getArch() {
			return arch;
		}

		public void setArch(String arch) {
			this.arch = arch;
		}

		@Override
		public String toString() {
			return "Image [id=" + id + ", type=" + type + ", major=" + major + ", minor=" + minor + ", patch=" + patch
					+ ", build=" + build + ", arch=" + arch + "]";
		}

	}
	
	public static class Template{
		
		@JSONField(name = "config_file")
		private String configFile;
		
		@JSONField(name = "keysets")
		private java.util.List<KeySet> keySets;

		public String getConfigFile() {
			return configFile;
		}
		

		public void setConfigFile(String configFile) {
			this.configFile = configFile;
		}


		public java.util.List<KeySet> getKeySets() {
			return keySets;
		}
		


		public void setKeySets(java.util.List<KeySet> keySets) {
			this.keySets = keySets;
		}
		


		@Override
		public String toString() {
			return "Template [configFile=" + configFile + ", keySets=" + keySets + "]";
		}
	}
	
	public static class KeySet{
		
		@JSONField(name = "can_set")
		private Boolean canSet;
		
		@JSONField(name = "must_restart")
		private Boolean mustRestart;
		
		@JSONField(name = "key")
		private String key;
		
		@JSONField(name = "value")
		private String value;
		
		@JSONField(name = "default")
		private String defaultValue;
		
		@JSONField(name = "desc")
		private String desc;
		
		@JSONField(name = "range")
		private String range;

		public Boolean getCanSet() {
			return canSet;
		}
		

		public void setCanSet(Boolean canSet) {
			this.canSet = canSet;
		}
		

		public Boolean getMustRestart() {
			return mustRestart;
		}
		

		public void setMustRestart(Boolean mustRestart) {
			this.mustRestart = mustRestart;
		}
		

		public String getKey() {
			return key;
		}
		

		public void setKey(String key) {
			this.key = key;
		}
		

		public String getValue() {
			return value;
		}
		

		public void setValue(String value) {
			this.value = value;
		}
		

		public String getDefaultValue() {
			return defaultValue;
		}
		

		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		

		public String getDesc() {
			return desc;
		}
		

		public void setDesc(String desc) {
			this.desc = desc;
		}
		

		public String getRange() {
			return range;
		}
		

		public void setRange(String range) {
			this.range = range;
		}


		@Override
		public String toString() {
			return "KeySet [canSet=" + canSet + ", mustRestart=" + mustRestart + ", key=" + key + ", value=" + value
					+ ", defaultValue=" + defaultValue + ", desc=" + desc + ", range=" + range + "]";
		}
		
		
		
	}
}
