Customer,Order->dataModels
CustomerRepository.java - simuleaza o baza de date cu un Map cu 3 clienti 
DiscountService.java - interfata ce defineste tipurile de discount avand metoda calculate() ce primeste un client si o comanda si calculeaza discountul
NoDiscount/FixedDiscount/PercentageDiscount.java sunt tipurile de discount ce implementeaza metoda calculate
OrderService.java - aici e serviciul principal care proceseaza comenzile, calculeeaza discountul, il aplica pe comanda si publica evenimentul pentru anumite discounturi (peste 50)
Avem si validatori folosind adnotari pentru a marca metodele ce necesita validarea inainte de executie
ValidationAspect.java = intercepteaza metodele adnotate cu @ValidateCustomer verifica daca clientul exista deja si e valid sau arunca exceptie daca esueaza
Ca avenimente avem DiscountEventListener.java - ascculta LargeDiscountEvent ce logheaza un event daca e aplicat un discount mai mare afisand numele clientului si suma discountului
In OrderApplication.java - e clasa main cu @SpringBootApplication ce include adaugarea unei comenzi de 1200 pentru un client (id 1) si o proceseaza si afiseaza suma finala
De altfel in application.properties e tipul de discount ce se va lua in considerare