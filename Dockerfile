FROM openjdk:11
COPY java/com.example.crud_spring/ /temp
WORKDIR /temp
CMD java com.jetbrains.Main