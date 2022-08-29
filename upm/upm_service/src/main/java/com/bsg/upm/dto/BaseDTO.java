package com.bsg.upm.dto;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月4日
 */
public class BaseDTO {

    private TypeDTO typeDTO;

    private InfoDTO infoDTO;

    private TaskDTO taskDTO;
    
    public class TypeDTO {
        /**
         * 类型
         */
        private String code;
        /**
         * 类型展示
         */
        private String display;

        /**
         * 获取 类型
         * 
         * @return code
         */
        public String getCode() {
            return code;
        }

        /**
         * 设置类型
         * 
         * @param code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * 获取 类型展示
         * 
         * @return display
         */
        public String getDisplay() {
            return display;
        }

        /**
         * 设置类型展示
         * 
         * @param display
         */
        public void setDisplay(String display) {
            this.display = display;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "TypeDTO [code=" + code + ", display=" + display + "]";
        }

    }

    public class InfoDTO {

        /**
         * 时间
         */
        private String timestamp;

        /**
         * 人物姓名
         */
        private String username;

        /**
         * 获取 时间
         * 
         * @return timestamp
         */
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * 设置时间
         * 
         * @param timestamp
         */
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * 获取 人物姓名
         * 
         * @return username
         */
        public String getUsername() {
            return username;
        }

        /**
         * 设置人物姓名
         * 
         * @param username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "InfoDTO [timestamp=" + timestamp + ", username=" + username + "]";
        }

    }

    public class TaskDTO {

        /**
         * 任务编码
         */
        private String id;

        /**
         * 任务动作
         */
        private TypeDTO action;

        /**
         * 任务状态
         */
        private TypeDTO status;

        /**
         * 获取 任务编码
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置任务编码
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 任务动作
         * 
         * @return action
         */
        public TypeDTO getAction() {
            return action;
        }

        /**
         * 设置任务动作
         * 
         * @param action
         */
        public void setAction(TypeDTO action) {
            this.action = action;
        }

        /**
         * 获取 任务状态
         * 
         * @return status
         */
        public TypeDTO getStatus() {
            return status;
        }

        /**
         * 设置任务状态
         * 
         * @param status
         */
        public void setStatus(TypeDTO status) {
            this.status = status;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "TaskDTO [id=" + id + ", action=" + action + ", status=" + status + "]";
        }

    }
    
}
