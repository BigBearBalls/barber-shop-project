FROM openjdk:17-oracle as extractor
WORKDIR /account-service
COPY /build/libs/app-0.0.1-SNAPSHOT.jar /account-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /account-service/dependencies/. /app/.
COPY --from=extractor /account-service/spring-boot-loader/. /app/.
COPY --from=extractor /account-service/snapshot-dependencies/. /app/.
COPY --from=extractor /account-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]