--DROP DATABASE IF EXISTS apidb;
--CREATE DATABASE apidb DEFAULT CHARACTER SET utf8;

CREATE TABLE auth_user (
  id char(32) NOT NULL,
  name varchar(64) DEFAULT NULL,
  mobile_number varchar(32) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  password varchar(128) NOT NULL,
  last_login datetime(6) DEFAULT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_auth_user_name (name),
  UNIQUE KEY UK_auth_user_email (email),
  UNIQUE KEY UK_auth_user_mobile_number (mobile_number)
);

CREATE TABLE auth_role (
  id char(32) NOT NULL,
  name varchar(128) NOT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_auth_role_name (name)
);

CREATE TABLE auth_permission (
  id char(32) NOT NULL,
  code varchar(64) NOT NULL,
  name varchar(255) NOT NULL,
  module_code varchar(32) DEFAULT NULL,
  model_code varchar(32) DEFAULT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_auth_permission_code (code),
  UNIQUE KEY UK_auth_permission_name (name)
);

CREATE TABLE auth_user_role (
  id char(32) NOT NULL,
  user_id char(32) NOT NULL,
  role_id char(32) NOT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_auth_user_role_user_id_role_id (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES auth_user (id),
  FOREIGN KEY (role_id) REFERENCES auth_role (id)
);

CREATE TABLE auth_role_permission (
  id char(32) NOT NULL,
  role_id char(32) NOT NULL,
  permission_id char(32) NOT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_auth_role_permission_role_id_permission_id (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES auth_role (id),
  FOREIGN KEY (permission_id) REFERENCES auth_permission (id)
);
