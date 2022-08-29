package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.GroupChkRsEnum;
import com.bsg.upm.domain.GroupDO;
import com.bsg.upm.form.GroupForm;

@Service
public class GroupCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param groupForm
     * @return
     */
    public CheckResult checkSave(GroupForm groupForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(groupForm);
        if (checkResult.getCode() != GroupChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(groupForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param groupId
     * @param groupForm
     * @return
     */
    public CheckResult checkUpdate(String groupId, GroupForm groupForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(groupForm);
        if (checkResult.getCode() != GroupChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(groupId, groupForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param groupId
     * @return
     */
    public CheckResult checkRemove(String groupId) {
        GroupDO groupDO = groupDAO.get(groupId);
        if (groupDO == null) {
            return CheckResult.failure(GroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, groupId);
        }

        int userCnt = groupUserDAO.countByGroupId(groupId);
        if (userCnt != 0) {
            return CheckResult.failure(GroupChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_USER, groupDO.getName());
        }
        return CheckResult.success();
    }
    
    /**
     * check for save user
     * 
     * @param groupId
     * @return
     */
    public CheckResult checkSaveUser(String groupId, List<String> usernames) {
        GroupDO groupDO = groupDAO.get(groupId);
        if (groupDO == null) {
            return CheckResult.failure(GroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, groupId);
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param groupForm
     * @return
     */
    private CheckResult checkSaveNonLogic(GroupForm groupForm) {
        if (StringUtils.isBlank(groupForm.getName())) {
            return CheckResult.failure(GroupChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param groupForm
     * @return
     */
    private CheckResult checkSaveLogic(GroupForm groupForm) {
        GroupDO groupDO = groupDAO.getByNameAndCreator(groupForm.getName(), getUsername());
        if (groupDO != null) {
            return CheckResult.failure(GroupChkRsEnum.ADD_ILLEGAL_NAME_EXIST, groupForm.getName());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param groupForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(GroupForm groupForm) {
        if (groupForm.getName() != null && StringUtils.isEmpty(groupForm.getName())) {
            return CheckResult.failure(GroupChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param groupId
     * @param groupForm
     * @return
     */
    private CheckResult checkUpdateLogic(String groupId, GroupForm groupForm) {
        GroupDO groupDO = groupDAO.get(groupId);
        if (groupDO == null) {
            return CheckResult.failure(GroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, groupId);
        }

        if (StringUtils.isNotBlank(groupForm.getName()) && !groupForm.getName().equals(groupDO.getName())) {
            GroupDO newGroupDO = groupDAO.getByNameAndCreator(groupForm.getName(), getUsername());
            if (newGroupDO != null) {
                return CheckResult.failure(GroupChkRsEnum.UPDATE_ILLEGAL_NAME_EXIST, groupForm.getName());
            }
        }
        return CheckResult.success();
    }
}
