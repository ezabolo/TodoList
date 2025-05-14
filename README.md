# Java To-Do List Application

A simple Java To-Do List application with both console and GUI versions.

## Features

- Add tasks with descriptions
- View all tasks, active tasks, or completed tasks
- Mark tasks as completed or not completed
- Remove tasks
- Console interface (TodoListConsole.java)
- GUI interface using Java Swing (TodoListGUI.java)

## Project Structure

- `Task.java` - Represents a single task with ID, description, and completion status
- `TaskManager.java` - Manages a collection of tasks and provides methods for manipulating them
- `TodoListConsole.java` - Console-based interface for the application
- `TodoListGUI.java` - Swing-based graphical interface for the application
- `Dockerfile` - Instructions for building the application container
- `k8s/` - Kubernetes deployment manifests

## Docker Deployment

To build and run the application in Docker:

```bash
# Build the Docker image
docker build -t todolist-app .

# Run the container
docker run -p 8080:8080 todolist-app
```

## Kubernetes Deployment

To deploy the application to Kubernetes (minikube):

```bash
# Start minikube if not already running
minikube start

# Apply the deployment configuration
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# Access the application
minikube service todolist-service
```
