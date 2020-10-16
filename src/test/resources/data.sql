insert into person(`id`, `name`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`, `job`) values (1, 'hyungil', 1994, 10, 10, 'programmer');
insert into person(`id`, `name`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values (2, 'yunggon', 1994, 4, 1);
insert into person(`id`, `name`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values (3, 'gihyug', 1995, 3, 2);
insert into person(`id`, `name`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values (4, 'hosuck', 1995, 2, 3);
insert into person(`id`, `name`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values (5, 'jinmin', 1995, 10, 4);

insert into block(`id`, `name`) values (1, 'gihyug');
insert into block(`id`, `name`) values (2, 'hosuck');

update person set block_id = 1 where id = 3;
update person set block_id = 2 where id = 4;
