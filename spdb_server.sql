/*
Navicat MySQL Data Transfer

Source Server         : 浦发测试环境
Source Server Version : 50725
Source Host           : 192.168.22.40:3306
Source Database       : spdb_server

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-22 11:51:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_app
-- ----------------------------
DROP TABLE IF EXISTS `tbl_app`;
CREATE TABLE `tbl_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `pid` int(11) NOT NULL COMMENT '父权限id',
  `sequence` int(11) NOT NULL COMMENT '显示顺序',
  `name` varchar(22) NOT NULL COMMENT '权限名称',
  `type` varchar(32) NOT NULL COMMENT '权限类型',
  `code` varchar(255) NOT NULL COMMENT '权限编码',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_app
-- ----------------------------
INSERT INTO `tbl_app` VALUES ('1', '0', '2', '资源管理', 'menu', 'resource', 'fa fa-lg fa-fw fa-cloud');
INSERT INTO `tbl_app` VALUES ('15', '1', '6', '镜像管理', 'page', '/app/resource/softwareImage/list', 'fa fa_icon_css fa-puzzle-piece fa-1');
INSERT INTO `tbl_app` VALUES ('16', '15', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('19', '1', '3', '网段管理', 'page', '/app/resource/networking/list', 'fa fa_icon_css fa-sitemap fa-1');
INSERT INTO `tbl_app` VALUES ('21', '19', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('23', '19', '3', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('24', '19', '4', '停用', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('26', '19', '2', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('28', '19', '5', '注销', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('34', '1', '2', '集群管理', 'page', '/app/resource/cluster/list', 'fa fa_icon_css fa-clone fa-1');
INSERT INTO `tbl_app` VALUES ('36', '34', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('37', '34', '3', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('38', '34', '4', '停用', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('39', '34', '2', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('41', '34', '6', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('42', '15', '2', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('43', '15', '3', '停用', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('44', '15', '4', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('47', '1', '5', '主机管理', 'page', '/app/resource/host/list', 'fa fa_icon_css fa-server fa-1');
INSERT INTO `tbl_app` VALUES ('50', '47', '3', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('51', '47', '1', '入库', 'button', 'in', null);
INSERT INTO `tbl_app` VALUES ('52', '47', '4', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('53', '47', '5', '停用', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('54', '47', '7', '出库', 'button', 'out', null);
INSERT INTO `tbl_app` VALUES ('59', '1', '4', '存储管理', 'page', '/app/resource/san/list', 'fa fa_icon_css fa-folder-open-o fa-1');
INSERT INTO `tbl_app` VALUES ('60', '59', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('61', '59', '4', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('62', '59', '3', '停用', 'button', 'stop', '');
INSERT INTO `tbl_app` VALUES ('63', '59', '5', '资源池', 'button', 'rgmanage', null);
INSERT INTO `tbl_app` VALUES ('64', '63', '1', '新增', 'subButton', 'add', null);
INSERT INTO `tbl_app` VALUES ('65', '63', '4', '删除', 'subButton', 'delete', null);
INSERT INTO `tbl_app` VALUES ('66', '63', '2', '启用', 'subButton', 'start', null);
INSERT INTO `tbl_app` VALUES ('67', '63', '3', '停用', 'subButton', 'stop', null);
INSERT INTO `tbl_app` VALUES ('68', '0', '3', '申请管理', 'menu', 'apply', 'fa fa-lg fa-fw fa-list');
INSERT INTO `tbl_app` VALUES ('69', '68', '1', 'MySQL', 'page', '/app/workorder/mysql/add', null);
INSERT INTO `tbl_app` VALUES ('71', '68', '5', '申请单审批', 'page', '/app/workorder/mysql/list', 'fa fa_icon_css fa-calendar-check-o fa-1');
INSERT INTO `tbl_app` VALUES ('72', '71', '1', '审批', 'button', 'examine', null);
INSERT INTO `tbl_app` VALUES ('73', '71', '2', '执行', 'button', 'execute', null);
INSERT INTO `tbl_app` VALUES ('74', '71', '3', '详情', 'button', 'detail', null);
INSERT INTO `tbl_app` VALUES ('75', '71', '4', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('76', '71', '5', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('79', '0', '4', '服务管理', 'menu', 'service', 'fa fa-lg fa-fw fa-cubes');
INSERT INTO `tbl_app` VALUES ('111', '79', '2', 'MySQL管理', 'page', '/app/service/mysql/list', null);
INSERT INTO `tbl_app` VALUES ('112', '111', '1', '启动', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('113', '111', '2', '停止', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('114', '111', '3', '备份', 'button', 'backup', null);
INSERT INTO `tbl_app` VALUES ('115', '111', '4', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('116', '111', '5', '扩容', 'button', 'change', null);
INSERT INTO `tbl_app` VALUES ('117', '111', '6', '升级', 'button', 'changeVersion', null);
INSERT INTO `tbl_app` VALUES ('118', '111', '7', '移交', 'button', 'transfer', null);
INSERT INTO `tbl_app` VALUES ('119', '111', '8', '导出', 'button', 'exportUpsql', null);
INSERT INTO `tbl_app` VALUES ('120', '111', '9', '管理', 'button', 'manageUpsql', null);
INSERT INTO `tbl_app` VALUES ('121', '220', '1', '启动', 'sSubButton', 'startUpsql', null);
INSERT INTO `tbl_app` VALUES ('122', '220', '2', '停止', 'sSubButton', 'stopUpsql', null);
INSERT INTO `tbl_app` VALUES ('123', '220', '3', '备份', 'sSubButton', 'backupUpsql', null);
INSERT INTO `tbl_app` VALUES ('124', '220', '4', '还原', 'sSubButton', 'restoreUpsql', null);
INSERT INTO `tbl_app` VALUES ('125', '220', '7', '迁移', 'sSubButton', 'migrateUpsql', null);
INSERT INTO `tbl_app` VALUES ('126', '220', '6', '重建', 'sSubButton', 'rebuildUpsql', null);
INSERT INTO `tbl_app` VALUES ('127', '220', '8', '隔离', 'sSubButton', 'isolateUpsql', null);
INSERT INTO `tbl_app` VALUES ('128', '220', '9', '回切', 'sSubButton', 'recoverUpsql', null);
INSERT INTO `tbl_app` VALUES ('129', '220', '10', '升级', 'sSubButton', 'changVersionUpsql', null);
INSERT INTO `tbl_app` VALUES ('130', '220', '5', '监控', 'sSubButton', 'monitorUpsql', null);
INSERT INTO `tbl_app` VALUES ('131', '220', '11', '慢日志', 'sSubButton', 'slowlogUpsql', null);
INSERT INTO `tbl_app` VALUES ('132', '221', '1', '启动', 'sSubButton', 'startUpproxy', null);
INSERT INTO `tbl_app` VALUES ('133', '221', '2', '停止', 'sSubButton', 'stopUpproxy', null);
INSERT INTO `tbl_app` VALUES ('134', '221', '3', '重建', 'sSubButton', 'rebuildUpproxy', null);
INSERT INTO `tbl_app` VALUES ('135', '221', '4', '升级', 'sSubButton', 'changeVersionUpproxy', null);
INSERT INTO `tbl_app` VALUES ('136', '221', '5', '监控', 'sSubButton', 'monitorUpproxy', null);
INSERT INTO `tbl_app` VALUES ('137', '222', '1', '启动', 'sSubButton', 'startSwitchManager', null);
INSERT INTO `tbl_app` VALUES ('138', '222', '2', '停止', 'sSubButton', 'stopSwitchManager', null);
INSERT INTO `tbl_app` VALUES ('139', '222', '3', '重建', 'sSubButton', 'rebuildSwitchManager', null);
INSERT INTO `tbl_app` VALUES ('140', '222', '4', '升级', 'sSubButton', 'changeVersionSwitchManager', null);
INSERT INTO `tbl_app` VALUES ('141', '222', '5', '监控', 'sSubButton', 'monitorSwitchManager', null);
INSERT INTO `tbl_app` VALUES ('142', '223', '1', '参数编辑', 'sSubButton', 'updateParamUpsql', null);
INSERT INTO `tbl_app` VALUES ('143', '225', '1', '新增', 'sSubButton', 'addDatabase', null);
INSERT INTO `tbl_app` VALUES ('144', '225', '2', '删除', 'sSubButton', 'deleteDatabase', null);
INSERT INTO `tbl_app` VALUES ('145', '226', '1', '新增', 'sSubButton', 'addUser', null);
INSERT INTO `tbl_app` VALUES ('146', '226', '2', '编辑', 'sSubButton', 'updateUser', null);
INSERT INTO `tbl_app` VALUES ('147', '226', '3', '删除', 'sSubButton', 'deleteUser', null);
INSERT INTO `tbl_app` VALUES ('148', '226', '4', '重置密码', 'sSubButton', 'resetPasswordUser', null);
INSERT INTO `tbl_app` VALUES ('149', '227', '1', '新增', 'sSubButton', 'addDirectUser', null);
INSERT INTO `tbl_app` VALUES ('150', '227', '3', '删除', 'sSubButton', 'deleteDirectUser', null);
INSERT INTO `tbl_app` VALUES ('151', '228', '1', '新增', 'sSubButton', 'addBackupStrategy', null);
INSERT INTO `tbl_app` VALUES ('152', '228', '2', '编辑', 'sSubButton', 'updateBackupStrategy', null);
INSERT INTO `tbl_app` VALUES ('153', '228', '3', '删除', 'sSubButton', 'deleteBackupStrategy', null);
INSERT INTO `tbl_app` VALUES ('154', '228', '4', '启用', 'sSubButton', 'startBackupStrategy', null);
INSERT INTO `tbl_app` VALUES ('155', '228', '5', '停用', 'sSubButton', 'stopBackupStrategy', null);
INSERT INTO `tbl_app` VALUES ('156', '229', '1', '删除', 'sSubButton', 'deleteBackup', null);
INSERT INTO `tbl_app` VALUES ('164', '0', '6', '系统维护', 'menu', 'system', 'fa fa-lg fa-fw fa-cogs');
INSERT INTO `tbl_app` VALUES ('165', '164', '5', '用户管理', 'page', '/app/system/user/manager', 'fa fa_icon_css fa-user fa-1');
INSERT INTO `tbl_app` VALUES ('166', '165', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('167', '165', '2', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('168', '165', '3', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('169', '165', '4', '停用', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('170', '164', '3', '角色管理', 'page', '/app/system/role/list', 'fa fa_icon_css fa-briefcase fa-1');
INSERT INTO `tbl_app` VALUES ('171', '170', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('172', '170', '2', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('173', '170', '3', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('174', '164', '4', '组别管理', 'page', '/app/system/group/list', 'fa fa_icon_css fa-users fa-1');
INSERT INTO `tbl_app` VALUES ('175', '174', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('176', '174', '2', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('177', '174', '3', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('179', '178', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('186', '164', '6', '字典管理', 'page', '/app/system/dicts/dictionary', 'fa fa_icon_css fa-book fa-1');
INSERT INTO `tbl_app` VALUES ('187', '186', '1', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('193', '192', '1', '主机启用', 'subButton', 'startHost', null);
INSERT INTO `tbl_app` VALUES ('194', '192', '2', '主机停用', 'subButton', 'stopHost', null);
INSERT INTO `tbl_app` VALUES ('195', '192', '3', '服务启用', 'subButton', 'startService', null);
INSERT INTO `tbl_app` VALUES ('196', '192', '4', '服务停用', 'subButton', 'stopService', null);
INSERT INTO `tbl_app` VALUES ('197', '164', '2', '规模管理', 'page', '/app/workorder/scale/list', 'fa fa_icon_css fa-align-justify fa-1');
INSERT INTO `tbl_app` VALUES ('198', '197', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('199', '197', '2', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('200', '197', '5', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('206', '164', '1', '操作记录', 'page', '/app/system/event/opelatelog', 'fa fa_icon_css fa-file-text-o fa-1');
INSERT INTO `tbl_app` VALUES ('207', '197', '3', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('208', '197', '4', '停用', 'button', 'stop', null);
INSERT INTO `tbl_app` VALUES ('211', '165', '5', '修改密码', 'button', 'updatePwd', null);
INSERT INTO `tbl_app` VALUES ('219', '120', '1', '部署架构', 'subButton', 'graphTab', null);
INSERT INTO `tbl_app` VALUES ('220', '120', '2', '数据库', 'subButton', 'mysqlTab', null);
INSERT INTO `tbl_app` VALUES ('221', '120', '3', '代理', 'subButton', 'upproxyTab', null);
INSERT INTO `tbl_app` VALUES ('222', '120', '4', '高可用', 'subButton', 'swmTab', null);
INSERT INTO `tbl_app` VALUES ('223', '120', '5', '参数管理', 'subButton', 'paramTab', null);
INSERT INTO `tbl_app` VALUES ('224', '120', '6', '分库策略', 'subButton', 'cfgContentsTab', null);
INSERT INTO `tbl_app` VALUES ('225', '120', '7', '库管理', 'subButton', 'databaseTab', null);
INSERT INTO `tbl_app` VALUES ('226', '120', '8', '用户管理', 'subButton', 'userTab', null);
INSERT INTO `tbl_app` VALUES ('227', '120', '9', '直连用户', 'subButton', 'userDirectTab', null);
INSERT INTO `tbl_app` VALUES ('228', '120', '10', '备份策略', 'subButton', 'backupStrategyTab', null);
INSERT INTO `tbl_app` VALUES ('229', '120', '11', '备份列表', 'subButton', 'backupListTab', null);
INSERT INTO `tbl_app` VALUES ('230', '120', '12', '监控管理', 'subButton', 'monitorTab', null);
INSERT INTO `tbl_app` VALUES ('233', '15', '8', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('234', '19', '6', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('235', '29', '5', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('236', '34', '7', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('237', '47', '6', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('238', '59', '6', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('239', '71', '8', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('240', '80', '9', '刷新', 'button', 'refresh', '');
INSERT INTO `tbl_app` VALUES ('241', '214', '10', '刷新', 'sSubButton', 'refreshUpredis', '');
INSERT INTO `tbl_app` VALUES ('242', '215', '7', '刷新', 'sSubButton', 'refreshUrproxy', null);
INSERT INTO `tbl_app` VALUES ('243', '216', '7', '刷新', 'sSubButton', 'refreshSentinel', null);
INSERT INTO `tbl_app` VALUES ('244', '111', '10', '刷新', 'button', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('245', '220', '12', '刷新', 'sSubButton', 'refreshUpsql', null);
INSERT INTO `tbl_app` VALUES ('246', '221', '6', '刷新', 'sSubButton', 'refreshUpproxy', null);
INSERT INTO `tbl_app` VALUES ('247', '222', '6', '刷新', 'sSubButton', 'refreshSwitchManager', null);
INSERT INTO `tbl_app` VALUES ('249', '165', '5', '刷新', 'button', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('250', '170', '4', '刷新', 'button', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('251', '174', '4', '刷新', 'button', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('254', '197', '6', '刷新', 'button', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('257', '63', '5', '刷新', 'subButton', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('260', '174', '4', '人员配置', 'button', 'setting', null);
INSERT INTO `tbl_app` VALUES ('261', '260', '2', '保存', 'subButton', 'save', null);
INSERT INTO `tbl_app` VALUES ('262', '260', '1', '刷新', 'subButton', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('264', '217', '2', '刷新', 'sSubButton', 'refreshParamRedis', null);
INSERT INTO `tbl_app` VALUES ('265', '223', '2', '刷新', 'sSubButton', 'refreshParamUpsql', '');
INSERT INTO `tbl_app` VALUES ('266', '225', '3', '刷新', 'sSubButton', 'refreshDatabase', null);
INSERT INTO `tbl_app` VALUES ('267', '226', '5', '刷新', 'sSubButton', 'refreshUser', null);
INSERT INTO `tbl_app` VALUES ('268', '227', '3', '刷新', 'sSubButton', 'refreshDirectUser', null);
INSERT INTO `tbl_app` VALUES ('269', '228', '6', '刷新', 'sSubButton', 'refreshBackupStrategy', null);
INSERT INTO `tbl_app` VALUES ('270', '229', '2', '刷新', 'sSubButton', 'refreshBackup', '');
INSERT INTO `tbl_app` VALUES ('272', '59', '2', '启用', 'button', 'start', null);
INSERT INTO `tbl_app` VALUES ('275', '1', '1', '站点管理', 'page', '/app/site/list', 'fa fa_icon_css fa-sitemap fa-1');
INSERT INTO `tbl_app` VALUES ('276', '275', '1', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('277', '275', '3', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('278', '275', '4', '刷新', 'button', 'refresh', null);
INSERT INTO `tbl_app` VALUES ('279', '227', '4', '重置密码', 'sSubButton', 'resetPasswordUser', null);
INSERT INTO `tbl_app` VALUES ('280', '220', '13', '角色变更', 'sSubButton', 'roleChangeUpsql', null);
INSERT INTO `tbl_app` VALUES ('281', '47', '2', '监控注册', 'button', 'monitorRegister', null);
INSERT INTO `tbl_app` VALUES ('282', '47', '6', '监控注销', 'button', 'monitorCancel', null);
INSERT INTO `tbl_app` VALUES ('283', '111', '10', '监控注册', 'button', 'monitorRegister', null);
INSERT INTO `tbl_app` VALUES ('284', '227', '2', '编辑', 'sSubButton', 'updateDirectUser', null);
INSERT INTO `tbl_app` VALUES ('285', '220', '12', '设置为从', 'sSubButton', 'roleChangeSlaveUpsql', null);
INSERT INTO `tbl_app` VALUES ('286', '164', '7', '系统名称管理', 'page', '/app/workorder/sys/list', 'fa fa_icon_css fa-align-justify fa-1');
INSERT INTO `tbl_app` VALUES ('287', '286', '2', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('288', '286', '3', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('289', '286', '4', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('290', '164', '8', '子系统名称管理', 'page', '/app/workorder/subsys/list', 'fa fa_icon_css fa-align-left fa-1');
INSERT INTO `tbl_app` VALUES ('291', '290', '2', '新增', 'button', 'add', null);
INSERT INTO `tbl_app` VALUES ('292', '290', '3', '编辑', 'button', 'update', null);
INSERT INTO `tbl_app` VALUES ('293', '290', '4', '删除', 'button', 'delete', null);
INSERT INTO `tbl_app` VALUES ('294', '275', '2', '编辑', 'button', 'update', null);

-- ----------------------------
-- Table structure for tbl_business_subsystem
-- ----------------------------
DROP TABLE IF EXISTS `tbl_business_subsystem`;
CREATE TABLE `tbl_business_subsystem` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `business_system_id` varchar(64) NOT NULL,
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_business_subsystem
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_business_system
-- ----------------------------
DROP TABLE IF EXISTS `tbl_business_system`;
CREATE TABLE `tbl_business_system` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `owner` varchar(255) NOT NULL,
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_business_system
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_dict
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dict`;
CREATE TABLE `tbl_dict` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `dict_type_code` varchar(32) NOT NULL COMMENT '字典类型编码',
  `code` varchar(32) NOT NULL COMMENT '字典编码',
  `name` varchar(22) NOT NULL COMMENT '名称',
  `sequence` int(11) unsigned NOT NULL COMMENT '显示顺序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of tbl_dict
-- ----------------------------
INSERT INTO `tbl_dict` VALUES ('1', 'image_type', 'mysql', 'MySQL', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('10', 'networking_topology', 'topology2', '拓扑二', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('100', 'arch_cnt', '1', '1', '1', '2019-08-13 10:53:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('101', 'arch_cnt', '2', '2', '2', '2019-08-13 10:53:15', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('102', 'arch_cnt', '3', '3', '3', '2019-08-13 10:53:29', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('103', 'arch_cnt', '4', '4', '4', '2019-08-13 10:53:44', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('108', 'task_action', 'app-unit-state-edit', '单元启动/停止', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('109', 'task_action', 'app-delete', '删除', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('11', 'networking_topology', 'topology3', '拓扑三', '3', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('110', 'task_action', 'image-delete', '删除', '1', '2019-07-31 13:15:04', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('111', 'task_action', 'remote-storage-pool-delete', '删除', '1', '2019-07-31 13:12:52', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('112', 'backup_type', 'full', '全备', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('113', 'task_action', 'app-image-edit', '升级', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('114', 'task_action', 'app-resource-edit', '扩容', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('115', 'replication_role', 'master', '主', '1', '2019-08-16 14:48:57', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('116', 'replication_role', 'slave', '从', '1', '2019-08-16 14:49:29', '', null, null);
INSERT INTO `tbl_dict` VALUES ('117', 'task_action', 'app-unit-restore', '单元还原', '1', '2019-08-16 14:49:29', '', null, null);
INSERT INTO `tbl_dict` VALUES ('12', 'networking_topology', 'topology4', '拓扑四', '4', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('120', 'order_status', 'overtime', '执行超时', '7', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('121', 'rebuild_strategy', 'auto', '自动', '1', '2019-09-11 14:32:26', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('122', 'rebuild_strategy', 'manual', '手动', '2', '2019-09-11 14:32:26', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('13', 'remote_storage_type', 'fc', 'fc', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('14', 'remote_storage_type', 'iscsi', 'iscsi', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('15', 'remote_storage_pool_type', 'data', 'data', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('16', 'remote_storage_pool_type', 'backup', 'backup', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('17', 'storage_type', 'hdd', 'HDD', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('18', 'storage_type', 'ssd', 'SSD', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('19', 'data_scope', 'oneself', '仅本人数据', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('2', 'region', 'BJ', '北京', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('20', 'data_scope', 'group', '本组数据', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('21', 'data_scope', 'all', '所有数据', '3', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('22', 'auth_type', 'native', '本地认证', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('23', 'auth_type', 'sso', 'SSO认证', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('24', 'performance', 'high', '高性能', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('25', 'performance', 'medium', '中等性能', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('26', 'task_status', 'not_running', '未开始', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('27', 'task_status', 'running', '中...', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('28', 'task_status', 'success', '成功', '3', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('29', 'task_status', 'failed', '失败', '4', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('3', 'region', 'SH', '上海', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('30', 'task_status', 'canceled', '取消', '5', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('31', 'task_action', 'host-add', '入库', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('32', 'task_action', 'host-delete', '出库', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('33', 'order_create_type', 'new', '创建', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('34', 'order_status', 'unapproved', '未审批', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('35', 'order_status', 'approved', '审批通过', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('36', 'order_status', 'unapprove', '审批拒绝', '3', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('37', 'order_status', 'executing', '执行中', '4', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('38', 'order_status', 'success', '执行成功', '5', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('39', 'order_status', 'failed', '执行失败', '6', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('4', 'site_type', 'kubernetes', 'kubernetes', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('40', 'dir_type', 'host', '本地存储', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('41', 'dir_type', 'remote', '外置存储', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('42', 'character_sets', 'utf8mb4', 'UTF-8-mb4', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('43', 'character_sets', 'gbk', 'GBK', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('5', 'area', 'area1', '区域一', '1', '2019-01-01 00:00:00', 'admin', '2019-11-05 15:03:49', 'admin');
INSERT INTO `tbl_dict` VALUES ('6', 'area', 'area2', '区域二', '2', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('7', 'area', 'area3', '区域三', '3', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('75', 'ntp_server', '192.168.2.254', '192.168.2.254', '1', '2019-07-30 10:19:52', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('77', 'task_action', 'host-edit', '编辑', '0', '2019-08-01 15:06:45', '', null, null);
INSERT INTO `tbl_dict` VALUES ('78', 'task_action', 'remote-storage-add', '新增', '1', '2019-07-31 13:08:40', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('79', 'task_action', 'remote-storage-edit', '编辑', '2', '2019-07-31 13:10:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('8', 'area', 'area4', '区域四', '4', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('80', 'task_action', 'remote-storage-delete', '删除', '3', '2019-07-31 13:10:51', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('81', 'task_action', 'remote-storage-pool-add', '新增', '1', '2019-07-31 13:12:52', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('82', 'task_action', 'image-add', '新增', '1', '2019-07-31 13:15:04', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('83', 'order_create_type', 'delete', '删除', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('84', 'order_create_type', 'scale_up', '扩容', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('85', 'order_create_type', 'image_update', '版本变更', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('86', 'order_create_type', 'serv_scale_out', '分片扩展', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('87', 'order_create_type', 'unit_scale_out', '节点扩展', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('88', 'task_action', 'app-add', '创建', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('89', 'task_action', 'unit create', '创建', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('9', 'networking_topology', 'topology1', '拓扑一', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('90', 'task_action', 'app-state-edit', '启动/停止', '1', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('91', 'enabled', 'true', '启用', '1', '2019-08-08 14:33:27', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('92', 'enabled', 'false', '停用', '2', '2019-08-08 14:33:52', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('93', 'order_status', 'failure', '执行失败', '6', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('94', 'order_status', 'running', '执行中', '4', '2019-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('95', 'arch_type', 'single', '单节点', '1', '2019-08-13 10:50:47', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('96', 'arch_type', 'replication_semi_sync', '主备(半同步)', '2', '2019-08-13 10:51:17', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('97', 'arch_type', 'replication_async', '主备(异步)', '3', '2019-08-13 10:51:48', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('98', 'arch_cnt', '5', '5', '5', '2019-08-13 10:52:19', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('99', 'arch_type', 'clone', '克隆', '4', '2020-08-13 10:51:17', 'admin', null, null);

INSERT INTO `tbl_dict` VALUES ('118', 'replication_role', 'leader', '主', '1', '2020-08-16 14:48:57', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('119', 'replication_role', 'follower', '备', '1', '2020-08-16 14:49:29', 'admin', null, null);


INSERT INTO `tbl_dict` VALUES ('124', 'image_type', 'cmha', 'CMHA', '2', '2021-03-02 00:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('125', 'image_type', 'proxysql', 'PROXYSQL', '3', '2021-03-02 00:00:00', 'admin', null, null);

INSERT INTO `tbl_dict` VALUES ('126', 'backup_storage_type', 'nfs', 'NFS', '2', '2021-05-31 16:00:00', 'admin', null, null);
INSERT INTO `tbl_dict` VALUES ('127', 'backup_storage_type', 'S3', '对象存储S3', '2', '2021-05-31 16:00:00', 'admin', null, null);



-- ----------------------------
-- Table structure for tbl_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dict_type`;
CREATE TABLE `tbl_dict_type` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '类型编码',
  `name` varchar(24) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';

-- ----------------------------
-- Records of tbl_dict_type
-- ----------------------------
INSERT INTO `tbl_dict_type` VALUES ('1', 'image_type', '镜像类型');
INSERT INTO `tbl_dict_type` VALUES ('10', 'auth_type', '认证方式');
INSERT INTO `tbl_dict_type` VALUES ('11', 'performance', '性能等级');
INSERT INTO `tbl_dict_type` VALUES ('12', 'task_action', '任务动作');
INSERT INTO `tbl_dict_type` VALUES ('13', 'task_status', '任务状态');
INSERT INTO `tbl_dict_type` VALUES ('14', 'order_create_type', '工单创建类型');
INSERT INTO `tbl_dict_type` VALUES ('15', 'order_status', '工单状态');
INSERT INTO `tbl_dict_type` VALUES ('16', 'dir_type', '目录类型');
INSERT INTO `tbl_dict_type` VALUES ('17', 'character_sets', '字符集');
INSERT INTO `tbl_dict_type` VALUES ('18', 'ntp_server', 'ntp服务器地址');
INSERT INTO `tbl_dict_type` VALUES ('19', 'enabled', '是否启用');
INSERT INTO `tbl_dict_type` VALUES ('2', 'region', '地域');
INSERT INTO `tbl_dict_type` VALUES ('20', 'arch_type', '架构类型');
INSERT INTO `tbl_dict_type` VALUES ('21', 'arch_cnt', '架构单元数量');
INSERT INTO `tbl_dict_type` VALUES ('22', 'replication_role', 'replication复制角色');
INSERT INTO `tbl_dict_type` VALUES ('3', 'site_type', '站点类型');
INSERT INTO `tbl_dict_type` VALUES ('4', 'area', '区域');
INSERT INTO `tbl_dict_type` VALUES ('5', 'networking_topology', '网络拓扑');
INSERT INTO `tbl_dict_type` VALUES ('6', 'remote_storage_type', '外置存储类型');
INSERT INTO `tbl_dict_type` VALUES ('7', 'remote_storage_pool_type', '外置存储池类型');
INSERT INTO `tbl_dict_type` VALUES ('9', 'data_scope', '数据范围');

-- ----------------------------
-- Table structure for tbl_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_group`;
CREATE TABLE `tbl_group` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(16) NOT NULL COMMENT '组名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='组别表';

-- ----------------------------
-- Records of tbl_group
-- ----------------------------
INSERT INTO `tbl_group` VALUES ('1', '管理组', null, '2018-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_group` VALUES ('2', '租户组', null, '2018-01-01 00:00:00', 'admin', null, null);

-- ----------------------------
-- Table structure for tbl_group_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_group_user`;
CREATE TABLE `tbl_group_user` (
  `id` varchar(64) NOT NULL COMMENT '自增主键',
  `group_id` varchar(64) NOT NULL COMMENT '关联的组唯一标识符',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_group_user
-- ----------------------------
INSERT INTO `tbl_group_user` VALUES ('1', '1', 'admin');

-- ----------------------------
-- Table structure for tbl_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operate_log`;
CREATE TABLE `tbl_operate_log` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `obj_type` varchar(32) NOT NULL COMMENT '操作模块',
  `action` varchar(32) NOT NULL COMMENT '操作动作',
  `obj_name` varchar(255) NOT NULL COMMENT '操作对象名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `uptime` int(11) unsigned NOT NULL COMMENT '操作时间',
  `is_success` tinyint(1) unsigned NOT NULL COMMENT '操作是否成功 1：成功；0：失败',
  `error_msg` text COMMENT '错误信息',
  `site_id` varchar(64) DEFAULT NULL COMMENT '站点编码',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UNIQUE_INDEX_id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

-- ----------------------------
-- Records of tbl_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
  `id` varchar(64) NOT NULL COMMENT '主键，子订单ID',
  `order_group_id` varchar(32) NOT NULL COMMENT '关联的工单组唯一标识符',
  `type` varchar(32) NOT NULL COMMENT '工单组类型',
  `major_version` int(11) unsigned  COMMENT '镜像主版本',
  `minor_version` int(11) unsigned  COMMENT '镜像次版本',
  `patch_version` int(11) unsigned  COMMENT '镜像修订版本',
  `build_version` int(11) unsigned  COMMENT '镜像编译版本',
  `arch_type_code` varchar(32) NOT NULL COMMENT '架构模式   single：单节点；replication_semi_sync：主备(半同步)；replication_async：主备(异步)',
  `unit_cnt` int(11) NOT NULL COMMENT '架构单元数量',
  `cpu_cnt` int(11) unsigned NOT NULL COMMENT 'CPU数量',
  `mem_size` bigint(20) NOT NULL COMMENT '内存容量',
  `data_dir_size` bigint(20) NOT NULL COMMENT '数据目录大小',
  `data_dir_type` varchar(32) NOT NULL COMMENT '数据目录类型',
  `data_dir_performance` varchar(32) NOT NULL COMMENT '数据目录性能等级   low：低；medium：中等；high：高',
  `log_dir_size` bigint(20) NOT NULL COMMENT '日志目录大小',
  `log_dir_type` varchar(32) NOT NULL COMMENT '日志目录类型',
  `log_dir_performance` varchar(32) NOT NULL COMMENT '日志目录性能等级 low：低；medium：中等；high：高',
  `network_bandwidth` int(11) unsigned DEFAULT NULL COMMENT '带宽',
  `host_ha` tinyint(1) NOT NULL COMMENT '主机是否高可用  是：1；否：0',
  `network_ha` tinyint(1) NOT NULL COMMENT '网络是否高可用  是：1；否：0',
  `cluster_ha` tinyint(1) NOT NULL COMMENT '集群是否高可用  是：1；否：0',
  `storage_ha` tinyint(1) NOT NULL COMMENT '存储是否高可用  是：1；否：0',
  `cfgs` longtext NOT NULL COMMENT '配置',
  `port` int(11) NOT NULL COMMENT '端口',
  `cnt` int(11) DEFAULT NULL COMMENT '分片数量',
  `pre_major_version` int(11) unsigned DEFAULT NULL COMMENT '变更前镜像主版本',
  `pre_minor_version` int(11) unsigned DEFAULT NULL COMMENT '变更前镜像次版本',
  `pre_patch_version` int(11) unsigned DEFAULT NULL COMMENT '变更前镜像修订版本',
  `pre_build_version` int(11) unsigned DEFAULT NULL COMMENT '变更前镜像编译版本',
  `pre_arch_type_code` varchar(32) DEFAULT NULL COMMENT '变更前架构模式',
  `pre_unit_cnt` int(11) DEFAULT NULL COMMENT '变更前架构单元数量',
  `pre_cpu_cnt` int(11) unsigned DEFAULT NULL COMMENT '变更前CPU数量',
  `pre_mem_size` bigint(20) DEFAULT NULL COMMENT '变更前内存容量',
  `pre_data_dir_size` bigint(20) DEFAULT NULL COMMENT '变更前数据目录大小',
  `pre_log_dir_size` bigint(20) DEFAULT NULL COMMENT '变更前日志目录大小',
  `pre_network_bandwidth` int(11) unsigned DEFAULT NULL COMMENT '变更前带宽',
  `pre_cnt` int(11) DEFAULT NULL COMMENT '变更前分片数量',
  `schedule` varchar(30) DEFAULT NULL COMMENT '备份时间',
  `hamode` varchar(10) DEFAULT NULL COMMENT '高可用方式',
  `hacontainer` varchar(10) DEFAULT NULL COMMENT '是否容器化漂移',
  `architecture` varchar(10) DEFAULT NULL COMMENT '硬件架构,X86、ARM',
  `imageId` varchar(60) DEFAULT NULL COMMENT 'mysql镜像id',
  `cmhaImageId` varchar(60) DEFAULT NULL COMMENT 'Cmha镜像id',
  `proxyImageId` varchar(60) DEFAULT NULL COMMENT 'proxy镜像id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_order_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_group`;
CREATE TABLE `tbl_order_group` (
  `id` varchar(64) NOT NULL COMMENT '自增主键 工单组ID',
  `type` varchar(32) NOT NULL COMMENT '工单组类型  ',
  `site_id` varchar(64) NOT NULL COMMENT '关联的站点编码唯一标识符',
  `area_code` varchar(32) NOT NULL COMMENT '关联的区域代码唯一标识符',
  `name` varchar(64) NOT NULL COMMENT '服务名',
  `action_type` varchar(16) NOT NULL COMMENT '创建类型  new：创建；delete：删除；scale_up：扩容；image_update：版本升级；unapproved：未审批；approved：审批通过；unapprove：审批拒绝',
  `status` varchar(16) NOT NULL COMMENT '状态  success：执行成功；failure：执行失败；running：执行中',
  `msg` varchar(255) NOT NULL COMMENT '审批信息',
  `owner` varchar(32) NOT NULL COMMENT '所属者',
  `extend_json_str` longtext COMMENT '扩展',
  `relate_app_id` varchar(64) DEFAULT NULL COMMENT '关联的服务唯一标识符',
  `relate_task_id` varchar(64) DEFAULT NULL COMMENT '关联的任务唯一标识符',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志位 1：已删除 0：未删除',
  `business_subsystem_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_order_group
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_privilege
-- ----------------------------
DROP TABLE IF EXISTS `tbl_privilege`;
CREATE TABLE `tbl_privilege` (
  `code` varchar(64) NOT NULL COMMENT '权限编码',
  `description` varchar(255) NOT NULL COMMENT '权限描述',
  `is_enabled` tinyint(1) unsigned NOT NULL COMMENT '是否可用 1：可用；0：不可用',
  `is_global` tinyint(1) unsigned NOT NULL COMMENT '是否是全局权限  1：是；0：否',
  `sequence` int(11) NOT NULL COMMENT '显示顺序',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_privilege
-- ----------------------------
INSERT INTO `tbl_privilege` VALUES ('ALTER', 'ALTER', '1', '0', '50');
INSERT INTO `tbl_privilege` VALUES ('ALTER ROUTINE', 'ALTER ROUTINE', '1', '0', '110');
INSERT INTO `tbl_privilege` VALUES ('CREATE', 'CREATE', '1', '0', '70');
INSERT INTO `tbl_privilege` VALUES ('CREATE ROUTINE', 'CREATE ROUTINE', '1', '0', '10');
INSERT INTO `tbl_privilege` VALUES ('CREATE TEMPORARY TABLES', 'CREATE TEMPORARY TABLES', '1', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('CREATE USER', 'CREATE USER', '0', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('CREATE VIEW', 'CREATE VIEW', '1', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('DELETE', 'DELETE', '1', '0', '40');
INSERT INTO `tbl_privilege` VALUES ('DROP', 'DROP', '1', '0', '80');
INSERT INTO `tbl_privilege` VALUES ('EVENT', 'EVENT', '1', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('EXECUTE', 'EXECUTE', '1', '0', '60');
INSERT INTO `tbl_privilege` VALUES ('FILE', 'FILE', '0', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('GRANT OPTION', 'GRANT OPTION', '0', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('INDEX', 'INDEX', '1', '0', '90');
INSERT INTO `tbl_privilege` VALUES ('INSERT', 'INSERT', '1', '0', '30');
INSERT INTO `tbl_privilege` VALUES ('LOCK TABLES', 'LOCK TABLES', '1', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('PROCESS', 'PROCESS', '1', '1', '990');
INSERT INTO `tbl_privilege` VALUES ('REFERENCES', 'REFERENCES', '1', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('REPLICATION CLIENT', 'REPLICATION CLIENT', '1', '1', '990');
INSERT INTO `tbl_privilege` VALUES ('REPLICATION SLAVE', 'REPLICATION SLAVE', '1', '1', '990');
INSERT INTO `tbl_privilege` VALUES ('SELECT', 'SELECT', '1', '0', '10');
INSERT INTO `tbl_privilege` VALUES ('SHOW DATABASES', 'SHOW DATABASES', '0', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('SHOW VIEW', 'SHOW VIEW', '1', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('SHUTDOWN', 'SHUTDOWN', '0', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('SUPER', 'SUPER', '1', '1', '990');
INSERT INTO `tbl_privilege` VALUES ('TRIGGER', 'TRIGGER', '0', '0', '990');
INSERT INTO `tbl_privilege` VALUES ('UPDATE', 'UPDATE', '1', '0', '20');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `id` varchar(64) NOT NULL COMMENT '自增主键',
  `name` varchar(16) NOT NULL COMMENT '角色名称',
  `sequence` int(11) NOT NULL COMMENT '显示顺序',
  `description` varchar(255) NOT NULL COMMENT '角色描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES ('1', '超级管理员', '0', '', '2018-01-01 00:00:00', 'admin', null, null);
INSERT INTO `tbl_role` VALUES ('2', '租户', '0', '', '2018-01-01 00:00:00', 'admin', null, null);

-- ----------------------------
-- Table structure for tbl_role_cfg_app
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_cfg_app`;
CREATE TABLE `tbl_role_cfg_app` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `app_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_role_cfg_app
-- ----------------------------
INSERT INTO `tbl_role_cfg_app` VALUES ('3516', '2', '68');
INSERT INTO `tbl_role_cfg_app` VALUES ('3517', '2', '69');
INSERT INTO `tbl_role_cfg_app` VALUES ('3518', '2', '70');
INSERT INTO `tbl_role_cfg_app` VALUES ('3519', '2', '71');
INSERT INTO `tbl_role_cfg_app` VALUES ('3520', '2', '74');
INSERT INTO `tbl_role_cfg_app` VALUES ('3521', '2', '75');
INSERT INTO `tbl_role_cfg_app` VALUES ('3522', '2', '76');
INSERT INTO `tbl_role_cfg_app` VALUES ('3523', '2', '78');
INSERT INTO `tbl_role_cfg_app` VALUES ('3524', '2', '79');
INSERT INTO `tbl_role_cfg_app` VALUES ('3525', '2', '80');
INSERT INTO `tbl_role_cfg_app` VALUES ('3526', '2', '81');
INSERT INTO `tbl_role_cfg_app` VALUES ('3527', '2', '82');
INSERT INTO `tbl_role_cfg_app` VALUES ('3528', '2', '83');
INSERT INTO `tbl_role_cfg_app` VALUES ('3529', '2', '84');
INSERT INTO `tbl_role_cfg_app` VALUES ('3530', '2', '85');
INSERT INTO `tbl_role_cfg_app` VALUES ('3531', '2', '86');
INSERT INTO `tbl_role_cfg_app` VALUES ('3532', '2', '87');
INSERT INTO `tbl_role_cfg_app` VALUES ('3533', '2', '88');
INSERT INTO `tbl_role_cfg_app` VALUES ('3534', '2', '101');
INSERT INTO `tbl_role_cfg_app` VALUES ('3535', '2', '102');
INSERT INTO `tbl_role_cfg_app` VALUES ('3536', '2', '103');
INSERT INTO `tbl_role_cfg_app` VALUES ('3537', '2', '104');
INSERT INTO `tbl_role_cfg_app` VALUES ('3538', '2', '105');
INSERT INTO `tbl_role_cfg_app` VALUES ('3539', '2', '106');
INSERT INTO `tbl_role_cfg_app` VALUES ('3540', '2', '107');
INSERT INTO `tbl_role_cfg_app` VALUES ('3541', '2', '108');
INSERT INTO `tbl_role_cfg_app` VALUES ('3542', '2', '109');
INSERT INTO `tbl_role_cfg_app` VALUES ('3543', '2', '110');
INSERT INTO `tbl_role_cfg_app` VALUES ('3544', '2', '111');
INSERT INTO `tbl_role_cfg_app` VALUES ('3545', '2', '112');
INSERT INTO `tbl_role_cfg_app` VALUES ('3546', '2', '113');
INSERT INTO `tbl_role_cfg_app` VALUES ('3547', '2', '114');
INSERT INTO `tbl_role_cfg_app` VALUES ('3548', '2', '115');
INSERT INTO `tbl_role_cfg_app` VALUES ('3549', '2', '116');
INSERT INTO `tbl_role_cfg_app` VALUES ('3550', '2', '117');
INSERT INTO `tbl_role_cfg_app` VALUES ('3551', '2', '118');
INSERT INTO `tbl_role_cfg_app` VALUES ('3552', '2', '119');
INSERT INTO `tbl_role_cfg_app` VALUES ('3553', '2', '120');
INSERT INTO `tbl_role_cfg_app` VALUES ('3554', '2', '121');
INSERT INTO `tbl_role_cfg_app` VALUES ('3555', '2', '122');
INSERT INTO `tbl_role_cfg_app` VALUES ('3556', '2', '123');
INSERT INTO `tbl_role_cfg_app` VALUES ('3557', '2', '124');
INSERT INTO `tbl_role_cfg_app` VALUES ('3558', '2', '125');
INSERT INTO `tbl_role_cfg_app` VALUES ('3559', '2', '126');
INSERT INTO `tbl_role_cfg_app` VALUES ('3560', '2', '127');
INSERT INTO `tbl_role_cfg_app` VALUES ('3561', '2', '128');
INSERT INTO `tbl_role_cfg_app` VALUES ('3562', '2', '129');
INSERT INTO `tbl_role_cfg_app` VALUES ('3563', '2', '130');
INSERT INTO `tbl_role_cfg_app` VALUES ('3564', '2', '131');
INSERT INTO `tbl_role_cfg_app` VALUES ('3565', '2', '132');
INSERT INTO `tbl_role_cfg_app` VALUES ('3566', '2', '133');
INSERT INTO `tbl_role_cfg_app` VALUES ('3567', '2', '134');
INSERT INTO `tbl_role_cfg_app` VALUES ('3568', '2', '135');
INSERT INTO `tbl_role_cfg_app` VALUES ('3569', '2', '136');
INSERT INTO `tbl_role_cfg_app` VALUES ('3570', '2', '137');
INSERT INTO `tbl_role_cfg_app` VALUES ('3571', '2', '138');
INSERT INTO `tbl_role_cfg_app` VALUES ('3572', '2', '139');
INSERT INTO `tbl_role_cfg_app` VALUES ('3573', '2', '140');
INSERT INTO `tbl_role_cfg_app` VALUES ('3574', '2', '141');
INSERT INTO `tbl_role_cfg_app` VALUES ('3575', '2', '142');
INSERT INTO `tbl_role_cfg_app` VALUES ('3576', '2', '143');
INSERT INTO `tbl_role_cfg_app` VALUES ('3577', '2', '144');
INSERT INTO `tbl_role_cfg_app` VALUES ('3578', '2', '145');
INSERT INTO `tbl_role_cfg_app` VALUES ('3579', '2', '146');
INSERT INTO `tbl_role_cfg_app` VALUES ('3580', '2', '147');
INSERT INTO `tbl_role_cfg_app` VALUES ('3581', '2', '148');
INSERT INTO `tbl_role_cfg_app` VALUES ('3582', '2', '149');
INSERT INTO `tbl_role_cfg_app` VALUES ('3583', '2', '150');
INSERT INTO `tbl_role_cfg_app` VALUES ('3584', '2', '151');
INSERT INTO `tbl_role_cfg_app` VALUES ('3585', '2', '152');
INSERT INTO `tbl_role_cfg_app` VALUES ('3586', '2', '153');
INSERT INTO `tbl_role_cfg_app` VALUES ('3587', '2', '154');
INSERT INTO `tbl_role_cfg_app` VALUES ('3588', '2', '155');
INSERT INTO `tbl_role_cfg_app` VALUES ('3589', '2', '156');
INSERT INTO `tbl_role_cfg_app` VALUES ('3590', '2', '164');
INSERT INTO `tbl_role_cfg_app` VALUES ('3591', '2', '206');
INSERT INTO `tbl_role_cfg_app` VALUES ('4821', '1', '1');
INSERT INTO `tbl_role_cfg_app` VALUES ('4822', '1', '15');
INSERT INTO `tbl_role_cfg_app` VALUES ('4823', '1', '16');
INSERT INTO `tbl_role_cfg_app` VALUES ('4824', '1', '42');
INSERT INTO `tbl_role_cfg_app` VALUES ('4825', '1', '43');
INSERT INTO `tbl_role_cfg_app` VALUES ('4826', '1', '44');
INSERT INTO `tbl_role_cfg_app` VALUES ('4827', '1', '233');
INSERT INTO `tbl_role_cfg_app` VALUES ('4828', '1', '275');
INSERT INTO `tbl_role_cfg_app` VALUES ('4829', '1', '276');
INSERT INTO `tbl_role_cfg_app` VALUES ('4830', '1', '277');
INSERT INTO `tbl_role_cfg_app` VALUES ('4831', '1', '278');
INSERT INTO `tbl_role_cfg_app` VALUES ('4832', '1', '34');
INSERT INTO `tbl_role_cfg_app` VALUES ('4833', '1', '36');
INSERT INTO `tbl_role_cfg_app` VALUES ('4834', '1', '39');
INSERT INTO `tbl_role_cfg_app` VALUES ('4835', '1', '37');
INSERT INTO `tbl_role_cfg_app` VALUES ('4836', '1', '38');
INSERT INTO `tbl_role_cfg_app` VALUES ('4837', '1', '41');
INSERT INTO `tbl_role_cfg_app` VALUES ('4838', '1', '236');
INSERT INTO `tbl_role_cfg_app` VALUES ('4839', '1', '19');
INSERT INTO `tbl_role_cfg_app` VALUES ('4840', '1', '21');
INSERT INTO `tbl_role_cfg_app` VALUES ('4841', '1', '26');
INSERT INTO `tbl_role_cfg_app` VALUES ('4842', '1', '23');
INSERT INTO `tbl_role_cfg_app` VALUES ('4843', '1', '24');
INSERT INTO `tbl_role_cfg_app` VALUES ('4844', '1', '28');
INSERT INTO `tbl_role_cfg_app` VALUES ('4845', '1', '234');
INSERT INTO `tbl_role_cfg_app` VALUES ('4846', '1', '59');
INSERT INTO `tbl_role_cfg_app` VALUES ('4847', '1', '60');
INSERT INTO `tbl_role_cfg_app` VALUES ('4848', '1', '272');
INSERT INTO `tbl_role_cfg_app` VALUES ('4849', '1', '62');
INSERT INTO `tbl_role_cfg_app` VALUES ('4850', '1', '61');
INSERT INTO `tbl_role_cfg_app` VALUES ('4851', '1', '63');
INSERT INTO `tbl_role_cfg_app` VALUES ('4852', '1', '64');
INSERT INTO `tbl_role_cfg_app` VALUES ('4853', '1', '66');
INSERT INTO `tbl_role_cfg_app` VALUES ('4854', '1', '67');
INSERT INTO `tbl_role_cfg_app` VALUES ('4855', '1', '65');
INSERT INTO `tbl_role_cfg_app` VALUES ('4856', '1', '257');
INSERT INTO `tbl_role_cfg_app` VALUES ('4857', '1', '238');
INSERT INTO `tbl_role_cfg_app` VALUES ('4858', '1', '47');
INSERT INTO `tbl_role_cfg_app` VALUES ('4859', '1', '51');
INSERT INTO `tbl_role_cfg_app` VALUES ('4860', '1', '50');
INSERT INTO `tbl_role_cfg_app` VALUES ('4861', '1', '52');
INSERT INTO `tbl_role_cfg_app` VALUES ('4862', '1', '53');
INSERT INTO `tbl_role_cfg_app` VALUES ('4863', '1', '54');
INSERT INTO `tbl_role_cfg_app` VALUES ('4864', '1', '237');
INSERT INTO `tbl_role_cfg_app` VALUES ('4865', '1', '68');
INSERT INTO `tbl_role_cfg_app` VALUES ('4866', '1', '69');
INSERT INTO `tbl_role_cfg_app` VALUES ('4867', '1', '71');
INSERT INTO `tbl_role_cfg_app` VALUES ('4868', '1', '72');
INSERT INTO `tbl_role_cfg_app` VALUES ('4869', '1', '73');
INSERT INTO `tbl_role_cfg_app` VALUES ('4870', '1', '74');
INSERT INTO `tbl_role_cfg_app` VALUES ('4871', '1', '75');
INSERT INTO `tbl_role_cfg_app` VALUES ('4872', '1', '76');
INSERT INTO `tbl_role_cfg_app` VALUES ('4873', '1', '239');
INSERT INTO `tbl_role_cfg_app` VALUES ('4874', '1', '79');
INSERT INTO `tbl_role_cfg_app` VALUES ('4875', '1', '111');
INSERT INTO `tbl_role_cfg_app` VALUES ('4876', '1', '112');
INSERT INTO `tbl_role_cfg_app` VALUES ('4877', '1', '113');
INSERT INTO `tbl_role_cfg_app` VALUES ('4878', '1', '114');
INSERT INTO `tbl_role_cfg_app` VALUES ('4879', '1', '115');
INSERT INTO `tbl_role_cfg_app` VALUES ('4880', '1', '116');
INSERT INTO `tbl_role_cfg_app` VALUES ('4881', '1', '117');
INSERT INTO `tbl_role_cfg_app` VALUES ('4882', '1', '118');
INSERT INTO `tbl_role_cfg_app` VALUES ('4883', '1', '119');
INSERT INTO `tbl_role_cfg_app` VALUES ('4884', '1', '120');
INSERT INTO `tbl_role_cfg_app` VALUES ('4885', '1', '219');
INSERT INTO `tbl_role_cfg_app` VALUES ('4886', '1', '220');
INSERT INTO `tbl_role_cfg_app` VALUES ('4887', '1', '121');
INSERT INTO `tbl_role_cfg_app` VALUES ('4888', '1', '122');
INSERT INTO `tbl_role_cfg_app` VALUES ('4889', '1', '245');
INSERT INTO `tbl_role_cfg_app` VALUES ('4890', '1', '221');
INSERT INTO `tbl_role_cfg_app` VALUES ('4891', '1', '132');
INSERT INTO `tbl_role_cfg_app` VALUES ('4892', '1', '133');
INSERT INTO `tbl_role_cfg_app` VALUES ('4893', '1', '134');
INSERT INTO `tbl_role_cfg_app` VALUES ('4894', '1', '135');
INSERT INTO `tbl_role_cfg_app` VALUES ('4895', '1', '136');
INSERT INTO `tbl_role_cfg_app` VALUES ('4896', '1', '222');
INSERT INTO `tbl_role_cfg_app` VALUES ('4897', '1', '137');
INSERT INTO `tbl_role_cfg_app` VALUES ('4898', '1', '138');
INSERT INTO `tbl_role_cfg_app` VALUES ('4899', '1', '139');
INSERT INTO `tbl_role_cfg_app` VALUES ('4900', '1', '140');
INSERT INTO `tbl_role_cfg_app` VALUES ('4901', '1', '141');
INSERT INTO `tbl_role_cfg_app` VALUES ('4902', '1', '223');
INSERT INTO `tbl_role_cfg_app` VALUES ('4903', '1', '142');
INSERT INTO `tbl_role_cfg_app` VALUES ('4904', '1', '224');
INSERT INTO `tbl_role_cfg_app` VALUES ('4905', '1', '225');
INSERT INTO `tbl_role_cfg_app` VALUES ('4906', '1', '143');
INSERT INTO `tbl_role_cfg_app` VALUES ('4907', '1', '144');
INSERT INTO `tbl_role_cfg_app` VALUES ('4908', '1', '266');
INSERT INTO `tbl_role_cfg_app` VALUES ('4909', '1', '226');
INSERT INTO `tbl_role_cfg_app` VALUES ('4910', '1', '145');
INSERT INTO `tbl_role_cfg_app` VALUES ('4911', '1', '146');
INSERT INTO `tbl_role_cfg_app` VALUES ('4912', '1', '147');
INSERT INTO `tbl_role_cfg_app` VALUES ('4913', '1', '148');
INSERT INTO `tbl_role_cfg_app` VALUES ('4914', '1', '227');
INSERT INTO `tbl_role_cfg_app` VALUES ('4915', '1', '149');
INSERT INTO `tbl_role_cfg_app` VALUES ('4916', '1', '150');
INSERT INTO `tbl_role_cfg_app` VALUES ('4917', '1', '228');
INSERT INTO `tbl_role_cfg_app` VALUES ('4918', '1', '151');
INSERT INTO `tbl_role_cfg_app` VALUES ('4919', '1', '152');
INSERT INTO `tbl_role_cfg_app` VALUES ('4920', '1', '153');
INSERT INTO `tbl_role_cfg_app` VALUES ('4921', '1', '154');
INSERT INTO `tbl_role_cfg_app` VALUES ('4922', '1', '155');
INSERT INTO `tbl_role_cfg_app` VALUES ('4923', '1', '229');
INSERT INTO `tbl_role_cfg_app` VALUES ('4924', '1', '156');
INSERT INTO `tbl_role_cfg_app` VALUES ('4925', '1', '230');
INSERT INTO `tbl_role_cfg_app` VALUES ('4926', '1', '164');
INSERT INTO `tbl_role_cfg_app` VALUES ('4927', '1', '206');
INSERT INTO `tbl_role_cfg_app` VALUES ('4928', '1', '197');
INSERT INTO `tbl_role_cfg_app` VALUES ('4929', '1', '198');
INSERT INTO `tbl_role_cfg_app` VALUES ('4930', '1', '199');
INSERT INTO `tbl_role_cfg_app` VALUES ('4931', '1', '207');
INSERT INTO `tbl_role_cfg_app` VALUES ('4932', '1', '208');
INSERT INTO `tbl_role_cfg_app` VALUES ('4933', '1', '200');
INSERT INTO `tbl_role_cfg_app` VALUES ('4934', '1', '254');
INSERT INTO `tbl_role_cfg_app` VALUES ('4935', '1', '170');
INSERT INTO `tbl_role_cfg_app` VALUES ('4936', '1', '171');
INSERT INTO `tbl_role_cfg_app` VALUES ('4937', '1', '172');
INSERT INTO `tbl_role_cfg_app` VALUES ('4938', '1', '173');
INSERT INTO `tbl_role_cfg_app` VALUES ('4939', '1', '250');
INSERT INTO `tbl_role_cfg_app` VALUES ('4940', '1', '174');
INSERT INTO `tbl_role_cfg_app` VALUES ('4941', '1', '175');
INSERT INTO `tbl_role_cfg_app` VALUES ('4942', '1', '176');
INSERT INTO `tbl_role_cfg_app` VALUES ('4943', '1', '177');
INSERT INTO `tbl_role_cfg_app` VALUES ('4944', '1', '260');
INSERT INTO `tbl_role_cfg_app` VALUES ('4945', '1', '262');
INSERT INTO `tbl_role_cfg_app` VALUES ('4946', '1', '261');
INSERT INTO `tbl_role_cfg_app` VALUES ('4947', '1', '251');
INSERT INTO `tbl_role_cfg_app` VALUES ('4948', '1', '165');
INSERT INTO `tbl_role_cfg_app` VALUES ('4949', '1', '166');
INSERT INTO `tbl_role_cfg_app` VALUES ('4950', '1', '167');
INSERT INTO `tbl_role_cfg_app` VALUES ('4951', '1', '168');
INSERT INTO `tbl_role_cfg_app` VALUES ('4952', '1', '169');
INSERT INTO `tbl_role_cfg_app` VALUES ('4953', '1', '211');
INSERT INTO `tbl_role_cfg_app` VALUES ('4954', '1', '249');
INSERT INTO `tbl_role_cfg_app` VALUES ('4955', '1', '186');
INSERT INTO `tbl_role_cfg_app` VALUES ('4956', '1', '187');
INSERT INTO `tbl_role_cfg_app` VALUES ('4957', '1', '279');
INSERT INTO `tbl_role_cfg_app` VALUES ('4958', '1', '123');
INSERT INTO `tbl_role_cfg_app` VALUES ('4959', '1', '124');
INSERT INTO `tbl_role_cfg_app` VALUES ('4960', '1', '269');
INSERT INTO `tbl_role_cfg_app` VALUES ('4961', '1', '125');
INSERT INTO `tbl_role_cfg_app` VALUES ('4962', '1', '126');
INSERT INTO `tbl_role_cfg_app` VALUES ('4963', '1', '280');
INSERT INTO `tbl_role_cfg_app` VALUES ('4964', '1', '281');
INSERT INTO `tbl_role_cfg_app` VALUES ('4965', '1', '282');
INSERT INTO `tbl_role_cfg_app` VALUES ('4966', '1', '283');
INSERT INTO `tbl_role_cfg_app` VALUES ('4967', '1', '284');
INSERT INTO `tbl_role_cfg_app` VALUES ('4968', '1', '285');
INSERT INTO `tbl_role_cfg_app` VALUES ('4969', '1', '287');
INSERT INTO `tbl_role_cfg_app` VALUES ('4970', '1', '288');
INSERT INTO `tbl_role_cfg_app` VALUES ('4971', '1', '289');
INSERT INTO `tbl_role_cfg_app` VALUES ('4972', '1', '291');
INSERT INTO `tbl_role_cfg_app` VALUES ('4973', '1', '292');
INSERT INTO `tbl_role_cfg_app` VALUES ('4974', '1', '293');
INSERT INTO `tbl_role_cfg_app` VALUES ('4975', '1', '290');
INSERT INTO `tbl_role_cfg_app` VALUES ('4976', '1', '286');
INSERT INTO `tbl_role_cfg_app` VALUES ('4977', '1', '294');

-- ----------------------------
-- Table structure for tbl_role_cfg_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_cfg_data_scope`;
CREATE TABLE `tbl_role_cfg_data_scope` (
  `id` varchar(64) NOT NULL COMMENT '自增主键',
  `role_id` varchar(64) NOT NULL COMMENT '关联的角色唯一标识符',
  `order_group` varchar(32) NOT NULL COMMENT '工单数据可见范围',
  `serv_group` varchar(32) NOT NULL COMMENT '服务数据可见范围',
  `operate_log` varchar(32) NOT NULL COMMENT '操作日志数据可见范围',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色服务配置表';

-- ----------------------------
-- Records of tbl_role_cfg_data_scope
-- ----------------------------
INSERT INTO `tbl_role_cfg_data_scope` VALUES ('1', '1', 'all', 'all', 'all');
INSERT INTO `tbl_role_cfg_data_scope` VALUES ('2', '2', 'group', 'group', 'group');

-- ----------------------------
-- Table structure for tbl_role_cfg_others
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_cfg_others`;
CREATE TABLE `tbl_role_cfg_others` (
  `id` varchar(64) NOT NULL COMMENT '自增主键',
  `role_id` varchar(64) NOT NULL COMMENT '关联的角色唯一标识符',
  `order_auto_audit` tinyint(1) unsigned NOT NULL COMMENT '工单是否自动审批  1：是；0：否',
  `order_auto_execute` tinyint(1) unsigned NOT NULL COMMENT '工单是否自动执行  1：是；0：否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色其他配置表';

-- ----------------------------
-- Records of tbl_role_cfg_others
-- ----------------------------
INSERT INTO `tbl_role_cfg_others` VALUES ('1', '1', '0', '0');
INSERT INTO `tbl_role_cfg_others` VALUES ('2', '2', '0', '0');

-- ----------------------------
-- Table structure for tbl_scale
-- ----------------------------
DROP TABLE IF EXISTS `tbl_scale`;
CREATE TABLE `tbl_scale` (
  `id` varchar(64) NOT NULL COMMENT '自增主键',
  `type` varchar(32) NOT NULL COMMENT '类型',
  `name` varchar(16) NOT NULL COMMENT '规模名称',
  `cpu_cnt` int(11) unsigned NOT NULL COMMENT 'cpu数量',
  `mem_size` bigint(20) unsigned NOT NULL COMMENT '内存容量',
  `is_enabled` tinyint(1) unsigned NOT NULL COMMENT '是否可用   1：可用；0：不可用',
  `sequence` int(11) unsigned NOT NULL COMMENT '显示顺序',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_def_serv_code&name` (`type`,`name`) USING BTREE,
  UNIQUE KEY `uk_def_serv_code&cpu&mem` (`type`,`cpu_cnt`,`mem_size`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='服务规模定义表';


-- ----------------------------
-- Table structure for tbl_serv_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_serv_group`;
CREATE TABLE `tbl_serv_group` (
  `id` varchar(64) NOT NULL,
  `relate_id` varchar(64) DEFAULT NULL,
  `business_subsystem_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_serv_group
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_storage_remote_vendor
-- ----------------------------
DROP TABLE IF EXISTS `tbl_storage_remote_vendor`;
CREATE TABLE `tbl_storage_remote_vendor` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '品牌名称',
  `code` varchar(32) NOT NULL COMMENT '品牌代码',
  `version` varchar(32) NOT NULL COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAN品牌定义表';

-- ----------------------------
-- Records of tbl_storage_remote_vendor
-- ----------------------------
INSERT INTO `tbl_storage_remote_vendor` VALUES ('1', '华为', 'HUAWEI', 'OceanStorV3');
INSERT INTO `tbl_storage_remote_vendor` VALUES ('2', '华为', 'HUAWEI', 'FusionStorV10');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `auth_type` varchar(32) NOT NULL COMMENT '认证方式',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `telephone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `company` varchar(32) DEFAULT NULL COMMENT '所属单位',
  `emer_contact` varchar(16) DEFAULT NULL COMMENT '紧急联系人',
  `emer_tel` varchar(11) DEFAULT NULL COMMENT '紧急联系人电话',
  `is_enabled` tinyint(1) unsigned NOT NULL COMMENT '是否可用  1：可用；0：不可用',
  `role_id` varchar(64) NOT NULL COMMENT '关联的角色唯一标识符',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建者',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'admin', 'native', '670b14728ad9902aecba32e22fa4f6bd', '超级管理员', '13800000000', 'huchangkuan@bsgchina.com', '信息中心', '', '', '1', '1', '2018-01-01 00:00:00', 'admin', '2019-10-25 13:24:28', 'admin');
INSERT INTO `tbl_user` VALUES ('2', 'user', 'native', '670b14728ad9902aecba32e22fa4f6bd', '租户', '13900000000', 'user@email.com', '信息中心', '1', '18673652782', '0', '2', '2018-01-01 00:00:00', 'admin', '2019-10-25 13:24:34', 'admin');



DROP TABLE IF EXISTS `tbl_db_user`;
CREATE TABLE `tbl_db_user` (
  `order_id` varchar(64) NOT NULL COMMENT '主键',
  `username` varchar(32)  COMMENT '用户名',
  `userpwd` varchar(32)  COMMENT '用户密码',
  `dbname` varchar(32)  COMMENT '数据库实例名称',
  `type` varchar(32)  COMMENT '类型:user,schema',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='客户数据库表';



DROP TABLE IF EXISTS `tbl_cmha_proxy`;
CREATE TABLE `tbl_cmha_proxy` (
  `order_id` varchar(64) NOT NULL COMMENT '主键',
  `mode` varchar(32)  COMMENT '服务架构模式',
  `replicas` int  COMMENT '服务架构单元数量',
  `port` int  COMMENT '端口',
  `cpu` int  COMMENT 'cpu',
  `memory` int  COMMENT '内存',
  `netBandwidth` int  COMMENT '带宽',
  `performance` varchar(32)  COMMENT '存储性能等级',
  `data_capacity` bigint(20)  COMMENT '数据存储容量',
  `log_capacity` bigint(20)  COMMENT '日志存储容量',
  `type` varchar(32)  COMMENT '类型:cmha,proxy',
  `img_id` varchar(64) COMMENT '镜像id',
  `createTime` datetime  COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='cmha和proxy信息表';
