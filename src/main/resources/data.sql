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
     (gen_random_uuid(),'Smycz',10, (select id from categories where name = 'Zwierzęta')),
     (gen_random_uuid(),'Legowisko',20, (select id from categories where name = 'Zwierzęta'));
insert into products (id, name, category_id)values
    (gen_random_uuid(),'Lalka', (select id from categories where name = 'Dziecko')),
    (gen_random_uuid(),'Samochodzik', (select id from categories where name = 'Dziecko'));
insert into products (id, name,price, category_id)values
  (gen_random_uuid(),'Mata do ćwiczeń',10, (select id from categories where name = 'Zdrowie')),
  (gen_random_uuid(),'Roler',20, (select id from categories where name = 'Zdrowie'));
