package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.dto.BaseDTO;
import com.bsg.upm.mgm.model.MgmModel.Info;
import com.bsg.upm.mgm.model.MgmModel.Task;
import com.bsg.upm.mgm.model.MgmServerGroup.Image;
import com.bsg.upm.mgm.model.MgmServerGroup.Template;
import com.bsg.upm.query.ImageParam;

/**
 * 
 * @author Liuxd
 * @date 2019年8月22日
 */
public class MgmImageConf  implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "iamge")
    private Image iamge;

    @JSONField(name = "template")
    private Template template;

	public Image getIamge() {
		return iamge;
	}
	

	public void setIamge(Image iamge) {
		this.iamge = iamge;
	}
	

	public Template getTemplate() {
		return template;
	}
	

	public void setTemplate(Template template) {
		this.template = template;
	}


	@Override
	public String toString() {
		return "MgmImageConf [iamge=" + iamge + ", template=" + template + "]";
	}
	
}
