# websocket-receiver

## Building

```
$ mvn clean install
$ kubectl apply -f receiver-deploy.yml
$ kubectl apply -f receiver-service.yml
```

## Running locally

$ minikube service websocket-receiver-service

### Misc 

Open web: http://localhost:8091/

Send message to:
```
curl --location --request POST 'http://localhost:8090/submit' \
--header 'Content-Type: text/plain' \
--data-raw 'ok'
```
