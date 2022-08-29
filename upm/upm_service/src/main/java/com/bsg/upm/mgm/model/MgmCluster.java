package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmCluster extends MgmModel implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "site")
    private Site site;

    @JSONField(name = "zone")
    private String zone;

    @JSONField(name = "image_type")
    private List<String> imageType;

    @JSONField(name = "ha_tag")
    private String haTag;

    @JSONField(name = "network")
    private List<Network> network;

    @JSONField(name = "enabled")
    private Boolean enabled;

    @JSONField(name = "desc")
    private String desc;

    @JSONField(name = "created")
    private Info created;

    @JSONField(name = "modified")
    private Info modified;
    
   /* @JSONField(name = "nfs_ip")
    private String nfs_ip;
    
    @JSONField(name = "nfs_source")
    private String nfs_source;
    
    @JSONField(name = "nfs_opts")
    private String nfs_opts;

    public String getNfs_ip() {
		return nfs_ip;
	}

	public void setNfs_ip(String nfs_ip) {
		this.nfs_ip = nfs_ip;
	}

	public String getNfs_source() {
		return nfs_source;
	}

	public void setNfs_source(String nfs_source) {
		this.nfs_source = nfs_source;
	}

	public String getNfs_opts() {
		return nfs_opts;
	}

	public void setNfs_opts(String nfs_opts) {
		this.nfs_opts = nfs_opts;
	}*/

	/**
     * 获取 id
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 site
     * 
     * @return site
     */
    public Site getSite() {
        return site;
    }

    /**
     * 设置site
     * 
     * @param site
     */
    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * 获取 zone
     * 
     * @return zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * 设置zone
     * 
     * @param zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * 获取 imageType
     * 
     * @return imageType
     */
    public List<String> getImageType() {
        return imageType;
    }

    /**
     * 设置imageType
     * 
     * @param imageType
     */
    public void setImageType(List<String> imageType) {
        this.imageType = imageType;
    }

    /**
     * 获取 haTag
     * 
     * @return haTag
     */
    public String getHaTag() {
        return haTag;
    }

    /**
     * 设置haTag
     * 
     * @param haTag
     */
    public void setHaTag(String haTag) {
        this.haTag = haTag;
    }

    /**
     * 获取 network
     * 
     * @return network
     */
    public List<Network> getNetwork() {
        return network;
    }

    /**
     * 设置network
     * 
     * @param network
     */
    public void setNetwork(List<Network> network) {
        this.network = network;
    }

    /**
     * 获取 enabled
     * 
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置enabled
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 desc
     * 
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置desc
     * 
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取 created
     * 
     * @return created
     */
    public Info getCreated() {
        return created;
    }

    /**
     * 设置created
     * 
     * @param created
     */
    public void setCreated(Info created) {
        this.created = created;
    }

    /**
     * 获取 modified
     * 
     * @return modified
     */
    public Info getModified() {
        return modified;
    }

    /**
     * 设置modified
     * 
     * @param modified
     */
    public void setModified(Info modified) {
        this.modified = modified;
    }

    @Override
	public String toString() {
		return "MgmCluster [id=" + id + ", name=" + name + ", site=" + site + ", zone=" + zone + ", imageType="
				+ imageType + ", haTag=" + haTag + ", network=" + network + ", enabled=" + enabled + ", desc=" + desc
				+ ", created=" + created + ", modified=" + modified  + "]";
	}


    public static class Network {
        @JSONField(name = "id")
        private String id;

        @JSONField(name = "name")
        private String name;

        /**
         * 获取 id
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置id
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 name
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置name
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Network [id=" + id + ", name=" + name + "]";
        }

    }

    public static class Site {
        @JSONField(name = "id")
        private String id;

        @JSONField(name = "name")
        private String name;

        /**
         * 获取 id
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置id
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 name
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置name
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Site [id=" + id + ", name=" + name + "]";
        }

    }
}
