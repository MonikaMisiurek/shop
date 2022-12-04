insert into categories (id, name) values
          (gen_random_uuid(), 'Zdrowie'),
          (gen_random_uuid(), 'Zwierzęta'),
          (gen_random_uuid(), 'Uroda i styl'),
          (gen_random_uuid(), 'Edukacja'),
          (gen_random_uuid(), 'Gry'),
          (gen_random_uuid(), 'Hobby'),
          (gen_random_uuid(), 'Dom i ogród'),
          (gen_random_uuid(), 'Kulinaria'),
          (gen_random_uuid(), 'Komputery'),
          (gen_random_uuid(), 'Motoryzacja'),
          (gen_random_uuid(), 'Dziecko');

select *from categories;
select * from products;
insert into products (id, name,price, category_id)values
      (gen_random_uuid(),'Łopata',10, (select id from categories where name = 'Dom i ogród')),
      (gen_random_uuid(),'Grabie',10, (select id from categories where name = 'Dom i ogród'));
insert into products (id, name,price, category_id)values
     (gen_random_uuid(),'Smycz',10, (select id from categories where name = 'Zwierzęta')),
     (gen_random_uuid(),'Legowisko',10, (select id from categories where name = 'Zwierzęta'));
insert into products (id, name,price, category_id)values
      (gen_random_uuid(),'Drapak',10, (select id from categories where name = 'Zwierzęta')),
      (gen_random_uuid(),'Karma',10, (select id from categories where name = 'Zwierzęta')),
      (gen_random_uuid(),'Kaganiec',10, (select id from categories where name = 'Zwierzęta')),
      (gen_random_uuid(),'Domek',10, (select id from categories where name = 'Zwierzęta'));
insert into products (id, name,price, category_id)values
    (gen_random_uuid(),'Lalka',10, (select id from categories where name = 'Dziecko')),
    (gen_random_uuid(),'Wózek dla lalek',10, (select id from categories where name = 'Dziecko')),
    (gen_random_uuid(),'Miś pluszowy',10, (select id from categories where name = 'Dziecko')),
    (gen_random_uuid(),'Samochodzik',10, (select id from categories where name = 'Dziecko'));
insert into products (id, name,price, category_id)values
  (gen_random_uuid(),'Mata do ćwiczeń',10, (select id from categories where name = 'Zdrowie')),
  (gen_random_uuid(),'Roler',10, (select id from categories where name = 'Zdrowie'));


select * from users;
