CREATE TABLE user_profile (
  id char(32) NOT NULL,
  first_name varchar(32) NOT NULL,
  middle_name varchar(32) DEFAULT NULL,
  last_name varchar(64) DEFAULT NULL,
  birthdate date DEFAULT NULL,
  salutation_code varchar(32) DEFAULT NULL,
  nationality_code varchar(32) DEFAULT NULL,
  gender_code varchar(32) DEFAULT NULL,
  marital_status_code varchar(32) DEFAULT NULL,
  avatar longblob,
  description varchar(250) DEFAULT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_user_profile_id FOREIGN KEY (id) REFERENCES auth_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_identity (
  id char(32) NOT NULL,
  user_id char(32) NOT NULL,
  unique_code varchar(32) NOT NULL,
  unique_name varchar(32) NOT NULL,
  unique_number varchar(64) NOT NULL,
  expiry_date date DEFAULT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_user_identity_user_id (user_id),
  CONSTRAINT FK_user_identity_user_id FOREIGN KEY (user_id) REFERENCES auth_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_address (
  id char(32) NOT NULL,
  user_id char(32) NOT NULL,
  address_type_code varchar(32) NOT NULL,
  country_code varchar(32) NOT NULL,
  state_code varchar(32) DEFAULT NULL,
  city_code varchar(32) DEFAULT NULL,
  county_code varchar(32) DEFAULT NULL,
  town_code varchar(32) DEFAULT NULL,
  village_code varchar(32) DEFAULT NULL,
  address1 varchar(128) DEFAULT NULL,
  address2 varchar(128) DEFAULT NULL,
  postal_code varchar(20) DEFAULT NULL,
  post_box varchar(20) DEFAULT NULL,
  is_mailing_address tinyint(1) DEFAULT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_user_address_user_id (user_id),
  CONSTRAINT FK_user_address_user_id FOREIGN KEY (user_id) REFERENCES auth_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_contact (
  id char(32) NOT NULL,
  user_id char(32) NOT NULL,
  type_code varchar(32) DEFAULT NULL,
  classification_code varchar(32) DEFAULT NULL,
  name varchar(32) NOT NULL,
  detail varchar(64) DEFAULT NULL,
  is_primary tinyint(1) DEFAULT NULL,
  created_by varchar(64) NOT NULL,
  created_at datetime(6) NOT NULL,
  updated_by varchar(64) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  is_active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_user_contact_user_id (user_id),
  CONSTRAINT FK_user_contact_user_id FOREIGN KEY (user_id) REFERENCES auth_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
