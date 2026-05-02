1. Install maven: https://maven.apache.org/install.html
2. For each project:
   2.1. Execute `mvn clean package`
   2.2. You should read <span style="color:#00E000">**BUILD SUCCESS**</span>
4. Create a new dockerfile
5. Check your java version in any `pom.xml` where it says `<java.version>17</java.version>`
6. Write in your dockerfile:
   FROM amazoncorretto:{version}-alpine-jdk
   COPY target/{file you have created in step 2} /{name of your app}.jar
   ENTRYPOINT ["java", "-jar", "/{name of your app}.jar"]
7. Remove folder target in each gitignore (or comment)
8. Upload your project to GitHub