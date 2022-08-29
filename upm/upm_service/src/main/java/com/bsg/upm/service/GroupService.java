package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.GroupCheck;
import com.bsg.upm.check.resultenum.DictChkRsEnum;
import com.bsg.upm.check.resultenum.GroupChkRsEnum;
import com.bsg.upm.domain.GroupDO;
import com.bsg.upm.domain.GroupUserDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.GroupDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.GroupForm;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class GroupService extends BaseService {

    @Autowired
    private GroupCheck groupCheck;

    @Transactional
    public Result list() throws ServiceException {
        try {
            List<GroupDTO> groupDTOs = new ArrayList<>();
            List<GroupDO> groupDOs = groupDAO.list();
            String username = getUsername();
            UserDO userDO = userDAO.getByUsername(username);
            List<GroupDO> userGroupDOs = userDO.getGroups();

            for (GroupDO groupDO : groupDOs) {
                if (userDO.getRoleId().equals(1L)) {
                    GroupDTO groupDTO = getShowDTO(groupDO);
                    groupDTOs.add(groupDTO);
                } else {
                    boolean visiable = false;
                    for (GroupDO userGroupDO : userGroupDOs) {
                        if (groupDO.getId().equals(userGroupDO.getId())) {
                            visiable = true;
                            break;
                        }
                    }
                    if (groupDO.getCreator().equals(username) || visiable) {
                        GroupDTO groupDTO = getShowDTO(groupDO);
                        groupDTOs.add(groupDTO);
                    }
                }
            }

            return Result.success(groupDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String groupId) throws ServiceException {
        GroupDTO groupDTO = null;
        try {
        	if(nullCheck(groupId)) {
        		return Result.failure(CheckResult.failure(GroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            GroupDO groupDO = groupDAO.get(groupId);
            if (groupDO != null) {
                groupDTO = getShowDTO(groupDO);
            }

            return Result.success(groupDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(GroupForm groupForm) throws ServiceException {
        try {
            CheckResult checkResult = groupCheck.checkSave(groupForm);
            if (checkResult.getCode() != GroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            GroupDO groupDO = buildGroupForSave(groupForm);
            groupDAO.save(groupDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String groupId, GroupForm groupForm) throws ServiceException {
        try {
        	if(nullCheck(groupId)) {
        		return Result.failure(CheckResult.failure(GroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = groupCheck.checkUpdate(groupId, groupForm);
            if (checkResult.getCode() != GroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            GroupDO newGroupDO = buildGroupForUpdate(groupId, groupForm);
            groupDAO.update(newGroupDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String groupId) throws ServiceException {
        try {
        	if(nullCheck(groupId)) {
        		return Result.failure(CheckResult.failure(GroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = groupCheck.checkRemove(groupId);
            if (checkResult.getCode() != GroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            groupDAO.remove(groupId);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result saveUsers(String groupId, List<String> usernames) throws ServiceException {
        try {
        	if(nullCheck(groupId)) {
        		return Result.failure(CheckResult.failure(GroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = groupCheck.checkSaveUser(groupId, usernames);
            if (checkResult.getCode() != GroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            groupUserDAO.removeByGroupId(groupId);
            for (String username : usernames) {
            	int index=0;
            	String aa=""+index;
                UserDO userDO = new UserDO();
                List<String> groupIds = new ArrayList<>();
                groupIds.add(groupId);
                userDO.setGroupIds(groupIds);
                userDO.setUsername(username);
            	GroupUserDO groupUserdo=new GroupUserDO();
            	groupUserdo.setId(PinyinUtil.getUuid());
            	groupUserdo.setUsername(username);
            	groupUserdo.setGroupId(groupId);
	        	GroupUserDO groupUserDO=new GroupUserDO();
	        	groupUserDO.setGroupId(groupId);
	        	groupUserDO.setUsername(username);
	        	groupUserDO.setId(PinyinUtil.getUuid()+aa);
	        	groupUserDAO.saveUser(groupUserDO);
	        	index+=1;
//                groupUserDAO.save(userDO);
            }
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private GroupDTO getShowDTO(GroupDO groupDO) {
        GroupDTO groupDTO = new GroupDTO();
        InfoDTO created = groupDTO.new InfoDTO();
        created.setTimestamp(DateUtils.dateTimeToString(groupDO.getGmtCreate()));
        created.setUsername(groupDO.getCreator());

        InfoDTO modified = groupDTO.new InfoDTO();
        modified.setTimestamp(DateUtils.dateTimeToString(groupDO.getGmtModified()));
        modified.setUsername(groupDO.getEditor());

        groupDTO.setId(groupDO.getId());
        groupDTO.setName(groupDO.getName());
        groupDTO.setDescription(groupDO.getDescription());
        groupDTO.setCreated(created);
        return groupDTO;
    }

    private GroupDO buildGroupForSave(GroupForm groupForm) {
        GroupDO groupDO = new GroupDO();
        groupDO.setId(PinyinUtil.getUuid());
        groupDO.setName(groupForm.getName());
        groupDO.setDescription(StringUtils.trimToEmpty(groupForm.getDescription()));
        groupDO.setCreator(getUsername());
        groupDO.setGmtCreate(systemDAO.getCurrentSqlDateTime());
        return groupDO;
    }

    private GroupDO buildGroupForUpdate(String groupId, GroupForm groupForm) {
        GroupDO newGroupDO = new GroupDO();
        GroupDO groupDO = groupDAO.get(groupId);
        BeanUtils.copyProperties(groupDO, newGroupDO);

        if (groupForm.getName() != null) {
            newGroupDO.setName(groupForm.getName());
        }
        if (groupForm.getDescription() != null) {
            newGroupDO.setDescription(groupForm.getDescription());
        }
        newGroupDO.setEditor(getUsername());
        newGroupDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
        return newGroupDO;
    }

    public GroupDO getDomain(String groupId) {
        return groupDAO.get(groupId);
    }

}
