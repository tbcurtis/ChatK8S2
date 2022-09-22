# websocket-sender-service

## Building

```
$ mvn clean install
$ kubectl apply -f sender-deploy.yml
$ kubectl apply -f sender-service.yml
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
