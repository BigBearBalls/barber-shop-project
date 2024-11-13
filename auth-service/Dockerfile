FROM barber-shop-project as barber-shop

FROM openjdk:17-oracle as extractor
WORKDIR /auth-service
COPY --from=barber-shop /root-app/auth-service/build/libs/app-0.0.1-SNAPSHOT.jar /auth-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /auth-service/dependencies/. /app/.
COPY --from=extractor /auth-service/spring-boot-loader/. /app/.
COPY --from=extractor /auth-service/snapshot-dependencies/. /app/.
COPY --from=extractor /auth-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]