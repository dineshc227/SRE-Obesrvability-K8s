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

Test locally:

http://localhost:8080/
http://localhost:8080/load
http://localhost:8080/actuator/prometheus

Then continue DevOps flow

docker build -t springboot-monitoring .

Prometheus Setup:

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

helm install prometheus prometheus-community/prometheus

Grafana Dashboard

helm install grafana grafana/grafana

Datadog Setup

helm repo add datadog https://helm.datadoghq.com
helm install datadog datadog/datadog \
  --set datadog.apiKey=YOUR_API_KEY


kubectl apply -f ../k8s/


Use /load endpoint with:

while true; do curl http://<node-ip>:<port>/load; done
