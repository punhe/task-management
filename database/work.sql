CREATE TABLE `work` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
	`description` LONGTEXT NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`status` INT(10) UNSIGNED NOT NULL,
	`created_by` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
	`updated_by` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
	`created_date` VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
	`updated_date` VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
	`priority` INT(10) UNSIGNED NOT NULL,
	`due` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=48
;
