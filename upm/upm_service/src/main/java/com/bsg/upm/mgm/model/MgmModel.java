package com.bsg.upm.mgm.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */
public class MgmModel {

    public static class Task {
        @JSONField(name = "id")
        private String id;

        @JSONField(name = "action")
        private String action;

        @JSONField(name = "status")
        private String status;
        
        @JSONField(name = "user")
        private String user;

        public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		/**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id
         *            the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the action
         */
        public String getAction() {
            return action;
        }

        /**
         * @param action
         *            the action to set
         */
        public void setAction(String action) {
            this.action = action;
        }

        /**
         * @return the status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status
         *            the status to set
         */
        public void setStatus(String status) {
            this.status = status;
        }

        @Override
		public String toString() {
			return "Task [id=" + id + ", action=" + action + ", status=" + status + ", user=" + user + "]";
		}

    }

    public static class Info {
        @JSONField(name = "user")
        private String user;

        @JSONField(name = "timestamp")
        private String timestamp;

        /**
         * 获取 user
         * 
         * @return user
         */
        public String getUser() {
            return user;
        }

        /**
         * 设置user
         * 
         * @param user
         */
        public void setUser(String user) {
            this.user = user;
        }

        /**
         * 获取 timestamp
         * 
         * @return timestamp
         */
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * 设置timestamp
         * 
         * @param timestamp
         */
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Info [user=" + user + ", timestamp=" + timestamp + "]";
        }
    }
        
        public static class Site {
            @JSONField(name = "id")
            private String id;

            @JSONField(name = "name")
            private String name;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			@Override
			public String toString() {
				return "Site [id=" + id + ", name=" + name + "]";
			}


    }
        
}
