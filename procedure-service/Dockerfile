FROM barber-shop-project as barber-shop

FROM openjdk:17-oracle as extractor
WORKDIR /procedure-service
COPY --from=barber-shop /root-app/procedure-service/build/libs/app-0.0.1-SNAPSHOT.jar /procedure-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /procedure-service/dependencies/. /app/.
COPY --from=extractor /procedure-service/spring-boot-loader/. /app/.
COPY --from=extractor /procedure-service/snapshot-dependencies/. /app/.
COPY --from=extractor /procedure-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]