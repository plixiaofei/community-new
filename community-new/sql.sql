CREATE TABLE community_new.`user` (
      id BIGINT UNSIGNED auto_increment NOT NULL COMMENT '自增主键',
      username varchar(100) NOT NULL COMMENT '用户名',
      password varchar(100) NOT NULL COMMENT '用户密码',
      email varchar(100) NULL COMMENT '用户邮箱',
      icon varchar(500) NULL COMMENT '用户头像',
      nick_name varchar(100) NULL COMMENT '用户昵称',
      is_deleted TINYINT DEFAULT 0 NOT NULL COMMENT '逻辑删除',
      is_locked TINYINT DEFAULT 0 NOT NULL COMMENT '是否被锁',
      create_time DATETIME NOT NULL COMMENT '创建时间',
      login_time DATETIME NOT NULL COMMENT '上次登录时间',
      PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='用户表';
