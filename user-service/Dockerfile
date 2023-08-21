FROM openjdk:17-oracle

ARG LAYERS_DIR=build/layers

COPY ${LAYERS_DIR}/BOOT-INF/lib /user/lib
COPY ${LAYERS_DIR}/BOOT-INF/classes /user/classes
COPY ${LAYERS_DIR}/META-INF /user/META-INF

WORKDIR /user

EXPOSE  8080

ENTRYPOINT ["java","-cp","./classes:./lib/*","com.endyary.springelkexample.UserServiceApplication"]