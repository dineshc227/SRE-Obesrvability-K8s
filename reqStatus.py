import requests
import time
import random

base_url = "http://localhost:8081"

# All endpoints
endpoints = [
    "/",        # 200 OK
    "/load",    # 200 OK (with delay)
    "/bad",     # 400 error
    "/error"    # 500 error
]

rps = 30
duration = 60

request_count = 0
start_time = time.time()

# Track status codes
status_count = {
    200: 0,
    400: 0,
    500: 0,
    "other": 0
}

while time.time() - start_time < duration:
    second_start = time.time()

    for _ in range(rps):
        endpoint = random.choice(endpoints)  # randomly pick endpoint
        url = base_url + endpoint

        try:
            response = requests.get(url, timeout=5)
            request_count += 1

            status = response.status_code

            # Count statuses
            if status in status_count:
                status_count[status] += 1
            else:
                status_count["other"] += 1

            print(f"{endpoint} -> Status: {status}")

        except Exception as e:
            print(f"{endpoint} -> Error: {e}")

    elapsed = time.time() - second_start
    if elapsed < 1:
        time.sleep(1 - elapsed)

print("\n===== TEST SUMMARY =====")
print(f"Total Requests Sent: {request_count}")
print(f"200 OK: {status_count[200]}")
print(f"400 Errors: {status_count[400]}")
print(f"500 Errors: {status_count[500]}")
print(f"Other: {status_count['other']}")
