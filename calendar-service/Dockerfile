FROM barber-shop-project as barber-shop

FROM openjdk:17-oracle as extractor
WORKDIR /calendar-service
COPY --from=barber-shop /root-app/calendar-service/build/libs/app-0.0.1-SNAPSHOT.jar /calendar-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /calendar-service/dependencies/. /app/.
COPY --from=extractor /calendar-service/spring-boot-loader/. /app/.
COPY --from=extractor /calendar-service/snapshot-dependencies/. /app/.
COPY --from=extractor /calendar-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]