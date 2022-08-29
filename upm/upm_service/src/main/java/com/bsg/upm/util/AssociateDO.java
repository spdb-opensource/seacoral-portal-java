package com.bsg.upm.util;

import java.util.ArrayList;
import java.util.List;

import com.bsg.upm.domain.DictTypeDO;
import com.bsg.upm.domain.UserDO;

public class AssociateDO {

    private List<UserDO> userDOs = new ArrayList<>();
    private List<DictTypeDO> dictTypeDOs = new ArrayList<>();

    /**
     * 获取userDOs
     * 
     * @return userDOs userDOs
     */
    public List<UserDO> getUserDOs() {
        return userDOs;
    }

    /**
     * 设置userDOs
     * 
     * @param userDOs
     *            userDOs
     */
    public void setUserDOs(List<UserDO> userDOs) {
        this.userDOs = userDOs;
    }

   

  
    /**
     * 获取dictTypeDOs
     * 
     * @return dictTypeDOs dictTypeDOs
     */
    public List<DictTypeDO> getDictTypeDOs() {
        return dictTypeDOs;
    }

    /**
     * 设置dictTypeDOs
     * 
     * @param dictTypeDOs
     *            dictTypeDOs
     */
    public void setDictTypeDOs(List<DictTypeDO> dictTypeDOs) {
        this.dictTypeDOs = dictTypeDOs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AssociateDO [userDOs=" + userDOs +  ", dictTypeDOs=" + dictTypeDOs + "]";
    }

}
