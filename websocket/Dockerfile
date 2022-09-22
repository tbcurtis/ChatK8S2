FROM adoptopenjdk:11

WORKDIR /app

COPY    target/*dependencies.jar app.jar

#ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-cp", "app.jar", "com.statefarm.k8s.websocket.Chat" ]

EXPOSE 7070