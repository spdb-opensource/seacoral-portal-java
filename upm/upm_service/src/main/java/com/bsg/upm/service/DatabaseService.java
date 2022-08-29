package com.bsg.upm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.DatabaseCheck;
import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.dto.DatabaseDTO;
import com.bsg.upm.dto.DatabaseDTO.TableDTO;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.ServerGroupForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmDatabaseBody;
import com.bsg.upm.mgm.body.MgmServerGroupImageBody;
import com.bsg.upm.mgm.body.MgmServerGroupImageBody.ImageBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.DatabaseBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.RequestsBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.ResourcesBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.ServicesBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.SpecBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.StorageBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.UnitsBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.VolumesBody;
import com.bsg.upm.mgm.body.MgmServerGroupStateBody;
import com.bsg.upm.mgm.body.MgmSiteBody;
import com.bsg.upm.mgm.model.MgmDatabase;
import com.bsg.upm.mgm.model.MgmDatabase.Table;
import com.bsg.upm.mgm.query.MgmDatabaseQuery;

/**
 * 数据库service
 * 
 * @author swn
 *
 */
@Service
public class DatabaseService extends BaseService {

	@Autowired
	private MgmApi mgmApi;

	@Autowired
	private DatabaseCheck databaseCheck;

	/**
	 * 库列表查询
	 * 
	 * @param serverGroupId
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public Result list(String serverGroupId) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			final List<DatabaseDTO> databaseDTOs = new ArrayList<>();

			MgmDatabaseQuery mgmDatabaseQuery = new MgmDatabaseQuery();
			List<MgmDatabase> mgmDatabases = mgmApi.listDatabase(serverGroupId, mgmDatabaseQuery);
			for (MgmDatabase mgmDatabase : mgmDatabases) {
				DatabaseDTO databaseDTO = getShowBaseDTO(mgmDatabase);
				databaseDTOs.add(databaseDTO);
			}

			return Result.success(databaseDTOs);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 库详情查询
	 * 
	 * @param serverGroupId
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public Result get(String serverGroupId, String databaseName) throws ServiceException {
		DatabaseDTO databaseDTO = null;
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(databaseName)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_DATABASE_NAME_MUST_NOT_BE_BLANK));
        	}
			MgmDatabaseQuery mgmDatabaseQuery = new MgmDatabaseQuery();
			MgmDatabase mgmDatabase = mgmApi.detailDatabase(serverGroupId, databaseName, mgmDatabaseQuery);
			databaseDTO = getShowBaseDTO1(mgmDatabase);

			return Result.success(databaseDTO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 库保存
	 * 
	 * @param serverGroupId
	 * @param databaseForm
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
	public Result save(String serverGroupId, DatabaseForm databaseForm) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			CheckResult checkResult = databaseCheck.checkSave(databaseForm);
			if (checkResult.getCode() != SiteChkRsEnum.SUCCESS.getCode()) {
				logger.error(loginfo(checkResult));
				return Result.failure(checkResult);
			}

			MgmDatabaseBody mgmDatabaseBody = buildSaveMgmDatabaseBody(databaseForm);
			mgmApi.saveDatabase(serverGroupId, mgmDatabaseBody);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 库删除
	 * 
	 * @param serverGroupId
	 * @param databaseName
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
	public Result remove(String serverGroupId, String databaseName) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(databaseName)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_DATABASE_NAME_MUST_NOT_BE_BLANK));
        	}
			mgmApi.removeDatabase(serverGroupId, databaseName);
			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * restful接口数据转前端数据
	 * 
	 * @param mgmSite
	 * @return
	 * @throws IOException
	 * @throws CallingInterfaceException
	 * @throws ServiceNotFoundException
	 * @throws ConnectConsulException
	 */
	private DatabaseDTO getShowBaseDTO(MgmDatabase mgmDatabase)
			throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
		if (mgmDatabase == null) {
			return null;
		}

		DatabaseDTO databaseDTO = new DatabaseDTO();
		databaseDTO.setName(mgmDatabase.getName());
		databaseDTO.setCharacterSet(mgmDatabase.getCharacterSet());
		databaseDTO.setSize(mgmDatabase.getSize());
//		Table[] tables = mgmDatabase.getTables();
//		if (tables.length > 0) {
//			TableDTO[] tableDTOs = new TableDTO[tables.length];
//			databaseDTO.setTables(tableDTOs);
//
//			for (int i = 0; i < tables.length; i++) {
//				TableDTO tableDTO = databaseDTO.new TableDTO();
//				tableDTO.setName(tables[i].getName());
//				if (tables[i].getSize() != null) {
//					tableDTO.setSize((long) tables[i].getSize());
//				}
//				tableDTOs[i] = tableDTO;
//			}
//		}
		return databaseDTO;
	}
	
	private DatabaseDTO getShowBaseDTO1(MgmDatabase mgmDatabase)
			throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
		if (mgmDatabase == null) {
			return null;
		}

		DatabaseDTO databaseDTO = new DatabaseDTO();
		databaseDTO.setName(mgmDatabase.getName());
		databaseDTO.setCharacterSet(mgmDatabase.getCharacterSet());

		Table[] tables = mgmDatabase.getTables();
		if (tables.length > 0) {
			TableDTO[] tableDTOs = new TableDTO[tables.length];
			databaseDTO.setTables(tableDTOs);

			for (int i = 0; i < tables.length; i++) {
				TableDTO tableDTO = databaseDTO.new TableDTO();
				tableDTO.setName(tables[i].getName());
				if (tables[i].getSize() != null) {
					tableDTO.setSize((long) tables[i].getSize());
				}
				tableDTOs[i] = tableDTO;
			}
		}
		return databaseDTO;
	}

	private MgmDatabaseBody buildSaveMgmDatabaseBody(DatabaseForm databaseForm) {
		MgmDatabaseBody mgmDatabaseBody = new MgmDatabaseBody();

		mgmDatabaseBody.setName(databaseForm.getName());
		mgmDatabaseBody.setCharacterSet(databaseForm.getCharacterSet());

		return mgmDatabaseBody;
	}

}
