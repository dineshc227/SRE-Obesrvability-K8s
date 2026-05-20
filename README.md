# SRE Observability Project

## Stack
- Spring Boot
- Kubernetes
- Prometheus + Grafana
- Datadog

## Steps
1. Build Spring Boot app
2. Dockerize
3. Deploy to Kubernetes
4. Setup Prometheus & Grafana
5. Setup Datadog
6. Configure SLOs & Alerts

Now it’s fully runnable

Step 1: Build app

cd app

mvn clean package

Step 2: Run locally (test)

java -jar target/observability-app-0.0.1-SNAPSHOT.jar

Test:

http://localhost:8080/
http://localhost:8080/load
http://localhost:8080/actuator/prometheus

Then continue DevOps flow

docker build -t springboot-monitoring .

kubectl apply -f ../k8s/



Use /load endpoint with:

while true; do curl http://<node-ip>:<port>/load; done
