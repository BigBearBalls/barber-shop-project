FROM openjdk:17-oracle as extractor
WORKDIR /user-service
COPY build/libs/app-0.0.1-SNAPSHOT.jar /user-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /user-service/dependencies/. /app/.
COPY --from=extractor /user-service/spring-boot-loader/. /app/.
COPY --from=extractor /user-service/snapshot-dependencies/. /app/.
COPY --from=extractor /user-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]