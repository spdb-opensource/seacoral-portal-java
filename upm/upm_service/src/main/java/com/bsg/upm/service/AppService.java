package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bsg.upm.domain.JwtUser;
import com.bsg.upm.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.domain.AppDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.exception.ServiceException;

@Service
public class AppService extends BaseService {

	/*
	 * @Autowired private AppCheck appCheck;
	 */
	@Autowired
	JwtUtil jwtUtil;

	@Transactional
	public List<com.bsg.upm.dto.AppDTO> listBtnApp(HttpServletRequest request, String path) throws ServiceException {
		List<com.bsg.upm.dto.AppDTO> appDTOs = new ArrayList<>();
		// bug
		try {
//			HttpSession session = request.getSession();
//			UserDO userDO = (UserDO) session.getAttribute("user");
//			if(userDO==null){
//				return appDTOs;
//			}
			JwtUser jwtUser = jwtUtil.getJwtUserFromTokenInCache(request);
			if(jwtUser==null){
				return appDTOs;
			}
			List<AppDO> appDOs = appDAO.listBtnApp(jwtUser.getRoleId(), path);
			for (AppDO appDO : appDOs) {
				com.bsg.upm.dto.AppDTO appDTO = new com.bsg.upm.dto.AppDTO();
				appDTOs.add(appDTO);
				BeanUtils.copyProperties(appDO, appDTO);
			}
			return appDTOs;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Transactional
	public List<com.bsg.upm.dto.AppDTO> listSubBtnApp(HttpServletRequest request, String path) throws ServiceException {
		List<com.bsg.upm.dto.AppDTO> appDTOs = new ArrayList<>();
		// bug
		try {
//			HttpSession session = request.getSession();
//			UserDO userDO = (UserDO) session.getAttribute("user");
//			if(userDO==null){
//				return appDTOs;
//			}

			JwtUser jwtUser = jwtUtil.getJwtUserFromTokenInCache(request);
			if(jwtUser==null){
				return appDTOs;
			}
			List<AppDO> appDOs = appDAO.listSubBtnApp(jwtUser.getRoleId(), path);
			for (AppDO appDO : appDOs) {
				com.bsg.upm.dto.AppDTO appDTO = new com.bsg.upm.dto.AppDTO();
				appDTOs.add(appDTO);
				BeanUtils.copyProperties(appDO, appDTO);
			}
			return appDTOs;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Transactional
	public List<com.bsg.upm.dto.AppDTO> listsSubBtnApp(HttpServletRequest request, String path) throws ServiceException {
		List<com.bsg.upm.dto.AppDTO> appDTOs = new ArrayList<>();
		// bug
		try {
//			HttpSession session = request.getSession();
//			UserDO userDO = (UserDO) session.getAttribute("user");
//			if(userDO==null){
//				return appDTOs;
//			}
			JwtUser jwtUser = jwtUtil.getJwtUserFromTokenInCache(request);
			if(jwtUser==null){
				return appDTOs;
			}
			List<AppDO> appDOs = appDAO.listsSubBtnApp(jwtUser.getRoleId(), path);
			for (AppDO appDO : appDOs) {
				com.bsg.upm.dto.AppDTO appDTO = new com.bsg.upm.dto.AppDTO();
				appDTOs.add(appDTO);
				BeanUtils.copyProperties(appDO, appDTO);
			}
			return appDTOs;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Transactional
	public List<AppDTO> listPage(HttpServletRequest request) throws ServiceException {
		List<AppDTO> appDTOs = new ArrayList<>();
		// bug
		try {
//			HttpSession session = request.getSession();
//			UserDO userDO = (UserDO) session.getAttribute("user");
//			if(userDO==null){
//				return appDTOs;
//			}
			JwtUser jwtUser = jwtUtil.getJwtUserFromTokenInCache(request);
			if(jwtUser==null){
				return appDTOs;
			}
			List<AppDO> menuAppDOs = appDAO.listMenu(jwtUser.getRoleId());
			List<AppDO> pageAppDOs = appDAO.listPage(jwtUser.getRoleId());
			for (AppDO menuAppDO : menuAppDOs) {
				AppDTO menuAppDTO = new AppDTO();
				appDTOs.add(menuAppDTO);
				menuAppDTO.setId(menuAppDO.getId());
				menuAppDTO.setName(menuAppDO.getName());
				menuAppDTO.setType(menuAppDO.getType());
				menuAppDTO.setCode(menuAppDO.getCode());
				menuAppDTO.setIcon(menuAppDO.getIcon());
				List<subAppDTO> pageAppDTOs = new ArrayList<>();

				menuAppDTO.setSubApps(pageAppDTOs);
				for (AppDO pageAppDO : pageAppDOs) {
					if (menuAppDO.getId().equals(pageAppDO.getPid())) {
						subAppDTO pageAppDTO = new subAppDTO();
						pageAppDTOs.add(pageAppDTO);
						pageAppDTO.setId(pageAppDO.getId());
						pageAppDTO.setName(pageAppDO.getName());
						pageAppDTO.setType(pageAppDO.getType());
						pageAppDTO.setCode(pageAppDO.getCode());
						pageAppDTO.setIcon(pageAppDO.getIcon());
					}
				}
			}
			return appDTOs;
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	public class AppDTO {
		private Long id;
		private String name;
		private String type;
		private String code;
		private String icon;
		private List<subAppDTO> subApps = new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public List<subAppDTO> getSubApps() {
			return subApps;
		}

		public void setSubApps(List<subAppDTO> subApps) {
			this.subApps = subApps;
		}

		@Override
		public String toString() {
			return "AppDTO [id=" + id + ", name=" + name + ", type=" + type + ", code=" + code + ", icon=" + icon + ", subApps=" + subApps
					+ "]";
		}

	}

	public class subAppDTO {
		private Long id;
		private String name;
		private String type;
		private String code;
		private String icon;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		@Override
		public String toString() {
			return "AppDTO [id=" + id + ", name=" + name + ", type=" + type + ", code=" + code + ", icon=" + icon + "]";
		}

	}
}
