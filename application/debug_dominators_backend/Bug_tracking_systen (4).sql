CREATE TABLE `Role` (
  `role_id` integer PRIMARY KEY AUTO_INCREMENT,
  `role_name` varchar(255)
);

CREATE TABLE `User` (
  `user_id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `type_of_user_id` integer NOT NULL,
  `password` varchar(255),
  `registered` boolean DEFAULT false,
  `last_login` varchar(255),
  `imported_on` date,
  `registered_on` date
);

CREATE TABLE `Project` (
  `project_id` integer PRIMARY KEY AUTO_INCREMENT,
  `project_name` varchar(255),
  `description` varchar(255),
  `start_date` date,
  `end_date` date,
  `status` int,
  `project_manager_id` integer NOT NULL
);

CREATE TABLE `ProjectMember` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `project_id` integer NOT NULL,
  `user_id` integer NOT NULL,
  `type_of_user_id` integer NOT NULL
);

CREATE TABLE `Bug` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `project_id` integer NOT NULL,
  `created_by_id` integer NOT NULL,
  `open_date` date,
  `assigned_to_id` integer NOT NULL,
  `marked_for_closing` boolean DEFAULT false,
  `closed_by_id` integer NOT NULL,
  `closed_on` date,
  `status` int,
  `severity_level_id` int NOT NULL
);

CREATE TABLE `SeverityLevel` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `auth` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` integer,
  `session_login` date DEFAULT (now())
);

ALTER TABLE `User` ADD FOREIGN KEY (`type_of_user_id`) REFERENCES `Role` (`role_id`);

ALTER TABLE `Project` ADD FOREIGN KEY (`project_manager_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `ProjectMember` ADD FOREIGN KEY (`project_id`) REFERENCES `Project` (`project_id`);

ALTER TABLE `ProjectMember` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `ProjectMember` ADD FOREIGN KEY (`type_of_user_id`) REFERENCES `Role` (`role_id`);

ALTER TABLE `Bug` ADD FOREIGN KEY (`project_id`) REFERENCES `Project` (`project_id`);

ALTER TABLE `Bug` ADD FOREIGN KEY (`created_by_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `Bug` ADD FOREIGN KEY (`assigned_to_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `Bug` ADD FOREIGN KEY (`closed_by_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `Bug` ADD FOREIGN KEY (`severity_level_id`) REFERENCES `SeverityLevel` (`id`);

ALTER TABLE `auth` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);
