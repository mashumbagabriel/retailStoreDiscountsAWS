FROM openjdk:8
MAINTAINER com.generic.retailStoreDiscounts
COPY target/retailStoreDiscounts-0.0.1-SNAPSHOT.jar retailStoreDiscount.jar
ENTRYPOINT ["java","-jar","/retailStoreDiscount.jar"]
