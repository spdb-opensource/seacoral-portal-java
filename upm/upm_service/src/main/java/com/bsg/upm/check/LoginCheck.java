package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bsg.upm.check.resultenum.LoginChkRsEnum;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.form.LoginForm;
import com.bsg.upm.util.DecodeBase64Util;

@Service
public class LoginCheck extends BaseCheck {

    /**
     * 
     * @param loginForm
     * @return
     */
    public CheckResult checkSave(LoginForm loginForm) {
        String username = loginForm.getUsername();
        if (StringUtils.isBlank(username)) {
            return CheckResult.failure(LoginChkRsEnum.ILLEGAL_USERNAME_MUST_NOT_BE_BLANK);
        }

        String password = loginForm.getPassword();
        if (StringUtils.isBlank(password)) {
            return CheckResult.failure(LoginChkRsEnum.ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK);
        }
        // 密码先经过base64解密
//        password = new String(DecodeBase64Util.decode(password));

        // 密码加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        UserDO userDO = userDAO.get(username, password);

        if (userDO == null) {
            return CheckResult.failure(LoginChkRsEnum.INVALID);
        }

        if (!userDO.getEnabled()) {
            return CheckResult.failure(LoginChkRsEnum.ILLEGAL_USERNAME_DISABLED, username);
        }

        return CheckResult.success();
    }

}
