use carClub;
insert into genre (name, description) values ('sedan', 'Klasyczny sedan z eleganckim designem i komfortowym wnętrzem, idealny do codziennych podróży z stylu.'),
('SUV', 'Uniwersalny SUV, który łączy doskonałe osiągi z przestronnym wnętrzem, doskonały wybór dla aktywnych rodzin.'),
 ('hatchback', 'Praktyczny hatchback, oferujący zwinne prowadzenie i funkcjonalność, doskonały do miejskich przygód.'),
('convertible', 'Elegancki kabriolet, który dostarcza niezapomnianych wrażeń z jazdy, idealny na słoneczne dni i romantyczne podróże.'),
 ('pickup truck', 'Wszechstronny pickup, gotowy do pracy i przygód, oferujący funkcjonalność i solidność.'),
 ('minivan', 'Przestronny minivan z wygodnymi opcjami siedzeń, doskonały dla rodzin i podróży z większą ilością osób.'),
('coupe', 'Sportowe coupe z dynamicznym designem i doskonałymi osiągami, stworzone dla miłośników szybkiej jazdy.'),
 ('sports car', 'Sportowy samochód, który zapewnia intensywne doznania z jazdy, idealny dla pasjonatów szybkiej i ekscytującej motoryzacji.'),
 ('compact car', 'Kompaktowy samochód, doskonały do miejskich eskapad, oferujący ekonomiczną jazdę i łatwe parkowanie.'),
 ('luxury car', 'Luksusowy samochód, który łączy w sobie elegancję, wygodę i najnowsze technologie, adresowany do wymagających koneserów motoryzacji.');
insert into car (brand, model, short_description, description, car_year, youtube_trailer_id, genre_id, promoted,poster)
values
 ('Audi', '100', 'Klasyczny sedan z niemieckim stylem.', 'Audi 100 z 1989 roku to klasyczny sedan z niemieckim stylem. Jego elegancki wygląd i komfortowe wnętrze sprawiają, że jest to wybór dla osób ceniących klasę i doskonałą jakość.', 1989, '0Vqwnc9twAg&t=5s', 1, true,'audi.png');