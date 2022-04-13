create table `auth_db`.`sessions` (
  `session_id` int unsigned not null auto_increment,
  `user_id` int unsigned not null,
  `expire_at` timestamp not null,
  `created_at` timestamp not null default current_timestamp,
  primary key (`session_id`),
  foreign key fk_sessions_user_id (`user_id`) references `auth_db`.`users` (`user_id`)
) default charset=utf8;
