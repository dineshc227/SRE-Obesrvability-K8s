import requests
import time
import random

base_url = "http://localhost:8081/api"

# All endpoints
endpoints = [
    "/health",
    "/",
    "/load",
    "/error",
    "/message?name=SRE"
]

rps = 30  # requests per second
duration = 60  # seconds

request_count = 0
start_time = time.time()

while time.time() - start_time < duration:
    second_start = time.time()

    for _ in range(rps):
        endpoint = random.choice(endpoints)  # randomly hit endpoints
        url = base_url + endpoint

        try:
            response = requests.get(url, timeout=5)
            request_count += 1
            print(f"[{endpoint}] Request #{request_count} Status: {response.status_code}")
        except Exception as e:
            print(f"[{endpoint}] Error: {e}")

    elapsed = time.time() - second_start
    if elapsed < 1:
        time.sleep(1 - elapsed)

print(f"\nTotal Requests Sent: {request_count}")
