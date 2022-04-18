create table `auth_db`.`users` (
  `user_id` int unsigned not null auto_increment,
  `email` varchar(255) unique,
  `hashed_password` varchar(255),
  `salt` varchar(255),
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  primary key (`user_id`)
) default charset=utf8;
