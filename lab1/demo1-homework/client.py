import requests

def postController(param):
    url = "http://localhost:8080/homework/controller-servlet"

    data = {
        'page': param
    }

    try:
        print("\nTesting with param=" + param)
        response = requests.post(url, data=data)
        print(f"Response Code: {response.status_code}")
        print(f"Response: {response.text}")
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    postController("1")
    postController("2")