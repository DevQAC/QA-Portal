alter table training.app_menu_item
	add display_on_menu boolean default true not null;

update training.app_menu_item set display_on_menu = false where id = 6;
