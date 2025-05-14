FROM openjdk:11-jdk

# Set the working directory
WORKDIR /app

# Copy the source code
COPY *.java /app/

# Compile the application
RUN javac *.java

# Run the GUI application (modify to run the console version if needed)
CMD ["java", "TodoListConsole"]
