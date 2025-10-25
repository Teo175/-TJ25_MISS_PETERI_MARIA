Aplicatia are 2 configurarir de rulare, fiecare cu baza ei de date(in postgres: app.db):
->dev
->prod

DatabaseProperties.java citeste valorile din fisierele YAML (prod/dev) in functie de profilul activ folosind @ConfigurationProperties si nu @Value pentru ca grupeaza toate proprietatile legate de db intr o singura clasa + e mai usor la refactoring fara sa redenumesti manual peste tot unde sunt utilizate.
application.yml contine configuratia comuna(actuator/port), iar in application-dev/application-prod specifica configurarile fiecaruia (H2-db temporar/POSTGRES)
DataSourceConfig.java - aici in functie de profil creeaza bean-uri 
StartupRunner.java implementeaza CommandLineRunner. La pornirea app printeaza profilul activ si creaza tabela customers daca nu exista si insereaza date si afiseaza clientii
DatabaseContributor.java - afiseaza info despre URL, vendor, nr. clienti
Lab2Application.java - pornirea app
