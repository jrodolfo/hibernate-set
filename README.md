# Hibernate and Java Set 

Small project that shows how a NonUniqueObjectException can happen in Hibernate
projects, specially when we are keeping the entities on a Set data structure.
We have in this project class MessageA that does NOT implement equals(), and class
MessageB that implements equals(). Both are being used in Set, and being persisted
via Hibernate. One would think that a class implementing equals() would be enough to
avoid this NonUniqueObjectException, but what I found in this project is that this is not
enough. If you run MessageApp.main(), you will see that both MessageA and MessageB throws
NonUniqueObjectException. Therefore something else needs to be done to prevent the exception.

More info:

Implementing equals() and hashCode()
https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/persistent-classes-equalshashcode.html

Effective Java: Equals and HashCode
http://www.ideyatech.com/effective-java-equals-and-hashcode/


## Pre-requisites, configurations, and how to run it

Pre-requisites: JDK, Maven, MySQL server, MySQL client (e.g. Oracle SQL Developer, HeidiSQL, TOAD), and Git.

1) Clone the project:

git clone https://github.com/jrodolfo/hibernate-set

2) Run sql script db/create-db.sql

3) Update username and password in file hibernate.cfg.xml

4) Run MessageApp.main()
