use todolist;
CREATE TABLE `view` (
	`viewed` INT(10) UNSIGNED NOT NULL ,
	PRIMARY KEY (`viewed`) USING BTREE
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;
