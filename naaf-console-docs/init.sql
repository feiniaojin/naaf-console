--初始化SQL
CREATE DATABASE naaf_console DEFAULT CHARACTER SET = 'utf8mb4';
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `uid` varchar(64) NULL COMMENT '账号,业务使用',
  `mobile_phone` varchar(64) NULL COMMENT '手机号',
  `email` varchar(64) NULL COMMENT '邮箱',
  `user_name` varchar(64) NULL COMMENT '用户名',
  `password` varchar(64) NULL COMMENT '密码',
  `salt` varchar(64) NULL COMMENT '加密使用的盐',
  `status` int NULL DEFAULT 0 COMMENT '账号状态[0-正常;1-已冻结]',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_uid`(`uid`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户账号表';
CREATE TABLE `u_user_info_event` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `uid` varchar(64) NULL COMMENT '账号,业务使用',
  `event_id` varchar(64) NULL COMMENT '事件id',
  `event_type` varchar(64) NULL COMMENT '事件类型',
  `mobile_phone` varchar(64) NULL COMMENT '手机号',
  `email` varchar(64) NULL COMMENT '邮箱',
  `user_name` varchar(64) NULL COMMENT '用户名',
  `profile_img_url` varchar(512) NULL COMMENT '头像',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_eventId_uid`(`event_id`, `uid`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息表事件';
CREATE TABLE `u_user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `uid` varchar(64) NULL COMMENT '账号,业务使用',
  `mobile_phone` varchar(64) NULL COMMENT '手机号',
  `email` varchar(64) NULL COMMENT '邮箱',
  `user_name` varchar(64) NULL COMMENT '用户名',
  `profile_img_url` varchar(512) NULL COMMENT '头像',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `evs_start_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '拉链表记录生效时间',
  `evs_end_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '拉链表记录失效时间',
  `evs_mark` varchar(64) NULL COMMENT '拉链表记录生成操作标识,用于记录该记录生成的原因,insert/update/delete',
  `evs_current` int NULL DEFAULT 1 COMMENT '拉链表当前记录,默认为1,非当前则为0',
  `evs_event_id` varchar(64) NULL COMMENT '拉链表记录对应的事件id',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_uid`(`uid`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息表';
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `resource_id` varchar(64) NOT NULL COMMENT '资源id',
  `parent_resource_id` varchar(64) NOT NULL DEFAULT 0 COMMENT '父资源id',
  `path` varchar(64) NULL COMMENT '资源路径',
  `mapping_method` varchar(64) NULL COMMENT 'java类中RequestMapping对应的方法',
  `http_method` varchar(64) NULL COMMENT 'http请求的方法',
  `type` int NOT NULL DEFAULT 0 COMMENT '资源类型,0请求路径,1菜单',
  `visible` int NOT NULL DEFAULT 1 COMMENT '是否展示,0不展示,1展示',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_resource_id`(`resource_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '受限资源表';
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `role_name` varchar(64) NULL COMMENT '角色名称',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_role_id`(`role_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色表';
CREATE TABLE `sys_role_rel_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `resource_id` varchar(64) NULL COMMENT '资源id',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_role_resource`(`role_id`, `resource_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色与受限资源关联表';
CREATE TABLE `sys_user_rel_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键,业务不用',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `uid` varchar(64) NULL COMMENT '用户uid',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常;1-已删除]',
  `created_by` VARCHAR(100) COMMENT '创建人',
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_by` VARCHAR(100) COMMENT '更新人',
  `modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  index `idx_uid_role`(`uid`, `role_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户与角色关联表';