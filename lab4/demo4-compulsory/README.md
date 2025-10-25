In SQL.txt sunt query-urile rulate in pgAdmin inaintea rularii aplicatiei
E o aplicatie Spring Boot integrata cu PostgreSQL folosing Spring Data JPA.
Avem clase ce au relatii cu tabelele (Studenti, Instructori, Pachete de cursuri, Cursuri)
Folosesc adnotari JPA precum @Entity, @Table, @Column pentru a defini constrangeri
StudentRepository.java extinde  JpaRepository<Student, Long> si permite utilizarea metodelor CRUD automate. Pe langa acestea avem si metode precum findByCode, findByYear.
JPA genereaza automat implementarile acestor metode bazandu-se pe conventia de denumire.

RunApplication.java e entry-point-ul aplicatiei. Contine un bean CommandLineRunner ce executa "testarea" la pornirea aplicatiei si :
* afiseaza toti studentii
* cauta un anumit student
* gaseste studentii din anul 1
* salveaza un nou student
* numara toti studentii
* cauta studentii cu "John" in nume si altele

La pornire aplicatia se conecteaza la PostgreSQL folosing confiiguratiile din application.properties