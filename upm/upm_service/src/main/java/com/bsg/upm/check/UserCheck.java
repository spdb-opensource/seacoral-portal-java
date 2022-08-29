package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bsg.upm.check.resultenum.UserChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.domain.GroupDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.form.PwdForm;
import com.bsg.upm.form.UserForm;

@Service
public class UserCheck extends BaseCheck {

    /**
     * 
     * @param userForm
     * @return
     */
    public CheckResult checkSave(UserForm userForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(userForm);
        if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(userForm);
        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param username
     * @param userForm
     * @return
     */
    public CheckResult checkUpdate(String username, UserForm userForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(userForm);
        if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(username, userForm);

        return checkResult;
    }

    /**
     * 
     * @param username
     * @param enbaled
     * @return
     */
    public CheckResult checkEnabled(String username, boolean enable) {
        UserDO userDO = userDAO.getByUsername(username);
        if (userDO == null) {
            return CheckResult.failure(UserChkRsEnum.ILLEGAL_USERNAME_NOT_EXIST, username);
        }
        return CheckResult.success();
    }

    /**
     * 
     * @param username
     * @param pwdForm
     * @return
     */
    public CheckResult checkUpdatePwd(String username, PwdForm pwdForm) {
        UserDO userDO = userDAO.getByUsername(username);
        if (userDO == null) {
            return CheckResult.failure(UserChkRsEnum.ILLEGAL_USERNAME_NOT_EXIST, username);
        }

        if (!userDO.getAuthType().equals(DictConsts.AUTH_TYPE_NATIVE)) {
            return CheckResult.failure(UserChkRsEnum.PWD_ILLEGAL_FORBID_UPDATE);
        }

        if (StringUtils.isBlank(pwdForm.getOriginalPwd())) {
            return CheckResult.failure(UserChkRsEnum.PWD_ILLEGAL_ORIGINAL_PWD_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(pwdForm.getNewPwd())) {
            return CheckResult.failure(UserChkRsEnum.PWD_ILLEGAL_NEW_PWD_MUST_NOT_BE_BLANK);
        }

        if (pwdForm.getOriginalPwd().equals(pwdForm.getNewPwd())) {
            return CheckResult.failure(UserChkRsEnum.PWD_ILLEGAL_NEW_PWD_NOT_SAME_WITH_NEW_PWD);
        }

        if (!userDO.getPassword().equals(DigestUtils.md5DigestAsHex(pwdForm.getOriginalPwd().getBytes()))) {
            return CheckResult.failure(UserChkRsEnum.PWD_ILLEGAL_ORIGINAL_PWD_ERROR);
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param userForm
     * @return
     */
    private CheckResult checkSaveNonLogic(UserForm userForm) {
        if (StringUtils.isBlank(userForm.getUsername())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK);
        }

        /*if (StringUtils.isBlank(userForm.getAuthType())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_AUTHTYPE_MUST_NOT_BE_BLANK);
        }

        if (!userForm.getAuthType().equals(DictConsts.AUTH_TYPE_NATIVE)
                && !userForm.getAuthType().equals(DictConsts.AUTH_TYPE_SSO)) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_AUTHTYPE_FORMAT_ERROR);
        }*/

        if (StringUtils.isBlank(userForm.getPassword())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(userForm.getName())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(userForm.getTelephone())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_TELEPHONE_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(userForm.getEmail())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_EMAIL_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(userForm.getCompany())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_COMPANY_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(userForm.getEmerContact())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_EMER_CONTACT_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(userForm.getEmerTel())) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_EMER_TEL_MUST_NOT_BE_BLANK);
        }

        if (userForm.getRoleId() == null) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_ROLE_ID_MUST_NOT_BE_NULL);
        }

        if (userForm.getGroupIds() == null || userForm.getGroupIds().size() == 0) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_GROUP_IDS_MUST_NOT_BE_NULL);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param userForm
     * @return
     */
    private CheckResult checkSaveLogic(UserForm userForm) {
        UserDO userDO = userDAO.getByUsername(userForm.getUsername());
        if (userDO != null) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_USERNAME_EXIST, userForm.getUsername());
        }

        int telephoneCnt = userDAO.countByTelephone(userForm.getTelephone());
        if (telephoneCnt != 0) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_TELEPHONE_EXIST, userForm.getTelephone());
        }

        int emailCnt = userDAO.countByEmail(userForm.getEmail());
        if (emailCnt != 0) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_EMAIL_EXIST, userForm.getEmail());
        }

        RoleDO roleDO = roleDAO.get(userForm.getRoleId());
        if (roleDO == null) {
            return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_ROLE_ID_NOT_EXIST, userForm.getRoleId());
        }

        List<String> groupIds = userForm.getGroupIds();
        for (String groupId : groupIds) {
            GroupDO groupDO = groupDAO.get(groupId);
            if (groupDO == null) {
                return CheckResult.failure(UserChkRsEnum.ADD_ILLEGAL_GROUP_ID_NOT_EXIST, groupId);
            }
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param userForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(UserForm userForm) {
        if (userForm.getName() != null && StringUtils.isEmpty(userForm.getName())) {
            return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        if (userForm.getTelephone() != null && StringUtils.isEmpty(userForm.getTelephone())) {
            return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_TELEPHONE_MUST_NOT_BE_EMPTY);
        }

        if (userForm.getEmail() != null && StringUtils.isEmpty(userForm.getEmail())) {
            return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_EMAIL_MUST_NOT_BE_EMPTY);
        }

        if (userForm.getCompany() != null && StringUtils.isEmpty(userForm.getCompany())) {
            return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_COMPANY_MUST_NOT_BE_EMPTY);
        }

        if (userForm.getEmerContact() != null && StringUtils.isEmpty(userForm.getEmerContact())) {
            return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_EMER_CONTACT_MUST_NOT_BE_EMPTY);
        }

        if (userForm.getEmerTel() != null && StringUtils.isEmpty(userForm.getEmerTel())) {
            return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_EMER_TEL_MUST_NOT_BE_EMPTY);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param areaId
     * @param areaForm
     * @return
     */
    private CheckResult checkUpdateLogic(String username, UserForm userForm) {
        UserDO userDO = userDAO.getByUsername(username);
        if (userDO == null) {
            return CheckResult.failure(UserChkRsEnum.ILLEGAL_USERNAME_NOT_EXIST, username);
        }

        if (StringUtils.isNotBlank(userForm.getTelephone()) && !userForm.getTelephone().equals(userDO.getTelephone())) {
            int telephoneCnt = userDAO.countByTelephone(userForm.getTelephone());
            if (telephoneCnt != 0) {
                return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_TELEPHONE_EXIST, userForm.getTelephone());
            }
        }

        if (StringUtils.isNotBlank(userForm.getEmail()) && !userForm.getEmail().equals(userDO.getEmail())) {
            int emailCnt = userDAO.countByEmail(userForm.getEmail());
            if (emailCnt != 0) {
                return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_EMAIL_EXIST, userForm.getEmail());
            }
        }

        if (userForm.getRoleId() != null && !userForm.getRoleId().equals(userDO.getRoleId())) {
            RoleDO roleDO = roleDAO.get(userForm.getRoleId());
            if (roleDO == null) {
                return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_ROLE_ID_NOT_EXIST, userForm.getRoleId());
            }
        }

        if (userForm.getGroupIds() != null) {
            List<String> groupIds = userForm.getGroupIds();
            for (String groupId : groupIds) {
                GroupDO groupDO = groupDAO.get(groupId);
                if (groupDO == null) {
                    return CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_GROUP_ID_NOT_EXIST, groupId);
                }
            }
        }

        return CheckResult.success();
    }

}
