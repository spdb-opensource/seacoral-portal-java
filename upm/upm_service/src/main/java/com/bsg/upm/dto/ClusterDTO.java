package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.HostDTO.SiteDTO;

public class ClusterDTO extends BaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 集群编码
     */
    private String id;

    /**
     * 集群名称
     */
    private String name;

    /**
     * 站点
     */
    private SiteDTO site;

    /**
     * 区域
     */
    private TypeDTO area;

    /**
     * 包含软件类型
     */
    private List<String> imageTypes;

    /**
     * 高可用标签
     */
    private String haTag;

    /**
     * 网段
     */
    private List<NetworkingDTO> networkings;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建
     */
    private InfoDTO created;

    /**
     * 变更
     */
    private InfoDTO modified;
    
    /**
     * nfs服务IP地址
     */
    private String nfs_ip;
    
    /**
     * nfs服务源目录
     */
    private String nfs_source;
    
    /**
     * nfs服务挂载参数
     */
    private String nfs_opts;

    /**
     * 获取 集群编码
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置集群编码
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 集群名称
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置集群名称
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 站点
     * 
     * @return site
     */
    public SiteDTO getSite() {
        return site;
    }

    /**
     * 设置站点
     * 
     * @param site
     */
    public void setSite(SiteDTO site) {
        this.site = site;
    }

    /**
     * 获取 区域
     * 
     * @return area
     */
    public TypeDTO getArea() {
        return area;
    }

    /**
     * 设置区域
     * 
     * @param area
     */
    public void setArea(TypeDTO area) {
        this.area = area;
    }

    /**
     * 获取 包含软件类型
     * 
     * @return imageTypes
     */
    public List<String> getImageTypes() {
        return imageTypes;
    }

    /**
     * 设置包含软件类型
     * 
     * @param imageTypes
     */
    public void setImageTypes(List<String> imageTypes) {
        this.imageTypes = imageTypes;
    }

    /**
     * 获取 高可用标签
     * 
     * @return haTag
     */
    public String getHaTag() {
        return haTag;
    }

    /**
     * 设置高可用标签
     * 
     * @param haTag
     */
    public void setHaTag(String haTag) {
        this.haTag = haTag;
    }

    /**
     * 获取 网段
     * 
     * @return networkings
     */
    public List<NetworkingDTO> getNetworkings() {
        return networkings;
    }

    /**
     * 设置网段
     * 
     * @param networkings
     */
    public void setNetworkings(List<NetworkingDTO> networkings) {
        this.networkings = networkings;
    }

    /**
     * 获取 是否可用
     * 
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 描述
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取 创建
     * 
     * @return created
     */
    public InfoDTO getCreated() {
        return created;
    }

    /**
     * 设置创建
     * 
     * @param created
     */
    public void setCreated(InfoDTO created) {
        this.created = created;
    }

    /**
     * 获取 变更
     * 
     * @return modified
     */
    public InfoDTO getModified() {
        return modified;
    }

    /**
     * 设置变更
     * 
     * @param modified
     */
    public void setModified(InfoDTO modified) {
        this.modified = modified;
    }

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
  	}

    @Override
	public String toString() {
		return "ClusterDTO [id=" + id + ", name=" + name + ", site=" + site + ", area=" + area + ", imageTypes="
				+ imageTypes + ", haTag=" + haTag + ", networkings=" + networkings + ", enabled=" + enabled
				+ ", description=" + description + ", created=" + created + ", modified=" + modified + ", nfs_ip="
				+ nfs_ip + ", nfs_source=" + nfs_source + ", nfs_opts=" + nfs_opts + "]";
	}

    public class SiteDTO {

        /**
         * 站点编码
         */
        private String id;

        /**
         * 站点名称
         */
        private String name;

        /**
         * 获取 站点编码
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置站点编码
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 站点名称
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置站点名称
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
            return "SiteDTO [id=" + id + ", name=" + name + "]";
        }

    }

    public class NetworkingDTO {

        /**
         * 网段编码
         */
        private String id;

        /**
         * 网段名称
         */
        private String name;

        /**
         * 获取 网段编码
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置网段编码
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 网段名称
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置网段名称
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
            return "NetworkDTO [id=" + id + ", name=" + name + "]";
        }

    }
}
