FROM openjdk:8
MAINTAINER com.generic.retailStoreDiscounts
COPY target/RetailStoreDiscounts.jar retailStoreDiscounts.jar
ENTRYPOINT ["java","-jar","/retailStoreDiscounts.jar"]
EXPOSE 8080
