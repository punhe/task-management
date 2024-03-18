CREATE TABLE `users` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`password` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`status` TINYINT(4) NOT NULL DEFAULT '1',
	`firstName` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`lastName` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`role` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;
